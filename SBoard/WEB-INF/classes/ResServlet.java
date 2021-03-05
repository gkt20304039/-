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
		//ArrayListを使用
		ArrayList<ResBean> responses = new ArrayList<ResBean>();
		//DBAccessをインスタンス化
		ResDBAccess orcl=new ResDBAccess();
		//URLのパラメータから板ID,スレッドIDを取得する
		bdID=req.getParameter("bdID");
		thID=req.getParameter("thID");
		//閲覧しているスレッドのIDを格納する
		ThreadServlet.thisBdID = bdID;
		thisThID = thID;
		//閲覧しているページがスレッド一覧か各スレッド内かをいれておく（header.jspの検索欄の処理用）
		String thisPage = "response";
		//Oracleにアクセスし、取得したパラメータをDBに登録する
		orcl.selectResExecute(thisThID);

		responses = orcl.getResponses();

		//HttpServletRequestの実装クラスのインスタンスに
		//responsesという名前でデータを登録する
		req.setAttribute("responses",responses);
		req.setAttribute("thisPage",thisPage);
		req.setAttribute("thName", DBAccess.thName);

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

		//閲覧している板のIDを格納する
		if(thID!=null){thisThID = thID;}
		if(bdID!=null){ThreadServlet.thisBdID = bdID;}
		//POST要求によって送信されたパラメータを取得する
		String n=req.getParameter("resName");
		String t=req.getParameter("resText");
		//取得したレスに改行が含まれていた場合<br>に改行文字を変換
		t=newLine(t);
		t=reply(t);
		//ArrayListを使用
		ArrayList<ResBean> responses = new ArrayList<ResBean>();
		//DBAccessをインスタンス化
		ResDBAccess orcl=new ResDBAccess();
		
		//Oracleにアクセスし、取得したパラメータをDBに登録する
		orcl.setSQLAttr(n, t);
		orcl.insertResExecute(ThreadServlet.thisBdID, thisThID);

		responses = orcl.getResponses();

		//HttpServletRequestの実装クラスのインスタンスに
		//responsesという名前でデータを登録する
		req.setAttribute("responses",responses);
		
		res.sendRedirect("addres?thID="+thisThID+"&bdID="+ThreadServlet.thisBdID);
		//doGet(req, res);
		//RequestDispatcherインターフェイスを実装するクラスの
		//インスタンスを取得する
		//引数は転送先のURL
		//RequestDispatcher dispatcher=
		//	req.getRequestDispatcher("ContentsInput");
		
		//レスポンスに別のサーブレットの出力を含める
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