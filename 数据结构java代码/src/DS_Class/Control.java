package DS_Class;

public class Control {
     final int queue_number=3;    //设置有多少条队列
     Queue[] queue_array;        //对象数组
     public Control(){       
    	 //定义对象数组的大小，并为每个对象分配内存空间
    	 queue_array=new Queue[queue_number];
    	 for(int i=0;i<queue_number;++i){
    		 queue_array[i]=new Queue();
    	 }
     }
     
            //显示当前队列的情况
     public void Show_queue(){            
    	 for(int i=0;i<queue_number;++i){    //从第一条队列开始遍历到最后一条
    		 System.out.print("cherker "+(i+1)+" : ");
    		 if(queue_array[i].Item!=0){
        		 queue_array[i].Display();
    		 }
    		 System.out.println();
    	 }
     }
             
               //时间流逝
     public void Timer_queue(){    
    	     //根据产生的随机数来选择在哪条队移除一个顾客，如果随机数不在范围就不做任何操作
    	 int time=(int)(Math.random()*(queue_number+3));
    	 for(int i=0;i<queue_number;++i){
    		 if(time==i){
    			 if(queue_array[i].Item!=0){
    				 System.out.println("checker "+(i+1)+" :"+"离开了一个人");
    				 System.out.println();
    			 }
    			 queue_array[i].Remve();
    		 }
    	 }
    	 if(time>queue_number){
    		 System.out.println("没有人离开！");
    	 }
    	 if(time==queue_number){
    		 System.out.println("来了一个顾客！");
    		 Enter_queue();
    	 }
    	// this.Show_queue();
     }
     
          //顾客入队
     public void Enter_queue(){
    	 int min=0;
    	 int custom_number=(int)(Math.random()*(100));  //用随机数代表一个顾客
    	 //根据哪一条队人少来选择入哪一条队，默认从第一条队开始检索
    	 for(int i=1;i<queue_number;++i){
    		 if((queue_array[i-1].Item)>(queue_array[i].Item)){
    			 min=i;
    		 }
    	 }
    	 queue_array[min].Insert(custom_number);
     }
}
