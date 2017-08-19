package DS_java_code;

import DS_Class.Link_list;

public class List_container_text {
     public static void main(String args[]){
    	 final long length=1000;
    	 Link_list list=new Link_list();
    		 for(int i=1;;++i){
    			 list.Insert(i);
    			 if(i%length==0){
    					 System.out.println("现在有"+i+"个数据");
    			 }
    		 }
     }
}
