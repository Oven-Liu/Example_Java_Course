package arithmetic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * ����
 * 
 * @author Xudong.Liu  2018��8��31�� ����9:13:10
 *
 */
public class testArray {
	
	public static void main(String[] args) {
		int arr[] = {2, 43, 21, 3, 8, 2, 9, 16, 3, 8, 21, 2, 9, 2, 9};
		testCount(arr);
		testCount2(arr);
	}
	
	/**
	 * ���������ظ�Ԫ�صĸ�����ʹ�� map ����
	 * @param arr int ����
	 */
	public static void testCount(int[] arr) {
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i < arr.length-1; i++) {
			int k = 1; // ��ʼ�����ִ���
			for (int j = i+1; j < arr.length; j++) {
				if (!map.containsKey(arr[i])) { // ��� map �������Ѿ�������ֵ�������ټ�����ֵĴ�������Ϊ�Ѿ�������
					if (arr[i] == arr[j]) { // ���������ֵ���ֵ��ȣ������ k �� 1
						k++;
					}
				}
			}
			if (!map.containsKey(arr[i])) { // ��� map ������û�а�����ֵ���򽫸�ֵ�͸�ֵ���ֵĴ����ֱ���Ϊ����ֵ��ӵ� map ��
				map.put(arr[i], k);
			}
		}
		Set<Entry<Integer, Integer>> entrySet = map.entrySet();
		Iterator<Entry<Integer, Integer>> it = entrySet.iterator();
		while (it.hasNext()) {
			Entry<Integer, Integer> entry = it.next();
			System.out.println(entry.getKey() + "���֣�" + entry.getValue() + "��");
		}
	}
	
	/**
	 * ���������ظ�Ԫ�صĸ�����ֱ��ʹ������
	 * @param arr int ����
	 */
	public static void testCount2(int[] arr) {
		
		/* ͳ�������ظ�Ԫ�صĸ��� */
		int same = 0;
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					same++;
					break;
				}
			}
		}
		
		/* ����û���ظ�Ԫ�ص������� */
		int [] newArr = new int[arr.length-same];
		int k = 0;
		for (int i = 0; i < arr.length; i++) {
			boolean isContains = false;
			for (int j = 0; j < newArr.length; j++) {
				if (arr[i] == newArr[j]) {
					isContains = true;
				}
			}
			if (!isContains) {
				newArr[k++] = arr[i];
			}
		}
		System.out.println(Arrays.toString(newArr));
		
		/* ��� arr �����и�Ԫ�صĸ��� */
		for (int i = 0; i < newArr.length; i++) {
			int count = 0;
			for (int j = 0; j < arr.length; j++) {
				if (newArr[i] == arr[j]) {
					count++;
				}
			}
			System.out.println(newArr[i] + "���֣�" + count + "��");
		}
	}
}
