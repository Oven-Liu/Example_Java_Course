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
	 * ���ػ�����ʱ��
	 */
	public static void testLocalDateTime() {

		// ��ȡ��ǰ������ʱ��
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("��ǰʱ��: " + currentTime); // 2018-08-08T17:42:35.446
		// ͨ������ʱ���ȡ����
		LocalDate localDate = currentTime.toLocalDate();
		System.out.println("localDate: " + localDate); // 2018-08-08
		// ��ȡ����ʱ����ꡢ�¡��ա�ʱ���֡���
		Month month = currentTime.getMonth();
		int day = currentTime.getDayOfMonth();
		int seconds = currentTime.getSecond();
		System.out.println("��: " + month +", ��: " + day +", ��: " + seconds); //��: AUGUST, ��: 8, ��: 35
		System.out.println("��:" + currentTime.getMonthValue()); // 8
		
		// �޸�����ʱ��
		LocalDateTime localDateTime = currentTime.withDayOfMonth(10).withYear(2012);
		System.out.println("localDateTime: " + localDateTime); // 2012-08-10T17:42:35.446

		// ��������
		LocalDate localDate2 = LocalDate.of(2014, Month.DECEMBER, 12);
		System.out.println("localDate2: " + localDate2); // 2014-12-12

		// ����ʱ��
		LocalTime localTime = LocalTime.of(22, 15);
		System.out.println("localTime: " + localTime); // 22:15

		// �����ַ���
		LocalTime localTime2 = LocalTime.parse("20:15:30");
		System.out.println("localTime2: " + localTime2); // 20:15:30
	}
	
	/**
	 * ʱ������ʱ��
	 */
	public static void testZoneDateTime() {
		// ��ȡ��ǰ����ʱ��
		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
		System.out.println("��ǰ����ʱ�䣺" + zonedDateTime);
		
		// ����ʱ�� ID
		ZoneId id = ZoneId.of("Europe/Paris");
		System.out.println("ZoneId: " + id);
		
		// ��ȡ��ǰʱ�� ID
		ZoneId currentZone = ZoneId.systemDefault();
		System.out.println("����ʱ��: " + currentZone);
	}
}
