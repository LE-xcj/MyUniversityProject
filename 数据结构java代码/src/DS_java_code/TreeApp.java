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
        	System.out.println("1����Ҷ����");
        	System.out.println("2����˳����");
        	System.out.println("3����ʾҶ����");
        	System.out.println("4����ʾ˳����");
        	flag=input.nextInt();
        	switch(flag){
	        	case 1:{
	        		System.out.println("�������ַ���");
	        		str=input.next();
	        		operator.BuildTree(str);
	        	}
	        	break;
	        	case 2:{
	        		System.out.println("�������ַ���");
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
