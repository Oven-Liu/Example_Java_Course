package exam;

import java.util.Random;
import java.util.Scanner;

/**
 * ţţ�����򶼺�ϲ����ݡ��������Ǿ����������Ϸ��
�����һ��װ��n����ݵ�����,ţţ���������ν���,ţţ�ȿ�ʼ����ÿ���غ���,ÿ����ұ����һЩ�����е����,���Ե���ݷ���������4��x����,����1,4,16,64�ȵȡ������������гԵ���Ч������ݵ������ܡ��ٶ�ţţ�������ǰ�����ѷ���������Ϸ,�����ʤ���ߵ����֡� 
��������:
�������t+1�С�
��һ�а���һ������t(1 �� t �� 100),��ʾ�����.
������t��ÿ��һ��n(1 �� n �� 10^9),��ʾ��ݷ���


�������:
����ÿһ��n,���ţţʤ�����"niu",�������ʤ�����"yang"��

��������1:
3
1
2
3

�������1:
niu
yang
niu
 * 
 * @author Xudong.Liu  2018��1��22�� ����9:50:53  
 */
public class BGame {

	public static void main(String[] args) {
		System.out.println("�����������(<= 100)��");
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t > 0) {
			int n = in.nextInt();
			/* ������1+1������Ϊ�������ŷ�������һ���������ٳԣ�1+4���� */
			int num = n % 5; // 1-niu, 2-yang, 3-niu, 4-niu, 5-yang, 6-niu, 7-yang, 8-niu, 9-niu, 10-yang
			System.out.println((num == 1 || num == 3|| num == 4) ? "niu" : "yang");
			t--;
		}
		in.close();
	}
	
}
