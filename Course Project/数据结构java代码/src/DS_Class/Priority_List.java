package DS_Class;

public class Priority_List {    //������ʵ�����ȶ���
     Link first;
     public Priority_List() {
		first=null;
	 }
     //�������ݣ�������루����
     public void Insert(int data){     
    	 Link newlink=new Link(data);
    	 Link current=first;
    	 Link previous=current;
    	 //���������ҵ���һ����Ҫ�����������������privious��¼��һ��Link��λ��
    	 while(current!=null&&current.data<data){
    		 previous=current;
    		 current=current.next;
    	 }
    	 //�����if���������������1������Ϊ�գ�2�������λ���ڱ�ͷ
    	 if(current==first){
    		 first=newlink;
    	 }
    	 //�����λ�ã�1�������У�2�������β
    	 else{
    		 previous.next=newlink;
    	 }
    	 newlink.next=current;
     }
     
     //ɾ������㣬���ȶ��е����ʣ�ɾ���ؼ���С��Link��Ҳ���ǵ�һ�������first��
     public Link Delete(){
    	 Link temp=first;
    	 first=first.next;
    	 return temp;
     }
     
     //��ʾ������ÿһ�����ӵ�
     public void DisplayList(){
    	 Link current=first;
    	 while(current!=null){
    		 current.Display();
    		 current=current.next;
    	 }
     }
}
