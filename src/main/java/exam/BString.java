package exam;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * 
	��һ����Ȥ���ַ�����ֵ���㷽ʽ:ͳ���ַ�����ÿ���ַ����ֵĴ���,Ȼ���������ַ�������ƽ������Ϊ�ַ����ļ�ֵ
	����: �ַ���"abacaba",�������4��'a',2��'b',1��'c',��������ַ����ļ�ֵΪ4 * 4 + 2 * 2 + 1 * 1 = 21
	ţţ��һ���ַ���s,�����������s���Ƴ����k���ַ�,���Ŀ�����õõ����ַ����ļ�ֵ��С��
 * 
 * @author Xudong.Liu  2018��1��23�� ����11:23:34  
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
