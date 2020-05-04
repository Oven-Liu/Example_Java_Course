package exam;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * 
	有一种有趣的字符串价值计算方式:统计字符串中每种字符出现的次数,然后求所有字符次数的平方和作为字符串的价值
	例如: 字符串"abacaba",里面包括4个'a',2个'b',1个'c',于是这个字符串的价值为4 * 4 + 2 * 2 + 1 * 1 = 21
	牛牛有一个字符串s,并且允许你从s中移除最多k个字符,你的目标是让得到的字符串的价值最小。
 * 
 * @author Xudong.Liu  2018年1月23日 上午11:23:34  
 */
public class BString {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		int num = in.nextInt();
		
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < str.length(); i++) {
			if (!map.containsKey(str.charAt(i))) {
				map.put(str.charAt(i), 1);
			} else {
				map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
			}
		}
		
		for (int i = 0; i < num; i++) {
			int max = 0;
			char key = ' ';
			Set<Entry<Character, Integer>> entrySet = map.entrySet();
			for (Entry<Character, Integer> entry : entrySet) {
				if (entry.getValue() > max) {
					max = entry.getValue();
					key = entry.getKey();
				}
			}
			map.put(key, max - 1);
		}
		
		Set<Entry<Character, Integer>> entrySet = map.entrySet();
		int sum = 0;
		for (Entry<Character, Integer> entry : entrySet) {
			sum += entry.getValue() * entry.getValue();
		}
		System.out.println(sum);
		in.close();
	}
	
}
