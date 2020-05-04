package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class a05_MyRecursiveAction extends RecursiveAction {

	private long workload = 0;
	
	public a05_MyRecursiveAction(long workload) {
		this.workload = workload;
	}
	
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		a05_MyRecursiveAction action = new a05_MyRecursiveAction(70);
		forkJoinPool.invoke(action);
		/* 
			结果
		 	Splitting workload : 70
			Splitting workload : 35
			Splitting workload : 35
			Splitting workload : 17
			Splitting workload : 18
			Splitting workload : 18
			Splitting workload : 17
			Doing workload myself: 9
			Doing workload myself: 9
			Doing workload myself: 8
			Doing workload myself: 8
			Doing workload myself: 9
			Doing workload myself: 9
		 */
	}

	@Override
	protected void compute() {
		/* 如果工作超过阈值，把任务分解成更小的任务  */
		if (workload > 16) {
			System.out.println("Splitting workload : " + this.workload);
			ArrayList<a05_MyRecursiveAction> subtasks = new ArrayList<a05_MyRecursiveAction>();
//			subtasks.addAll(createSubtasks());
			a05_MyRecursiveAction subtask1 = new a05_MyRecursiveAction(workload / 2);
			a05_MyRecursiveAction subtask2 = new a05_MyRecursiveAction(workload - (workload / 2));
			subtasks.add(subtask1);
			subtasks.add(subtask2);
			for (a05_MyRecursiveAction subtask : subtasks) {
				subtask.fork();
			}
		} else {
			System.out.println("Doing workload myself: " + this.workload);
		}
		
	}
	
	private List<a05_MyRecursiveAction> createSubtasks() {
		ArrayList<a05_MyRecursiveAction> subtasks = new ArrayList<a05_MyRecursiveAction>();
		a05_MyRecursiveAction subtask1 = new a05_MyRecursiveAction(workload / 2);
		a05_MyRecursiveAction subtask2 = new a05_MyRecursiveAction(workload / 2);
		subtasks.add(subtask1);
		subtasks.add(subtask2);
		return subtasks;
	}

}
