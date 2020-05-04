package method;

/**
 * 折半(二分)查找法，数组元素必须按大小排序，假设按升序排列
 * 
 * @author Xudong.Liu  2018年1月23日 下午12:17:06  
 */
public class Bisearch {

	public static void main(String[] args) {
		int[] arr = new int[]{1, 3, 5, 8, 9, 10, 18};
		System.out.println(bisearch(arr, 9));
		System.out.println(bisearch(arr, 9, 0, arr.length - 1));
	}
	
	/**
	 * 折半查找法
	 * @param arr 数组
	 * @param dest 目标数
	 * @return 如果数组中存在目标数，就返回该目标数在数组中的索引号，如果不存在就返回-1
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
	 * 折半查找法，递归
	 * @param arr 数组
	 * @param dest 目标数
	 * @return 如果数组中存在目标数，就返回该目标数在数组中的索引号，如果不存在就返回-1
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
