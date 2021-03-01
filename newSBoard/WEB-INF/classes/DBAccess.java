import java.io.IOException;
import java.util.ArrayList;

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
					//リストに追加する
					users.add(user);
				} 
				if(type == "Res"){
					num=rs.getInt (4);		//2列目のデータを取得
					text=rs.getString(5);	//5列目のデータを取得

					ResBean user=new ResBean();
					user.setAllContents(id, name, date, num, text);
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
				sql="SELECT (11-(RANK() OVER(ORDER BY res_date DESC))) * ROUND(res_number/2, 0) AS RANKPOINT, th_id FROM THREAD_RESPONSE ORDER BY RANKPOINT DESC FETCH FIRST 10 ROWS ONLY";	
			}
			//板ごとのランキングの時
			if(type == "BOARD") {
				sql="SELECT (11-(RANK() OVER(ORDER BY res_date DESC))) * ROUND(res_number/2, 0) AS RANKPOINT, th_id FROM THREAD_RESPONSE " + AddThServlet.thisBdID + " ORDER BY RANKPOINT DESC FETCH FIRST 10 ROWS ONLY";	
			}
            
            //Statementインターフェイスを実装するクラスをインスタンス化する2
            st=cn.createStatement();
			Statement st2=cn.createStatement();
            rs=st.executeQuery(sql);
    
			while(rs.next()){
				//カーソルを一行だけスクロールし、データをフェッチする
				num=rs.getInt (1);		//1列目のデータを取得
				id=rs.getString (2);	//2列目のデータを取得
				
				//ランク入りしたスレッドの名前、最終更新日、総レス数を取得
				sql="SELECT t.th_name, res_number, r.res_date FROM thread_overview t LEFT JOIN thread_response r USING(th_id) WHERE th_id = " + id + " AND r.res_number = (SELECT MAX(res_number) FROM thread_response)";
				
				ResultSet rs2=st2.executeQuery(sql);

				//UserBeanをインスタンス化
				ResBean user=new ResBean();
				if(rs2.next()){
					user.setId(id);
					user.setName(rs2.getString(1));
					user.setNum(rs2.getInt(2));
					user.setDate(rs2.getString(3));
					//リストに追加する
					ranking.add(user);
				}
			}
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQL関連の例外みたい。");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}