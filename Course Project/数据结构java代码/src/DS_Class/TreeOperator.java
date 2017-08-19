package DS_Class;

import java.util.Stack;

public class TreeOperator {
	private int Element;
	private int NumberOfSubTree;
	private int counter;
	private Tree RealRoot;
	private int floor;
	private int length;
	private Tree root;
	Tree[] array;
	
    public TreeOperator(){
    	  Element=0;
    	  NumberOfSubTree=0;
    	  counter=0;
    	  floor=0;
    	  length=0;
    	  RealRoot=null;
    	  root=null;
    }
      
    //����Ҷ�ڵ㶼����ĸ�Ķ�����
    //���¶��ϵĿ�ʼ������������������ϳ�Ϊһ���µ�����
    //�����ʣ�������û�н�Ͼͽ����������λ��һ��������󣩽������µ������ĺ���
    public void BuildTree(String str){
    	  Initial(str);
    	  floor=0;
    	  while(NumberOfSubTree!=1){
    		  counter=0;
    		  for(int i=1;i<NumberOfSubTree;i+=2){
    			  Tree Father=new Tree();
    			  //�������������Ϊһ���µ�����
    			  Father.LeftTree=array[i-1];
    			  Father.RightTree=array[i];
    			  array[counter]=Father;
    			  ++counter;     //��¼�µ������ĸ���
    		  }
    		  if(NumberOfSubTree%2!=0){
    			  array[counter++]=array[NumberOfSubTree-1];
    		  }   		    
    		  ++floor;   		//��¼������Ĳ���     		  
    		  NumberOfSubTree=counter;  	//���������ĸ���
    	  }
    	  //����������ĸ�
    	  RealRoot=array[0];
    }

    //���ַ�����ÿ���ַ����һ��ֻ��һ���ڵ����
	private void Initial(String str) {
  	  Element=str.length();
  	  array=new Tree[Element];
	  for(int out=0;out<Element;++out){
		  array[out]=new Tree(str.charAt(out));
	  }
	  //��¼����ĸ�������ĸ���
	  NumberOfSubTree=Element;
	}
	
	//��ʾ���ֲ�ͬ�Ķ�����
	public void DisplayTree(int flag){
		Stack<Tree> stack=new Stack<Tree>(); 	//��¼ÿһ��Ľڵ�
		//���ݱ�־��ʾ��ͬ�Ķ�����
		if(flag==3)
			stack.push(RealRoot);
		else
			stack.push(root);
		//����Ҫ��ӡ��߿ո�ĸ���
		int space=(int) Math.pow(2, floor);
		boolean isFloorEmpty=false;
		System.out.println("-------------------------------------------------------");
		for(int out=0;out<space;++out){
			System.out.print(" ");
		}
		//����һ��û���κνڵ�ͽ���
		while(isFloorEmpty==false){
			//����ÿһ������нڵ���ӽڵ�
			Stack<Tree> workStack=new Stack<Tree>();			
			isFloorEmpty=true;
			//��ӡ�ò�ÿ���ڵ����Ϣ
			while(stack.isEmpty()==false){	
				//����ջ����һ���ڵ㣬ʵ�ֱ�����һ�����нڵ�
				Tree temp=stack.pop();
				if(temp!=null){
					temp.DisplayTree();
					//����һ��ڵ���ӽڵ�浽workStack
					workStack.push(temp.LeftTree);
					workStack.push(temp.RightTree);
					if(temp.LeftTree!=null||temp.RightTree!=null)
						isFloorEmpty=false;
				}
				else{
					System.out.print("- ");
					workStack.push(null);
					workStack.push(null);
				}
				//���ݲ�������ӡÿһ��ÿһ���ڵ�֮��ļ��
				for(int out=0;out<space*2-2;++out)
					System.out.print(" ");				
			}
			
			System.out.println();
			space/=2;     //������һ�����Ҫ��ӡ�Ŀո����		
			for(int out=0;out<space;++out){
				System.out.print(" ");
			}
			//����stack�Ľڵ㣬��һ������ߵĽڵ�һ������ջ��
			while(workStack.isEmpty()==false){
				stack.push(workStack.pop());
			}
			
		}
		
	}
	
	//���������϶��¡����������򡱵Ķ�����
	public void Build_Another_Tree(String str){
		//��¼��ǰ��һ��Ľڵ�
		Stack<Tree> stack=new Stack<Tree>();
		length=str.length();	
		Tree current;
		root=new Tree(str.charAt(0));   //������õĸ�
		int count=1;	//��¼������Ѿ�����Ľڵ����
		floor=0;
		stack.push(root);
		while(count!=length){
			Stack<Tree> workStack=new Stack<Tree>();			
			while(stack.isEmpty()==false){
				current=stack.pop();
				Tree temp;
				//�����ӽڵ��ȿ�ʼ����
				if(count<length){
					temp =new Tree(str.charAt(count++));
					current.LeftTree=temp;
					workStack.push(temp);   //��ջ��һ��Ľڵ�
				}	
				//�ֵ����ӽڵ�
				if(count<length){
					temp=new Tree(str.charAt(count++));
					current.RightTree=temp;
					workStack.push(temp);	//��ջ��һ��Ľڵ�
				}
			}
			++floor;
			//����һ��Ľڵ��������һ��Ľڵ�
			while(workStack.isEmpty()==false)
				stack.push(workStack.pop());
		}
	}
	
}
