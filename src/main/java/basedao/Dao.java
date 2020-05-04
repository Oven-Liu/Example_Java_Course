package basedao;

import java.util.List;

/**
 * 对数据进行增删查改操作
 * 
 * @author Xudong.Liu
 */
public class Dao extends BaseDao{

	/**
	 * 添加数据
	 */
	public void add(Admin admin) {
		String sql = "INSERT INTO Admin(userName,pwd) VALUES (?,?)"; 
		Object[] paramValues = {admin.getUserName(), admin.getPwd()};
		super.update(sql, paramValues);
	}
	
	/**
	 * 删除所有数据
	 */
	public void delete(int id) {
		String sql = "DELETE FROM Admin WHERE id=?";
		Object[] paramValues = {id};
		super.update(sql, paramValues);
	}
	
	/**
	 * 删除指定数据
	 */
	public void delete() {
		String sql = "DELETE FROM Admin";
		Object[] paramValues = {null};
		super.update(sql, paramValues);
	}
	
	/**
	 * 修改数据
	 */
	public void update(int id, String passWord) {
		String sql = "UPDATE Admin SET pwd=? WHERE id=?";
		Object[] paramValues = {passWord, id};
		super.update(sql, paramValues);
	}
	
	/**
	 * 查询所有数据
	 */
	public List<Admin> query() {
		String sql = "SELECT * FROM Admin";
		Object[] paramValues = {null};
		return super.query(sql, paramValues, Admin.class);
	}
	
	/**
	 * 查询指定id数据
	 */
	public List<Admin> query(int id) {
		String sql = "SELECT * FROM Admin WHERE id=?";
		Object[] paramValues = {id};
		return super.query(sql, paramValues, Admin.class);
	}
}
