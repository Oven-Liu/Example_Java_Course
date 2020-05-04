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
	 * ð������
	 * 
	 * @param arr int ����
	 * @return �ź���� int ����
	 */
	/*public static int[] bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) { // ����ѭ������
			for (int j = 0; j < arr.length-1-i; j++) { // ����һ��ѭ���ȽϵĴ���
				 ���ǰһ�������ں�һ�����ͽ��н��� 
				if (arr[j] > arr[j+1]) {
					int temp = arr[j]; // ���ڽ�������ʱ����
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
	 * ѡ������
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
			int min = i; // �����С��ֵ
			for (int j = i+1; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j; // �ҵ���Сֵ���±�
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
			int min = i; // �����С��ֵ
			for (int j = i+1; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j; // �ҵ���Сֵ���±�
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
	 * ֱ�Ӳ���ѡ��
	 * @param arr
	 * @return
	 */
/*	public static int[] insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i]; // ��¼Ҫ���������
			int j = i;
			while (j > 0 && arr[j-1] > temp) { // ����������ұ߿�ʼ�Ƚϣ��ҵ�����С����
				arr[j] = arr[j-1]; // ���Ų��
				j--;
			}
			arr[j] = temp; // ��������
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
	 * ϣ������ - knuth �������
	 */
	/*public static int[] shellKnuthSort(int[] arr) {
		System.out.println("ԭ����Ϊ" + Arrays.toString(arr));
		int step = 1 ;
		int len = arr.length;
		while(step <= len/3){
			step = step*3 + 1; // 1,4,13,40......
		}  
		while(step > 0){
			//�ֱ��ÿ�����������������
			for(int i = step; i < len; i++) {
				int temp = arr[i];
				int j = i;
				while(j >= step && temp <= arr[j-step]) {
					arr[j] = arr[j-step];
					j -= step;
				}
				arr[j] = temp;
			}
			System.out.println("���Ϊ" + step + "��������Ϊ" + Arrays.toString(arr));
			step = (step-1) / 3;
		}
		System.out.println("��������" + Arrays.toString(arr));
		return arr;
	}*/
	
	public static int[] shellKnuthSort(int[] arr) {
		System.out.println("ԭ����Ϊ��" + Arrays.toString(arr));
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
			System.out.println("���Ϊ" + step + "������Ϊ��" + Arrays.toString(arr));
			step = (step - 1) / 3;
		}
		return arr;
	}
	
	
	/**
	 * ϣ������ - �������2h
	 */
	public static int[] shellSort(int[] arr) {
		System.out.println("ԭ����Ϊ" + Arrays.toString(arr));
		int step;
		int len = arr.length;
		for(step = len/2; step > 0; step /= 2) {
			// �ֱ��ÿ�����������������
			for(int i = step; i < arr.length; i++) {
				int j = i;
				int temp = arr[j];
				while(j-step >=0 && temp < arr[j-step]) {
					arr[j] = arr[j-step];
					j -= step;
				}
				arr[j] = temp;
			}
			System.out.println("���Ϊ" + step + "��������Ϊ" + Arrays.toString(arr));
		}
		return arr;
	}
	
/*	public static int[] shellSort(int[] arr) {
		System.out.println("ԭ����Ϊ" + Arrays.toString(arr));
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
			System.out.println("���Ϊ" + step + "��������Ϊ��" + Arrays.toString(arr));
		}
		return arr;
	}*/

}
