package ba.bitcamp.project1.chat.queue;
//
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ClientQueue {

	private Integer limit;
	private BlockingQueue<Object> clientQueue;

	public ClientQueue() {
		this.limit = new Integer(1000);
		this.clientQueue = new ArrayBlockingQueue<Object>(this.limit);
	}

	public ClientQueue(Integer limit) {
		this.limit = limit;
		this.clientQueue = new ArrayBlockingQueue<Object>(this.limit);
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public BlockingQueue<Object> getClientQueue() {
		return clientQueue;
	}

	public void setClientQueue(BlockingQueue<Object> clientQueue) {
		this.clientQueue = clientQueue;
	}
	
	public void addModel(Object model) throws InterruptedException {
		
		if(model == null)
			return;
		
		this.clientQueue.put(model);
	}

}
