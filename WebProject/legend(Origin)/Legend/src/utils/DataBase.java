package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DataBase {
	private static Properties pro=new Properties();
	private static Connection connection=null;
	private static PreparedStatement ps=null;
	static{
		//ͨ������properties�ļ����ؽ���
		InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
		try {
			pro.load(in);
			//��������
			Class.forName(pro.getProperty("driverName"));
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�������
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(pro.getProperty("url"), 
											pro.getProperty("userName"), 
											pro.getProperty("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//���ӡ�ɾ�����޸ĵĲ���
	public static void update(String sql,Object ...params){
		connection=getConnection();
		try {
			ps=connection.prepareStatement(sql);	//����Ԥ������䣬�൱��һ��SQL���ģ��
			if(null!=params){
				for(int index=0;index<params.length;++index){
					ps.setObject(index+1, params[index]);		//�������׽�SQL���ģ��
				}
			}
			ps.executeUpdate();		//ִ�и��²���
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null);		//�ر���Դ
		}
		
	}
	
	//��ѯ����
	public static ResultSet query(String sql,Object ...params){
		connection=getConnection();
		ResultSet result=null;
		try {
			ps=connection.prepareStatement(sql);
			if(null!=params){
				for(int index=0;index<params.length;++index){
					ps.setObject(index+1, params[index]);
				}
			}
			result=ps.executeQuery();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//�ر���Դ���رյĹ������ȿ���һ������ô�������һ���ر�
	public static void close(ResultSet result){
		if(null!=result){
			try {
				result.close();		//�ر�ResultSet
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(null!=ps){
					try {
						ps.close();		//�ر�PrepareStatement
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						if(null!=connection){
							try {
								connection.close();		//�ر�����
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}	
	}
	
}
