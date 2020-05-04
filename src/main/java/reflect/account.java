package reflect;

public class account {

	private int id;
	private String accountName;
	private double money;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "account [id=" + id + ", accountName=" + accountName + ", money=" + money + "]";
	}
	
}
