package JavaTutorial;


import java.util.Arrays;

public class SortArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] num=new int[] {6,1,9,10};
		String[] alpha= {"Yanzee","Pratik","Anita","Sunita","Himpi"};
		
		Arrays.sort(num);
		System.out.println("Sorted numeric array" + Arrays.toString(num));
		
		Arrays.sort(alpha);
		System.out.println("Sorted string array" + Arrays.toString(alpha));
				
	}

}
