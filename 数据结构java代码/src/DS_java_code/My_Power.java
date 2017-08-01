package DS_java_code;

import java.util.Scanner;

public class My_Power {
     public static void main(String args[]){
    	 @SuppressWarnings("resource")
		 Scanner input=new Scanner(System.in);
    	 int x,y;
    	 while(true){
    		 System.out.println("请输入x：");
    		 x=input.nextInt();
    		 System.out.println("请输入y：");
    		 y=input.nextInt();
    		 System.out.println("x的y次方："+power(x,y));
    	 }
     }
     
     public static long power(int x,int y){
    	 int quotient=y/2;
    	 int remainder=y%2;
    	 long result;
    	 if(quotient==0&&remainder==1){
    		 return x;
    	 }
    	 else{
    		 if(remainder==0){
        		 result=power(x,quotient);
        		 return result*result;
    		 }
    		 else{
    			 result=power(x,quotient);
    			 return result*result*x;
    		 }
    	 }
     }
}
