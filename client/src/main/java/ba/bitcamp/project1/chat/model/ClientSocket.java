package ba.bitcamp.project1.chat.model;
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import ba.bitcamp.project1.chat.common.json.JSONMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientSocket {

	private String hostname;
	private Integer port;
	private Socket cSocket;

	private PrintWriter out;
	private BufferedReader in;
	
	private static ObjectMapper objMapper = JSONMapper.getObjectMapper();
	
	public ClientSocket() {
		throw new IllegalArgumentException("You have to pass parameters for creating client socket.");
	}

	public ClientSocket(String hostname, Integer port) throws UnknownHostException, IOException {
		this.hostname = hostname;
		this.port = port;
		
		this.cSocket = new Socket(this.hostname, this.port);
		this.out = new PrintWriter(this.cSocket.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(this.cSocket.getInputStream()));
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public synchronized Socket getcSocket() {
		return cSocket;
	}

	public void setcSocket(Socket cSocket) {
		this.cSocket = cSocket;
	}
	
	public synchronized PrintWriter getOutputStream() {
		return this.out;
	}
	
	public synchronized BufferedReader getInputStream() {
		return this.in;
	}
	
	public void send(Object value) throws JsonProcessingException {
		getOutputStream().println(objMapper.writeValueAsString(value));
	}

}
