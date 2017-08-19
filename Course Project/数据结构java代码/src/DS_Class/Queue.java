package DS_Class;

public class Queue {
	 final int length=2;
     int front;    //队头
     int rear;     //队尾
     int Item;     //队里的人数
     int[] Array;
     
       //初始化
     public Queue(){
    	 front=0;
    	 rear=-1;
    	 Item=0;
    	 Array=new int[length];
     }
     
                //插入
     public void Insert(int Custom_number){
    	   //插入之前先判断队列的人数是否已经满了
    	 if(Item==length){
    		 System.out.println("Sorry!This queue is full!");
    	 }
    	 else{
    		 //循环队列
    		 if(length-1==rear){
    			 rear=-1;
    		 }
    			 Array[++rear]=Custom_number;
    			 ++Item;
    	 }
     }
     
     //顾客出队
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
     
     //显示队列的情况
     public void Display(){
    	 int flag=front;    //标志变量
    	 for(int i=0;i<Item;++i){
    		 if(flag==length){
    			 flag=0;
    		 }
    			 System.out.print(Array[flag]+" ");
    			 ++flag;
    	 }
     }
}
