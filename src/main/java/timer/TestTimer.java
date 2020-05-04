package timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ʹ�ö�ʱ������� 4 ��ִ��һ�Σ��ټ�� 2 ��ִ��һ�Σ���� 4 ��ִ��һ�Σ��Դ�����ִ�С�˫�ض�ʱ��
 * @author Xudong.Liu  2018��9��1�� ����3:15:06
 */
public class TestTimer {
	private static volatile int count = 0;
	
	public static void main(String[] args) {
		new Timer().schedule(new MyTimerTask(), 0);
		
		/* ÿ���ӡ����ǰ���� */
		while (true) {
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��̬�ڲ���
	 */
	static class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			count = (count + 1) % 2;
			System.out.println("Hello Word");
			new Timer().schedule(new MyTimerTask(), 2000 + 2000 * count); // �ݹ飬ÿ�� 4 �� 2 �����һ��
		}
	}
}
