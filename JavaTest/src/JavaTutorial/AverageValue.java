package JavaTutorial;

public class AverageValue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] num= {5,9,11,60};
		double sum=0;
		double avgnum=0;
		
		for(int i:num)
			sum+=i;
		
			avgnum=sum/num.length;
			
		System.out.println("Average num of array is " + avgnum);
		
	}

}
