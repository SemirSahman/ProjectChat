package ba.bitcamp.project1.chat.server.socket;
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ba.bitcamp.project1.chat.common.enums.ProtocolEnum;
import ba.bitcamp.project1.chat.common.json.JSONMapper;
import ba.bitcamp.project1.chat.common.model.ClientRequestModel;
import ba.bitcamp.project1.chat.common.model.MessageModel;
import ba.bitcamp.project1.chat.common.model.meta.UserMetaModel;
import ba.bitcamp.project1.chat.common.thread.pool.ChatThreadExecutor;

public class ChatServerSocket {

	private Integer limitConnection;
	private Integer port;
	private ServerSocket serverSocket;
	private Map<String, SocketModel> mapUsers;
	private ChatThreadExecutor chatThreadExecutor;

	public ChatServerSocket() {

	}

	public ChatServerSocket(Integer port, Integer limitConnection) {
		this.limitConnection = limitConnection;
		this.port = port;
		this.mapUsers = new HashMap<String, SocketModel>();
		this.chatThreadExecutor = new ChatThreadExecutor(this.limitConnection);
	}

	public Integer getLimitConnection() {
		return limitConnection;
	}

	public void setLimitConnection(Integer limitConnection) {
		this.limitConnection = limitConnection;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public ServerSocket getServerSocket() throws IOException {

		if (this.serverSocket == null)
			this.serverSocket = new ServerSocket(getPort());

		return this.serverSocket;
	}

	public ServerSocket getServerSocket(String hostname)
			throws UnknownHostException, IOException {

		if (this.serverSocket == null)
			this.serverSocket = new ServerSocket(getPort(), 50,
					InetAddress.getByName(hostname));

		return this.serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public Map<String, SocketModel> getMapUsers() {
		return mapUsers;
	}

	public ChatThreadExecutor getChatThreadExecutor() {
		return chatThreadExecutor;
	}

	public void setChatThreadExecutor(ChatThreadExecutor chatThreadExecutor) {
		this.chatThreadExecutor = chatThreadExecutor;
	}

	public void handleSocket(Socket tSocket) {
		this.chatThreadExecutor.getExecutorService().execute(
				new ServerSocketThread(tSocket, this));
	}

	public void handleData(Object dataModel, Socket socket, PrintWriter out,
			BufferedReader in) throws IOException {
		boolean writePermission = true;

		if (dataModel instanceof ClientRequestModel) {
			ClientRequestModel initConnModel = (ClientRequestModel) dataModel;
			SocketModel uSocket = mapUsers.get(initConnModel.getUid());

			if (initConnModel.getCode() == ProtocolEnum.INIT_CONNECTION) {

				if (uSocket == null) {
					mapUsers.put(
							initConnModel.getUid(),
							new SocketModel(socket,
									initConnModel.getNickName(), initConnModel
											.getUid()));
					initConnModel.setCode(ProtocolEnum.SUCCESS);

				} else {
					initConnModel.setCode(ProtocolEnum.ALREADY_CONNECTED);
				}

			} else if (initConnModel.getCode() == ProtocolEnum.CLOSE_CONNECTION) {

				if (uSocket != null) {
					out.close();
					in.close();
					uSocket.getuSocket().close();
					mapUsers.remove(initConnModel.getUid());
					writePermission = false;
				} else {
					initConnModel.setCode(ProtocolEnum.FAIL);
				}

			} else if (initConnModel.getCode() == ProtocolEnum.GET_CONNECTED_USERS) {

				List<UserMetaModel> userList = new ArrayList<UserMetaModel>();

				for (Map.Entry<String, SocketModel> userEntry : mapUsers
						.entrySet()) {
					if (!userEntry.getKey().equals(initConnModel.getUid()))
						userList.add(new UserMetaModel(userEntry.getValue()
								.getNickName(), userEntry.getValue().getUid()));
				}

				initConnModel.setData(userList);
			} else {
				initConnModel.setCode(ProtocolEnum.FAIL);
			}

			// prepared data send to client
			if (writePermission) {
				out.println(JSONMapper.getObjectMapper().writeValueAsString(
						initConnModel));
				out.flush();
			}
		} else if (dataModel instanceof MessageModel) {
			MessageModel messageModel = (MessageModel) dataModel;

			// TODO: Implement some additional checks for object inside message
			// model
			SocketModel uSocket = mapUsers.get(messageModel.getFrom().getUid());

			if (uSocket != null) {
				SocketModel tSocket = mapUsers.get(messageModel.getTo()
						.getUid());

				if (tSocket != null) {

					messageModel.setCode(ProtocolEnum.SUCCESS);
					PrintWriter pww = new PrintWriter(tSocket.getuSocket()
							.getOutputStream());
					pww.println(JSONMapper.getObjectMapper()
							.writeValueAsString(messageModel));
					pww.flush();

				}

			} else {
				messageModel.setCode(ProtocolEnum.FAIL);
			}

			if (writePermission) {
				out.println(JSONMapper.getObjectMapper().writeValueAsString(
						messageModel));
				out.flush();
			}
		}

	}// end method

}
