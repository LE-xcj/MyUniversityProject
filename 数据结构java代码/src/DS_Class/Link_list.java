package DS_Class;

public class Link_list {
     Link first;
     public Link_list(){
    	 first=null;
     }
     
     //�ڱ�ͷ�����µ�Link
     public void Insert(int data){
    	 Link newlink=new Link(data);
    	 newlink.next=first;
    	 first=newlink;
     }
     
     //��ʾ����ÿһ��Link������
     public void DisplayList(){
    	 Link current=first;
    	 while(first!=null){
    		 current.Display();
    		 current=current.next;
    	 }
     }
     
}
