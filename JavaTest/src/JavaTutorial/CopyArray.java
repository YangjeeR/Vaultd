package JavaTutorial;

import java.util.Arrays;

public class CopyArray {
	
	
	public static void main(String[] args)
	{
	
	int[] num= {1,6,8,9,10,0,4};
	int[] copy_num=new int[7];
	
	for(int i=0;i<num.length;i++)
	{
		copy_num[i]=num[i];
	}
	
	System.out.println(Arrays.toString(copy_num));
	
	}
}
