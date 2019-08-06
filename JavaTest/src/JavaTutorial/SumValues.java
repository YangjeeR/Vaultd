package JavaTutorial;

public class SumValues {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] num= {4,7,8,10};
		int total = 0;
		
	/*	for(i=0;i<=3;i++)
		{
			 total=num[i]+total;
			
		}
		
		System.out.println("The total sum of number is" +  total);
		*/
		
		for(int i:num)
			total+=i;
		System.out.println("The total sum of number is" +  total);
	}

}
