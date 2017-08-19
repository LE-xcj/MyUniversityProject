package GUI;

import Operator.RootControl;
import java.sql.*;


public class Main {
	public static void main(String[] args) {
		Connection connection=null;
		try{
			//加载驱动
			String driver="com.mysql.jdbc.Driver";
			Class.forName(driver);
			
			String url="jdbc:mysql://localhost:3306/User_db";
			String user="root";
			String password="575534";
			
			//建立与数据库的连接
			connection=DriverManager.getConnection(url, user, password);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//控制器
		RootControl control=new RootControl(connection);
		//显示UI界面
        new Login_Registor_Frame(control);
        
    }
}
