package practice;

import java.util.Arrays;

public class insertsort {

	public static void main(String[] args) {
		
//		//System.out.println("start");
//		int[] array = {1,2,3,-1,-2,4};
//
//		for (int i = 1; i < array.length; i++) {
//			
//		//	System.out.println("for");
//			
//			int j = i;
//			while (j > 0) {
//				
//				//System.out.println("while");
//
//				if (array[j] < array[j - 1]) {
//					int t = array[j - 1];
//					array[j - 1] = array[j];
//					array[j] = t;
//					
//				}
//				j--;
//			}
//			
//
//		}
//		
//		System.out.println("array");
//		
//		for(int a:array) {
//			System.out.println(a);
//		}
//
	
	
		String s="ROTOR";
		int c=(s.length())/2;
		int i=0;
		int j=s.length()-1;
		while(i<c && j>c ) {
			
			if(s.charAt(i)==s.charAt(j)) {
				i++;
				j--;
				
				
			}
		}
		
		if(i==j) {
			System.out.println("pallindrome");
		}else{
			System.out.println("Not pallindrome");
		}
		
		
		
		
		
	
	
	}
}
