package basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import util.JdbcUtil;

/**
 * 进行更新和查询数据的通用方法
 * 
 * @author Xudong.Liu
 */
public class BaseDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/**
	 * 更新数据
	 * 
	 * @param sql  更新的sql语句（insert、update、delete）
	 * @param paramValues  sql语句中占位符的值（如果没有，传入null）
	 */
	public void update(String sql, Object[] paramValues) {

		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 获取参数数量
			int count = pstmt.getParameterMetaData().getParameterCount();
			if (paramValues != null && paramValues.length > 0) {
				// 设置参数值
				for (int i = 0; i < count; i++) {
					pstmt.setObject(i+1, paramValues[i]);
				}
			}
			// 执行更新数据
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(conn, pstmt, null);
		}
	}
	
	/**
	 * 查询数据
	 * 
	 * @param sql  查询的sql语句
	 * @param paramValues  sql语句中占位符的值
	 * @param clazz  返回对象的类型
	 * @return  查询对象的集合
	 */
	public <T>List<T> query(String sql, Object[] paramValues, Class<T> clazz) {
		
		try {
			// 创建List集合
			List<T> list = new ArrayList<T>();
			// 1、获取连接
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 获取占位符的数量
			int paramCount = pstmt.getParameterMetaData().getParameterCount();
			// 2、为参数设置值
			if (paramValues != null && paramValues.length > 0) {
				for (int i = 0; i < paramCount; i++) {
					pstmt.setObject(i + 1, paramValues[i]);
				}
			}
			// 3、执行sql语句
			rs = pstmt.executeQuery();
			// 4、获取查询结果，并创建Class对象，为其封装属性
			while (rs.next()) {
				// 使用反射，创建T的对象
				T t = clazz.newInstance();
				// 获取结果集的列数
				int ColunmConunt = pstmt.getMetaData().getColumnCount();
				for (int i = 0; i < ColunmConunt; i++) {
					// 获取结果集列名称
					String columnName = pstmt.getMetaData().getColumnName(i + 1);
					// 获取当前行的指定列的值
					Object value = rs.getObject(columnName);
					// 使用BeanUilts将数据封装到对象
					BeanUtils.copyProperty(t, columnName, value);
				}
				// 将t对象添加到
				list.add(t);
			}
			// 返回list
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeResource(conn, pstmt, rs);
		}
	}
}
