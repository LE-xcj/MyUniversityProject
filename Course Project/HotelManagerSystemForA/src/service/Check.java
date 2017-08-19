package service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pojo.Bill;
import utils.ContainerForPojo;

public class Check {
    private static Pattern pattern;  
    private static final String USING="入住";
    private static final String BOOKING="预定";
    private static final String ENABLE="禁用";
	private static final String UNCOMPLISH="未结账"; 
    
  
    //\\b  表示 限定单词边界  比如  select 不通过   1select则是可以的  
    public static boolean isUnValid(String str1,String str2){ 
    	String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"  
                + "(\\b(select|update|union|and|or|delete|insert|trancate|char|into|substr|ascii|"
                + "declare|exec|count|master|into|drop|execute)\\b)";  
    	pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);  
	    if (pattern.matcher(str1).find())   
	        return true;      
	    if(pattern.matcher(str2).find())
	    	return true;
	    return false;  
	} 
    
    public static boolean canComplishBill(int index){
    	String status=ContainerForPojo.room_list.get(index).getStatus();
    	return USING.equals(status);
    }
    
    public static int billNumIndex(String billNum){
    	ArrayList<Bill> bills=ContainerForPojo.bill_list;
    	int length=bills.size();
    	for(int i=0;i<length;++i){
    		if(billNum.equals(bills.get(i).getBillNum()))
    			return i;
    	}
    	return -1;
    }
    
    public static boolean isDiscount(String discount){
    	String reg="^0(\\.[0-9]{1,4})?$|^1(\\.[0]{1,4})?$";
        pattern = Pattern.compile(reg);  
        Matcher match=pattern.matcher(discount);  
        return match.matches();  
    }
    
    public static boolean canUsingRoom(int index){
    	String status=ContainerForPojo.room_list.get(index).getStatus();
    	return (BOOKING.equals(status));
    }
    
    public static boolean canBooking(int index){
    	String status=ContainerForPojo.room_list.get(index).getStatus();
    	return (!ENABLE.equals(status));
    }
    
    public static boolean dateIsRight(String from ,String to){
    	int flag=from.compareTo(to);
    	return (flag<=0);
    }
    
   
    public static boolean validDate(String date){
    	 String rexDate = "^((\\d{2}(([02468][048])|"
    	    		+ "([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|"
    	    		+ "([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|"
    	    		+ "([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|"
    	    		+ "(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
    	    		+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))"; 
    	
        Pattern pat = Pattern.compile(rexDate);      
        
        Matcher mat = pat.matcher(date);      
            
        return mat.matches();
    }
    
    public static boolean isMoney(String str){
    	String reg="^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";
        Pattern pattern = Pattern.compile(reg);  
        Matcher match=pattern.matcher(str);  
        return match.matches();  
    }
    
    public static boolean isNumber(String str){ 
    	if("".equals(str))
    		return false;
    	String reg="^[0-9]*$";
        Pattern pattern = Pattern.compile(reg);  
        Matcher match=pattern.matcher(str);  
        return match.matches();  
    } 
    
    public static boolean canCancleOrComplish(String status){
    	if(UNCOMPLISH.equals(status))
    		return true;
    	return false;
    }
    
    public static boolean canDeleteBill(String status){
    	if(UNCOMPLISH.equals(status))
    		return false;
    	return true;
    }
    
    public static boolean dayGapRight(String dayGap){
    	String str="[0-9]*";
        Pattern pattern = Pattern.compile(str);  
        Matcher match=pattern.matcher(dayGap);  
        return match.matches();  
    }
    
    
}
