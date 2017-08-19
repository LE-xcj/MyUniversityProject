package DS_java_code;

import java.util.Scanner;

import DS_Class.MergeSort;

public class Merge_Sort_time {
     public static void main(String args[]){
    	 long begin,end,time,averge;
    	 long result;
    	 int length;
    	 @SuppressWarnings("resource")
		 Scanner input=new Scanner(System.in);
    	 MergeSort merge_sort;
    	 System.out.println("请输入你要测试的次数");
    	 int count=input.nextInt();
    	 while(true){
    		 result=0;	
    		 System.out.println("请输入要排序的数据规模");
    		 length=input.nextInt();
    		 merge_sort=new MergeSort(length); 		 
        	 for(int out=0;out<count;++out){
        		 //merge_sort=new MergeSort(length);
        		 merge_sort.Initial();
        		 begin=System.currentTimeMillis();
        		 merge_sort.merge_sort();
        		 end=System.currentTimeMillis();
        		 time=end-begin;
        		 result+=time;
        	 }
        	 averge=result/count;
        	 System.out.println("归并排序（升序）"+count+"次，平均所需要的时间："+averge+"毫秒");  
    	 }
     }
}
