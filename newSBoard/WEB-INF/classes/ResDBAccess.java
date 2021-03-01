import java.util.ArrayList;

import java.sql.SQLException;

import th.*;

/* RESPONSE */
class ResDBAccess extends DBAccess {
	//ArrayListを使用
    private ArrayList<ResBean> users = new ArrayList<ResBean>();
	
	public ArrayList<ResBean> getUsers(){
		return users;
	}

	public void oracleResInsert(String bdID, String thID) {
		try {
			//insert文（名前が入力されていない場合デフォルト名を適用）
            if(name == ""){
                sql="INSERT INTO THREAD_RESPONSE VALUES('" + bdID + "', '" + thID +
					"', dbms_random.string('A',9), (SELECT MAX(res_number)+1 FROM thread_response WHERE th_id='" + thID + "' GROUP BY th_id), DEFAULT , SYSDATE , '"+
					 text +"')";
            } else {
			    sql="INSERT INTO THREAD_RESPONSE VALUES('" + bdID + "', '" + thID +
					"', dbms_random.string('A',9), (SELECT MAX(res_number)+1 FROM thread_response WHERE th_id='" + thID + "' GROUP BY th_id),'"+
					 name +"' , SYSDATE , '" + text +"')";
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

	public void oracleResSelect(String thID) {
		try {
			sql=" SELECT res_id, res_name, res_date, res_number, res_text FROM thread_response WHERE th_id ='" + thID + "' ORDER BY res_number ASC";
			
			//Statementインターフェイスを実装するクラスをインスタンス化する
			st=cn.createStatement();
			rs=st.executeQuery(sql);

			//sql文を実行してrsに格納したデータを取得する
			dataFetch(users, "Res");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL関連の例外みたい。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void sarch(String thName, ArrayList users, String type) {
		try {
			sql=" SELECT res_id, res_name, res_date, res_number, res_text FROM thread_response WHERE RES_TEXT like '%"+thName+"%' ORDER BY res_number ASC";
			
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

	public void insertResExecute(String ID1, String ID2) {

		try{
			//oracleに接続
			oracleConnect();

			//INSERT
			oracleResInsert(ID1, ID2);

			//SELECT
			oracleResSelect(ID1);

			//oracleから切断
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void selectResExecute(String ID) {

		try{
			//oracleに接続
			oracleConnect();

			//SELECT
			oracleResSelect(ID);

			//oracleから切断
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void serchResExecute(String name) {

		try{
			//oracleに接続
			oracleConnect();

			//検索
			sarch(name, users, "Res");

			//oracleから切断
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
