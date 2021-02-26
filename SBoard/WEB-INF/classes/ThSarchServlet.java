import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.ThSarchBean;

public class ThSarchServlet extends HttpServlet {
	static String thisBdID;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//POST要求によって送信された文字列をクライアントで
		//エンコードしたときの文字コードを指定する
		//これを指定しないと文字化けする可能性がある
		req.setCharacterEncoding("Windows-31J");
		
		//POST要求によって送信されたパラメータを取得する
		String tn=req.getParameter("sarch");
		
		//ArrayListを使用
		ArrayList<ThSarchBean> threads = new ArrayList<ThSarchBean>();
		//DBAccessをインスタンス化
		ThSarchDBAccess orcl=new ThSarchDBAccess();

		//Oracleにアクセスし、取得したパラメータをDBに登録する
		orcl.setSQLAttr(tn);
		orcl.selectThExecute();

		threads = orcl.getUsers();

		//HttpServletRequestの実装クラスのインスタンスに
		//threadsという名前でデータを登録する
		req.setAttribute("threads",threads);
		//res.sendRedirect("sarch?bdID="+thisBdID);
		//doGet(req, res);
		//res.sendRedirect("addres");

		//RequestDispatcherインターフェイスを実装するクラスの
		//インスタンスを取得する
		//引数は転送先のURL
		RequestDispatcher dispatcher=
		req.getRequestDispatcher("threadView");
		
		//レスポンスに別のサーブレットの出力を含める
		dispatcher.forward(req,res);
	}
}
