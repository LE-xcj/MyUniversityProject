package DS_java_code;

public class Insert_sort {
    public static void main(String args[]){
    	int[] array={10,5,7,6,4,23,1,2,7,8};
    	int temp,out,in;
    	int length=array.length;
    	for(out=1;out<length;++out){
    		temp=array[out];
    		in=out;
    		while(in>0&&array[in-1]>temp){
    			array[in]=array[in-1];
    			--in;
    		}
    		array[in]=temp;
    	}
    	for(int i=0;i<length;++i){
    		System.out.print(array[i]+" ");
    	}
    	System.out.println();
    }
}
