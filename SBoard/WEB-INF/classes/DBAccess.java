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

	//public void oracleInsert(String bdID, String thID){};

	//public abstract void oracleSelect(String id);
}

