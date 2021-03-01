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
		//ArrayListを使用
		ArrayList<ThBean> threads = new ArrayList<ThBean>();
		//DBAccessをインスタンス化
		ThDBAccess orcl=new ThDBAccess();
		//URLのパラメータから板IDを取得する
		String bdID=req.getParameter("bdID");
		//閲覧している板のIDを格納する
		thisBdID = bdID;
		//Oracleにアクセスし、現在までのデータを取得する
		orcl.selectThExecute(thisBdID);

		threads = orcl.getUsers();

		//HttpServletRequestの実装クラスのインスタンスに
		//threadsという名前でデータを登録する
		req.setAttribute("threads",threads);
		req.setAttribute("ranking",orcl.getRanking());

		String view = "/threadView";
    	RequestDispatcher dispatcher = req.getRequestDispatcher(view);

    	dispatcher.forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//POST要求によって送信された文字列をクライアントで
		//エンコードしたときの文字コードを指定する
		//これを指定しないと文字化けする可能性がある
		req.setCharacterEncoding("Windows-31J");
		
		//POST要求によって送信されたパラメータを取得する
		String tn=req.getParameter("thName");
		String n=req.getParameter("resName");
		String t=req.getParameter("resText");
		
		//ArrayListを使用
		ArrayList<ThBean> threads = new ArrayList<ThBean>();
		//DBAccessをインスタンス化
		ThDBAccess orcl=new ThDBAccess();

		//Oracleにアクセスし、取得したパラメータをDBに登録する
		orcl.setSQLAttr(tn, n, t);
		orcl.insertThExecute(thisBdID);

		threads = orcl.getUsers();

		//HttpServletRequestの実装クラスのインスタンスに
		//threadsという名前でデータを登録する
		req.setAttribute("threads",threads);
		
		//doGet(req, res);
		res.sendRedirect("addth?bdID="+thisBdID);

		//RequestDispatcherインターフェイスを実装するクラスの
		//インスタンスを取得する
		//引数は転送先のURL
		//RequestDispatcher dispatcher=
		//	req.getRequestDispatcher("ContentsInput");
		
		//レスポンスに別のサーブレットの出力を含める
		//dispatcher.forward(req,res);
	}
}
