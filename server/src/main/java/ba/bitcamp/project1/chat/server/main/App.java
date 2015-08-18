package ba.bitcamp.project1.chat.server.main;
//
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ba.bitcamp.project1.chat.server.socket.ChatServerSocket;

public class App {

	public static void main(String[] args) throws IOException {
		ChatServerSocket chatSocket = new ChatServerSocket(2815, 100);
		
		ServerSocket ss = chatSocket.getServerSocket();
		
		while(true) {
			Socket client = ss.accept();		
			System.out.println("Connection accepted" + client.getInetAddress().getCanonicalHostName() + " from port: " + client.getPort());	
			chatSocket.handleSocket(client);
		}
		
	}

}
