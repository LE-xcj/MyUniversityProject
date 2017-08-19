package DS_Class;

public class MergeSort {
     private int merge_length;
     int[] Array;
     
     public MergeSort(int length){
    	 merge_length=length;
    	 Array=new int[merge_length];
    	 //Initial();
     }
     
     //�������������
     public void Initial(){
    	 for(int out=0;out<merge_length;++out){
    		 Array[out]=(int)(Math.random()*merge_length);
    	 }
     }
     
     //�ݹ���������
     public void merge_sort(){
    	 //��ʱ���飬�����������õ�
    	 int[] workPlace=new int[merge_length];
    	 Merge(workPlace, 0, merge_length-1);
     }
     
     private void Merge(int[] workPlace,int lower,int upper){
    	 if(lower==upper){
    		 return;
    	 }
    	 else{
    		 int mid=(lower+upper)/2;
    		 Merge(workPlace,lower,mid);    //����ߵ�������л���
    		 Merge(workPlace,mid+1,upper);    //���ұߵ�������л���
    		 Sort(workPlace,lower,mid+1,upper);   //����������������������
    	 }
     }
     
     private void Sort(int[] workPlace,int Left_point,int Right_point,int Right_upper){
    	 int count=0;	
    	 int point=Left_point;                  //���¸�ֵ��ʱ����Ҫ�õ�
    	 int Left_upper=Right_point-1;             //��¼�������λ�õ�����
    	 int amount=Right_upper-Left_point+1;        //��¼��������ж��ٸ�����
    	 
    	 while(Left_point<=Left_upper&&Right_point<=Right_upper){
    		 if(Array[Left_point]<Array[Right_point]){
    			 workPlace[count++]=Array[Left_point++];
    		 }
    		 else{
    			 workPlace[count++]=Array[Right_point++];
    		 }
    	 }
    	 
    	 //����������������
    	 while(Left_point<=Left_upper){
    		 workPlace[count++]=Array[Left_point++];
    	 }
    	 
    	 //�����Ұ����������
    	 while(Right_point<=Right_upper){
    		 workPlace[count++]=Array[Right_point++];
    	 }
    	 
    	 //���ź�������ݴ���ʱ���������¸�ԭ�������Ӧ��λ�ø�ֵ
    	 for(count=0;count<amount;++count){
    		 Array[point+count]=workPlace[count];
    	 }
    	 
     }
     
     //��ʾ
     public void Display(){
    	 for(int out=0;out<merge_length;++out){
    		 System.out.print(Array[out]+" ");
    	 }
    	 System.out.println();
     }
}
