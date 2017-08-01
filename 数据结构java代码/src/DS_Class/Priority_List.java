package DS_Class;

public class Priority_List {    //用链表实现优先队列
     Link first;
     public Priority_List() {
		first=null;
	 }
     //插入数据，有序插入（升序）
     public void Insert(int data){     
    	 Link newlink=new Link(data);
    	 Link current=first;
    	 Link previous=current;
    	 //在链表中找到第一个比要插入数大的数，并用privious记录上一个Link的位置
    	 while(current!=null&&current.data<data){
    		 previous=current;
    		 current=current.next;
    	 }
    	 //这里的if语句包含两种情况：1、链表为空；2、插入的位置在表头
    	 if(current==first){
    		 first=newlink;
    	 }
    	 //插入的位置：1、链表中；2、链表表尾
    	 else{
    		 previous.next=newlink;
    	 }
    	 newlink.next=current;
     }
     
     //删除链结点，优先队列的性质：删除关键字小的Link（也就是第一个链结点first）
     public Link Delete(){
    	 Link temp=first;
    	 first=first.next;
    	 return temp;
     }
     
     //显示链表中每一个链接点
     public void DisplayList(){
    	 Link current=first;
    	 while(current!=null){
    		 current.Display();
    		 current=current.next;
    	 }
     }
}
