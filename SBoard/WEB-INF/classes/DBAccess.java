import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import th.*;

abstract class DBAccess{
	protected String id;
	protected int num;
	protected String name;
	protected String text;
	protected String date;
	protected String sql;
	protected Connection cn;
	protected Statement st;
	protected ResultSet rs;
	public static String thName;

	public void setSQLAttr(String name, String text) {
		this.name = name;
		this.text = text;
	}

	protected void oracleConnect() {
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

	protected void oracleDisConnect() {
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

	//abstract void oracleInsert(String bdID, String thID){};

	//abstract void oracleSelect(String id);

	protected void dataFetch(ArrayList users, String type){
		try {
			while(rs.next()){
				id=rs.getString (1);	//1列目のデータを取得
				name=rs.getString(2);	//2列目のデータを取得
				date=rs.getString(3);	//3列目のデータを取得
				
				//スレッドなら3列分取得した後リストに追加
				if(type == "Thread"){
					//UserBeanをインスタンス化
					ThBean user=new ThBean();
					user.setAllContents(id, name, date);
					user.setBdID(ThreadServlet.thisBdID);
					//リストに追加する
					users.add(user);
				} 
				if(type == "Res"){
					num=rs.getInt (4);		//2列目のデータを取得
					text=rs.getString(5);	//5列目のデータを取得
					thName=rs.getString(6);

					ResBean user=new ResBean();
					user.setAllContents(id, name, date, num, text);
					user.setBdID(ThreadServlet.thisBdID);
					users.add(user);
				}
			}	
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL関連の例外みたい。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//ランキング機能
	//ランキング用のArrayList
	private ArrayList<ResBean> ranking = new ArrayList<ResBean>();
	//ランキング処理中のth_idを格納するArrayList
	private ArrayList<String> thIDs = new ArrayList<String>();
	//ランキングの要素を取得するメソッド
	public ArrayList<ResBean> getRanking(){
		return ranking;
	}

	//SQLでランク付けするメソッド
	protected void ranking(String type){
        try {
			//掲示板全体のランキングの時
			if(type == "ALL") {
				//(11-[最終レス投稿日時の順位])×[総レス数÷2を四捨五入]でランク付けし、上位10位をth_idとともに取得
				sql="SELECT MAX(ROUND((to_number(res_date - to_date('1970/01/01 00:00:00', 'YYYY/MM/DD HH24:MI:SS')) * (24 * 60 * 60))/48505250, 0) * ROUND(res_number/2, 0)) AS rankpoint, th_id FROM thread_response GROUP BY th_id ORDER BY rankpoint DESC FETCH FIRST 10 ROWS ONLY";
			}
			//板ごとのランキングの時
			if(type == "BOARD") {
				sql="SELECT MAX(ROUND((to_number(res_date - to_date('1970/01/01 00:00:00', 'YYYY/MM/DD HH24:MI:SS')) * (24 * 60 * 60))/48505250, 0) * ROUND(res_number/2, 0)) AS rankpoint, th_id FROM thread_response WHERE bd_id = '" +
				ThreadServlet.thisBdID + "' GROUP BY th_id ORDER BY rankpoint DESC FETCH FIRST 10 ROWS ONLY";
			}
            
            //Statementインターフェイスを実装するクラスをインスタンス化する2
            st=cn.createStatement();
            rs=st.executeQuery(sql);
    
			while(rs.next()){
				//カーソルを一行だけスクロールし、データをフェッチする
				num=rs.getInt(1);		//1列目のデータを取得
				id=rs.getString(2);	//2列目のデータを取得

				ResBean idBean=new ResBean();
				idBean.setId(id);
				thIDs.add(idBean.getId());
			}
		
			//ランク入りしたスレッドの名前、最終更新日、総レス数を取得
			for(int i=0; i < thIDs.size(); i++){
				sql="SELECT th_id, t.th_name, MAX(r.res_number), TO_CHAR(MAX(r.res_date),'YY-MM-DD HH:MM:SS'), t.bd_id FROM thread_overview t JOIN thread_response r USING(th_id) WHERE th_id = '" + thIDs.get(i) + "' GROUP BY th_id, t.th_name, t.bd_id";
				rs=st.executeQuery(sql);
				while(rs.next()){
					id = rs.getString(1);
					name = rs.getString(2);
					num = rs.getInt(3);
					date = rs.getString(4);
					String bdID = rs.getString(5);
					//UserBeanをインスタンス化
					ResBean user=new ResBean();
					user.setAllContents(id, name, date, num, null);
					user.setBdID(bdID);
					
					//リストに追加する
					ranking.add(user);					
				}
			}
		} catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQL関連の例外みたい。");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}