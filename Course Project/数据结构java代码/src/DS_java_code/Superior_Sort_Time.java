package DS_java_code;

import java.util.Scanner;

import DS_Class.Superior_Sort_Operator;

public class Superior_Sort_Time {
     public static void main(String args[]){
    	 int flag;
    	 Superior_Sort_Operator operator=new Superior_Sort_Operator();	 
    	 @SuppressWarnings("resource")
		 Scanner input=new Scanner(System.in);   	 
    	 while(true){
    		 System.out.println("1����ʾϣ��������������");
    		 System.out.println("2������ϣ����������������Ч��");
    		 System.out.println("3���޸����ݹ�ģ");
    		 System.out.println("4����ʾ���������Ľ����");
    		 flag=input.nextInt();
    		 switch(flag){
	    		 case 1:{
	    			 operator.Display_Shell_Sort();
	    		 }
	    		 break;
	    		 case 2:{
	    			 operator.Compare_TwoSort_Eefficency();
	    		 }
	    		 break;
	    		 case 3:{
	    			 operator.Change_Length();
	    		 }
	    		 break;
	    		 case 4:{
	    			 operator.Display_Quick();
	    		 }
	    		 break;
    		 }
    	 }
     }
}
