package JDK8newspeciality.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

	public static void main(String[] args) {
		testGenerateStream();
//		testForEach();
//		testMap();
//		testFilter();
//		testSorted();
//		testCollector();
//		testSummaryStatistics();
//		testFlatMap();
//		testMaxMin();
		testReduce();
	}
	
	/**
	 * ���Լ��Ͻӿ��������ķ�����#stream��#parallelStream���� Stream �������ķ�����#of
	 * parallelStream �������д������Ĵ��淽����
	 */
	public static void testGenerateStream() {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		// #stream
		strings.stream().filter(string -> !string.isEmpty()).forEach(System.out::println);
		// #stream -> #parallelStream
		strings.stream().parallel().forEach(System.out::println);
		// #parallelStream
		strings.parallelStream().filter(string -> !string.isEmpty()).forEach(System.out::println);
		// #of
		Stream.of(1, 2, 5, 9).forEach(System.out::println);
	}
	
	/**
	 * ���� #forEach
	 * Stream �ṩ���µķ���'forEach' ���������е�ÿ�����ݡ�
	 * ���� #limit
	 * limit �������ڻ�ȡָ������������
	 */
	public static void testForEach() {
		Random random = new Random();
		random.ints().limit(10).forEach(System.out::println);
	}
	
	/**
	 * ���� #map
	 * map ��������ӳ��ÿ��Ԫ�ص���Ӧ�Ľ����
	 */
	public static void testMap() {
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		// ��ȡÿ��Ԫ�ص�ƽ��
		numbers.stream().map(i -> i*i).distinct().forEach(System.out::println);
	}
	
	/**
	 * ���� #filter
	 * filter ��������ͨ�����õ��������˳�Ԫ�ء�
	 */
	public static void testFilter() {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		// ��ȡ���ַ���������
		long count = strings.stream().filter(string -> string.isEmpty()).count();
		System.out.println("���ַ����������ǣ�" + count);
	}
	
	/**
	 * ���� #sorted
	 * sorted �������ڶ�����������
	 */
	public static void testSorted() {
		Random random = new Random();
		random.ints().limit(10).sorted().forEach(System.out::println);
	}
	
	/**
	 * ���� Collector ��
	 * Collectors ��ʵ���˺ܶ��Լ���������罫��ת���ɼ��Ϻ;ۺ�Ԫ�ء�
	 */
	public static void testCollector() {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		// ����ת��Ϊ����
		List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		System.out.println("ɸѡ�б��ϣ�" + filtered);
		// �����е�Ԫ�ؾۺ�
		String mergeString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
		System.out.println("�ϲ��ַ�����" + mergeString);
	}
	
	/**
	 * ���� #summaryStatistics
	 * ����ͳ��
	 */
	public static void testSummaryStatistics() {
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		IntSummaryStatistics sm = numbers.stream().mapToInt(i -> i).summaryStatistics();
		System.out.println("��������" + sm.getMax());
		System.out.println("��С������" + sm.getMin());
		System.out.println("������֮�ͣ�" + sm.getSum());
		System.out.println("ƽ������" + sm.getAverage());
	}
	
	/**
	 * ���� #flatMap
	 * �ϲ������
	 */
	public static void testFlatMap() {
		List<Integer> together = Stream.of(Arrays.asList(1, 2),Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
		System.out.println("�ϲ� Stream��" + together);
	}
	
	/**
	 * ���� #max��#min
	 */
	public static void testMaxMin() {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(3);
		arrayList.add(5);
		arrayList.add(2);
		arrayList.add(9);
		arrayList.add(1);
//		int max = arrayList.stream().max(Integer::compareTo).get();
		int max = arrayList.stream().max((a, b) -> a.compareTo(b)).get();
		int min = arrayList.stream().min(Integer::compareTo).get();
		System.out.println("���ֵ��" + max + "����Сֵ��" + min);
	}
	
	/**
	 * ���� #reduce
	 * #reduce ��һ����Ϊ��ʼֵ
	 */
	public static void testReduce() {
		// �ۼ�
		int acc = Stream.of(1, 2, 3, 5).reduce((a, b) -> a + b).get();
		int acc2 = Stream.of(1, 2, 3, 5).reduce(0, (a, b) -> a + b);
		System.out.println("acc = " + acc + "��acc2 = " + acc2);
		// �۳�
		Integer multiply = Stream.of(1, 2, 3, 5).reduce(1, (a, b) -> a * b);
		System.out.println("multiply = " + multiply);
	}
}
