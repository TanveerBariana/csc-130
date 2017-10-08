import java.util.*;
public class MergerSort
{
	public static void main(String[] args)
	{
      System.out.print("how many elements are in the array: ");// prompt for array 
      Scanner in = new Scanner(System.in);
      int end= in.nextInt(); //see lenghth of array 
      int mid = end/2;
		int [] a = new int [end];
      for(int i=0; i< end; i++){// fill in array 
         System.out.print("element "+(i+1)+ " is: ");
         a[i]= in.nextInt();
      }  
		mergeSort(a);// go to method to create secondary array 
		System.out.println(Arrays.toString(a));
      median(a, mid);
      
	}

	public static void mergeSort(int [ ] a)
	{
		int[] tmp = new int[a.length];// declare secondary array
		mergeSort(a, tmp,  0,  a.length - 1);// go to actual merge sort
	}


	private static void mergeSort(int[ ] a, int[ ] tmp, int left, int right)
	{
		if( left < right )// is smallest position still lower than highest position  
		{
			int center = (left + right) / 2; //take half to establish center to break arrays 
			mergeSort(a, tmp, left, center); //first half
			mergeSort(a, tmp, center + 1, right);// second half
			merge(a, tmp, left, center + 1, right);//put together
		}

	}
   
   private static void median (int[] a, int center){
      double midNum;
      if(center %2 == 0){//see if array has even amount of numbers
         
         double num1 = a[center-1];
         double num2 = a[center];
         midNum = (num1+num2)/2;
         }else{
         midNum =(double)a[center+1];
         }
         System.out.println("mediam is " + midNum);
   }


    private static void merge(int[ ] a, int[ ] tmp, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;// establish end of left array at center
        int k = left; //store left for manipulaition in the megre step of this method
        int num = rightEnd - left + 1;// first value 

        while(left <= leftEnd && right <= rightEnd)// while arrays are in respective bounds 
            if(a[left] <= (a[right]))
                tmp[k++] = a[left++];//store value if value in left array is <= value in right array 
            else
                tmp[k++] = a[right++];//store value if value in right array is <= value in left array

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }
 }
