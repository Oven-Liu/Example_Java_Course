package reflect;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtil {

	private static DataSource dataSource = null;
	
	static {
		dataSource = new ComboPooledDataSource();
	}
	
	public static QueryRunner getQueryRunner() {
		return new QueryRunner(dataSource);
	}
}
