package DS_java_code;

import java.util.Scanner;

public class Compare_lineSearch_and_binarySearch {
	 final static int length=50000000;
	 static int[] order_array=new int[length];
     public static void main(String args[]){  
    	 for(int i=0;i<length;++i){             //��ʼ������
    		 order_array[i]=i;
    	 }
    	 @SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
    	 int value;
    	 long start,end,result;           //1����ʼ��ʱ�̣�2��������ʱ�̣�3�����
    	 while(true){
    		 System.out.println("��������Ҫ���ҵ�����");
    		 value=input.nextInt();
    		 start=System.currentTimeMillis();
    		 //��¼�������Բ��ҿ�ʼ��ʱ��
    		 line_find(value);         //���Բ���
    		 end=System.currentTimeMillis();
    		 //��¼�������ʱ��
    		 result=end-start;   //���������ʱ��
    		 System.out.println("���Բ�������Ҫ��ʱ�䣺"+result+"����");
    		 
    		 start=System.currentTimeMillis();
    		 binary_find(value);    //���ֲ���
    		 end=System.currentTimeMillis();
    		 result=end-start;
    		 System.out.println("���ֲ�������Ҫ��ʱ�䣺"+result+"����");
    	 }
     }
     public static int line_find(int value){    //���Բ���
    	 for(int i=0;i<length;++i){
    		 if(value==order_array[i]){
    			 return i;
    		 }
    	 }
    	 return -1;
     }
     public static int binary_find(int value){   //���ֲ���
    	 int lower=0;
    	 int upper=length-1;
    	 int mid;
    	 while(lower<=upper){
    		 mid=(lower+upper)/2;
    		 if(order_array[mid]==value){
    			 return mid;
    		 }
    		 else{
    			 if(value>order_array[mid]){
    				 lower=mid+1;
    			 }
    			 else{
    				 upper=mid-1;
    			 }
    		 }
    	 }
    	 return -1;
     }
}
