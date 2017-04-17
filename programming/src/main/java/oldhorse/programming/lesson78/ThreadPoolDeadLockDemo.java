package oldhorse.programming.lesson78;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class ThreadPoolDeadLockDemo {

	private static final int THREAD_NUM = 5;
	//static ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);//dead lock
	//static ExecutorService executor = Executors.newCachedThreadPool();	//no dead lock
	static ExecutorService executor = new ThreadPoolExecutor(	//测试RejectedExecutionException
			THREAD_NUM, THREAD_NUM, 0, TimeUnit.SECONDS,
			new SynchronousQueue<Runnable>()
			);
	
	static class TaskA implements Runnable{

		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Future<?> result = executor.submit(new TaskB());
			try {
				result.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("taskA complete.");
		}
		
	}
	
	static class TaskB implements Runnable{

		public void run() {
			System.out.println("taskB complete.");
		}
		
	}
	
	public static void main(String[] args){
		//Collection<TaskA> list = new ArrayList<TaskA>();
//		ThreadPoolDeadLockDemo deadLockDemo = new ThreadPoolDeadLockDemo();
//		for (int i=0; i<5; i++){
//			//list.add(new TaskA());
//			deadLockDemo.executor.submit(deadLockDemo.new TaskA());
//		}

		for (int i=0; i<THREAD_NUM; i++) {
			//executor.submit(new TaskA());	//不会触发RejectedExecutionException
			executor.execute(new TaskA());	//使用SynchronousQueue时会处罚RejectedExecutionException	
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.shutdown();
	}
}
