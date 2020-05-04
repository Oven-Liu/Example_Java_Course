package basedao;

import org.junit.Test;

public class TestDao {
	
	Dao dao = new Dao();
	
	/**
	 * �������
	 */
	@Test
	public void testAdd(){
		Admin admin = new Admin();
		admin.setUserName("����");
		admin.setPwd("666666");
		dao.add(admin);
	}
	
	/**
	 * ɾ������
	 */
	@Test
	public void testDelete() {
//		int id = 3;
//		dao.delete(id);
		dao.delete();
	}
	
	/**
	 * ��������
	 */
	@Test
	public void testupDate() {
		int iD = 3;
		String pwd = "55555";
		dao.update(iD, pwd);
	}
	
	/**
	 * ��ѯ����
	 */
	@Test
	public void testQuery() throws Exception {
//		System.out.println(dao.query());
		System.out.println(dao.query(4));
	}
}
