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
		  System.out.println("������Ҫ���ֵ����ݹ�ģ��");
		  length=input.nextInt();
		  text=new Partition(length);
    	  while(true){
    		  System.out.println("1����������");
    		  System.out.println("2��Ѱ������ֵ");
    		  System.out.println("3���������ݹ�ģ");
    		  flag=input.nextInt();
    		  switch(flag){
	    		  case 1:{
	        		  System.out.println("����ǰ���ݵķֲ���");
	        		  text.Display();
	        		  text.Peek();
	        		  System.out.println("���ֺ����ݵķֲ���");
	        		  text.Partition_Access();
	    		  }
	    		  break;
	    		  case 2:{
	    			  System.out.print("����������ݵķֲ�:");
	    			  text.Display();
	    			  text.Find_Middle_Value();
	    			  System.out.print("����֮�����ݵķֲ�:");
	    			  text.Display();
	    			  System.out.print("-----�����---:");
	    			  text.Sort_to_Show();
	    		  }
	    		  break;
	    		  case 3:{
	    			  System.out.println("������Ҫ���ֵ����ݹ�ģ��");
	    			  length=input.nextInt();
	    			  text=new Partition(length);
	    		  }
	    		  break;
    		  }		  
    	  }
      }
}
