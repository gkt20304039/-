import java.io.IOException;
import java.util.ArrayList;

import java.sql.SQLException;

import th.ResSarchBean;

/* Thread */
class ResSarchDBAccess extends DBAccess {
	private String thName;
	private String thID;
	//ArrayListを使用
    private ArrayList<ResSarchBean> users = new ArrayList<ResSarchBean>();
	
	public ArrayList<ResSarchBean> getUsers(){
		return users;
	}

	public void setSQLAttr(String thName) {
		this.thName = thName;
	}

	

	public void oracleThSarch() {
		try {
				sql=" SELECT res_id, res_number, res_name, res_date,res_text FROM thread_response WHERE RES_TEXT like '%"+thName+"%' ORDER BY res_number ASC";

			
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
				ResSarchBean user=new ResSarchBean();
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
	
	
	public void selectResExecute() {

		try{
			//oracleに接続
			oracleConnect();

			//SELECT
			oracleThSarch();

			//oracleから切断
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
