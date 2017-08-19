package DS_Class;

public class Node {
      int row,col,value;
      Node right,down;
      Node(){
    	  value=0;
    	  row=0;
    	  col=0;
    	  right=null;
    	  down=null;
      }
      Node(int row,int col,int value){
    	  this.row=row;
    	  this.col=col;
    	  this.value=value;
    	  right=null;
    	  down=null;
      }
}