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
		//ArrayListを使用
		ArrayList<ResBean> users = new ArrayList<ResBean>();
		//DBAccessをインスタンス化
		ResDBAccess orcl=new ResDBAccess();
		//URLのパラメータから板IDを取得する
		String thID=req.getParameter("thID");
		//閲覧しているスレッドのIDを格納する
		thisThID = thID;
		//Oracleにアクセスし、取得したパラメータをDBに登録する
		orcl.selectResExecute(thisThID);

		users = orcl.getUsers();

		//HttpServletRequestの実装クラスのインスタンスに
		//usersという名前でデータを登録する
		req.setAttribute("users",users);

		String view = "/ContentsInput";
    	RequestDispatcher dispatcher = req.getRequestDispatcher(view);

    	dispatcher.forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//POST要求によって送信された文字列をクライアントで
		//エンコードしたときの文字コードを指定する
		//これを指定しないと文字化けする可能性がある
		req.setCharacterEncoding("Windows-31J");
		
		//URLのパラメータから板IDを取得する
		String thID=req.getParameter("thID");
		//POST要求によって送信されたパラメータを取得する
		String n=req.getParameter("resName");
		String t=req.getParameter("resText");
		//取得したレスに改行が含まれていた場合<br>に改行文字を変換
		t=newLine(t);
		
		//ArrayListを使用
		ArrayList<ResBean> users = new ArrayList<ResBean>();
		//DBAccessをインスタンス化
		ResDBAccess orcl=new ResDBAccess();

		//Oracleにアクセスし、取得したパラメータをDBに登録する
		orcl.setSQLAttr(n, t);
		orcl.insertResExecute(AddThServlet.thisBdID, thisThID);

		users = orcl.getUsers();

		//HttpServletRequestの実装クラスのインスタンスに
		//usersという名前でデータを登録する
		req.setAttribute("users",users);
		
		res.sendRedirect("addres?thID="+thisThID);
		//doGet(req, res);
		//RequestDispatcherインターフェイスを実装するクラスの
		//インスタンスを取得する
		//引数は転送先のURL
		//RequestDispatcher dispatcher=
		//	req.getRequestDispatcher("ContentsInput");
		
		//レスポンスに別のサーブレットの出力を含める
		//dispatcher.forward(req,res);
	}
	
	public String newLine(String text){		
		if(text.indexOf("\n")>=0 || text.indexOf("\r")>=0 || text.indexOf("\r\n")>=0){
			text = text.replaceAll("\\r\\n|\\r|\\n", "<br>");
		}
		return text;
	}
}
