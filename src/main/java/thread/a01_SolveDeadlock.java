package thread;

/**
 * �������� - ����˳���̰߳���һ����˳�������
 * 
 * @author Xudong.Liu  2018��9��6�� ����3:38:05
 */
public class a01_SolveDeadlock {
	public int flag = 1; 
	// ��̬������������ж������
	private static Object o1 = new Object(), o2 = new Object(); 
	
	public void money(int flag) {
		this.flag=flag;
		if( flag ==1){
			synchronized (o1) { 
				try { 
					Thread.sleep(500); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
				synchronized (o2) { 
					System.out.println("��ǰ���߳���"+
							Thread.currentThread().getName()+" "+"flag ��ֵ"+"1"); 
				} 
			} 
		} 
		if(flag ==0){
			synchronized (o2) { 
				try { 
					Thread.sleep(500); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
				synchronized (o1) { 
					System.out.println("��ǰ���߳���"+
							Thread.currentThread().getName()+" "+"flag ��ֵ"+"0"); 
				} 
			} 
		}
		
	} public static void main(String[] args) { 
		final a01_SolveDeadlock td1 = new a01_SolveDeadlock(); 
		final a01_SolveDeadlock td2 = new a01_SolveDeadlock(); 
		td1.flag = 1; 
		td2.flag = 0; 
		// td1,td2 �����ڿ�ִ��״̬���� JVM �̵߳�����ִ���ĸ��߳��ǲ�ȷ���ġ�
		// td2 �� run()������ td1 �� run()֮ǰ����
		Thread t1=new Thread(new Runnable(){
			public void run() {
				td1.flag = 1; 
				td1.money(1);
			} 
		});
		t1.start();

		Thread t2= new Thread(new Runnable(){
			public void run() {
				try {
					// �� t2 �ȴ� t1 ִ����
					t1.join(); // ���Ĵ��룬�� t1 ִ����� t2 �Ż�ִ��
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				td2.flag = 0;
				td1.money(0);
			} 
		});
		t2.start();
	} 
}
