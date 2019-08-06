package JavaTutorial;

public class SpecificeValue {

	public static boolean contains(int[] arr,int item)
	{
		for(int i:arr)
			if(item==i)
			{
				return true;
			}
		
		return false;
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] num= {2014,2019,501};
		
		System.out.println(contains(num,5));
		System.out.println(contains(num,501));
	}
	
	

}
