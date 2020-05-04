package sort;

import java.util.Arrays;
import java.util.Iterator;

public class Sort {

	public static void main(String[] args) {
		int[] arr = {12, 9, 23, 32, 3, 21, 15};
//		System.out.println(Arrays.toString(bubbleSort(arr)));
//		System.out.println(Arrays.toString(selectSort(arr)));
//		System.out.println(Arrays.toString(insertSort(arr)));
//		System.out.println(Arrays.toString(shellKnuthSort(arr)));
		System.out.println(Arrays.toString(shellSort(arr)));
	}
	
	/**
	 * 冒泡排序法
	 * 
	 * @param arr int 数组
	 * @return 排好序的 int 数组
	 */
	/*public static int[] bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) { // 控制循环次数
			for (int j = 0; j < arr.length-1-i; j++) { // 控制一次循环比较的次数
				 如果前一个数大于后一个数就进行交换 
				if (arr[j] > arr[j+1]) {
					int temp = arr[j]; // 用于交换的临时变量
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		return arr;
	}*/
	
	public static int[] bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length-i-1; j++) {
				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		return arr;
	}
	
	/**
	 * 选择排序法
	 * 
	 * @param arr
	 * @return
	 */
	/*public static int[] selectSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}*/
	
/*	public static int[] selectSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			int min = i; // 标记最小的值
			for (int j = i+1; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j; // 找到最小值的下标
				}
			}
			if (min != i) {
				int temp = arr[i];
				arr[i] = arr[min];
				arr[min] = temp;
			}
		}
		return arr;
	}*/
	
	public static int[] selectSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			int min = i; // 标记最小的值
			for (int j = i+1; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j; // 找到最小值的下标
				}
			}
			if (min != i) {
				int temp = arr[i];
				arr[i] = arr[min];
				arr[min] = temp;
			}
		}
		return arr;
	}
	
	/**
	 * 直接插入选择法
	 * @param arr
	 * @return
	 */
/*	public static int[] insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i]; // 记录要插入的数据
			int j = i;
			while (j > 0 && arr[j-1] > temp) { // 从已排序的右边开始比较，找到比其小的数
				arr[j] = arr[j-1]; // 向后挪动
				j--;
			}
			arr[j] = temp; // 插入数据
		}
		return arr;
	}*/
	
	public static int[] insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int j = i;
			while (j > 0 && temp < arr[j-1]) {
				arr[j] = arr[j-1];
				j--;
			}
			arr[j] = temp;
		}
		return arr;
	}

	/**
	 * 希尔排序 - knuth 间隔序列
	 */
	/*public static int[] shellKnuthSort(int[] arr) {
		System.out.println("原数组为" + Arrays.toString(arr));
		int step = 1 ;
		int len = arr.length;
		while(step <= len/3){
			step = step*3 + 1; // 1,4,13,40......
		}  
		while(step > 0){
			//分别对每个增量间隔进行排序
			for(int i = step; i < len; i++) {
				int temp = arr[i];
				int j = i;
				while(j >= step && temp <= arr[j-step]) {
					arr[j] = arr[j-step];
					j -= step;
				}
				arr[j] = temp;
			}
			System.out.println("间隔为" + step + "的排序结果为" + Arrays.toString(arr));
			step = (step-1) / 3;
		}
		System.out.println("最终排序：" + Arrays.toString(arr));
		return arr;
	}*/
	
	public static int[] shellKnuthSort(int[] arr) {
		System.out.println("原数组为：" + Arrays.toString(arr));
		int step = 1;
		int len = arr.length;
		while (step <= len/3) {
			step = step*3 + 1;
		}
		while (step > 0) {
			for (int i = step; i < len; i++) {
				int temp = arr[i];
				int j = i;
				while (j >= step && temp < arr[j-step]) {
					arr[j] = arr[j-step];
					j -= step;
				}
				arr[j] = temp;
			}
			System.out.println("间隔为" + step + "的排序为：" + Arrays.toString(arr));
			step = (step - 1) / 3;
		}
		return arr;
	}
	
	
	/**
	 * 希尔排序 - 间隔序列2h
	 */
	public static int[] shellSort(int[] arr) {
		System.out.println("原数组为" + Arrays.toString(arr));
		int step;
		int len = arr.length;
		for(step = len/2; step > 0; step /= 2) {
			// 分别对每个增量间隔进行排序
			for(int i = step; i < arr.length; i++) {
				int j = i;
				int temp = arr[j];
				while(j-step >=0 && temp < arr[j-step]) {
					arr[j] = arr[j-step];
					j -= step;
				}
				arr[j] = temp;
			}
			System.out.println("间隔为" + step + "的排序结果为" + Arrays.toString(arr));
		}
		return arr;
	}
	
/*	public static int[] shellSort(int[] arr) {
		System.out.println("原数组为" + Arrays.toString(arr));
		int len = arr.length;
		int step;
		for (step = len/2; step > 0; step /= 2) {
			for (int i = step; i < len; i++) {
				int temp = arr[i];
				int j = i;
				while (j >= step && temp < arr[j-step]) {
					arr[j] = arr[j-step];
					j -= step;
				}
				arr[j] = temp;
			}
			System.out.println("间隔为" + step + "的排序结果为：" + Arrays.toString(arr));
		}
		return arr;
	}*/

}
