import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.ThBean;

public class AddThServlet extends HttpServlet {
	static String thisBdID;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		//ArrayList���g�p
		ArrayList<ThBean> threads = new ArrayList<ThBean>();
		//DBAccess���C���X�^���X��
		ThDBAccess orcl=new ThDBAccess();
		//URL�̃p�����[�^�����ID���擾����
		String bdID=req.getParameter("bdID");
		//�{�����Ă����ID���i�[����
		thisBdID = bdID;
		//Oracle�ɃA�N�Z�X���A���݂܂ł̃f�[�^���擾����
		orcl.selectThExecute(thisBdID);

		threads = orcl.getUsers();

		//HttpServletRequest�̎����N���X�̃C���X�^���X��
		//threads�Ƃ������O�Ńf�[�^��o�^����
		req.setAttribute("threads",threads);
		req.setAttribute("ranking",orcl.getRanking());

		String view = "/threadView";
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
		String tn=req.getParameter("thName");
		String n=req.getParameter("resName");
		String t=req.getParameter("resText");
		
		//ArrayList���g�p
		ArrayList<ThBean> threads = new ArrayList<ThBean>();
		//DBAccess���C���X�^���X��
		ThDBAccess orcl=new ThDBAccess();

		//Oracle�ɃA�N�Z�X���A�擾�����p�����[�^��DB�ɓo�^����
		orcl.setSQLAttr(tn, n, t);
		orcl.insertThExecute(thisBdID);

		threads = orcl.getUsers();

		//HttpServletRequest�̎����N���X�̃C���X�^���X��
		//threads�Ƃ������O�Ńf�[�^��o�^����
		req.setAttribute("threads",threads);
		
		//doGet(req, res);
		res.sendRedirect("addth?bdID="+thisBdID);

		//RequestDispatcher�C���^�[�t�F�C�X����������N���X��
		//�C���X�^���X���擾����
		//�����͓]�����URL
		//RequestDispatcher dispatcher=
		//	req.getRequestDispatcher("ContentsInput");
		
		//���X�|���X�ɕʂ̃T�[�u���b�g�̏o�͂��܂߂�
		//dispatcher.forward(req,res);
	}
}
