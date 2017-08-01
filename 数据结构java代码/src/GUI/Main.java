package GUI;

import Operator.RootControl;
import java.sql.*;


public class Main {
	public static void main(String[] args) {
		Connection connection=null;
		try{
			//��������
			String driver="com.mysql.jdbc.Driver";
			Class.forName(driver);
			
			String url="jdbc:mysql://localhost:3306/User_db";
			String user="root";
			String password="575534";
			
			//���������ݿ������
			connection=DriverManager.getConnection(url, user, password);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//������
		RootControl control=new RootControl(connection);
		//��ʾUI����
        new Login_Registor_Frame(control);
        
    }
}
