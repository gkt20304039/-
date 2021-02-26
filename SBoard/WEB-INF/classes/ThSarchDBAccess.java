import java.io.IOException;
import java.util.ArrayList;

import java.sql.SQLException;

import th.ThSarchBean;

/* Thread */
class ThSarchDBAccess extends DBAccess {
	private String thName;
	private String thID;
	//ArrayListを使用
    private ArrayList<ThSarchBean> users = new ArrayList<ThSarchBean>();
	
	public ArrayList<ThSarchBean> getUsers(){
		return users;
	}

	public void setSQLAttr(String thName) {
		this.thName = thName;
	}

	

	public void oracleThSarch() {
		try {
			sql=" SELECT th_id,th_name, th_date FROM thread_overview WHERE  TH_NAME like '%"+thName+"%' ORDER BY th_id ASC";
			
			//Statementインターフェイスを実装するクラスをインスタンス化する
			st=cn.createStatement();
			rs=st.executeQuery(sql);

			System.out.println("TH_ID"+"\t"+"TH_NAME"+"\t"+"TH_DATE");
			while(rs.next()){
				//カーソルを一行だけスクロールし、データをフェッチする
				id=rs.getString (1);	//1列目のデータを取得
				name=rs.getString(2);	//2列目のデータを取得
                date=rs.getString(3);	//3列目のデータを取得
				
				//UserBeanをインスタンス化
				ThSarchBean user=new ThSarchBean();
				user.setAllContents(id, name, date);
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

	
	
	public void selectThExecute() {

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
