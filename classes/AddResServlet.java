import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.UserBean;

public class AddResServlet extends HttpServlet {
    
    //ArrayList���g�p
	private ArrayList<UserBean> users = new ArrayList<UserBean>();
	//UserBean���C���X�^���X��
	private DBAccess orcl=new DBAccess();

	public void orclExecute() {
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		//Oracle�ɃA�N�Z�X���A�擾�����p�����[�^��DB�ɓo�^����
		orcl.oracleConnect();
		orcl.oracleSelect();
		orcl.oracleDisConnect();

		users = orcl.getUsers();

		//HttpServletRequest�̎����N���X�̃C���X�^���X��
		//users�Ƃ������O�Ńf�[�^��o�^����
		req.setAttribute("users",users);

		String view = "/ContentsInput";
    	RequestDispatcher dispatcher = req.getRequestDispatcher(view);

    	dispatcher.forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//POST�v���ɂ���đ��M���ꂽ��������N���C�A���g��
		//�G���R�[�h�����Ƃ��̕����R�[�h���w�肷��
		//������w�肵�Ȃ��ƕ�����������\��������
		req.setCharacterEncoding("Windows-31J");
		
		//POST�v���ɂ���đ��M���ꂽ�p�����[�^���擾����
		String n=req.getParameter("resName");
		String t=req.getParameter("resText");
		
		//Oracle�ɃA�N�Z�X���A�擾�����p�����[�^��DB�ɓo�^����
		orcl.setSQLAttr(n, t);
		orcl.oracleExecute();

		users = orcl.getUsers();

		//HttpServletRequest�̎����N���X�̃C���X�^���X��
		//users�Ƃ������O�Ńf�[�^��o�^����
		req.setAttribute("users",users);
		
		res.sendRedirect("addres");
		//RequestDispatcher�C���^�[�t�F�C�X����������N���X��
		//�C���X�^���X���擾����
		//�����͓]�����URL
		//RequestDispatcher dispatcher=
		//	req.getRequestDispatcher("ContentsInput");
		
		//���X�|���X�ɕʂ̃T�[�u���b�g�̏o�͂��܂߂�
		//dispatcher.forward(req,res);
	}
}
