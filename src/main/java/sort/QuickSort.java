package sort;

import java.util.Arrays;

/**
 * ��������
 * 
 * @author Xudong.Liu  2018��1��23�� ����11:17:03  
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] array = {16, 11, 1, 16, 27, 15};
		//int[] array = {1, 2, 3, 4, 5, 6};
		quickSort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}
	
	/**
	 * @param array ��Ҫ�������������
	 * @param startIndex ����Ŀ�ʼ����
	 * @param endIndex ����Ľ�������
	 */
	/*public static void quickSort(int[] array, int startIndex, int endIndex) {

		// �� startIndex >= endIndex ʱ����ʾ���εݹ������Ѿ���ɣ���ֹ���εݹ� 
		if (startIndex < endIndex) {
			int standard = array[startIndex]; // �����׼
			int leftIndex = startIndex; // ��ָ��
			int rightIndex = endIndex; // ��ָ��

			// ���ȱ�׼��С�������ŷ��ڱ�׼������ߣ����ȱ�׼������������ڱ�׼�����ұ� 
			while(leftIndex < rightIndex) {
				while(leftIndex < rightIndex && array[rightIndex] >= standard) {
					rightIndex--; // ����ָ�봦�������ڵ��ڱ�׼��ʱ����ָ�������ƶ�һ��λ��
				}
				array[leftIndex] = array[rightIndex]; // ����ָ��С�ڱ�׼��ʱ������ָ�봦������ֵ����ָ�봦

				while(leftIndex < rightIndex && array[leftIndex] <= standard) {
					leftIndex++; // ����ָ�봦����С�ڵ��ڱ�׼��ʱ����ָ�������ƶ�һ��λ��
				}
				array[rightIndex] = array[leftIndex]; // ����ָ����ڱ�׼��ʱ������ָ�봦������ֵ����ָ�봦
				//int temp = array[leftIndex];
				//array[leftIndex] = array[rightIndex];
				//array[rightIndex] = temp;
			}

			array[leftIndex] = standard; // ��׼���ع�����ȷ������λ��
			//int tem = array[leftIndex];
			//array[leftIndex] = array[startIndex];
			//array[startIndex] = tem;

			// ��׼����߲����ظ���������򷽷����ݹ�����
			quickSort(array, startIndex, leftIndex - 1);

			// ��׼���ұ߲����ظ���������򷽷����ݹ�����
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