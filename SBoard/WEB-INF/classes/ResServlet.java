import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.*;

public class ResServlet extends HttpServlet {
	static String thisThID;
	String bdID;
	String thID;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		//ArrayList���g�p
		ArrayList<ResBean> responses = new ArrayList<ResBean>();
		//DBAccess���C���X�^���X��
		ResDBAccess orcl=new ResDBAccess();
		//URL�̃p�����[�^�����ID,�X���b�hID���擾����
		bdID=req.getParameter("bdID");
		thID=req.getParameter("thID");
		//�{�����Ă���X���b�h��ID���i�[����
		ThreadServlet.thisBdID = bdID;
		thisThID = thID;
		//�{�����Ă���y�[�W���X���b�h�ꗗ���e�X���b�h����������Ă����iheader.jsp�̌������̏����p�j
		String thisPage = "response";
		//Oracle�ɃA�N�Z�X���A�擾�����p�����[�^��DB�ɓo�^����
		orcl.selectResExecute(thisThID);

		responses = orcl.getResponses();

		//HttpServletRequest�̎����N���X�̃C���X�^���X��
		//responses�Ƃ������O�Ńf�[�^��o�^����
		req.setAttribute("responses",responses);
		req.setAttribute("thisPage",thisPage);
		req.setAttribute("thName", DBAccess.thName);

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

		//�{�����Ă����ID���i�[����
		if(thID!=null){thisThID = thID;}
		if(bdID!=null){ThreadServlet.thisBdID = bdID;}
		//POST�v���ɂ���đ��M���ꂽ�p�����[�^���擾����
		String n=req.getParameter("resName");
		String t=req.getParameter("resText");
		//�擾�������X�ɉ��s���܂܂�Ă����ꍇ<br>�ɉ��s������ϊ�
		t=newLine(t);
		t=reply(t);
		//ArrayList���g�p
		ArrayList<ResBean> responses = new ArrayList<ResBean>();
		//DBAccess���C���X�^���X��
		ResDBAccess orcl=new ResDBAccess();
		
		//Oracle�ɃA�N�Z�X���A�擾�����p�����[�^��DB�ɓo�^����
		orcl.setSQLAttr(n, t);
		orcl.insertResExecute(ThreadServlet.thisBdID, thisThID);

		responses = orcl.getResponses();

		//HttpServletRequest�̎����N���X�̃C���X�^���X��
		//responses�Ƃ������O�Ńf�[�^��o�^����
		req.setAttribute("responses",responses);
		
		res.sendRedirect("addres?thID="+thisThID+"&bdID="+ThreadServlet.thisBdID);
		//doGet(req, res);
		//RequestDispatcher�C���^�[�t�F�C�X����������N���X��
		//�C���X�^���X���擾����
		//�����͓]�����URL
		//RequestDispatcher dispatcher=
		//	req.getRequestDispatcher("ContentsInput");
		
		//���X�|���X�ɕʂ̃T�[�u���b�g�̏o�͂��܂߂�
		//dispatcher.forward(req,res);
	}
	protected String newLine(String text){		
		if(text.indexOf("\n")>=0 || text.indexOf("\r")>=0 || text.indexOf("\r\n")>=0){
			text = text.replaceAll("\\r\\n|\\r|\\n", "<br>");
		}
		return text;
	}
	protected String reply(String text){
		if(text.indexOf(">>")>=0 && text.indexOf(">>")<=1){
			String repText = String.join("",text.split(">>[0-999]"));
			String repNum = text.substring(text.indexOf(">>"), text.indexOf(repText));
			text = text.replace(repNum, "<a href=''#" + repNum + "''> " + repNum + "</a>");
		}
		return text;
	}
}