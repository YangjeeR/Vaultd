package JavaTutorial;

public class MaxSal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int sal=0;
		int[] salary= {2000,40000,10000,50000};
		
		for(int i=0;i<=3;i++)
		{			
			
			if(sal<salary[i])
			{
				sal=salary[i];				
			}			
			
		}
		
	System.out.println("Max salary:" + sal);

	}

}
