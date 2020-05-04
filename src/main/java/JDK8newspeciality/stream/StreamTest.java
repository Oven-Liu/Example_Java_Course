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
	 * 测试集合接口生成流的方法：#stream、#parallelStream，及 Stream 创建流的方法：#of
	 * parallelStream 是流并行处理程序的代替方法。
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
	 * 测试 #forEach
	 * Stream 提供了新的方法'forEach' 来迭代流中的每个数据。
	 * 测试 #limit
	 * limit 方法用于获取指定数量的流。
	 */
	public static void testForEach() {
		Random random = new Random();
		random.ints().limit(10).forEach(System.out::println);
	}
	
	/**
	 * 测试 #map
	 * map 方法用于映射每个元素到对应的结果。
	 */
	public static void testMap() {
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		// 获取每个元素的平方
		numbers.stream().map(i -> i*i).distinct().forEach(System.out::println);
	}
	
	/**
	 * 测试 #filter
	 * filter 方法用于通过设置的条件过滤出元素。
	 */
	public static void testFilter() {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		// 获取空字符串的数量
		long count = strings.stream().filter(string -> string.isEmpty()).count();
		System.out.println("空字符串的数量是：" + count);
	}
	
	/**
	 * 测试 #sorted
	 * sorted 方法用于对流进行排序。
	 */
	public static void testSorted() {
		Random random = new Random();
		random.ints().limit(10).sorted().forEach(System.out::println);
	}
	
	/**
	 * 测试 Collector 类
	 * Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。
	 */
	public static void testCollector() {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		// 将流转换为集合
		List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		System.out.println("筛选列表集合：" + filtered);
		// 将流中的元素聚合
		String mergeString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
		System.out.println("合并字符串：" + mergeString);
	}
	
	/**
	 * 测试 #summaryStatistics
	 * 用于统计
	 */
	public static void testSummaryStatistics() {
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		IntSummaryStatistics sm = numbers.stream().mapToInt(i -> i).summaryStatistics();
		System.out.println("最大的数：" + sm.getMax());
		System.out.println("最小的数：" + sm.getMin());
		System.out.println("所有数之和：" + sm.getSum());
		System.out.println("平均数：" + sm.getAverage());
	}
	
	/**
	 * 测试 #flatMap
	 * 合并多个流
	 */
	public static void testFlatMap() {
		List<Integer> together = Stream.of(Arrays.asList(1, 2),Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
		System.out.println("合并 Stream：" + together);
	}
	
	/**
	 * 测试 #max、#min
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
		System.out.println("最大值：" + max + "，最小值：" + min);
	}
	
	/**
	 * 测试 #reduce
	 * #reduce 第一参数为初始值
	 */
	public static void testReduce() {
		// 累加
		int acc = Stream.of(1, 2, 3, 5).reduce((a, b) -> a + b).get();
		int acc2 = Stream.of(1, 2, 3, 5).reduce(0, (a, b) -> a + b);
		System.out.println("acc = " + acc + "，acc2 = " + acc2);
		// 累乘
		Integer multiply = Stream.of(1, 2, 3, 5).reduce(1, (a, b) -> a * b);
		System.out.println("multiply = " + multiply);
	}
}
