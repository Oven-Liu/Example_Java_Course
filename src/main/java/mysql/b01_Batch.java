package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import util.JdbcUtil;

public class b01_Batch {
	Connection con = null;
	PreparedStatement presta = null;
	
	@Test
	public void testBatch() {
		try {
			con = JdbcUtil.getConnection();
			String sql = "INSERT INTO statement (NAME,gender) VALUES (?,?)";
			presta = con.prepareStatement(sql);
			presta.setString(1, "����2");
			presta.setString(2, "Ů");
			presta.addBatch();	// ���©дaddBatch������Ĳ������ý�����ǰ��ģ�����ǰ��Ĳ���û������
			presta.setString(1, "����3");
			presta.setString(2, "��");
			presta.addBatch();
			presta.setString(1, "����4");
			presta.setString(2, "Ů");
			presta.addBatch();
			int[] content = presta.executeBatch();
			presta.clearBatch();
			for (int cont : content) {
				System.out.println(cont);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(con, presta, null);
		}
	}
}
