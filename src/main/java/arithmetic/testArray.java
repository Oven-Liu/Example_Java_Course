package arithmetic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 数组
 * 
 * @author Xudong.Liu  2018年8月31日 下午9:13:10
 *
 */
public class testArray {
	
	public static void main(String[] args) {
		int arr[] = {2, 43, 21, 3, 8, 2, 9, 16, 3, 8, 21, 2, 9, 2, 9};
		testCount(arr);
		testCount2(arr);
	}
	
	/**
	 * 求数组中重复元素的个数，使用 map 储存
	 * @param arr int 数组
	 */
	public static void testCount(int[] arr) {
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i < arr.length-1; i++) {
			int k = 1; // 初始化出现次数
			for (int j = i+1; j < arr.length; j++) {
				if (!map.containsKey(arr[i])) { // 如果 map 集合中已经包含该值，则用再计算出现的次数，因为已经计算了
					if (arr[i] == arr[j]) { // 如果后面有值与该值相等，则次数 k 加 1
						k++;
					}
				}
			}
			if (!map.containsKey(arr[i])) { // 如果 map 集合中没有包含该值，则将该值和该值出现的次数分别作为键和值添加到 map 中
				map.put(arr[i], k);
			}
		}
		Set<Entry<Integer, Integer>> entrySet = map.entrySet();
		Iterator<Entry<Integer, Integer>> it = entrySet.iterator();
		while (it.hasNext()) {
			Entry<Integer, Integer> entry = it.next();
			System.out.println(entry.getKey() + "出现：" + entry.getValue() + "次");
		}
	}
	
	/**
	 * 求数组中重复元素的个数，直接使用数组
	 * @param arr int 数组
	 */
	public static void testCount2(int[] arr) {
		
		/* 统计数组重复元素的个数 */
		int same = 0;
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					same++;
					break;
				}
			}
		}
		
		/* 创建没有重复元素的新数组 */
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
		
		/* 输出 arr 数组中各元素的个数 */
		for (int i = 0; i < newArr.length; i++) {
			int count = 0;
			for (int j = 0; j < arr.length; j++) {
				if (newArr[i] == arr[j]) {
					count++;
				}
			}
			System.out.println(newArr[i] + "出现：" + count + "次");
		}
	}
}
