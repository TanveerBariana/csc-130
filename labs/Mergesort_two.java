import java.util.*;
public class Mergesort_two {
   
   public static void main(String args[]){
      System.out.print("how many elements are in the array: ");
      Scanner in = new Scanner(System.in);
      int end = in.nextInt();
      int[] A= new int[end];
      int[] B= new int[end];
      for(int i=0; i< end; i++){
         System.out.print("element "+(i+1)+ " is: ");
         B[i]= in.nextInt();
      }  
      B = sort(A,0, end, B);// store sorted array in B
         System.out.print(Arrays.toString(B)); // print array 
        
 
   }
	/*public static int[] mergsort(int[]B,int end){// take in two arrays 
      B = sort(0, end, B);// store sorted array in B
      return B;////have B be the sorted array passed up to the call
   }*/
	
	public static int[] sort(int[] A, int start,int end,int[]B){ // call recursivly until array is broken down to single elements
		if(end - start < 2){// end when passed in array size 1
         return B;
      }
      int middle= (end+start)/2 ;
		A = sort(A, start, middle, B); //do first half of passed in aray 
		B = sort(A, middle, end, B); //do second half of passed in array
      B= merge(A, start, middle, end, B); // put together halves into B 
      return B; // return sorted array B back up the chain
	}
	public static int[] merge(int[] A, int start,int middle, int end,int[] B){
	   int a = start;
      int b = middle;
      for(int i= start; i< end; i++){// go through entire array 
         if(a < middle && (b>= end || B[a] <= A[b])){//B[i]= A[a] if the start of Array A is smaller than the middle of Arrray A 
            B[i] = A[a];                             //and if their both still in the array , and  
            a++; //icrement to teh next value in the A half
         }else{
            B[i] = A[b];
            b++;// increment to teh next value of teh B half
         }
      }
      return B;// return sorted array B back up the chain
   } 
}