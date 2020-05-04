package reflect;

import org.junit.Test;

public class TestBaseDao {

	@Test
	public void testAccount() {
		accountDao ad = new accountDao();
		account account = ad.findById(2);
		System.out.println(account);
	}
	
	@Test
	public void testAdmin() {
		AdminDao adminDao = new AdminDao();
		Admin admin = adminDao.findById(2);
		System.out.println(admin);
	}
}
