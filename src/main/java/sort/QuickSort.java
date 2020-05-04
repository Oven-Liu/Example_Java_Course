package sort;

import java.util.Arrays;

/**
 * 快速排序法
 * 
 * @author Xudong.Liu  2018年1月23日 上午11:17:03  
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] array = {16, 11, 1, 16, 27, 15};
		//int[] array = {1, 2, 3, 4, 5, 6};
		quickSort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}
	
	/**
	 * @param array 需要排序的整数数组
	 * @param startIndex 数组的开始索引
	 * @param endIndex 数组的结束索引
	 */
	/*public static void quickSort(int[] array, int startIndex, int endIndex) {

		// 当 startIndex >= endIndex 时，表示本次递归排序已经完成，终止本次递归 
		if (startIndex < endIndex) {
			int standard = array[startIndex]; // 定义标准
			int leftIndex = startIndex; // 左指针
			int rightIndex = endIndex; // 右指针

			// 将比标准数小的数都放发在标准数的左边，将比标准数大的数都放在标准数的右边 
			while(leftIndex < rightIndex) {
				while(leftIndex < rightIndex && array[rightIndex] >= standard) {
					rightIndex--; // 当右指针处的数大于等于标准数时，右指针向左移动一个位置
				}
				array[leftIndex] = array[rightIndex]; // 当右指针小于标准数时，将右指针处的数赋值给左指针处

				while(leftIndex < rightIndex && array[leftIndex] <= standard) {
					leftIndex++; // 当左指针处的数小于等于标准数时，左指针向右移动一个位置
				}
				array[rightIndex] = array[leftIndex]; // 当左指针大于标准数时，将左指针处的数赋值给右指针处
				//int temp = array[leftIndex];
				//array[leftIndex] = array[rightIndex];
				//array[rightIndex] = temp;
			}

			array[leftIndex] = standard; // 标准数回归其正确的排序位置
			//int tem = array[leftIndex];
			//array[leftIndex] = array[startIndex];
			//array[startIndex] = tem;

			// 标准数左边部分重复上面的排序方法，递归排序
			quickSort(array, startIndex, leftIndex - 1);

			// 标准数右边部分重复上面的排序方法，递归排序
			quickSort(array, leftIndex + 1, endIndex);
		}
	}*/
	
	public static void quickSort(int[] arr, int startIndex, int endIndex) {
		/*if (startIndex < endIndex) {
			int leftIndex = startIndex;
			int rightIndex = endIndex;
			int standard = arr[startIndex];
			while (leftIndex < rightIndex) {
				while (leftIndex < rightIndex && arr[rightIndex] >= standard) {
					rightIndex--;
				}
				//arr[leftIndex] = arr[rightIndex];
				while (leftIndex < rightIndex && arr[leftIndex] <= standard) {
					leftIndex++;
				}
				//arr[rightIndex] = arr[leftIndex];
				
				int temp = arr[leftIndex];
				arr[leftIndex] = arr[rightIndex];
				arr[rightIndex] = temp;
			}
			//arr[leftIndex] = standard;
			int tem = arr[leftIndex];
			arr[leftIndex] = arr[startIndex];
			arr[startIndex] = tem;
			
			quickSort(arr, startIndex, leftIndex - 1);
			quickSort(arr, leftIndex + 1, endIndex);
		}*/
		if (startIndex < endIndex) {
			int leftIndex = startIndex;
			int rightIndex = endIndex;
			int standard = arr[startIndex];
			while (leftIndex < rightIndex) {
				while (leftIndex < rightIndex && arr[rightIndex] >= standard) {
					rightIndex--;
				}
				//arr[leftIndex] = arr[rightIndex];
				while (leftIndex < rightIndex && arr[leftIndex] <= standard) {
					leftIndex++;
				}
				//arr[rightIndex] = arr[leftIndex];
				int temp = arr[leftIndex];
				arr[leftIndex] = arr[rightIndex];
				arr[rightIndex] = temp;
			}
			//arr[leftIndex] = standard;
			int temp = arr[startIndex];
			arr[startIndex] = arr[leftIndex];
			arr[leftIndex] = temp;
			quickSort(arr, startIndex, leftIndex-1);
			quickSort(arr, leftIndex+1, endIndex);
		}
	}
}