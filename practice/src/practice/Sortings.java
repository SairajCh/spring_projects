package practice;

import java.util.Arrays;

public class Sortings {

	public static void main(String[] args) {
		// bubbleSort(new int[] { 1, 2, 3, 4, 5 });

		// insertionSort(new int[] { 5,4,3,2,1 });
		
		int[] a=mergeSort(new int[] {5,4,3,2,1},0,4);
		
		System.out.println(Arrays.toString(a));

	}

	/***
	 * worst case time complexity O(n^2) best case O(n)
	 * 
	 * 
	 * 
	 * static void bubbleSort(int[] a) { int c = 0;
	 * 
	 * for (int i = 1; i <= a.length - 1; i++) { for (int j = 0; j <= a.length - 2;
	 * j++) { if (a[j] > a[j + 1]) { c++; int t = a[j]; a[j] = a[j + 1]; a[j + 1] =
	 * t;
	 * 
	 * }
	 * 
	 * } if (c == 0) { System.out.println("sorted already::" + Arrays.toString(a));
	 * return;
	 * 
	 * } }
	 * 
	 * System.out.println(Arrays.toString(a));
	 * 
	 * }
	 * 
	 * 
	 */

	/*
	 * 
	 * Insertion Sort
	 * 
	 * static void insertionSort(int[] a) {
	 * 
	 * for (int i = 0; i <= a.length - 2; i++) { for (int j = i + 1; j > 0; j--) {
	 * if (a[j] > a[j - 1]) { break; } else {
	 * 
	 * int t = a[j]; a[j] = a[j - 1]; a[j - 1] = t;
	 * 
	 * }
	 * 
	 * } }
	 * 
	 * System.out.println(Arrays.toString(a));
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	static int[] mergeSort(int[] a, int s, int e) {

		if ( e-s==0) {
			return new int[] {a[s]};
		}

		int mid = (e + s) / 2;

		int[] left = mergeSort(a, s, mid);
		int[] right = mergeSort(a, mid + 1, e);

		return merge(left, right);

	}

	static int[] merge(int[] left, int[] right) {

		int a[] = new int[left.length + right.length];
		int i = 0, j = 0, k = 0;

		while (i <= left.length-1 && j <= right.length-1) {

			if (left[i] < right[j]) {
				a[k] = left[i];
				i++;
				k++;
			} else {
				a[k] = right[j];
				j++;
				k++;
			}
		}

			while (i <= left.length-1) {

				a[k] = left[i];
				k++;
				i++;

			}
			while (j <= right.length-1) {

				a[k] = right[j];
				k++;
				j++;

			}
			
		
			return a;
	}

}
