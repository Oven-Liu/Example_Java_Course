package concurrent;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ExecutorService ִ��������
 * @author Xudong.Liu  2018��9��2�� ����12:21:38
 */
public class a02_ExecutorService {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//testExecute();
		//testSubmit();
		//testSubmit2();
		//testSubmit3();
		//testInvokeAny();
		testInvokeAll();
	}
	
	/**
	 * execute(Runnable) ����
	 */
	public static void testExecute() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("Asynchronous task");
			}
		});
		executorService.shutdown();
	}
	
	/**
	 * submit(Runnable) ����������ֵΪ null
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void testSubmit() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<?> future = executorService.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("Asynchronous task");
			}
		});
		// ���ִ���� run ������ķ���ֵ������ʹ�õ� Runnable����������û�з���ֵ�����ص��� null
		Object result = future.get();
		System.out.println(result); // null
		executorService.shutdown();
	}
	
	/**
	 * submit(Runnable, T) ����
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void testSubmit2() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<Object> future = executorService.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("Asynchronous task");
			}
		}, "Runnable Result");
		// ���ִ���� run ������ķ���ֵ
		Object result = future.get();
		System.out.println(result); // Runnable Result
		executorService.shutdown();
	}
	
	/**
	 * submit(Callable) ����
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void testSubmit3() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<Object> future = executorService.submit(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				System.out.println("Asynchronous task");
				return "Callable Result";
			}
		});
		// ���ִ���� run ������ķ���ֵ
		Object result = future.get();
		System.out.println(result); // Callable Result
		executorService.shutdown();
	}
	
	/**
	 * invokeAny(Collection<Callable>) ����
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void testInvokeAny() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		HashSet<Callable<String>> callables = new HashSet<Callable<String>>();
		callables.add(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "task 1";
			}
		});
		callables.add(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "task 2";
			}
		});
		callables.add(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "task 3";
			}
		});
		String result = executorService.invokeAny(callables);
		System.out.println(result);
		executorService.shutdown();
	}
	
	/**
	 * invokeAll(Collection<Callable>) ����
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void testInvokeAll() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		HashSet<Callable<String>> callables = new HashSet<Callable<String>>();
		callables.add(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "task 1";
			}
		});
		callables.add(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "task 2";
			}
		});
		callables.add(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "task 3";
			}
		});
		List<Future<String>> list = executorService.invokeAll(callables);
		for (Future<String> future : list) {
			System.out.println("future.get()=" + future.get());
		}
		executorService.shutdown();
	}
}
