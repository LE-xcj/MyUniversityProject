package DS_Class;

public class MergeSort {
     private int merge_length;
     int[] Array;
     
     public MergeSort(int length){
    	 merge_length=length;
    	 Array=new int[merge_length];
    	 //Initial();
     }
     
     //往数组插入数据
     public void Initial(){
    	 for(int out=0;out<merge_length;++out){
    		 Array[out]=(int)(Math.random()*merge_length);
    	 }
     }
     
     //递归排序的入口
     public void merge_sort(){
    	 //临时数组，用来作排序用的
    	 int[] workPlace=new int[merge_length];
    	 Merge(workPlace, 0, merge_length-1);
     }
     
     private void Merge(int[] workPlace,int lower,int upper){
    	 if(lower==upper){
    		 return;
    	 }
    	 else{
    		 int mid=(lower+upper)/2;
    		 Merge(workPlace,lower,mid);    //对左边的区域进行划分
    		 Merge(workPlace,mid+1,upper);    //对右边的区域进行划分
    		 Sort(workPlace,lower,mid+1,upper);   //对两边有序的区域进行排序
    	 }
     }
     
     private void Sort(int[] workPlace,int Left_point,int Right_point,int Right_upper){
    	 int count=0;	
    	 int point=Left_point;                  //重新赋值的时候需要用到
    	 int Left_upper=Right_point-1;             //记录左边区域位置的上限
    	 int amount=Right_upper-Left_point+1;        //记录这段区域有多少个数据
    	 
    	 while(Left_point<=Left_upper&&Right_point<=Right_upper){
    		 if(Array[Left_point]<Array[Right_point]){
    			 workPlace[count++]=Array[Left_point++];
    		 }
    		 else{
    			 workPlace[count++]=Array[Right_point++];
    		 }
    	 }
    	 
    	 //补齐左半区域的数据
    	 while(Left_point<=Left_upper){
    		 workPlace[count++]=Array[Left_point++];
    	 }
    	 
    	 //补齐右半区域的数据
    	 while(Right_point<=Right_upper){
    		 workPlace[count++]=Array[Right_point++];
    	 }
    	 
    	 //将排好序的数据从临时数组中重新给原来数组对应的位置赋值
    	 for(count=0;count<amount;++count){
    		 Array[point+count]=workPlace[count];
    	 }
    	 
     }
     
     //显示
     public void Display(){
    	 for(int out=0;out<merge_length;++out){
    		 System.out.print(Array[out]+" ");
    	 }
    	 System.out.println();
     }
}
