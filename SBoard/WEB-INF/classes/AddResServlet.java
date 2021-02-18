import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.*;

public class AddResServlet extends HttpServlet {
	static String thisThID;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		//ArrayList���g�p
		ArrayList<ResBean> users = new ArrayList<ResBean>();
		//DBAccess���C���X�^���X��
		ResDBAccess orcl=new ResDBAccess();
		//URL�̃p�����[�^�����ID���擾����
		String thID=req.getParameter("thID");
		//�{�����Ă���X���b�h��ID���i�[����
		thisThID = thID;
		//Oracle�ɃA�N�Z�X���A�擾�����p�����[�^��DB�ɓo�^����
		orcl.selectResExecute(thisThID);

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
		
		//URL�̃p�����[�^�����ID���擾����
		String thID=req.getParameter("thID");
		//POST�v���ɂ���đ��M���ꂽ�p�����[�^���擾����
		String n=req.getParameter("resName");
		String t=req.getParameter("resText");
		
		//ArrayList���g�p
		ArrayList<ResBean> users = new ArrayList<ResBean>();
		//DBAccess���C���X�^���X��
		ResDBAccess orcl=new ResDBAccess();

		//Oracle�ɃA�N�Z�X���A�擾�����p�����[�^��DB�ɓo�^����
		orcl.setSQLAttr(n, t);
		orcl.insertResExecute(AddThServlet.thisBdID, thisThID);

		users = orcl.getUsers();

		//HttpServletRequest�̎����N���X�̃C���X�^���X��
		//users�Ƃ������O�Ńf�[�^��o�^����
		req.setAttribute("users",users);
		
		res.sendRedirect("addres?thID="+thisThID);
		//doGet(req, res);
		//RequestDispatcher�C���^�[�t�F�C�X����������N���X��
		//�C���X�^���X���擾����
		//�����͓]�����URL
		//RequestDispatcher dispatcher=
		//	req.getRequestDispatcher("ContentsInput");
		
		//���X�|���X�ɕʂ̃T�[�u���b�g�̏o�͂��܂߂�
		//dispatcher.forward(req,res);
	}
}
