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
	 * 1、加载驱动
	 * 2、声明连接IP、及端口以及要连接的数据库名称
	 * 3、数据库的登录用户名、密码
	 * 4、连接数据库并返回statement查询对象
	 */
	
	public static void setConnetion(){
		setIp();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url="jdbc:sqlserver://"+ip+":1433;databaseName=Hotel";
			String user="Hoteladmin";//
			String password="575534";//密码
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
		
	//查询,返回结果集
	public static ResultSet select(String sql){
		try {
			result=statement.executeQuery(sql);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//增加、删除、修改
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

