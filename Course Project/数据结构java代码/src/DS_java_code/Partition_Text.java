package DS_java_code;

import java.util.Scanner;

import DS_Class.Partition;

public class Partition_Text {
      public static void main(String args[]){
    	  @SuppressWarnings("resource")
		  Scanner input=new Scanner(System.in);
    	  Partition text;
    	  int length;
    	  int flag;
		  System.out.println("请输入要划分的数据规模：");
		  length=input.nextInt();
		  text=new Partition(length);
    	  while(true){
    		  System.out.println("1、划分数据");
    		  System.out.println("2、寻找中心值");
    		  System.out.println("3、设置数据规模");
    		  flag=input.nextInt();
    		  switch(flag){
	    		  case 1:{
	        		  System.out.println("划分前数据的分布：");
	        		  text.Display();
	        		  text.Peek();
	        		  System.out.println("划分后数据的分布：");
	        		  text.Partition_Access();
	    		  }
	    		  break;
	    		  case 2:{
	    			  System.out.print("数组的内数据的分布:");
	    			  text.Display();
	    			  text.Find_Middle_Value();
	    			  System.out.print("划分之后数据的分布:");
	    			  text.Display();
	    			  System.out.print("-----排序后---:");
	    			  text.Sort_to_Show();
	    		  }
	    		  break;
	    		  case 3:{
	    			  System.out.println("请输入要划分的数据规模：");
	    			  length=input.nextInt();
	    			  text=new Partition(length);
	    		  }
	    		  break;
    		  }		  
    	  }
      }
}
