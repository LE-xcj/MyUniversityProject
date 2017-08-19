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
    		  System.out.println("1、插入数据");
    		  System.out.println("2、移除数据");
    		  System.out.println("3、展示队列数据");
    		  flag=input.nextInt();
    		  switch(flag){
    		     case 1:{
    			     System.out.println("输入数据");
    			     data=input.nextInt();
    			     list.Insert(data);
    		     }
    		     break;
    		     case 2:{
    		    	 System.out.println("移除了"+list.Delete().data);
    		     }
    		     break;
    		     case 3:{
    		    	 System.out.println("队列里的数据：");
    		    	 list.DisplayList();
    		    	 System.out.println();
    		     }
    		     break;
    		  }
    	  }
      }
}
