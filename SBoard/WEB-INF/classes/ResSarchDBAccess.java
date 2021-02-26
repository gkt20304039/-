import java.io.IOException;
import java.util.ArrayList;

import java.sql.SQLException;

import th.ResSarchBean;

/* Thread */
class ResSarchDBAccess extends DBAccess {
	private String thName;
	private String thID;
	//ArrayList���g�p
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
				ResSarchBean user=new ResSarchBean();
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
	
	
	public void selectResExecute() {

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
