package pattern.single.hungry;

public class Single {

	private static Single s = new Single();
	private Single() {}
	public static Single getInstance() {
		return s;
	}
}

