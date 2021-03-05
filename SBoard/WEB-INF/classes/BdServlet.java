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
		//閲覧しているページがどこかをいれておく（header.jspの検索欄の処理用）
		String thisPage = "top";

		//DBAccessをインスタンス化
		ResDBAccess orcl=new ResDBAccess();

        //ランキングのメソッドの実行
        orcl.oracleConnect();
        orcl.ranking("ALL");
        orcl.oracleDisConnect();

		//HttpServletRequestの実装クラスのインスタンスに
		//rankingという名前でデータを登録する
		req.setAttribute("ranking",orcl.getRanking());
		req.setAttribute("thisPage",thisPage);

		String view = "/index";
    	RequestDispatcher dispatcher = req.getRequestDispatcher(view);

    	dispatcher.forward(req, res);
	}
	    
}