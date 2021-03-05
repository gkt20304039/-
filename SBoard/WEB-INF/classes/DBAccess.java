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

			//Oracle�ɐڑ�����
			cn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
				"boardowner","bdon");
			System.out.println("�ڑ�����");

		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("�N���X���Ȃ��݂����B");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void oracleDisConnect() {
		try{
			//Oracle����ؒf����
			cn.close();
			System.out.println("�ؒf����");

		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL�֘A�̗�O�݂����B");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//abstract void oracleInsert(String bdID, String thID){};

	//abstract void oracleSelect(String id);

	protected void dataFetch(ArrayList users, String type){
		try {
			while(rs.next()){
				id=rs.getString (1);	//1��ڂ̃f�[�^���擾
				name=rs.getString(2);	//2��ڂ̃f�[�^���擾
				date=rs.getString(3);	//3��ڂ̃f�[�^���擾
				
				//�X���b�h�Ȃ�3�񕪎擾�����ナ�X�g�ɒǉ�
				if(type == "Thread"){
					//UserBean���C���X�^���X��
					ThBean user=new ThBean();
					user.setAllContents(id, name, date);
					user.setBdID(ThreadServlet.thisBdID);
					//���X�g�ɒǉ�����
					users.add(user);
				} 
				if(type == "Res"){
					num=rs.getInt (4);		//2��ڂ̃f�[�^���擾
					text=rs.getString(5);	//5��ڂ̃f�[�^���擾
					thName=rs.getString(6);

					ResBean user=new ResBean();
					user.setAllContents(id, name, date, num, text);
					user.setBdID(ThreadServlet.thisBdID);
					users.add(user);
				}
			}	
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL�֘A�̗�O�݂����B");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//�����L���O�@�\
	//�����L���O�p��ArrayList
	private ArrayList<ResBean> ranking = new ArrayList<ResBean>();
	//�����L���O��������th_id���i�[����ArrayList
	private ArrayList<String> thIDs = new ArrayList<String>();
	//�����L���O�̗v�f���擾���郁�\�b�h
	public ArrayList<ResBean> getRanking(){
		return ranking;
	}

	//SQL�Ń����N�t�����郁�\�b�h
	protected void ranking(String type){
        try {
			//�f���S�̂̃����L���O�̎�
			if(type == "ALL") {
				//(11-[�ŏI���X���e�����̏���])�~[�����X����2���l�̌ܓ�]�Ń����N�t�����A���10�ʂ�th_id�ƂƂ��Ɏ擾
				sql="SELECT MAX(ROUND((to_number(res_date - to_date('1970/01/01 00:00:00', 'YYYY/MM/DD HH24:MI:SS')) * (24 * 60 * 60))/48505250, 0) * ROUND(res_number/2, 0)) AS rankpoint, th_id FROM thread_response GROUP BY th_id ORDER BY rankpoint DESC FETCH FIRST 10 ROWS ONLY";
			}
			//���Ƃ̃����L���O�̎�
			if(type == "BOARD") {
				sql="SELECT MAX(ROUND((to_number(res_date - to_date('1970/01/01 00:00:00', 'YYYY/MM/DD HH24:MI:SS')) * (24 * 60 * 60))/48505250, 0) * ROUND(res_number/2, 0)) AS rankpoint, th_id FROM thread_response WHERE bd_id = '" +
				ThreadServlet.thisBdID + "' GROUP BY th_id ORDER BY rankpoint DESC FETCH FIRST 10 ROWS ONLY";
			}
            
            //Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������2
            st=cn.createStatement();
            rs=st.executeQuery(sql);
    
			while(rs.next()){
				//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
				num=rs.getInt(1);		//1��ڂ̃f�[�^���擾
				id=rs.getString(2);	//2��ڂ̃f�[�^���擾

				ResBean idBean=new ResBean();
				idBean.setId(id);
				thIDs.add(idBean.getId());
			}
		
			//�����N���肵���X���b�h�̖��O�A�ŏI�X�V���A�����X�����擾
			for(int i=0; i < thIDs.size(); i++){
				sql="SELECT th_id, t.th_name, MAX(r.res_number), TO_CHAR(MAX(r.res_date),'YY-MM-DD HH:MM:SS'), t.bd_id FROM thread_overview t JOIN thread_response r USING(th_id) WHERE th_id = '" + thIDs.get(i) + "' GROUP BY th_id, t.th_name, t.bd_id";
				rs=st.executeQuery(sql);
				while(rs.next()){
					id = rs.getString(1);
					name = rs.getString(2);
					num = rs.getInt(3);
					date = rs.getString(4);
					String bdID = rs.getString(5);
					//UserBean���C���X�^���X��
					ResBean user=new ResBean();
					user.setAllContents(id, name, date, num, null);
					user.setBdID(bdID);
					
					//���X�g�ɒǉ�����
					ranking.add(user);					
				}
			}
		} catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQL�֘A�̗�O�݂����B");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}