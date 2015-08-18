package ba.bitcamp.project1.chat.server.socket;
//
import java.net.Socket;

public class SocketModel {

	private Socket uSocket;

	private String nickName;

	private String uid;

	public SocketModel(Socket uSocket, String nickName, String uid) {
		this.uSocket = uSocket;
		this.nickName = nickName;
		this.uid = uid;
	}

	public SocketModel() {

	}

	public Socket getuSocket() {
		return uSocket;
	}

	public void setuSocket(Socket uSocket) {
		this.uSocket = uSocket;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
