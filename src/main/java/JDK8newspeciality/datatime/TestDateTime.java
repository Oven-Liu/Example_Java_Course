package JDK8newspeciality.datatime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestDateTime {

	public static void main(String[] args) {
		testLocalDateTime();
		testZoneDateTime();
	}
	
	/**
	 * 本地化日期时间
	 */
	public static void testLocalDateTime() {

		// 获取当前的日期时间
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("当前时间: " + currentTime); // 2018-08-08T17:42:35.446
		// 通过日期时间获取日期
		LocalDate localDate = currentTime.toLocalDate();
		System.out.println("localDate: " + localDate); // 2018-08-08
		// 获取日期时间的年、月、日、时、分、秒
		Month month = currentTime.getMonth();
		int day = currentTime.getDayOfMonth();
		int seconds = currentTime.getSecond();
		System.out.println("月: " + month +", 日: " + day +", 秒: " + seconds); //月: AUGUST, 日: 8, 秒: 35
		System.out.println("月:" + currentTime.getMonthValue()); // 8
		
		// 修改日期时间
		LocalDateTime localDateTime = currentTime.withDayOfMonth(10).withYear(2012);
		System.out.println("localDateTime: " + localDateTime); // 2012-08-10T17:42:35.446

		// 创建日期
		LocalDate localDate2 = LocalDate.of(2014, Month.DECEMBER, 12);
		System.out.println("localDate2: " + localDate2); // 2014-12-12

		// 创建时间
		LocalTime localTime = LocalTime.of(22, 15);
		System.out.println("localTime: " + localTime); // 22:15

		// 解析字符串
		LocalTime localTime2 = LocalTime.parse("20:15:30");
		System.out.println("localTime2: " + localTime2); // 20:15:30
	}
	
	/**
	 * 时区日期时间
	 */
	public static void testZoneDateTime() {
		// 获取当前日期时间
		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
		System.out.println("当前日期时间：" + zonedDateTime);
		
		// 创建时区 ID
		ZoneId id = ZoneId.of("Europe/Paris");
		System.out.println("ZoneId: " + id);
		
		// 获取当前时区 ID
		ZoneId currentZone = ZoneId.systemDefault();
		System.out.println("当期时区: " + currentZone);
	}
}
