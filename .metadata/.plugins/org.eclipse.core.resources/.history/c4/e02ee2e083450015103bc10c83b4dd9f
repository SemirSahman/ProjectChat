package ba.bitcamp.project1.chat.socket;

import java.io.IOException;

import ba.bitcamp.project1.chat.common.data.processing.JSONTransformator;
import ba.bitcamp.project1.chat.model.ClientSocket;
import ba.bitcamp.project1.chat.queue.ClientQueue;

public class ClientThreadReader implements Runnable {
	
	private ClientSocket cSocket;
	private ClientQueue readQueue;
	
	public ClientThreadReader() {
		throw new IllegalArgumentException("You have to pass client socket object.");
	}
	
	public ClientThreadReader(ClientSocket cSocket, ClientQueue clientQueue) {
		this.cSocket = cSocket;
		this.readQueue = clientQueue;
	}
	
	public void run() {
		
		String dataInput = null;
		try {
			while (cSocket != null && !cSocket.getcSocket().isClosed() && (dataInput = cSocket.getInputStream().readLine()) != null) {
				try {
					Object dataModel = JSONTransformator.convertString(dataInput);
					
					// add response in queue
					readQueue.addModel(dataModel);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Thread.sleep(1000);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

		
	}//

}
