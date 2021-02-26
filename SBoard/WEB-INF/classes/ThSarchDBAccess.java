import java.io.IOException;
import java.util.ArrayList;

import java.sql.SQLException;

import th.ThSarchBean;

/* Thread */
class ThSarchDBAccess extends DBAccess {
	private String thName;
	private String thID;
	//ArrayList���g�p
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
			
			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			st=cn.createStatement();
			rs=st.executeQuery(sql);

			System.out.println("TH_ID"+"\t"+"TH_NAME"+"\t"+"TH_DATE");
			while(rs.next()){
				//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
				id=rs.getString (1);	//1��ڂ̃f�[�^���擾
				name=rs.getString(2);	//2��ڂ̃f�[�^���擾
                date=rs.getString(3);	//3��ڂ̃f�[�^���擾
				
				//UserBean���C���X�^���X��
				ThSarchBean user=new ThSarchBean();
				user.setAllContents(id, name, date);
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

	
	
	public void selectThExecute() {

		try{
			//oracle�ɐڑ�
			oracleConnect();

			//SELECT
			oracleThSarch();

			//oracle����ؒf
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
