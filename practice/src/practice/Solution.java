package practice;

import java.util.Arrays;

class Solution {
    public  int[] searchRange() {
        
       
      int[] nums = {5,7,7,8,8,10};
       int target = 8;
      int fo=search(nums,target,true);
       
       //int lo=search(nums,target);
      int[] a ={fo,fo};
       return a;



    }

    public int search(int[] arr, int target,boolean firstoccurance){

         int start=0;
        int end=arr.length-1;
        int ans = -1;

        while(start< end){

            int mid = start+(end-start)/2;

            if(arr[mid] > target){
                end=mid-1;
            }else if(target>arr[mid]){

                start = mid + 1;
            }else{
                
                ans=mid;
               
                end=mid-1;
                //System.out.println(end);
                
            }
        }
       return ans; 
    }
    
    public static void main(String... args) {
    	Solution s = new Solution();
    	int[] searchRange = s.searchRange();
    	System.out.println(Arrays.toString(searchRange));
    }
}