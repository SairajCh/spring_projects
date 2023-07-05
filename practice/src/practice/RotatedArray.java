package practice;

public class RotatedArray {

	public static void main(String[] args) {
		int[] a  = {6,7,1,2,3,4,5};
		int findPivot = findPivot(a);
		System.out.println(findPivot);
	}
	
	static int findPivot(int[] a) {
		
		int s = 0;
		
		int e = a.length-1;
		
 
		while(s<=e) {
			
			if(s==e) {
				return e;
			}
			
			int mid = s+ (e-s)/2;
			
			if(mid<e&&a[mid]>a[mid+1]) {
				return mid;
			}
			if(a[mid]>a[s]) {
				s=mid+1;
			}
			else {
				e=mid-1;
			}
			
		}
		
		
		return -1;
	}
}
