package JavaTutorial;

import java.util.Arrays;

public class InsertElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] num= {5,8,11,13};
		int addnum=10;
		int indexpos=2;
		
		for(int i=num.length-1;i>indexpos;i--)
		{
		  num[i]=num[i-1];		
		
		}
		
		num[indexpos]=addnum;
		
		System.out.println(Arrays.toString(num));
	}
	
}
