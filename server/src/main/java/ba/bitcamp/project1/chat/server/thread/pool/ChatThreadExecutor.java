package ba.bitcamp.project1.chat.server.thread.pool;
//
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatThreadExecutor {

	private Integer limitThread;
	private ExecutorService executorService;

	public ChatThreadExecutor(Integer limit) {
		this.limitThread = limit;
		this.executorService = Executors.newFixedThreadPool(this.limitThread);
	}

	public Integer getLimitThread() {
		return limitThread;
	}

	public void setLimitThread(Integer limitThread) {
		this.limitThread = limitThread;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

}
