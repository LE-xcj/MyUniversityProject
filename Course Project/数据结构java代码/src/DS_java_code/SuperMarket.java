	package DS_java_code;
	
	import java.util.Scanner;
	
	import DS_Class.Control;
	
	public class SuperMarket {
	      public static void main(String args[]){
	    	  @SuppressWarnings("resource")
			  Scanner input=new Scanner(System.in);
	    	  Control control=new Control();
	    	  String s_input;
	    	  char c_input;
	    	  while(true){
	    		  System.out.println("show(��ʾ��ǰ����)");
	    		  System.out.println("timer(ʱ������)");
	    		  System.out.println("enter(�˿����)");
	    		  s_input=input.next();
	    		  c_input=s_input.charAt(0);
	    		  switch(c_input){
	    		    case 's':{
	    			     control.Show_queue();
	    		     }
	    		    break;
	    		    case 't':{
	    		    	 control.Timer_queue();
	    		    }
	    		    break;
	    		    case 'e':{
	    		    	 control.Enter_queue();
	    		    }
	    		    break;
	    		    default:{
	    		    	System.out.println("Sorry!Your input is wrong!");
	    		    }
	    		    break;
	    		  }
	    	  }
	      }
	}
