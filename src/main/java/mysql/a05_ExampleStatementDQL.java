package mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

/**
 *  π”√Statement÷¥––SQL”Ôæ‰
 * 
 * @author mengs
 */
public class a05_ExampleStatementDQL {
	Connection connection = null;
	Statement statement = null;
	
	@Test
	public void test() {
		ResultSet resultSet = null;
		try {
			connection = JdbcUtil.getConnection();
			statement = connection.createStatement();
			String sql = "SELECT * FROM statement";
			resultSet = statement.executeQuery(sql);
			
			/*Boolean flog = resultSet.next();
			flog = resultSet.next();
			if (flog) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString("name");
				String gender = resultSet.getString(3);
				System.out.println(id+","+name+","+gender);
			}*/
			
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString("name");
				String gender = resultSet.getString(3);
				System.out.println(id+","+name+","+gender);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(connection, statement, resultSet);
		}
	}
}
