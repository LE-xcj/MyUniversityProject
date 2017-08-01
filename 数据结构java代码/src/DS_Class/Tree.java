package DS_Class;

public class Tree {
     private char value;
     Tree LeftTree;
     Tree RightTree;
     
     public Tree(){
    	 value='+';
    	 LeftTree=null;
    	 RightTree=null;
     }
     
     public Tree(char word){
    	 value=word;
    	 RightTree=null;
    	 RightTree=null;
     }
     
     public void DisplayTree(){
    	 System.out.print(value+" ");
     }
}
