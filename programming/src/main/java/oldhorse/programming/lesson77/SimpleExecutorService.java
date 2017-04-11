package oldhorse.programming.lesson77;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleExecutorService extends AbstractExecutorService {

	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	public List<Runnable> shutdownNow() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	public void execute(Runnable command) {
		// TODO Auto-generated method stub
		new Thread(command).start();
	}

}
