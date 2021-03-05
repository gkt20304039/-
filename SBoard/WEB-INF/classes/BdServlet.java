import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.*;

public class BdServlet  extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		//�{�����Ă���y�[�W���ǂ���������Ă����iheader.jsp�̌������̏����p�j
		String thisPage = "top";

		//DBAccess���C���X�^���X��
		ResDBAccess orcl=new ResDBAccess();

        //�����L���O�̃��\�b�h�̎��s
        orcl.oracleConnect();
        orcl.ranking("ALL");
        orcl.oracleDisConnect();

		//HttpServletRequest�̎����N���X�̃C���X�^���X��
		//ranking�Ƃ������O�Ńf�[�^��o�^����
		req.setAttribute("ranking",orcl.getRanking());
		req.setAttribute("thisPage",thisPage);

		String view = "/index";
    	RequestDispatcher dispatcher = req.getRequestDispatcher(view);

    	dispatcher.forward(req, res);
	}
	    
}