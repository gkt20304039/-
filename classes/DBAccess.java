import java.io.IOException;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import th.UserBean;

class DBAccess{
	private String resName;
	private String resText;
	private String resNum;
	private String resDate;
	private String sql;
	private Connection cn;
	private Statement st;
	private ResultSet rs;
	
    //ArrayListを使用
    private ArrayList<UserBean> users = new ArrayList<UserBean>();
	
	public ArrayList<UserBean> getUsers(){
		return users;
	}

	public void setSQLAttr(String name, String text) {
		resName = name;
		resText = text;
	}

	public void oracleConnect() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracleに接続する
			cn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
				"boardowner","bdon");
			System.out.println("接続完了");

		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("クラスがないみたい。");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL関連の例外みたい。");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void oracleDisConnect() {
		try{
			//Oracleから切断する
			cn.close();
			System.out.println("切断完了");

		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL関連の例外みたい。");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void oracleInsert() {
		try {
			//insert文（名前が入力されていない場合デフォルト名を適用）
            if(resName == ""){
                sql="INSERT INTO THREAD_RESPONSE VALUES(RES_NUM_SEQ.NEXTVAL, DEFAULT , SYSDATE , '" + resText +"')";
            } else {
			    sql="INSERT INTO THREAD_RESPONSE VALUES(RES_NUM_SEQ.NEXTVAL, '" + resName +"' , SYSDATE , '" + resText +"')";
            }

			//Statementインターフェイスを実装するクラスをインスタンス化する
			st=cn.createStatement();

			//insert文とselect文を実行し
			//ResultSetインターフェイスを実装したクラスの
            //インスタンスが返る
			st.executeQuery(sql);

		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL関連の例外みたい。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void oracleSelect() {
		try {
			sql=" SELECT * FROM THREAD_RESPONSE ORDER BY RES_NUMBER ASC";
			
			//Statementインターフェイスを実装するクラスをインスタンス化する
			st=cn.createStatement();
			rs=st.executeQuery(sql);

			System.out.println("RES_NUMBER"+"\t"+"RES_NAME"+"\t"+"RES_DATE"+"\t"+"RES_TEXT");
			while(rs.next()){
				//カーソルを一行だけスクロールし、データをフェッチする
				resNum=rs.getString (1);	//1列目のデータを取得
				resName=rs.getString(2);	//2列目のデータを取得
                resDate=rs.getString(3);	//3列目のデータを取得
                resText=rs.getString(4);	//4列目のデータを取得
				
				//UserBeanをインスタンス化
				UserBean user=new UserBean();
				user.setAllContents(resNum, resName, resDate, resText);
				//リストに追加する
				users.add(user);
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL関連の例外みたい。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void oracleExecute() {

		try{
			//oracleに接続
			oracleConnect();

			//INSERT
			oracleInsert();

			//SELECT
			oracleSelect();

			//oracleから切断
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}