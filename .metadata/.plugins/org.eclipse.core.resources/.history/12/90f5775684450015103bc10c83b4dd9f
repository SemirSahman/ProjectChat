package ba.bitcamp.project1.chat.server.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ba.bitcamp.project1.chat.common.data.processing.JSONTransformator;

public class ServerSocketThread implements Runnable {
	
	private Socket tSocket;
	private ChatServerSocket chatServerSocket;
	
	public ServerSocketThread(Socket tSocket, ChatServerSocket chatServerSocket) {
		this.tSocket = tSocket;
		this.chatServerSocket = chatServerSocket;
	}

	public void run() {
		PrintWriter out = null;
		BufferedReader in = null;
		
		try {
			out = new PrintWriter(tSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(tSocket.getInputStream()));
			
			String inputLine;
			while(!tSocket.isClosed() && (inputLine = in.readLine()) != null) {		
				
				//TODO: Handle cases when user try to get data which are not for him
				Object transformedData = JSONTransformator.convertString(inputLine);
				chatServerSocket.handleData(transformedData, tSocket, out, in);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
