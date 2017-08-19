package DS_java_code;

import java.util.Scanner;

import DS_Class.TreeOperator;

public class TreeApp {
    public static void main(String args[]){
    	int flag;
    	String str;
    	@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
    	TreeOperator operator=new TreeOperator();
    	while(true){
        	System.out.println("1¡¢½¨Ò¶×ÓÊı");
        	System.out.println("2¡¢½¨Ë³ĞòÊ÷");
        	System.out.println("3¡¢ÏÔÊ¾Ò¶×ÓÊ÷");
        	System.out.println("4¡¢ÏÔÊ¾Ë³ĞòÊ÷");
        	flag=input.nextInt();
        	switch(flag){
	        	case 1:{
	        		System.out.println("ÇëÊäÈë×Ö·û´®");
	        		str=input.next();
	        		operator.BuildTree(str);
	        	}
	        	break;
	        	case 2:{
	        		System.out.println("ÇëÊäÈë×Ö·û´®");
	        		str=input.next();
	        		operator.Build_Another_Tree(str);
	        	}
	        	break;
	        	case 3:{
	        		operator.DisplayTree(flag);
	        	}
	        	break;
	        	case 4:{
	        		operator.DisplayTree(flag);
	        	}
	        	break;
        	}
    	}
    }
}
