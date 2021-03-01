import java.io.IOException;
import java.util.ArrayList;

import java.sql.SQLException;

import th.*;

/* Thread */
class ThDBAccess extends DBAccess {
	private String thName;
	private String thID;
	//ArrayList���g�p
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
			//�X���b�hID�𐶐����邽�߂�sql��
			sql = "SELECT to_char(trunc(to_number(sysdate - to_date('1970/01/01 00:00:00', 'YYYY/MM/DD HH24:MI:SS')) * (24 * 60 * 60))) FROM dual";
			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			st=cn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				thID = rs.getString(1);
			}
			//insert���P
			sql="INSERT INTO thread_overview VALUES('" + bdID + "', '" + thID + "', '" + thName + "', SYSDATE)";

			st.executeQuery(sql);

			//insert���Q�i���O�����͂���Ă��Ȃ��ꍇ�f�t�H���g����K�p�j
            if(name == ""){
                sql="INSERT INTO THREAD_RESPONSE VALUES('" + bdID + "', '" + thID +
							 "', dbms_random.string('A',9), 1, DEFAULT , SYSDATE , '" + text +"')";
            } else {
			    sql="INSERT INTO THREAD_RESPONSE VALUES('" + bdID + "', '" + thID +
							 "', dbms_random.string('A',9), 1, DEFAULT , SYSDATE , '" + text +"')";
            }

			//insert����select�������s��
			//ResultSet�C���^�[�t�F�C�X�����������N���X��
            //�C���X�^���X���Ԃ�
			st.executeQuery(sql);

		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL�֘A�̗�O�݂����B");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void oracleThSelect(String bdID) {
		try {
			sql=" SELECT th_id, th_name, th_date FROM thread_overview WHERE bd_id ='" + bdID + "' ORDER BY th_id ASC";
			
			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			
			//sql�������s����rs�Ɋi�[�����f�[�^���擾����
			dataFetch(users, "Thread");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL�֘A�̗�O�݂����B");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void sarch(String thName, ArrayList users, String type) {
		try {
			sql=" SELECT th_id,th_name, th_date FROM thread_overview WHERE  TH_NAME like '%"+thName+"%' ORDER BY th_id ASC";
			
			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			
			dataFetch(users, type);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL�֘A�̗�O�݂����B");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertThExecute(String ID1) {

		try{
			//oracle�ɐڑ�
			oracleConnect();

			//INSERT
			oracleThInsert(ID1);

			//SELECT
			oracleThSelect(ID1);

			//oracle����ؒf
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void selectThExecute(String ID) {

		try{
			//oracle�ɐڑ�
			oracleConnect();

			//SELECT
			oracleThSelect(ID);

			//�����L���O
			ranking("BOARD");

			//oracle����ؒf
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void serchThExecute(String name) {

		try{
			//oracle�ɐڑ�
			oracleConnect();

			//����
			sarch(name, users, "Thread");

			//oracle����ؒf
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
