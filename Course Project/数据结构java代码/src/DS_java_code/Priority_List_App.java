package DS_java_code;

import java.util.Scanner;

import DS_Class.Priority_List;

public class Priority_List_App {
      public static void main(String args[]){
    	  int flag;
    	  int data;
    	  Priority_List list=new Priority_List();
    	  @SuppressWarnings("resource")
		  Scanner input=new Scanner(System.in);
    	  while(true){
    		  System.out.println("1����������");
    		  System.out.println("2���Ƴ�����");
    		  System.out.println("3��չʾ��������");
    		  flag=input.nextInt();
    		  switch(flag){
    		     case 1:{
    			     System.out.println("��������");
    			     data=input.nextInt();
    			     list.Insert(data);
    		     }
    		     break;
    		     case 2:{
    		    	 System.out.println("�Ƴ���"+list.Delete().data);
    		     }
    		     break;
    		     case 3:{
    		    	 System.out.println("����������ݣ�");
    		    	 list.DisplayList();
    		    	 System.out.println();
    		     }
    		     break;
    		  }
    	  }
      }
}
