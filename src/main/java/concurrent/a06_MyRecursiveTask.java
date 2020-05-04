package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class a06_MyRecursiveTask extends RecursiveTask<Long> {

	private long workload = 0;
	
	public a06_MyRecursiveTask(long workload) {
		this.workload = workload;
	}
	
	public static void main(String[] args) {
		// 创建了一个并行级别为 4 的 ForkJoinPool
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		// 创建一个有返回值的任务
		a06_MyRecursiveTask task = new a06_MyRecursiveTask(70);
		// 线程池执行并返回结果
		Long mergedResult = forkJoinPool.invoke(task);
		System.out.println("mergedResult = " + mergedResult);
	}
	
	@Override
	protected Long compute() {
		if (this.workload > 16) {
			System.out.println("Splitting workload : " + this.workload);
			ArrayList<a06_MyRecursiveTask> subtasks = new ArrayList<a06_MyRecursiveTask>();
//			subtasks.addAll(createSubtasks());
			a06_MyRecursiveTask subtask1 = new a06_MyRecursiveTask(workload / 2);
			a06_MyRecursiveTask subtask2 = new a06_MyRecursiveTask(workload - (workload / 2));
			subtasks.add(subtask1);
			subtasks.add(subtask2);
			for (a06_MyRecursiveTask subtask : subtasks) {
				subtask.fork();
			}
			long result = 0;
			for (a06_MyRecursiveTask subtask : subtasks) {
				result += subtask.join();
			}
			System.out.println("result: " + result);
			return result;
		} else {
			System.out.println("Doing workload myself: " + this.workload);
			return workload * 3;
		}
		
	}
	
	private List<a06_MyRecursiveTask> createSubtasks() {
		ArrayList<a06_MyRecursiveTask> subtasks = new ArrayList<a06_MyRecursiveTask>();
		a06_MyRecursiveTask subtask1 = new a06_MyRecursiveTask(workload / 2);
		a06_MyRecursiveTask subtask2 = new a06_MyRecursiveTask(workload / 2);
		subtasks.add(subtask1);
		subtasks.add(subtask2);
		return subtasks;
	}

}
