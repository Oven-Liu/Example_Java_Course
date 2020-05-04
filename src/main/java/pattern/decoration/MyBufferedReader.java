package pattern.decoration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class MyBufferedReader {

	public static void main(String[] args) throws Exception {
		String path = "D:/Programming/Java/OutputFile/RegisterMessage.txt";
		readFile(path);
	}
	
	private static void readFile(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		MyQutoBufferedReader myQutoBufferedReader = new MyQutoBufferedReader(br);
		MyLineBufferedReader myLineBufferedReader = new MyLineBufferedReader(myQutoBufferedReader);
		String line = null;
		while ((line = myLineBufferedReader.readLine()) != null) {
			System.out.println(line);
		}
	}
}

class MyQutoBufferedReader extends BufferedReader {
	private BufferedReader bufferedReader;
	
	public MyQutoBufferedReader(BufferedReader bufferedReader) {
		super(bufferedReader);
		this.bufferedReader = bufferedReader;
	}
	
	@Override
	public String readLine() throws IOException {
		String line = bufferedReader.readLine();
		if (line != null) {
			return "\"" + line + "\"";
		} else {
			return null;
		}
	}
}

class MyLineBufferedReader extends BufferedReader {
	private BufferedReader bufferedReader;
	int count;

	public MyLineBufferedReader(BufferedReader bufferedReader) {
		super(bufferedReader);
		this.bufferedReader = bufferedReader;
	}
	
	@Override
	public String readLine() throws IOException {
		String line = bufferedReader.readLine();
		if (line != null) {
			count++;
			return count + ":" + line;
		} else {
			return null;
		}
	}
}




