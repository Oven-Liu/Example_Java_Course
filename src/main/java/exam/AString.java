package exam;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ͳ���ַ�����ÿ���ַ����ֵĴ�����Ȼ���������ַ�������ƽ������Ϊ�ַ����ļ�ֵ
 * ����: �ַ���"abacaba"���������4��'a'��2��'b'��1��'c'����������ַ����ļ�ֵΪ4 * 4 + 2 * 2 + 1 * 1 = 21
 * 
 * @author Xudong.Liu  2018��1��22�� ����8:50:15  
 */
public class AString {

	public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
	    System.out.println("������һ���ַ���");
	    String str = in.nextLine();
	    System.out.println("��������Ҫ�Ƴ��ַ��ĸ���");
	    int num = in.nextInt();
	    System.out.println("������ַ�Ϊ��" + str + "����Ҫ�Ƴ����ַ���Ϊ��" + num);
	    
	    /* ��ȡ str ��ÿ���ַ����ֵĴ������������������ */
	    char[] cha = str.toCharArray();
	    
	    /* �����ظ��ַ��ĸ����������� number ���� */
	    int repeat = 0;
		for(int i = 0; i < cha.length - 1; i++) {
			for(int j = i + 1; j < cha.length; j++) {
				if(cha[i] == cha[j]) {
					repeat++;
					break;
				}
			}
		}
		int[] number = new int[cha.length - repeat]; // ���ÿ���ַ����ֵĴ���ֵ
	    
		/* ��ÿ���ַ��ĸ������� number ������ */
		int remove = 0; // ��¼ cha ������ֵΪ 0 �ĸ���
	    a: for (int i = 0; i < cha.length; i++) {
	    	
	    	/* ���֮ǰ�и��ַ�������ֹ���ⲿѭ������Ϊ�Ѿ�ͳ���� */
	    	for (int k = 0; k < i; k++) {
	    		if (cha[i] == cha[k]) {
	    			remove++;
	    			continue a;
	    		} 
	    	}
	    	
	    	/* ��һ���ַ����ֵĴ���ֵ������ number ������ */
	    	int sum = 1;
	        for (int j = i + 1; j < cha.length; j++) {
	        	if (cha[i] == cha[j]) {
	        		sum++;
	        	}
	        }
        	number[i-remove] = sum;
	    }
	    System.out.println(Arrays.toString(number));
	    
	    /* ѭ�� num �Σ��� number �����У��ҵ����ֵ����������������������ֵ�� 1 */
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
	    
	    /* ���� number ������ֵ��ƽ���� */
	    int total = 0;
	    for (int i : number) {
			total += i*i;
		}
	    System.out.println(total);
	    in.close();
	}
}
