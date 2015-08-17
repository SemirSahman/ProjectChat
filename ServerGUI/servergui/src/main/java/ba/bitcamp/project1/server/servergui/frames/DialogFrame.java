package ba.bitcamp.project1.server.servergui.frames;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ba.bitcamp.project1.chat.server.socket.ChatServerSocket;
import ba.bitcamp.project1.server.servergui.models.ServerModelInfo;

public class DialogFrame  extends JFrame implements ActionListener{
	

			private static final long serialVersionUID = 1L;
			
			private JLabel ipAddress = new JLabel("IP address:");
			private JLabel port = new JLabel("Port:");
			private JLabel limit = new JLabel("Limit:");
			
			private JTextField ipAddressField = new JTextField("");
			private JTextField portField = new JTextField("");
			private JTextField limitField = new JTextField("");
			
			private JButton ok = new JButton("Ok");
			private JButton cancel = new JButton("Cancel");
			
			private ServerModelInfo serverModelInfoV;
			private ServerMainFrame serverMainFrame;
			
			public DialogFrame(ServerModelInfo serverModelInfo, ServerMainFrame serverMainFrame){
				this.serverModelInfoV = serverModelInfo;
				this.serverMainFrame = serverMainFrame;
				
				JPanel panel = new JPanel();
				
				panel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
				panel.setLayout(new GridLayout(4, 3, 2, 2));
				
				panel.add(ipAddress);
				panel.add(ipAddressField);
				
				panel.add(port);
				panel.add(portField);
				
				panel.add(limit);
				panel.add(limitField);
				
				panel.add(ok);
				panel.add(cancel);
				
				ok.addActionListener(this);
				cancel.addActionListener(this);
				add(panel);
					
				setTitle("Start setings");
				setSize(400, 250);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				setVisible(true);
			
			}

			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() instanceof JButton) {
					if (((JButton)e.getSource()).getText().equalsIgnoreCase("ok")) {
										
						serverModelInfoV.setHostname(ipAddressField.getText());
						serverModelInfoV.setPort(Integer.valueOf(portField.getText()));		
						serverModelInfoV.setLimitUserConnection(Integer.valueOf(limitField.getText()));
						
						this.serverMainFrame.setServerStarted(true);
						this.serverMainFrame.getExecutorService().execute(new Runnable() {
							
							public void run() {
								ChatServerSocket chatServerSocket = new ChatServerSocket(serverModelInfoV.getPort(), serverModelInfoV.getLimitUserConnection());
								serverMainFrame.setServerChatSocket(chatServerSocket);
								try {
									ServerSocket serverSocket = chatServerSocket.getServerSocket(serverModelInfoV.getHostname());
									serverMainFrame.getLogBox().append("Server started on port " + serverModelInfoV.getPort() + "\n");
									
									while(serverSocket != null && !serverSocket.isClosed()) {
										
										Socket cSocket = serverSocket.accept();
										serverMainFrame.getClientSockets().add(cSocket);
										serverMainFrame.getLogBox().append("Client connected from IP: " + cSocket.getInetAddress().getHostAddress() + "\n");										
										chatServerSocket.handleSocket(cSocket);		
										
										Thread.sleep(1000);
									}
										
								} catch (UnknownHostException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
						});
			
						dispose();
					} else {
						dispose();
					}
				}
				
			}

}
