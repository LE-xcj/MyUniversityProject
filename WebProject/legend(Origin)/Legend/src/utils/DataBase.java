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
		//通过流将properties文件加载进来
		InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
		try {
			pro.load(in);
			//加载驱动
			Class.forName(pro.getProperty("driverName"));
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获得连接
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
	
	//增加、删除和修改的操作
	public static void update(String sql,Object ...params){
		connection=getConnection();
		try {
			ps=connection.prepareStatement(sql);	//创建预编译语句，相当于一个SQL语句模板
			if(null!=params){
				for(int index=0;index<params.length;++index){
					ps.setObject(index+1, params[index]);		//将参数套进SQL语句模板
				}
			}
			ps.executeUpdate();		//执行更新操作
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null);		//关闭资源
		}
		
	}
	
	//查询操作
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
	
	//关闭资源，关闭的规则都是先开哪一个，那么就是最后一个关闭
	public static void close(ResultSet result){
		if(null!=result){
			try {
				result.close();		//关闭ResultSet
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(null!=ps){
					try {
						ps.close();		//关闭PrepareStatement
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						if(null!=connection){
							try {
								connection.close();		//关闭连接
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
