package DS_Class;

public class Matrix {
      Node[] top;            	  //ĳһ�е���
      Node[] left;                //ĳһ�е���
      Node current;               //��¼��ǰ��Node��λ��
      Node previous;              //��¼currentǰһ��Node��λ��
      int row_length,col_length;          //������������������
      
      //��ʼ��
      public Matrix(int row_length,int col_length){
    	  this.row_length=row_length;
    	  this.col_length=col_length;
    	  top=new Node[col_length];
    	  left=new Node[row_length];
    	  //���������ÿһ�е�Node����ռ�
    	  for(int out=0;out<row_length;++out){
    		  left[out]=new Node();
    	  }
    	  //���������ÿһ�е�Node����ռ�
    	  for(int out=0;out<col_length;++out){
    		  top[out]=new Node();
    	  }

    	  current=null;
    	  previous=null;
      }
      
      //��������
      public void Insert(int row,int col,int value){
    	  Node newNode=new Node(row,col,value);
    	  //����һ��û��Node��ʱ�򣬾�ֱ�Ӳ��룬����newNode��rightָ��left
    	  if(left[row-1].right==null){
    		  left[row-1].right=newNode;
    		  newNode.right=left[row-1];           //���һ��NodeҪָ��left����
    	  }
    	  //����һ�в�Ϊ�գ����������
    	  //1���ڵ�һ�в���
    	  //2�����м����
    	  //3�������һ�в���
    	  else{
    		  //current�����еĵ�һ��Node��ʼѰ��
    		  current=left[row-1].right;
    		  //previous��¼currentǰһ����Node
    		  previous=left[row-1];
    		  //Ѱ���ض�λ��
    		  //current!=left[row-1]���������3����������õ�
    		  //col>current.col���������1��2������õ�
    		  while(current!=left[row-1]&&col>current.col){
    			  previous=current;
    			  current=current.right;
    		  }
    		  //���ж����λ���Ƿ��С�����
    		  if(current.col==col){
    			  System.out.println("��Ҫ�����λ���Ѿ������ݣ����Բ��ܲ���ֻ���޸ģ�");
    			  return;
    		  }
    		  //û�����Ͳ���
    		  else{
        		  newNode.right=previous.right;
        		  previous.right=newNode;
    		  }
    	  }
    	  
    	  //����Node��down���и�ֵ
    	  //����һ��Ϊ�յ�ʱ��top����ֱ��ָ��newNode
    	  if(top[col-1].down==null){
    		  top[col-1].down=newNode;
    		  newNode.down=top[col-1];
    	  }
    	  //����һ�в�Ϊ�գ�Ҳ�Ƿ��������
    	  //1���ڵ�һ�в�����
    	  //2�����м����
    	  //3�������һ�в���
    	  else{
    		  current=top[col-1].down;
    		  previous=top[col-1];
    		  //�������whileѭ��ͬ��
    		  while(current!=top[col-1]&&row>current.row){
    			  previous=current;
    			  current=current.down;
    		  }
    		  if(current.row==row){
    			  System.out.println("��Ҫ�����λ���Ѿ������ݣ����Բ��ܲ���ֻ���޸ģ�");
    			  return;
    		  }
    		  else{
        		  newNode.down=previous.down;
        		  previous.down=newNode;
    		  }
    	  }
      }
      
      //ɾ������
      public int Delete(int row,int col){
		  Node temp;
    	  //�ж���������ָ�������Ƿ���Ԫ��
    	  if(left[row-1].right!=null&&top[col-1].down!=null){
    		  current=left[row-1].right;
    		  previous=left[row-1];
        	  //�Ӿ�����Ѱ��
    		  while(current!=left[row-1]&&current.col!=col){
    			  previous=current;
    			  current=current.right;
    		  }	
    		  //��current==left[row-1]�����ھ������Ҳ���Ҫɾ������
    		  if(current==left[row-1]){
    			  return 0;
    		  }
    		  //�ҵ��������Node��Ȼ���޸ġ������Node�ġ�ǰһ��Node����Rightָ��ķ���
    		  else{
    			  temp=current;
    			  previous.right=current.right;
    		  }
    		  //Ȼ���޸����Node��ǰһ��Node��Downָ��ķ���
    		  current=top[col-1].down;
    		  previous=top[col-1];
    		  //������Ҫ��Ѱ�����Nodeǰһ��Node����һ�е�λ��
    		  while(current.row!=row){
    			  previous=current;
    			  current=current.down;
    		  }
    		  //�޸�Down��ֵ
    		  previous.down=current.down;
    		  return temp.value;
    	  }
    	  //����������һ�л�����һ��û��Node
    	  else{  
    		  return 0;
    	  }
      }
      
      //��ʾ�����е�����
      public void Display(){
    	  //��ʾ������
    	  for(int out=0;out<row_length;++out){
    		  //��ÿһ�еĵ�һ������ʼ
    		  current=left[out].right;
    		  //���һ����һ��û��Node����һ����ʾ��Ϊ0
    		  if(current==null){
    			  for(int in=0;in<col_length;++in){
    				  System.out.print("0 ");
    			  }
    			  System.out.println();
    		  }
    		  //��һ��Node������������һ��Node
    		  else{
    			  for(int in=0;in<col_length;++in){
    				  //�������������λ��û��Node
    				  if(current.col!=in+1){
    					  System.out.print("0 ");
    				  }
    				  //��Node
    				  else{
    					  System.out.print(current.value+" ");
        				  current=current.right;
    				  }
    			  }
    			  System.out.println();
    		  }
    	  }
      }
      
      //��ʾĳһλ�õ�Node����һ��Node����ֵ��������һ��Node����ֵ
      public void Display_Right_Down(int row,int col){
    	  //��ʾ��ǰ������һ��Ҫ��Node
    	  if(left[row-1].right!=null&&top[col-1]!=null){
    		  current=left[row-1].right;
    		  //����һ�еĵ�һ����ʼ
    		  while(current!=left[row-1]&&current.col!=col){
    			  current=current.right;
    		  }
    		  if(current==left[row-1]){
    			  System.out.println("��Ҫ�ҵ�λ��û������");
    		  }
    		  if(current.col==col){
    			  System.out.println("�ұߵ����ǣ�"+current.right.value);
    			  System.out.println("�±ߵ����ǣ�"+current.down.value);
    		  }
    	  }
      }
      
      //�ı�ĳһλ�õ�Node��ֵ
      public void Change_Value(int row,int col,int value){
    	  //ָ��λ�õ��������Ƿ���Node
    	  if(left[row-1].right!=null&&top[col-1].down!=null){
    		  current=left[row-1].right;
    		  //Ѱ��
    		  while(current!=left[row-1]&&current.col!=col){
    			  current=current.right;
    		  }
    		  //û��
    		  if(current==left[row-1]){
    			  System.out.println("��Ҫ�޸ĵ�λ��û�����ݣ��޷��޸ģ�");
    		  }
    		  //�о��޸�
    		  else{
    			  int temp=current.value;
    			  current.value=value;
    			  System.out.println("�޸ĳɹ���");
    			  System.out.println("ԭ���������ǣ�"+temp);
    		  }
    	  }
    	  //û��Node
    	  else{
    		  System.out.println("��Ҫ�޸ĵ�λ��û�����ݣ��޷��޸ģ�");
    	  }
      }
      
      //���������
      public void Multiply(int time){
    	  //������������
    	  for(int out=1;out<=row_length;++out){
    		  for(int in=1;in<=col_length;++in){
    			  //������λ����Node�ͽ�������
    			  if(left[out-1].right.col==in){
    				  left[out-1].right.value*=time;
    			  }
    		  }
    	  }
      }
      
      //����֮������
      public Matrix Matrix_Plus(Matrix first,Matrix second){
    	      int temp =-1;
    	      //����һ����С�봫��ľ���һ������ʱ����newMatrix
    		  Matrix newMatrix=new Matrix(first.row_length,first.col_length);
    		  //�Ӿ����ÿһ�еĵ�һ����ʼ
    		  for(int out=1;out<=first.row_length;++out){
				  first.current=first.left[out-1].right;
				  second.current=second.left[out-1].right;
				  //�ж������������Ƿ����㡰������һ��������һ�в�Ϊ�ա�����ֻ�Ǵ��Ե��ж�
				  //��Ϊ��Ȼ���㡰��һ�в�Ϊ�ա���������������һ�����е��ж���Ϊ�գ����Ի�Ҫ���������
				  if(first.current!=null||second.current!=null){
					  //��ʼ������������ġ��С�
	    			  for(int in=1;in<=first.col_length;++in){
	    				  //����ӷ��ְ��������
	    				  
	    				  //A������������һ�ж���Ϊ��
	    				  //1����һ��������һλ�����������ҵڶ���������һλ������
	    				  //2����һ��������һλ�����������ǵڶ���������һλ��û����
	    				  //3����һ��������һλ��û���������ǵڶ���������һλ������
	    				  //4����һ����������һλ��û���������ҵڶ���������һλ��û����
	    				  
	    				  //B����һ��������һ�в�Ϊ�գ��ڶ���������һ��Ϊ��
	    				  //5����һ��������һλ��û���������ڶ���������һλ������
	    				  //6����һ��������һλ��û���������ҵڶ���������һλ��û����
	    				  
	    				  //C����һ��������һ��Ϊ�գ��ڶ���������һ�в�Ϊ��
	    				  //7���ڶ���������һλ������
	    				  //8���ڶ���������һλ��û����
	    				  
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
				  //������������һ�ж�Ϊ��
				  else{
					  continue;
				  }
    		  }//end for(out)
    		  return newMatrix;
      }
      
      //�������
      public Matrix Matrix_Multiply(Matrix first,Matrix second){
    	  int temp;
    	  Matrix newMatrix=new Matrix(first.row_length,second.col_length);
    	  
		  //�����Ч��ֻ�е�����������һλ�������ſ������
		  
		  //A����һ��������һ�в�Ϊ���ҵڶ���������һ�в�Ϊ��
		  //1����һ��������һλ�����������ҵڶ���������һλ����������Ч��
		  //2����һ��������һλ�����������ǵڶ���������һλ��û��������Ч��
		  //3����һ��������һλ��û���������ǵڶ���������һλ����������Ч��
		  //4����һ����������һλ��û���������ҵڶ���������һλ��û��������Ч��
    	  //�������������Ч��
    	  //�ӵ�һ�������һ�п�ʼ
    	  for(int out=1;out<=first.row_length;++out){
    		  first.current=first.left[out-1].right;
    		  //���п�ʼ
    		  //����һ��������һ�в�Ϊ��
    		  if(first.current!=null){
    			  //����һ�е��п�ʼ
        		  for(int mid=1;mid<=second.col_length;++mid){
        			  temp=0;
        			  //��first��current���¶�λ����һ�еĵ�һ��Node
            		  first.current=first.left[out-1].right;
            		  second.current=second.top[mid-1].down;
            		  //���ڶ�������һ�в�Ϊ��
            		  if(second.current!=null){
                		  for(int in=1;in<=first.col_length;++in){
                			  //�������������λ�ö��С�����
                			  if(first.current.col==second.current.row){
                				  temp+=first.current.value*second.current.value;
                				  if(first.current!=first.left[0]&&second.current!=second.top[0]){
                        			  first.current=first.current.right;
                        			  second.current=second.current.down;
                				  }else{          
                					  //�����else��Ϊ�ų����������current�ص�left��top
                					  continue;     //��һ��
                				  }
                			  }else{
                				  //������first��currentǰ������second��currentǰ��
                				  if(first.current.col>second.current.row){
                					 if(second.current!=second.top[0]){
                						 second.current=second.current.down;
                					 }else{    
                						 //�ų��ڶ��������current��top
                						 continue;     //��һ��
                					 }
                				  }else{
                					  if(first.current!=first.left[0]){
                						  first.current=first.current.right;
                					  }else{
                						  //�ų���һ�������current��left
                						  continue;     //��һ��
                					  }
                				  }
                			  }
                		  }//end for(in)
                		  newMatrix.Insert(out, mid, temp);
            		  }else{            			  
            			  continue;  //��һ�е���һ��
            		  }
        		  }//end for(mid)
    		  }else{
    			  continue;     //��һ��
    		  }
    	  }//end for(out)
    	  
    	  return newMatrix;
      }
      
      //�жϲ�������ݵ�λ���Ƿ���Ч
      public boolean Can_Insert(int row,int col){
    	  if(row_length>=row&&col_length>=col){
    		  return true;
    	  }
    	  else{
    		  return false;
    	  }
      }
      
      //�ж���������Ĵ�С�Ƿ���ͬ�����������Ҫ��
      public static boolean Same_size(Matrix first,Matrix second){
    	  if(first.row_length==second.row_length&&first.row_length==second.col_length){
    		  return true;
    	  }
    	  else{
    	  return false;
    	  }
      }
      
      //�жϵ�һ������������Ƿ���ڶ������������һ�������������Ҫ��
      public static boolean Can_Matrix_multiply(Matrix first,Matrix second){
    	  if(first.col_length==second.row_length){
    		  return true;
    	  }
    	  else{
    		  return false;
    	  }
      }
            
}

