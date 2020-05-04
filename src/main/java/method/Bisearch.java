package method;

/**
 * �۰�(����)���ҷ�������Ԫ�ر��밴��С���򣬼��谴��������
 * 
 * @author Xudong.Liu  2018��1��23�� ����12:17:06  
 */
public class Bisearch {

	public static void main(String[] args) {
		int[] arr = new int[]{1, 3, 5, 8, 9, 10, 18};
		System.out.println(bisearch(arr, 9));
		System.out.println(bisearch(arr, 9, 0, arr.length - 1));
	}
	
	/**
	 * �۰���ҷ�
	 * @param arr ����
	 * @param dest Ŀ����
	 * @return ��������д���Ŀ�������ͷ��ظ�Ŀ�����������е������ţ���������ھͷ���-1
	 */
	/*public static int bisearch(int[] arr, int dest) {
		int startIndex = 0;
		int endIndex = arr.length - 1;
		
		while (startIndex <= endIndex) {
			int middleIndex = (startIndex + endIndex) / 2;
			
			if (dest > arr[middleIndex]) {
				startIndex = middleIndex + 1;
			} else if (dest < arr[middleIndex]) {
				endIndex = middleIndex - 1;
			} else {
				return middleIndex;
			}
		}
		return -1;
	}*/

	public static int bisearch(int[] arr, int dest) {
		int startIndex = 0;
		int endIndex = arr.length - 1;
		while (startIndex <= endIndex) {
			int middleIndex = (startIndex + endIndex) / 2;
			if (dest < arr[middleIndex]) {
				endIndex = middleIndex - 1;
			} else if (dest > arr[middleIndex]) {
				startIndex = middleIndex + 1;
			} else {
				return middleIndex;
			}
		}
		return -1;
	}
	
	/**
	 * �۰���ҷ����ݹ�
	 * @param arr ����
	 * @param dest Ŀ����
	 * @return ��������д���Ŀ�������ͷ��ظ�Ŀ�����������е������ţ���������ھͷ���-1
	 */
	/*public static int bisearch(int[] arr, int dest, int startIndex, int endIndex) {
		int middleIndex = (startIndex + endIndex) / 2;
		if (startIndex > endIndex || arr[startIndex] > dest || arr[endIndex] < dest) {
			return -1;
		}
		if (dest > arr[middleIndex]) {
			return bisearch(arr, dest, middleIndex + 1, endIndex);
		} else if (dest < arr[middleIndex]) {
			return bisearch(arr, dest, startIndex, middleIndex - 1);
		} else {
			return middleIndex;
		}
	}*/
	
	public static int bisearch(int[] arr, int dest, int startIndex, int endIndex) {
		int middleIndex = (startIndex + endIndex) / 2;
		if (dest > arr[endIndex] || dest < arr[startIndex] || startIndex > endIndex) {
			return -1;
		}
		if (dest < arr[middleIndex]) {
			return bisearch(arr, dest, startIndex, middleIndex-1);
		} else if (dest > arr[middleIndex]) {
			return bisearch(arr, dest, middleIndex+1, endIndex);
		} else {
			return middleIndex;
		}
	}
}
