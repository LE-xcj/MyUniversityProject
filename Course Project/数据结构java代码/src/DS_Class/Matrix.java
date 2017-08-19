package DS_Class;

public class Matrix {
      Node[] top;            	  //某一列的链
      Node[] left;                //某一行的链
      Node current;               //记录当前的Node的位置
      Node previous;              //记录current前一个Node的位置
      int row_length,col_length;          //这个矩阵的行数与列数
      
      //初始化
      public Matrix(int row_length,int col_length){
    	  this.row_length=row_length;
    	  this.col_length=col_length;
    	  top=new Node[col_length];
    	  left=new Node[row_length];
    	  //对这个矩阵每一行的Node分配空间
    	  for(int out=0;out<row_length;++out){
    		  left[out]=new Node();
    	  }
    	  //对这个矩阵每一列的Node分配空间
    	  for(int out=0;out<col_length;++out){
    		  top[out]=new Node();
    	  }

    	  current=null;
    	  previous=null;
      }
      
      //插入数据
      public void Insert(int row,int col,int value){
    	  Node newNode=new Node(row,col,value);
    	  //当这一行没有Node的时候，就直接插入，并将newNode的right指向left
    	  if(left[row-1].right==null){
    		  left[row-1].right=newNode;
    		  newNode.right=left[row-1];           //最后一个Node要指向left【】
    	  }
    	  //当这一行不为空，分三种情况
    	  //1、在第一列插入
    	  //2、在中间插入
    	  //3、在最后一列插入
    	  else{
    		  //current从这行的第一个Node开始寻找
    		  current=left[row-1].right;
    		  //previous记录current前一个的Node
    		  previous=left[row-1];
    		  //寻找特定位置
    		  //current!=left[row-1]这个条件在3那种情况会用到
    		  //col>current.col这个条件在1、2情况会用到
    		  while(current!=left[row-1]&&col>current.col){
    			  previous=current;
    			  current=current.right;
    		  }
    		  //先判断这个位置是否有“数”
    		  if(current.col==col){
    			  System.out.println("你要插入的位置已经有数据，所以不能插入只能修改！");
    			  return;
    		  }
    		  //没有数就插入
    		  else{
        		  newNode.right=previous.right;
        		  previous.right=newNode;
    		  }
    	  }
    	  
    	  //对新Node的down进行赋值
    	  //当这一列为空的时候，top【】直接指向newNode
    	  if(top[col-1].down==null){
    		  top[col-1].down=newNode;
    		  newNode.down=top[col-1];
    	  }
    	  //当这一列不为空，也是分三种情况
    	  //1、在第一行插入语
    	  //2、在中间插入
    	  //3、在最后一行插入
    	  else{
    		  current=top[col-1].down;
    		  previous=top[col-1];
    		  //与上面的while循环同理
    		  while(current!=top[col-1]&&row>current.row){
    			  previous=current;
    			  current=current.down;
    		  }
    		  if(current.row==row){
    			  System.out.println("你要插入的位置已经有数据，所以不能插入只能修改！");
    			  return;
    		  }
    		  else{
        		  newNode.down=previous.down;
        		  previous.down=newNode;
    		  }
    	  }
      }
      
      //删除数据
      public int Delete(int row,int col){
		  Node temp;
    	  //判断这个矩阵的指定行列是否有元素
    	  if(left[row-1].right!=null&&top[col-1].down!=null){
    		  current=left[row-1].right;
    		  previous=left[row-1];
        	  //从矩阵中寻找
    		  while(current!=left[row-1]&&current.col!=col){
    			  previous=current;
    			  current=current.right;
    		  }	
    		  //用current==left[row-1]代表在矩阵中找不到要删除的数
    		  if(current==left[row-1]){
    			  return 0;
    		  }
    		  //找到这个数的Node，然后修改“这个”Node的“前一个Node”的Right指向的方向
    		  else{
    			  temp=current;
    			  previous.right=current.right;
    		  }
    		  //然后修改这个Node的前一个Node的Down指向的方向
    		  current=top[col-1].down;
    		  previous=top[col-1];
    		  //这里主要是寻找这个Node前一个Node在这一列的位置
    		  while(current.row!=row){
    			  previous=current;
    			  current=current.down;
    		  }
    		  //修改Down的值
    		  previous.down=current.down;
    		  return temp.value;
    	  }
    	  //这个矩阵的这一行或者这一列没有Node
    	  else{  
    		  return 0;
    	  }
      }
      
      //显示矩阵中的数据
      public void Display(){
    	  //显示多少行
    	  for(int out=0;out<row_length;++out){
    		  //从每一行的第一个二开始
    		  current=left[out].right;
    		  //情况一：这一行没有Node，这一行显示都为0
    		  if(current==null){
    			  for(int in=0;in<col_length;++in){
    				  System.out.print("0 ");
    			  }
    			  System.out.println();
    		  }
    		  //这一行Node，而且至少有一个Node
    		  else{
    			  for(int in=0;in<col_length;++in){
    				  //情况二：如果这个位置没有Node
    				  if(current.col!=in+1){
    					  System.out.print("0 ");
    				  }
    				  //有Node
    				  else{
    					  System.out.print(current.value+" ");
        				  current=current.right;
    				  }
    			  }
    			  System.out.println();
    		  }
    	  }
      }
      
      //显示某一位置的Node的右一个Node的数值，和下面一个Node的数值
      public void Display_Right_Down(int row,int col){
    	  //显示的前提是这一行要有Node
    	  if(left[row-1].right!=null&&top[col-1]!=null){
    		  current=left[row-1].right;
    		  //从这一行的第一个开始
    		  while(current!=left[row-1]&&current.col!=col){
    			  current=current.right;
    		  }
    		  if(current==left[row-1]){
    			  System.out.println("你要找的位置没有数据");
    		  }
    		  if(current.col==col){
    			  System.out.println("右边的数是："+current.right.value);
    			  System.out.println("下边的数是："+current.down.value);
    		  }
    	  }
      }
      
      //改变某一位置的Node的值
      public void Change_Value(int row,int col,int value){
    	  //指定位置的行与列是否有Node
    	  if(left[row-1].right!=null&&top[col-1].down!=null){
    		  current=left[row-1].right;
    		  //寻找
    		  while(current!=left[row-1]&&current.col!=col){
    			  current=current.right;
    		  }
    		  //没有
    		  if(current==left[row-1]){
    			  System.out.println("你要修改的位置没有数据，无法修改！");
    		  }
    		  //有就修改
    		  else{
    			  int temp=current.value;
    			  current.value=value;
    			  System.out.println("修改成功！");
    			  System.out.println("原来的数据是："+temp);
    		  }
    	  }
    	  //没有Node
    	  else{
    		  System.out.println("你要修改的位置没有数据，无法修改！");
    	  }
      }
      
      //矩阵的数乘
      public void Multiply(int time){
    	  //遍历整个矩阵
    	  for(int out=1;out<=row_length;++out){
    		  for(int in=1;in<=col_length;++in){
    			  //如果这个位置有Node就进行数乘
    			  if(left[out-1].right.col==in){
    				  left[out-1].right.value*=time;
    			  }
    		  }
    	  }
      }
      
      //矩阵之间的相加
      public Matrix Matrix_Plus(Matrix first,Matrix second){
    	      int temp =-1;
    	      //定义一个大小与传入的矩阵一样的临时矩阵newMatrix
    		  Matrix newMatrix=new Matrix(first.row_length,first.col_length);
    		  //从矩阵的每一行的第一个开始
    		  for(int out=1;out<=first.row_length;++out){
				  first.current=first.left[out-1].right;
				  second.current=second.left[out-1].right;
				  //判断这两个矩阵是否满足“至少有一个矩阵这一行不为空”，这只是粗略的判断
				  //因为虽然满足“这一行不为空”，但并不代表这一行所有的列都不为空，所以还要继续分情况
				  if(first.current!=null||second.current!=null){
					  //开始遍历两个矩阵的“列”
	    			  for(int in=1;in<=first.col_length;++in){
	    				  //这里加法分八种情况：
	    				  
	    				  //A当两个矩阵这一行都不为空
	    				  //1、第一个矩阵这一位置有数，而且第二个矩阵这一位置有数
	    				  //2、第一个矩阵这一位置有数，但是第二个矩阵这一位置没有数
	    				  //3、第一个矩阵这一位置没有数，但是第二个矩阵这一位置有数
	    				  //4、第一个矩阵这这一位置没有数，而且第二个矩阵这一位置没有数
	    				  
	    				  //B当第一个矩阵这一行不为空，第二个矩阵这一行为空
	    				  //5、第一个矩阵这一位置没有数，而第二个矩阵这一位置有数
	    				  //6、第一个矩阵这一位置没有数，而且第二个矩阵这一位置没有数
	    				  
	    				  //C当第一个矩阵这一行为空，第二个矩阵这一行不为空
	    				  //7、第二个矩阵这一位置有数
	    				  //8、第二个矩阵这一位置没有数
	    				  
	    				  //A
	    				  if(first.current!=null&&second.current!=null){
	    					  //1
	    					  if(first.current.col==in&&second.current.col==in){
	    						  temp=first.current.value+second.current.value;
	    						  first.current=first.current.right;
	    						  second.current=second.current.right;
	    					  }
	    					  else{
	    						  //2
	    						  if(first.current.col==in){
	    							  temp=first.current.value;
	    							  first.current=first.current.right;
	    						  }
	    						  else{
	    							  //3
	    							  if(second.current.col==in){
	    								  temp=second.current.value;
	    								  second.current=second.current.right;
	    							  }	
	    							  //4
	    							  else{
	    								  continue;
	    							  }
	    						  }
	    					  }
	    				  }else{
	    					  //B
	    					  if(first.current!=null){
	    						  //5
	    						  if(first.current.col==in){
	    							  temp=first.current.value;
	    							  first.current=first.current.right;
	    						  }
	    						  //6
	    						  else{
	    							  continue;
	    						  }
	    					  }//end B
	    					  
	    					  //C
	    					  else{
	    						  //7
	    						  if(second.current.col==in){
	    							  temp=second.current.value;
	    							  second.current=second.current.right;
	    						  }
	    						  //8
	    						  else{
	    							  continue;
	    						  }
	    					  }//end C
	    					  
	    				  }//end B or C
						  newMatrix.Insert(out, in, temp);
	    			  }//end for(in)
				  }
				  //这两个矩阵这一行都为空
				  else{
					  continue;
				  }
    		  }//end for(out)
    		  return newMatrix;
      }
      
      //矩阵相乘
      public Matrix Matrix_Multiply(Matrix first,Matrix second){
    	  int temp;
    	  Matrix newMatrix=new Matrix(first.row_length,second.col_length);
    	  
		  //相乘有效的只有当两个矩阵这一位置有数才可以相乘
		  
		  //A当第一个矩阵这一行不为空且第二个矩阵这一列不为空
		  //1、第一个矩阵这一位置有数，而且第二个矩阵这一位置有数（有效）
		  //2、第一个矩阵这一位置有数，但是第二个矩阵这一位置没有数（无效）
		  //3、第一个矩阵这一位置没有数，但是第二个矩阵这一位置有数（无效）
		  //4、第一个矩阵这这一位置没有数，而且第二个矩阵这一位置没有数（无效）
    	  //其余情况都是无效的
    	  //从第一个矩阵第一行开始
    	  for(int out=1;out<=first.row_length;++out){
    		  first.current=first.left[out-1].right;
    		  //从行开始
    		  //当第一个矩阵这一行不为空
    		  if(first.current!=null){
    			  //从这一行的列开始
        		  for(int mid=1;mid<=second.col_length;++mid){
        			  temp=0;
        			  //将first的current重新定位到这一行的第一个Node
            		  first.current=first.left[out-1].right;
            		  second.current=second.top[mid-1].down;
            		  //当第二个矩阵一列不为空
            		  if(second.current!=null){
                		  for(int in=1;in<=first.col_length;++in){
                			  //当两个矩阵这个位置都有“数”
                			  if(first.current.col==second.current.row){
                				  temp+=first.current.value*second.current.value;
                				  if(first.current!=first.left[0]&&second.current!=second.top[0]){
                        			  first.current=first.current.right;
                        			  second.current=second.current.down;
                				  }else{          
                					  //这里的else是为排除两个矩阵的current回到left与top
                					  continue;     //下一列
                				  }
                			  }else{
                				  //处理是first的current前进还是second的current前进
                				  if(first.current.col>second.current.row){
                					 if(second.current!=second.top[0]){
                						 second.current=second.current.down;
                					 }else{    
                						 //排除第二个矩阵的current是top
                						 continue;     //下一列
                					 }
                				  }else{
                					  if(first.current!=first.left[0]){
                						  first.current=first.current.right;
                					  }else{
                						  //排除第一个矩阵的current是left
                						  continue;     //下一列
                					  }
                				  }
                			  }
                		  }//end for(in)
                		  newMatrix.Insert(out, mid, temp);
            		  }else{            			  
            			  continue;  //这一行的下一列
            		  }
        		  }//end for(mid)
    		  }else{
    			  continue;     //下一行
    		  }
    	  }//end for(out)
    	  
    	  return newMatrix;
      }
      
      //判断插入的数据的位置是否有效
      public boolean Can_Insert(int row,int col){
    	  if(row_length>=row&&col_length>=col){
    		  return true;
    	  }
    	  else{
    		  return false;
    	  }
      }
      
      //判断两个矩阵的大小是否相同（矩阵相加需要）
      public static boolean Same_size(Matrix first,Matrix second){
    	  if(first.row_length==second.row_length&&first.row_length==second.col_length){
    		  return true;
    	  }
    	  else{
    	  return false;
    	  }
      }
      
      //判断第一个矩阵的列数是否与第二个矩阵的行数一样（矩阵相乘需要）
      public static boolean Can_Matrix_multiply(Matrix first,Matrix second){
    	  if(first.col_length==second.row_length){
    		  return true;
    	  }
    	  else{
    		  return false;
    	  }
      }
            
}

