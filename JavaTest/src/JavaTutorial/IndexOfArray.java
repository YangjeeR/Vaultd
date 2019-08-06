package JavaTutorial;

public class IndexOfArray {
	
	
	public static int findIndex(int[] arr,int item)
	{
		for(int i=0;i<4;i++)
		{
			if(arr[i]==item)
				return i;
		}
		
		return -1;
	}
	
	
	public static void main(String arg[])
	{
		
	int[] num= {1,8,10,6};
	System.out.println(findIndex(num,8));
	System.out.println(findIndex(num,15));
	
	}

}
