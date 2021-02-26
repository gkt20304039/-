import java.util.ArrayList;

import java.sql.SQLException;

import th.*;

/* RESPONSE */
class ResDBAccess extends DBAccess {
	//ArrayListを使用
    private ArrayList<ResBean> users = new ArrayList<ResBean>();
	//ランキング用のArrayList
	private ArrayList<ResBean> ranking = new ArrayList<ResBean>();
	
	public ArrayList<ResBean> getUsers(){
		return users;
	}
	//ランキングの要素を取得するメソッド
	public ArrayList<ResBean> getRanking(){
		return ranking;
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
			sql=" SELECT res_id, res_number, res_name, res_date,res_text FROM thread_response WHERE th_id ='" + thID + "' ORDER BY res_number ASC";
			
			//Statementインターフェイスを実装するクラスをインスタンス化する
			st=cn.createStatement();
			rs=st.executeQuery(sql);

			System.out.println("RES_ID"+"\t"+"RES_NUMBER"+"\t"+"RES_NAME"+"\t"+"RES_DATE"+"\t"+"RES_TEXT");
			while(rs.next()){
				//カーソルを一行だけスクロールし、データをフェッチする
				id=rs.getString (1);	//1列目のデータを取得
				num=rs.getInt (2);		//2列目のデータを取得
				name=rs.getString(3);	//3列目のデータを取得
                date=rs.getString(4);	//4列目のデータを取得
                text=rs.getString(5);	//5列目のデータを取得
				
				//UserBeanをインスタンス化
				ResBean user=new ResBean();
				user.setAllContents(id, num, name, date, text);
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

	//ランキングのメソッド
	public void oracleRanking(){

        //レスを格納する配列resを作成
        ArrayList<String> res = new ArrayList<String>();

        try {
			//SQLからレスポンスを日時を降順でソートし取得する
            sql="SELECT * FROM THREAD_RESPONSE ORDER BY RES_DATE DESC FETCH FIRST 10 ROWS ONLY";
            
            //Statementインターフェイスを実装するクラスをインスタンス化する2
            st=cn.createStatement();
            rs=st.executeQuery(sql);
    
            System.out.println("RES_NUMBER");
			while(rs.next()){
				//カーソルを一行だけスクロールし、データをフェッチする
				id=rs.getString (1);	//1列目のデータを取得
				num=rs.getInt (2);	//2列目のデータを取得
				name=rs.getString(3);	//3列目のデータを取得
                date=rs.getString(4);	//4列目のデータを取得
                text=rs.getString(5);	//5列目のデータを取得
				
				//UserBeanをインスタンス化
				ResBean user=new ResBean();
				
				user.setAllContents(id, num, name, date, text);
				//リストに追加する
				ranking.add(user);
			}
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

			//RANKING
			oracleRanking();

			//oracleから切断
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
