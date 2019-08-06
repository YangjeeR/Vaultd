package JavaTutorial;

import java.util.Arrays;

public class RemoveElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] num= {14,67,98,10,45,46};
		int remNum=2;
		
		for(int i=remNum;i<num.length-1;i++)
		{
			num[i]=num[i+1];
		}
		
		 System.out.println("After removing the third element: "+Arrays.toString(num));
	}

}
