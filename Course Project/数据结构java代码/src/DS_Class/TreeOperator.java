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
      
    //建立叶节点都是字母的二叉树
    //自下而上的开始搭建这棵树、两棵子树结合成为一棵新的子树
    //如果有剩余的子树没有结合就将这棵子树（位置一定是在最后）紧挨着新的子树的后面
    public void BuildTree(String str){
    	  Initial(str);
    	  floor=0;
    	  while(NumberOfSubTree!=1){
    		  counter=0;
    		  for(int i=1;i<NumberOfSubTree;i+=2){
    			  Tree Father=new Tree();
    			  //结合两棵子树成为一棵新的子树
    			  Father.LeftTree=array[i-1];
    			  Father.RightTree=array[i];
    			  array[counter]=Father;
    			  ++counter;     //记录新的子树的个数
    		  }
    		  if(NumberOfSubTree%2!=0){
    			  array[counter++]=array[NumberOfSubTree-1];
    		  }   		    
    		  ++floor;   		//记录这棵树的层数     		  
    		  NumberOfSubTree=counter;  	//更新子树的个数
    	  }
    	  //定义这棵树的根
    	  RealRoot=array[0];
    }

    //将字符串的每个字符变成一颗只有一个节点的树
	private void Initial(String str) {
  	  Element=str.length();
  	  array=new Tree[Element];
	  for(int out=0;out<Element;++out){
		  array[out]=new Tree(str.charAt(out));
	  }
	  //记录有字母的子树的个数
	  NumberOfSubTree=Element;
	}
	
	//显示两种不同的二叉树
	public void DisplayTree(int flag){
		Stack<Tree> stack=new Stack<Tree>(); 	//记录每一层的节点
		//根据标志显示不同的二叉树
		if(flag==3)
			stack.push(RealRoot);
		else
			stack.push(root);
		//计算要打印左边空格的个数
		int space=(int) Math.pow(2, floor);
		boolean isFloorEmpty=false;
		System.out.println("-------------------------------------------------------");
		for(int out=0;out<space;++out){
			System.out.print(" ");
		}
		//当这一层没有任何节点就结束
		while(isFloorEmpty==false){
			//储存每一层的所有节点的子节点
			Stack<Tree> workStack=new Stack<Tree>();			
			isFloorEmpty=true;
			//打印该层每个节点的信息
			while(stack.isEmpty()==false){	
				//弹出栈顶的一个节点，实现遍历这一层所有节点
				Tree temp=stack.pop();
				if(temp!=null){
					temp.DisplayTree();
					//将这一层节点的子节点存到workStack
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
				//根据层数，打印每一层每一个节点之间的间隔
				for(int out=0;out<space*2-2;++out)
					System.out.print(" ");				
			}
			
			System.out.println();
			space/=2;     //更新下一层左边要打印的空格个数		
			for(int out=0;out<space;++out){
				System.out.print(" ");
			}
			//更新stack的节点，下一层最左边的节点一定是在栈顶
			while(workStack.isEmpty()==false){
				stack.push(workStack.pop());
			}
			
		}
		
	}
	
	//建立“自上而下、从左到右有序”的二叉树
	public void Build_Another_Tree(String str){
		//记录当前这一层的节点
		Stack<Tree> stack=new Stack<Tree>();
		length=str.length();	
		Tree current;
		root=new Tree(str.charAt(0));   //定义这棵的根
		int count=1;	//记录这棵树已经插入的节点个数
		floor=0;
		stack.push(root);
		while(count!=length){
			Stack<Tree> workStack=new Stack<Tree>();			
			while(stack.isEmpty()==false){
				current=stack.pop();
				Tree temp;
				//从左子节点先开始插入
				if(count<length){
					temp =new Tree(str.charAt(count++));
					current.LeftTree=temp;
					workStack.push(temp);   //入栈下一层的节点
				}	
				//轮到右子节点
				if(count<length){
					temp=new Tree(str.charAt(count++));
					current.RightTree=temp;
					workStack.push(temp);	//入栈下一层的节点
				}
			}
			++floor;
			//将这一层的节点更换到下一层的节点
			while(workStack.isEmpty()==false)
				stack.push(workStack.pop());
		}
	}
	
}
