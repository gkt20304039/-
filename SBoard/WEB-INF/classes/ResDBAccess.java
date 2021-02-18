import java.util.ArrayList;

import java.sql.SQLException;

import th.*;

/* RESPONSE */
class ResDBAccess extends DBAccess {
	//ArrayList���g�p
    private ArrayList<ResBean> users = new ArrayList<ResBean>();
	
	public ArrayList<ResBean> getUsers(){
		return users;
	}

	public void oracleResInsert(String bdID, String thID) {
		try {
			//insert���i���O�����͂���Ă��Ȃ��ꍇ�f�t�H���g����K�p�j
            if(name == ""){
                sql="INSERT INTO THREAD_RESPONSE VALUES('" + bdID + "', '" + thID +
					"', dbms_random.string('A',9), (SELECT MAX(res_number)+1 FROM thread_response WHERE th_id='" + thID + "' GROUP BY th_id), DEFAULT , SYSDATE , '"+
					 text +"')";
            } else {
			    sql="INSERT INTO THREAD_RESPONSE VALUES('" + bdID + "', '" + thID +
					"', dbms_random.string('A',9), (SELECT MAX(res_number)+1 FROM thread_response WHERE th_id='" + thID + "' GROUP BY th_id),'"+
					 name +"' , SYSDATE , '" + text +"')";
            }

			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			st=cn.createStatement();

			//insert����select�������s��
			//ResultSet�C���^�[�t�F�C�X�����������N���X��
            //�C���X�^���X���Ԃ�
			st.executeQuery(sql);

		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL�֘A�̗�O�݂����B");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void oracleResSelect(String thID) {
		try {
			sql=" SELECT res_id, res_number, res_name, res_date,res_text FROM thread_response WHERE th_id ='" + thID + "' ORDER BY res_number ASC";
			
			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			st=cn.createStatement();
			rs=st.executeQuery(sql);

			System.out.println("RES_ID"+"\t"+"RES_NUMBER"+"\t"+"RES_NAME"+"\t"+"RES_DATE"+"\t"+"RES_TEXT");
			while(rs.next()){
				//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
				id=rs.getString (1);	//1��ڂ̃f�[�^���擾
				num=rs.getInt (2);		//2��ڂ̃f�[�^���擾
				name=rs.getString(3);	//3��ڂ̃f�[�^���擾
                date=rs.getString(4);	//4��ڂ̃f�[�^���擾
                text=rs.getString(5);	//5��ڂ̃f�[�^���擾
				
				//UserBean���C���X�^���X��
				ResBean user=new ResBean();
				user.setAllContents(id, num, name, date, text);
				//���X�g�ɒǉ�����
				users.add(user);
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL�֘A�̗�O�݂����B");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertResExecute(String ID1, String ID2) {

		try{
			//oracle�ɐڑ�
			oracleConnect();

			//INSERT
			oracleResInsert(ID1, ID2);

			//SELECT
			oracleResSelect(ID1);

			//oracle����ؒf
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void selectResExecute(String ID) {

		try{
			//oracle�ɐڑ�
			oracleConnect();

			//SELECT
			oracleResSelect(ID);

			//oracle����ؒf
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
