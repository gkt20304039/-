import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.UserBean;

public class AddResServlet extends HttpServlet {
    
    //ArrayListを使用
	private ArrayList<UserBean> users = new ArrayList<UserBean>();
	//UserBeanをインスタンス化
	private DBAccess orcl=new DBAccess();

	public void orclExecute() {
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		//Oracleにアクセスし、取得したパラメータをDBに登録する
		orcl.oracleConnect();
		orcl.oracleSelect();
		orcl.oracleDisConnect();

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
		
		//POST要求によって送信されたパラメータを取得する
		String n=req.getParameter("resName");
		String t=req.getParameter("resText");
		
		//Oracleにアクセスし、取得したパラメータをDBに登録する
		orcl.setSQLAttr(n, t);
		orcl.oracleExecute();

		users = orcl.getUsers();

		//HttpServletRequestの実装クラスのインスタンスに
		//usersという名前でデータを登録する
		req.setAttribute("users",users);
		
		res.sendRedirect("addres");
		//RequestDispatcherインターフェイスを実装するクラスの
		//インスタンスを取得する
		//引数は転送先のURL
		//RequestDispatcher dispatcher=
		//	req.getRequestDispatcher("ContentsInput");
		
		//レスポンスに別のサーブレットの出力を含める
		//dispatcher.forward(req,res);
	}
}
