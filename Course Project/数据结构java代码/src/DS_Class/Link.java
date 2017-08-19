package DS_Class;

public class Link {
    public int data;
    Link next;
    Link(int data){
    	this.data=data;
    	next=null;
    }
    public void Display(){
    	System.out.print(data+" ");
    }
}
