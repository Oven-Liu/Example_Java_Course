package JDK8newspeciality.interfacedefaultmethod;

public interface InterfaceDefaultMethod {
	public static void main(String args[]){
		Vehicle vehicle = new Car();
		vehicle.print();
	}
}

interface Vehicle {
	default void print(){
		System.out.println(" ����һ����!");
	}

	/**
	 * Java 8 ����һ�������ǽӿڿ������������ҿ����ṩʵ�֣���̬����
	 */
	static void blowHorn(){
		System.out.println(" ������!!!");
	}
}

interface FourWheeler {
	default void print(){
		System.out.println(" ����һ�����ֳ�!");
	}
}

class Car implements Vehicle, FourWheeler {
	
	/*public void print() {
		System.out.println(" ����һ����������!");
	}*/
	
	public void print(){
		Vehicle.super.print();
		FourWheeler.super.print();
		Vehicle.blowHorn();
		System.out.println(" ����һ������!");
	}
}
