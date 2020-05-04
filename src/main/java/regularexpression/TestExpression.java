package regularexpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestExpression {

	public static void main(String[] args) {
		String reg = "\\b[a-z]{3}\\b";
		String str = "abc abcd abcde bcd cde";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		while(m.find()) {
			System.out.println(m.start() + "...." + m.end()); // 0...3、15....18、19....22
			System.out.println("sub:" + str.substring(m.start(), m.end())); // abc、bcd、cde
			System.out.println(m.group()); // abc、bcd、cde
		}
	}
}
