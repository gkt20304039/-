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
		//POST要求によって送信された文字列をクライアントで
		//エンコードしたときの文字コードを指定する
		//これを指定しないと文字化けする可能性がある
		req.setCharacterEncoding("Windows-31J");
		
		//POST要求によって送信されたパラメータを取得する
		String thSarchWord = req.getParameter("thSarch");
		String resSarchWord = req.getParameter("resSarch");
		
		if (thSarchWord != null) {
			//DBAccessをインスタンス化
			ThDBAccess orcl = new ThDBAccess();
			//Oracleにアクセスし、取得したパラメータをDBに登録する
			orcl.serchThExecute(thSarchWord);	
			//HttpServletRequestの実装クラスのインスタンスに
			//threadsという名前でデータを登録する
			req.setAttribute("threads",orcl.getUsers());

			//RequestDispatcherインターフェイスを実装するクラスの
			//インスタンスを取得する
			//引数は転送先のURL
			RequestDispatcher dispatcher = req.getRequestDispatcher("sarchResult");
			
			//レスポンスに別のサーブレットの出力を含める
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
