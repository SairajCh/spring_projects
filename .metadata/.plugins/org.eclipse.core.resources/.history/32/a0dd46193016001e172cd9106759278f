package practice;

public class orderAgnosticBinarySearch {

	int binarySearch(int[] a, int target) {

		int s = 0;
		int e = a.length - 1;

		boolean isAsc = a[e] > a[s];

		while (s <= e) {
			int mid = s + (e - s) / 2;
			if (target == a[mid]) {
				return mid;
			}
			if (isAsc) {
				if (target< a[mid]) {
					e = mid - 1;
				} else {
					s = mid + 1;
				}

			}else {
				if(target>a[mid]) {
					e=mid-1;
				}else {
					s=mid+1;
				}
				
			
			}
		}

		return -1;
	}

	public static void main(String... args) {
		
		int a[]= {9,7,5,3,2,1};
		
		orderAgnosticBinarySearch o = new orderAgnosticBinarySearch();
		int result =o.binarySearch(a, 3);
		System.out.println(result);
	}

}
