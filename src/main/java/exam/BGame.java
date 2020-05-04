package exam;

import java.util.Random;
import java.util.Scanner;

/**
 * 牛牛和羊羊都很喜欢青草。今天他们决定玩青草游戏。
最初有一个装有n份青草的箱子,牛牛和羊羊依次进行,牛牛先开始。在每个回合中,每个玩家必须吃一些箱子中的青草,所吃的青草份数必须是4的x次幂,比如1,4,16,64等等。不能在箱子中吃到有效份数青草的玩家落败。假定牛牛和羊羊都是按照最佳方法进行游戏,请输出胜利者的名字。 
输入描述:
输入包括t+1行。
第一行包括一个整数t(1 ≤ t ≤ 100),表示情况数.
接下来t行每行一个n(1 ≤ n ≤ 10^9),表示青草份数


输出描述:
对于每一个n,如果牛牛胜利输出"niu",如果羊羊胜利输出"yang"。

输入例子1:
3
1
2
3

输出例子1:
niu
yang
niu
 * 
 * @author Xudong.Liu  2018年1月22日 下午9:50:53  
 */
public class BGame {

	public static void main(String[] args) {
		System.out.println("请输入情况数(<= 100)：");
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t > 0) {
			int n = in.nextInt();
			/* 出掉（1+1）（因为不是最优方案），一轮下来至少吃（1+4）份 */
			int num = n % 5; // 1-niu, 2-yang, 3-niu, 4-niu, 5-yang, 6-niu, 7-yang, 8-niu, 9-niu, 10-yang
			System.out.println((num == 1 || num == 3|| num == 4) ? "niu" : "yang");
			t--;
		}
		in.close();
	}
	
}
