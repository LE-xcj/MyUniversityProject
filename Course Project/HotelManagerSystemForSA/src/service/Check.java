package service;


import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.DataBase;
import dao.OperaPojo;

	/*
	 * service层
	 * 处理业务逻辑
	 * 只对用户输入的数据进行检验，是用户输入的数据进入dao层的的关卡
	 */

public class Check{
	private final static int adminBF=0;
	private final static int clientBF=1;
	private final static String UNCOMPLISH="未结账"; 
	public static boolean checkNP(String name,String pd){
		boolean flag=false;
		if(name==null||pd==null)
			return false;
		else{
			String sql="select * from sadmin where saname= '"+name+"' and sapwd= '"+pd+"'";
			try {
				flag= DataBase.select(sql).next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public static boolean checkNull(String str){
		return "".equals(str);
	}
	
	public static boolean checkNull(String str1 , String str2){
		return (str1.equals("")||str2.equals(""));
	}
	
	public static boolean checkNull(String str1,String str2,String str3,String str4){
		return ("".equals(str1)||"".equals(str2)||"".equals(str3)||"".equals(str4));
	}
	
	public static boolean checkDefaultNull(String str1,String str1_1,String str2,String str2_2){
		return str1.equals(str1_1)||str2.equals(str2_2);
	}
	
	public static boolean checkDefaultNull(String str1,String str1_1,String str2,String str2_2,String str3,String str3_3,String str4,String str4_4){
		return (str1.equals(str1_1)||str2.equals(str2_2)||str3.equals(str3_3)||str4.equals(str4_4));
	}
	
	//用于添加记录
	public static boolean checkExit(String pk,int flag){
		String sql=null;
		boolean exit=false;
		if(adminBF==flag)
			sql="select aname from admin where aname='"+pk+"'";
		else if(clientBF==flag)
			sql="select id from client where id='"+pk+"'";
		else
			sql="select roomID from room where roomid='"+pk+"'";

		try {
			exit=DataBase.select(sql).next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exit;
	}
	
	//用于修改信息的时候用
	public static boolean checkExit(String pk,String newPK,int flag){
		String sql=null;
		boolean exit=false;
		if(pk.equals(newPK))
			return false;
		if(adminBF==flag)
			sql="select * from admin where aname='"+newPK+"'";
		else if(clientBF==flag)
			sql="select * from client where id='"+newPK+"'";
		else
			sql="select * from room where roomid='"+newPK+"'";
		try {
			exit=DataBase.select(sql).next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exit;
	}
	
	public static boolean checkExitType(String type){
		String[] types=OperaPojo.roomType();
		if(types==null)
			return false;
		else{
			int length=types.length;
			for(int i=0;i<length;++i){
				if(type.equals(types[i]))
					return true;
			}
			return false;
		}
		
	}
	
    public static boolean isNumber(String str){ 
    	//String reg="^[0-9]+(.[0-9]*)?$";
    	String reg="^[0-9]*$";
        Pattern pattern = Pattern.compile(reg);  
        Matcher match=pattern.matcher(str);  
        return match.matches();  
    } 
    
    public static boolean isMoney(String str){
    	String reg="^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";
        Pattern pattern = Pattern.compile(reg);  
        Matcher match=pattern.matcher(str);  
        return match.matches();  
    }
    
    /**正则表达式**/  
    private static final String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"  
                                + "(\\b(select|update|union|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";  
  
    //\\b  表示 限定单词边界  比如  select 不通过   1select则是可以的  
    private static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);  
  
    public static boolean isUnValid(String str1,String str2){  
	    if (sqlPattern.matcher(str1).find())   
	        return true;      
	    if(sqlPattern.matcher(str2).find())
	    	return true;
	    return false;  
	}  
    
    private static final String rexDate = "^((\\d{2}(([02468][048])|"
    		+ "([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|"
    		+ "([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|"
    		+ "([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|"
    		+ "(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
    		+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))"; 
	
    public static boolean validDate(String date){
        Pattern pat = Pattern.compile(rexDate);      
        
        Matcher mat = pat.matcher(date);      
            
        return mat.matches();
    }
    
    
    public static boolean canCancleOrComplish(String status){
    	if(UNCOMPLISH.equals(status))
    		return true;
    	return false;
    }
    
    public static boolean dateIsRight(String from ,String to){
    	int flag=from.compareTo(to);
    	return (flag<=0);
    }
    
    public static boolean dateValid(String from ,String to){
    	int flag=from.compareTo(to);
    	return (flag<0);
    }
    
    
    public static boolean canDeleteBill(String status){
    	if(UNCOMPLISH.equals(status))
    		return false;
    	return true;
    }
    
    public static boolean isDiscount(String discount){
    	//String reg = "^\\d+\\.?\\d*\\%?$";
    	String reg="^0(\\.[0-9]{1,4})?$|^1(\\.[0]{1,4})?$";
        Pattern pattern = Pattern.compile(reg);  
        Matcher match=pattern.matcher(discount);  
        return match.matches();  
    }
    
    
}
