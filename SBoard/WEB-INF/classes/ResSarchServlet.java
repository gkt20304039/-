import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.ResSarchBean;

public class ResSarchServlet extends HttpServlet {
	static String thisBdID;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//POST�v���ɂ���đ��M���ꂽ��������N���C�A���g��
		//�G���R�[�h�����Ƃ��̕����R�[�h���w�肷��
		//������w�肵�Ȃ��ƕ�����������\��������
		req.setCharacterEncoding("Windows-31J");
		
		//POST�v���ɂ���đ��M���ꂽ�p�����[�^���擾����
		String tn=req.getParameter("rsarch");
		
		//ArrayList���g�p
		ArrayList<ResSarchBean> users = new ArrayList<ResSarchBean>();
		//DBAccess���C���X�^���X��
		ResSarchDBAccess orcl=new ResSarchDBAccess();

		//Oracle�ɃA�N�Z�X���A�擾�����p�����[�^��DB�ɓo�^����
		orcl.setSQLAttr(tn);
		orcl.selectResExecute();

		users = orcl.getUsers();

		//HttpServletRequest�̎����N���X�̃C���X�^���X��
		//threads�Ƃ������O�Ńf�[�^��o�^����
		req.setAttribute("users",users);
		//res.sendRedirect("sarch?bdID="+thisBdID);
		//doGet(req, res);
		//res.sendRedirect("addres");

		//RequestDispatcher�C���^�[�t�F�C�X����������N���X��
		//�C���X�^���X���擾����
		//�����͓]�����URL
		RequestDispatcher dispatcher=
		req.getRequestDispatcher("ContentsList");
		
		//���X�|���X�ɕʂ̃T�[�u���b�g�̏o�͂��܂߂�
		dispatcher.forward(req,res);
	}
}
