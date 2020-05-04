package recursion;

import java.io.File;

public class Recursion {

	public static void main(String[] args) {
		System.out.println(recursion(5));
		File file = new File("D:/Programming/Java/OutputFile");
		listFile(file, "-");
	}
	
	public static long recursion(int n) {
		if (n == 1) {
			return 1;
		} else {
			return n * recursion(n - 1);
		}
	}
	
	private static void listFile(File file, String str) {
		File[] listFiles = file.listFiles();
		for (File f : listFiles) {
			System.out.println(str + f.getName());
			if (f.isDirectory()) {
				listFile(f, "|  " + str);
			}
		}
	}
}
