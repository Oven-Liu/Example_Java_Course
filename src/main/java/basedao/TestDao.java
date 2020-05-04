package basedao;

import org.junit.Test;

public class TestDao {
	
	Dao dao = new Dao();
	
	/**
	 * 添加数据
	 */
	@Test
	public void testAdd(){
		Admin admin = new Admin();
		admin.setUserName("赵六");
		admin.setPwd("666666");
		dao.add(admin);
	}
	
	/**
	 * 删除数据
	 */
	@Test
	public void testDelete() {
//		int id = 3;
//		dao.delete(id);
		dao.delete();
	}
	
	/**
	 * 更改数据
	 */
	@Test
	public void testupDate() {
		int iD = 3;
		String pwd = "55555";
		dao.update(iD, pwd);
	}
	
	/**
	 * 查询数据
	 */
	@Test
	public void testQuery() throws Exception {
//		System.out.println(dao.query());
		System.out.println(dao.query(4));
	}
}
