package DS_Class;

public class Superior_Sort {
    private int[] Shell_array;
    private int[] Quick_array;
    private int length;
    public Superior_Sort(int length){
    	this.length=length;
    	Shell_array=new int[length];
    	Quick_array=new int[length];
    }
    
    //�����鸳ֵ
    public void Initial(){
    	int value;
    	for(int i=0;i<length;++i){
    		value=(int)(Math.random()*length);
    		Shell_array[i]=value;
    		Quick_array[i]=value;
    	}
    }
    
    //ϣ������
    public void Shell_Sort(){
    	//int count=1;
    	int gap=1;
    	int out,mid,in,temp;
    	//Ѱ�����ļ��
    	while(gap<=length/3){
    		gap=gap*3+1;
    	}
    	//ֱ���������0��ʾ�������Ѿ��ź��� 
//    	System.out.print("����δ��ʼ��������ݷֲ���");
//    	Display();
    	while(gap>0){
    		//�Ӽ���Ĵ�ʼ��������д��������
    		//��ʼ����С�ڵ�ǰ�������
			//System.out.println("���Ϊ��"+gap);
    		for(out=0;out<gap;++out){
    			//��ʼ��gap�ļ����ʼ��������Χ���������
    			for(mid=out;mid<length;mid+=gap){
    				temp=Shell_array[mid];
    				in=mid;
    				//һ����Ҫ�ж����in�Ƿ�����Ч�ķ�Χ�ڣ�Ҳ����in-gap���ڵ���0
    				while(in>(gap-1)&&Shell_array[in-gap]>temp){
    					Shell_array[in]=Shell_array[in-gap];
    					in-=gap;
    				}
    				Shell_array[in]=temp;
    			}
//    			System.out.print("ϣ�������"+count+"������");
//    			++count;
//    			Display();    			
    		}
    		//���¼������ֵ
			gap=(gap-1)/3;
    	}
    }
    
    //������������
    public void Quick_Sort_Entry(){
    	Quick_Sort(0,length-1);
    }
    
    //��������������Ŀ���������ǡ�������ȡ��ֵ����Ŀ�������
    private void Quick_Sort(int lower,int upper) {
    	//�����Χ�ж��ٸ���
		int size=upper-lower+1;
		//������ݸ�������3����ô��ֱ�ӽ��м򵥵�����
		if(size<=3){
			Normal_Sort(lower,upper);
		}
		else{
			//��¼��ֵ
			int key=Middle_Value_of_Three(lower, upper);
			//��¼����֮����ֵ��λ��
			int Middle_Index=Partition(lower, upper, key);
			//��ʼ����������
			Quick_Sort(lower, Middle_Index-1);
			Quick_Sort(Middle_Index+1,upper);
		}
	}

    //������ȡ��ֵ
    private int Middle_Value_of_Three(int lower,int upper){
    	int mid=(lower+upper)/2;
    	//�ȶ������������оֲ�����
    	if(Quick_array[lower]>Quick_array[mid])
    		Swap(lower, mid);
    	if(Quick_array[lower]>Quick_array[upper])
    		Swap(lower, upper);
    	if(Quick_array[mid]>Quick_array[upper])
    		Swap(mid, upper);
    	//�����ֵ���������ĵ����ڶ���������λ��
    	Swap(mid,upper-1);
    	//������ֵ
    	return Quick_array[upper-1];
    }
    
    //�Ի��ֵ�������бȽ��뽻��
    private int Partition(int Left_point,int Right_point,int key){
    	//��¼���������ֵ��λ��
    	int upper=Right_point-1;  
    	//���¶�����ָ���λ�ã���ֵ��λ�ã�
    	Right_point-=1;
    	while(true){
    		
    		//����߿�ʼѰ�ұ���ֵ�����ֵ
    		while(Quick_array[++Left_point]<key);
    		
    		//���ұ߿�ʼѰ�ұ���ֵС����ֵ
    		while(Quick_array[--Right_point]>key);
    		
    		if(Left_point>=Right_point){
    			break;
    		}
    		else{
    			Swap(Left_point,Right_point);
    		}
    	}
    	//����ֵ���������һ������λ�ý��н���
    	Swap(Left_point,upper);
    	//������ֵ��λ��
    	return Left_point;
    }
    
    //��������С��3��������м򵥱Ƚ�������
	private void Normal_Sort(int lower,int upper) {
		int size=upper-lower+1;
		if(size<=1){
			return;
		}
		if(size==2){
			if(Quick_array[lower]>Quick_array[upper]){
				Swap(lower,upper);
				return;
			}
		}else{
			int mid=(lower+upper)/2;
			if(Quick_array[lower]>Quick_array[mid])
				Swap(lower, mid);
			if(Quick_array[lower]>Quick_array[upper])
				Swap(lower, upper);
			if(Quick_array[mid]>Quick_array[upper])
				Swap(mid, upper);
			return;
		}
	}

	//��������
	private void Swap(int first, int second) {
		int temp=Quick_array[first];
		Quick_array[first]=Quick_array[second];
		Quick_array[second]=temp;		
	}

	//��ʾϣ������������
	public void Display(){
    	for(int i=0;i<length;++i){
    		System.out.print(Shell_array[i]+" ");
    	}
    	System.out.println();
    }  
	
	//��ʾ��������������
	public void Display_Quick(){
    	for(int i=0;i<length;++i){
    		System.out.print(Quick_array[i]+" ");
    	}
    	System.out.println();
	}
    
}
