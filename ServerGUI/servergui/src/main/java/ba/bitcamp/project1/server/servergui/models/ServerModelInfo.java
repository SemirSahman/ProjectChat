package ba.bitcamp.project1.server.servergui.models;

public class ServerModelInfo {

	private String hostname;
	private Integer port;
	private Integer limitUserConnection;

	public ServerModelInfo() {
		// TODO Auto-generated constructor stub
	}

	public ServerModelInfo(String hostname, Integer port, Integer limit) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.limitUserConnection = limit;
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

	public Integer getLimitUserConnection() {
		return limitUserConnection;
	}

	public void setLimitUserConnection(Integer limitUserConnection) {
		this.limitUserConnection = limitUserConnection;
	}

}
