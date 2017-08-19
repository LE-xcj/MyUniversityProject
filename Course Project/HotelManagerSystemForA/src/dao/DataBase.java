package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class DataBase {
	private static Statement statement=null;
	private static Connection connection=null;
	private static ResultSet result=null;
	
	private static final String FILE="txt"+File.separator+"IP.txt"; 
	private static String ip=null;
	
	
	/*
	 * 1����������
	 * 2����������IP�����˿��Լ�Ҫ���ӵ����ݿ�����
	 * 3�����ݿ�ĵ�¼�û���������
	 * 4���������ݿⲢ����statement��ѯ����
	 */
	
	public static void setConnetion(){
		setIp();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url="jdbc:sqlserver://"+ip+":1433;databaseName=Hotel";
			String user="Hoteladmin";//
			String password="575534";//����
			connection=DriverManager.getConnection(url, user, password);
			statement=connection.createStatement();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	//��ѯ,���ؽ����
	public static ResultSet select(String sql){
		try {
			result=statement.executeQuery(sql);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//���ӡ�ɾ�����޸�
	public static void other(String sql){
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void setIp(){
		FileInputStream is;
		InputStreamReader isr;
		BufferedReader br;
		try {
			is=new FileInputStream(FILE);
			isr=new InputStreamReader(is);
			br=new BufferedReader(isr);
			ip=br.readLine();
			
			is.close();
			isr.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}

