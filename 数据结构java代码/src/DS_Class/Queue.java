package DS_Class;

public class Queue {
	 final int length=2;
     int front;    //��ͷ
     int rear;     //��β
     int Item;     //���������
     int[] Array;
     
       //��ʼ��
     public Queue(){
    	 front=0;
    	 rear=-1;
    	 Item=0;
    	 Array=new int[length];
     }
     
                //����
     public void Insert(int Custom_number){
    	   //����֮ǰ���ж϶��е������Ƿ��Ѿ�����
    	 if(Item==length){
    		 System.out.println("Sorry!This queue is full!");
    	 }
    	 else{
    		 //ѭ������
    		 if(length-1==rear){
    			 rear=-1;
    		 }
    			 Array[++rear]=Custom_number;
    			 ++Item;
    	 }
     }
     
     //�˿ͳ���
     public void Remve(){
    	 if(0==Item)
    		return;
    	 else{
    		 if(front==length){
    			 front=0;
    		 }
    		 front++;
    		 --Item;
    	 }
     }
     
     //��ʾ���е����
     public void Display(){
    	 int flag=front;    //��־����
    	 for(int i=0;i<Item;++i){
    		 if(flag==length){
    			 flag=0;
    		 }
    			 System.out.print(Array[flag]+" ");
    			 ++flag;
    	 }
     }
}
