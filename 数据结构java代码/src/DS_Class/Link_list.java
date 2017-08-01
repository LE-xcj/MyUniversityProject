package DS_Class;

public class Link_list {
     Link first;
     public Link_list(){
    	 first=null;
     }
     
     //在表头插入新的Link
     public void Insert(int data){
    	 Link newlink=new Link(data);
    	 newlink.next=first;
    	 first=newlink;
     }
     
     //显示链表每一个Link的数据
     public void DisplayList(){
    	 Link current=first;
    	 while(first!=null){
    		 current.Display();
    		 current=current.next;
    	 }
     }
     
}
