package exam;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 统计字符串中每种字符出现的次数，然后求所有字符次数的平方和作为字符串的价值
 * 例如: 字符串"abacaba"，里面包括4个'a'、2个'b'、1个'c'，于是这个字符串的价值为4 * 4 + 2 * 2 + 1 * 1 = 21
 * 
 * @author Xudong.Liu  2018年1月22日 下午8:50:15  
 */
public class AString {

	public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
	    System.out.println("请输入一个字符串");
	    String str = in.nextLine();
	    System.out.println("请输入需要移除字符的个数");
	    int num = in.nextInt();
	    System.out.println("输入的字符为：" + str + "，需要移除的字符数为：" + num);
	    
	    /* 获取 str 中每个字符出现的次数，并其放入数组中 */
	    char[] cha = str.toCharArray();
	    
	    /* 计算重复字符的个数，并创建 number 数组 */
	    int repeat = 0;
		for(int i = 0; i < cha.length - 1; i++) {
			for(int j = i + 1; j < cha.length; j++) {
				if(cha[i] == cha[j]) {
					repeat++;
					break;
				}
			}
		}
		int[] number = new int[cha.length - repeat]; // 存放每个字符出现的次数值
	    
		/* 将每个字符的个数存入 number 数组中 */
		int remove = 0; // 记录 cha 数组中值为 0 的个数
	    a: for (int i = 0; i < cha.length; i++) {
	    	
	    	/* 如果之前有该字符，就终止此外部循环，因为已经统计了 */
	    	for (int k = 0; k < i; k++) {
	    		if (cha[i] == cha[k]) {
	    			remove++;
	    			continue a;
	    		} 
	    	}
	    	
	    	/* 将一个字符出现的次数值，放入 number 数组中 */
	    	int sum = 1;
	        for (int j = i + 1; j < cha.length; j++) {
	        	if (cha[i] == cha[j]) {
	        		sum++;
	        	}
	        }
        	number[i-remove] = sum;
	    }
	    System.out.println(Arrays.toString(number));
	    
	    /* 循环 num 次，在 number 数组中，找到最大值的索引，并将该索引出的值减 1 */
	    for (int i = 0; i < num; i++) {
	    	int flag = 0;
	    	int max = 0;
	    	for (int j = 0; j < number.length; j++) {
	    		if (number[j] > max) {
	    			max = number[j];
	    			flag = j;
	    		}
	    	}
	    	number[flag]--;
	    }
	    System.out.println(Arrays.toString(number));
	    
	    /* 计算 number 数组中值的平方和 */
	    int total = 0;
	    for (int i : number) {
			total += i*i;
		}
	    System.out.println(total);
	    in.close();
	}
}
