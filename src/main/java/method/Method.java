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
	 * ȥ���߿ո�
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
	 * ð�����򣬴�ǰ����һֱ���������ڵ������бȽϣ��ϴ�ķ��ں��棬
	 * ����һ����ѭ������������������棬�������ƣ�����С�ķ�����ǰ��
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
	 * ѡ�����򣬽�ǰ��λ�õ������������λ�õ������бȽϣ���С�ķ�����ǰ�棬
	 * ����һ����ѭ������С����������ǰ�棬�Դ����ƣ������������������
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
	 * ��һ���ַ������ַ�����
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
	 * �����ַ����������ַ����г��ֵĴ���
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

