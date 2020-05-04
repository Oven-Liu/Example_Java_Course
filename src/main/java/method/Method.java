package method;

import java.util.Arrays;

public class Method {
	
	public static void main(String[] args) {
		String str = trim("   hello    ");
		System.out.println(str);
		
		int[] arr = {2, 32, 21, 6, 9};
		System.out.println(Arrays.toString(bubbleSort(arr)));
		System.out.println(Arrays.toString(selectSort(arr)));
		
		System.out.println(reverseString("abcde"));
		
		System.out.println(getCount("abhaababdsda", "ab"));
		
		System.out.println(5>>1);
	}

	/**
	 * 去两边空格
	 */
	public static String trim(String str) {
		int start = 0;
		int end = str.length() - 1;
		
		for (int i = 0; i < str.length(); i++ ) {
			if (str.charAt(i) == ' ') {
				start++;
			} else {
				break;
			}
		}
		
		for (; end < str.length() && end >= 0;) {
			if (str.charAt(end) == ' ') {
				end--;
			} else {
				break;
			}
		}
		
		if(start < end) {
			return str.substring(start, (end + 1)); 
		} else {
			return "_";
		}
	}
	
	/**
	 * 冒泡排序，从前到后，一直是两个相邻的数进行比较，较大的放在后面，
	 * 即第一次外循环将最大的数放在最后面，依次类推，将最小的方法最前面
	 */
	public static int[] bubbleSort(int[] arr) {
		for(int i = 0; i < arr.length-1; i++) {
			for(int j = 0; j < arr.length-1-i; j++) {
				if(arr[j] > arr[j+1]) {
					int exchange = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = exchange;
				}
			}
		}
		return arr;
	}
	
	/**
	 * 选择排序，将前面位置的数依次与后面位置的数进行比较，最小的放在最前面，
	 * 即第一次外循环将最小的数放在最前面，以此类推，将最大的数放在最后面
	 */
	public static int[] selectSort(int[] arr) {
		for(int i = 0; i < arr.length-1; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i] > arr[j]) {
					int number = arr[i];
					arr[i] = arr[j];
					arr[j] = number;
				}
			}
		}
		return arr;
	}
	
	/**
	 * 将一个字符串的字符反序
	 */
	public static String reverseString(String src) {
		char chs[] = src.toCharArray();
		for (int start = 0, end = chs.length-1; start < end; start++, end--) {
			char temp = chs[end];
			chs[end] = chs[start];
			chs[start] = temp;
		}
		return new String(chs);
	}

	/**
	 * 求子字符串在整个字符串中出现的次数
	 */
	public static int getCount(String src, String tag) { 
		int index = 0;
		int count = 0;   
		while((index = src.indexOf(tag)) != -1) {
			src = src.substring(index + tag.length()); 
			count++;
		}
		return count;
	}


}

