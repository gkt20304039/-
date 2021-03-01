import java.io.IOException;
import java.util.ArrayList;

import java.sql.SQLException;

import th.*;

/* Thread */
class ThDBAccess extends DBAccess {
	private String thName;
	private String thID;
	//ArrayListを使用
    private ArrayList<ThBean> users = new ArrayList<ThBean>();
	
	public ArrayList<ThBean> getUsers(){
		return users;
	}

	public void setSQLAttr(String thName, String name, String text) {
		this.thName = thName;
		this.name = name;
		this.text = text;
	}

	public void oracleThInsert(String bdID) {
		try {
			//スレッドIDを生成するためのsql文
			sql = "SELECT to_char(trunc(to_number(sysdate - to_date('1970/01/01 00:00:00', 'YYYY/MM/DD HH24:MI:SS')) * (24 * 60 * 60))) FROM dual";
			//Statementインターフェイスを実装するクラスをインスタンス化する
			st=cn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				thID = rs.getString(1);
			}
			//insert文１
			sql="INSERT INTO thread_overview VALUES('" + bdID + "', '" + thID + "', '" + thName + "', SYSDATE)";

			st.executeQuery(sql);

			//insert文２（名前が入力されていない場合デフォルト名を適用）
            if(name == ""){
                sql="INSERT INTO THREAD_RESPONSE VALUES('" + bdID + "', '" + thID +
							 "', dbms_random.string('A',9), 1, DEFAULT , SYSDATE , '" + text +"')";
            } else {
			    sql="INSERT INTO THREAD_RESPONSE VALUES('" + bdID + "', '" + thID +
							 "', dbms_random.string('A',9), 1, DEFAULT , SYSDATE , '" + text +"')";
            }

			//insert文とselect文を実行し
			//ResultSetインターフェイスを実装したクラスの
            //インスタンスが返る
			st.executeQuery(sql);

		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL関連の例外みたい。");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void oracleThSelect(String bdID) {
		try {
			sql=" SELECT th_id, th_name, th_date FROM thread_overview WHERE bd_id ='" + bdID + "' ORDER BY th_id ASC";
			
			//Statementインターフェイスを実装するクラスをインスタンス化する
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			
			//sql文を実行してrsに格納したデータを取得する
			dataFetch(users, "Thread");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL関連の例外みたい。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void sarch(String thName, ArrayList users, String type) {
		try {
			sql=" SELECT th_id,th_name, th_date FROM thread_overview WHERE  TH_NAME like '%"+thName+"%' ORDER BY th_id ASC";
			
			//Statementインターフェイスを実装するクラスをインスタンス化する
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			
			dataFetch(users, type);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL関連の例外みたい。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertThExecute(String ID1) {

		try{
			//oracleに接続
			oracleConnect();

			//INSERT
			oracleThInsert(ID1);

			//SELECT
			oracleThSelect(ID1);

			//oracleから切断
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void selectThExecute(String ID) {

		try{
			//oracleに接続
			oracleConnect();

			//SELECT
			oracleThSelect(ID);

			//ランキング
			ranking("BOARD");

			//oracleから切断
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void serchThExecute(String name) {

		try{
			//oracleに接続
			oracleConnect();

			//検索
			sarch(name, users, "Thread");

			//oracleから切断
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
