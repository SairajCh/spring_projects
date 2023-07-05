package practice;

public class Pivot {
	
	int findPivot(int[] a) {
		int s = 0;
		int e = a.length-1;
		while(s<e) {
			int m = s + (e-s)/2;
			if(a[s]<a[m]) {
				s=m;
			}
			else if(a[s]>a[m]){
				e=m-1;
			}
			else if() {
				
			}
				
		}
		return s;
	}
	
	public static void main(String... args) {
		Pivot p  = new Pivot();
		int[] a = {4,5,6,7,8,1,2,3};
		int findPivot = p.findPivot(a);
		System.out.println(findPivot);
	}

}
