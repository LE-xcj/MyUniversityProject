package DS_java_code;

import java.util.Scanner;

public class Triangle {
     public static void main(String args[]){
    	 @SuppressWarnings("resource")
		 Scanner input=new Scanner(System.in);
    	 int number,result;
    	 while(true){
    		 System.out.println("��������������");
    		 number=input.nextInt();    		 
    		 result=triangle(number);
    		 System.out.println(number+"�������ǣ�"+result);
    	 }
     } 
     public static int triangle(int number){
    	 //if(number==1)
    		 //return number;
    	 //else
    		 return triangle(number-1)+number;
     }
}
