package DS_Class;

public class Control {
     final int queue_number=3;    //�����ж���������
     Queue[] queue_array;        //��������
     public Control(){       
    	 //�����������Ĵ�С����Ϊÿ����������ڴ�ռ�
    	 queue_array=new Queue[queue_number];
    	 for(int i=0;i<queue_number;++i){
    		 queue_array[i]=new Queue();
    	 }
     }
     
            //��ʾ��ǰ���е����
     public void Show_queue(){            
    	 for(int i=0;i<queue_number;++i){    //�ӵ�һ�����п�ʼ���������һ��
    		 System.out.print("cherker "+(i+1)+" : ");
    		 if(queue_array[i].Item!=0){
        		 queue_array[i].Display();
    		 }
    		 System.out.println();
    	 }
     }
             
               //ʱ������
     public void Timer_queue(){    
    	     //���ݲ������������ѡ�����������Ƴ�һ���˿ͣ������������ڷ�Χ�Ͳ����κβ���
    	 int time=(int)(Math.random()*(queue_number+3));
    	 for(int i=0;i<queue_number;++i){
    		 if(time==i){
    			 if(queue_array[i].Item!=0){
    				 System.out.println("checker "+(i+1)+" :"+"�뿪��һ����");
    				 System.out.println();
    			 }
    			 queue_array[i].Remve();
    		 }
    	 }
    	 if(time>queue_number){
    		 System.out.println("û�����뿪��");
    	 }
    	 if(time==queue_number){
    		 System.out.println("����һ���˿ͣ�");
    		 Enter_queue();
    	 }
    	// this.Show_queue();
     }
     
          //�˿����
     public void Enter_queue(){
    	 int min=0;
    	 int custom_number=(int)(Math.random()*(100));  //�����������һ���˿�
    	 //������һ����������ѡ������һ���ӣ�Ĭ�ϴӵ�һ���ӿ�ʼ����
    	 for(int i=1;i<queue_number;++i){
    		 if((queue_array[i-1].Item)>(queue_array[i].Item)){
    			 min=i;
    		 }
    	 }
    	 queue_array[min].Insert(custom_number);
     }
}
