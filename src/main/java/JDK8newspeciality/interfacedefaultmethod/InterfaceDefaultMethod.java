package JDK8newspeciality.interfacedefaultmethod;

public interface InterfaceDefaultMethod {
	public static void main(String args[]){
		Vehicle vehicle = new Car();
		vehicle.print();
	}
}

interface Vehicle {
	default void print(){
		System.out.println(" 我是一辆车!");
	}

	/**
	 * Java 8 的另一个特性是接口可以声明（并且可以提供实现）静态方法
	 */
	static void blowHorn(){
		System.out.println(" 按喇叭!!!");
	}
}

interface FourWheeler {
	default void print(){
		System.out.println(" 我是一辆四轮车!");
	}
}

class Car implements Vehicle, FourWheeler {
	
	/*public void print() {
		System.out.println(" 我是一辆四轮汽车!");
	}*/
	
	public void print(){
		Vehicle.super.print();
		FourWheeler.super.print();
		Vehicle.blowHorn();
		System.out.println(" 我是一辆汽车!");
	}
}
