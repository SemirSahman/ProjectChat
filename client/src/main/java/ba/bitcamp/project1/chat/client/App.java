package ba.bitcamp.project1.chat.client;
//
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import ba.bitcamp.project1.chat.common.enums.ProtocolEnum;
import ba.bitcamp.project1.chat.common.model.ClientRequestModel;
import ba.bitcamp.project1.chat.common.thread.pool.ChatThreadExecutor;
import ba.bitcamp.project1.chat.model.ClientSocket;
import ba.bitcamp.project1.chat.queue.ClientQueue;
import ba.bitcamp.project1.chat.socket.ClientThreadReader;

public class App {
	
	private static ClientSocket clientSocket;
	private static ClientThreadReader threadReader;
	private static ChatThreadExecutor threadExecutor;
	
	public static void initConnection(String hostname, Integer port, String nickname) throws UnknownHostException, IOException {
		clientSocket = new ClientSocket(hostname, port);
		threadReader = new ClientThreadReader(clientSocket, new ClientQueue(100));
		threadExecutor = new ChatThreadExecutor(5);
		
		threadExecutor.getExecutorService().execute(threadReader);
		
		System.out.println("Connection estabilished with server.");
		
		ClientRequestModel cc = new ClientRequestModel(nickname);
		cc.setCode(ProtocolEnum.INIT_CONNECTION);
		
		clientSocket.send(cc);
	}
	
	public static void getConnectedUsers() {
		
	}

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		
		String choice = "";
		
		while(!choice.equalsIgnoreCase("quit") || !choice.equalsIgnoreCase("q")) {
			System.out.println("1. Connect with server");
			System.out.println("2. Get list of online users");
			System.out.println("3. Send message to user(s)");
			
			System.out.println("Choice: ");
			Scanner scanIn = new Scanner(System.in);
			choice = scanIn.nextLine();
			
			if(choice.equalsIgnoreCase("1")) {
				System.out.println("Hostname: ");
				String host = scanIn.nextLine();
				System.out.println("Port: ");
				Integer port = Integer.valueOf(scanIn.nextLine());
				System.out.println("Nickname: ");
				String nickname = scanIn.nextLine();
				
				initConnection(host, port, nickname);
			}
		}

	}

}
