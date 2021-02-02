import java.io.IOException;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import th.UserBean;

class DBAccess{
	private String resName;
	private String resText;
	private String resNum;
	private String resDate;
	private String sql;
	private Connection cn;
	private Statement st;
	private ResultSet rs;
	
    //ArrayList���g�p
    private ArrayList<UserBean> users = new ArrayList<UserBean>();
	
	public ArrayList<UserBean> getUsers(){
		return users;
	}

	public void setSQLAttr(String name, String text) {
		resName = name;
		resText = text;
	}

	public void oracleConnect() {
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

	public void oracleDisConnect() {
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

	public void oracleInsert() {
		try {
			//insert���i���O�����͂���Ă��Ȃ��ꍇ�f�t�H���g����K�p�j
            if(resName == ""){
                sql="INSERT INTO THREAD_RESPONSE VALUES(RES_NUM_SEQ.NEXTVAL, DEFAULT , SYSDATE , '" + resText +"')";
            } else {
			    sql="INSERT INTO THREAD_RESPONSE VALUES(RES_NUM_SEQ.NEXTVAL, '" + resName +"' , SYSDATE , '" + resText +"')";
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

	public void oracleSelect() {
		try {
			sql=" SELECT * FROM THREAD_RESPONSE ORDER BY RES_NUMBER ASC";
			
			//Statement�C���^�[�t�F�C�X����������N���X���C���X�^���X������
			st=cn.createStatement();
			rs=st.executeQuery(sql);

			System.out.println("RES_NUMBER"+"\t"+"RES_NAME"+"\t"+"RES_DATE"+"\t"+"RES_TEXT");
			while(rs.next()){
				//�J�[�\������s�����X�N���[�����A�f�[�^���t�F�b�`����
				resNum=rs.getString (1);	//1��ڂ̃f�[�^���擾
				resName=rs.getString(2);	//2��ڂ̃f�[�^���擾
                resDate=rs.getString(3);	//3��ڂ̃f�[�^���擾
                resText=rs.getString(4);	//4��ڂ̃f�[�^���擾
				
				//UserBean���C���X�^���X��
				UserBean user=new UserBean();
				user.setAllContents(resNum, resName, resDate, resText);
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

	public void oracleExecute() {

		try{
			//oracle�ɐڑ�
			oracleConnect();

			//INSERT
			oracleInsert();

			//SELECT
			oracleSelect();

			//oracle����ؒf
			oracleDisConnect();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}