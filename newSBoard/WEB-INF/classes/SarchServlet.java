import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.*;

public class SarchServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//POST�v���ɂ���đ��M���ꂽ��������N���C�A���g��
		//�G���R�[�h�����Ƃ��̕����R�[�h���w�肷��
		//������w�肵�Ȃ��ƕ�����������\��������
		req.setCharacterEncoding("Windows-31J");
		
		//POST�v���ɂ���đ��M���ꂽ�p�����[�^���擾����
		String thSarchWord = req.getParameter("thSarch");
		String resSarchWord = req.getParameter("resSarch");
		
		if (thSarchWord != null) {
			//DBAccess���C���X�^���X��
			ThDBAccess orcl = new ThDBAccess();
			//Oracle�ɃA�N�Z�X���A�擾�����p�����[�^��DB�ɓo�^����
			orcl.serchThExecute(thSarchWord);	
			//HttpServletRequest�̎����N���X�̃C���X�^���X��
			//threads�Ƃ������O�Ńf�[�^��o�^����
			req.setAttribute("threads",orcl.getUsers());

			//RequestDispatcher�C���^�[�t�F�C�X����������N���X��
			//�C���X�^���X���擾����
			//�����͓]�����URL
			RequestDispatcher dispatcher = req.getRequestDispatcher("sarchResult");
			
			//���X�|���X�ɕʂ̃T�[�u���b�g�̏o�͂��܂߂�
			dispatcher.forward(req,res);
		} else {
			ResDBAccess orcl = new ResDBAccess();
			orcl.serchResExecute(resSarchWord);	
			req.setAttribute("responses",orcl.getUsers());
			RequestDispatcher dispatcher = req.getRequestDispatcher("resSarchResult");
			dispatcher.forward(req,res);
		}
	}
}
