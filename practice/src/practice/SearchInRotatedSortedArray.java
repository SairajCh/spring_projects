package practice;

class SearchInRotatedSortedArray {
	
	public static void main(String[] args) {
		int search = SearchInRotatedSortedArray.search(new int[] {5,1,3},1);
		
		System.out.println(search);
	}
	 
    public static int search(int[] nums, int target) {

        int pivot = findPivot(nums);
       System.out.println(pivot);
        int s= binarysearch(nums,target,0,pivot);
       // System.out.println(s);
        int s2=binarysearch(nums,target,pivot+1,nums.length-1);
       // System.out.println(s2);

        return Math.max(s,s2);
        
        


    }

    static int findPivot(int[] a) {
		int s = 0;
		int e = a.length-1;
		while(s<=e) {
			int m = s + (e-s)/2;

            if(m<e && a[m+1]<a[m]){
                return m;

            }

            if(m>s && a[m]<a[m-1]){
                return m-1;

            }


			if(a[s]>=a[m]) {
				e=m-1;
			}
			else {
				s=m+1;
			}
		}
		return -1;
	}

     public static int binarysearch(int[] arr, int target,int start,int end){

      
        int ans = -1;

        while(start<= end){

            int mid = start+(end-start)/2;

            if(arr[mid] > target){
                end=mid-1;
            }else if(target>arr[mid]){

                start = mid + 1;
            }else{
                
               return mid;
                
                
            }
        }
        
       return ans; 
    }
}