package consolidation;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Creating a class that inherits from the JFrame Class
public class Client extends JFrame {
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIP;
	private Socket connection;
	// ip address of the server
	private String serverIp;

	// give the ip address of the server to connect
	public Client(String host) {
		super("Client");
		serverIp = host;

		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				sendMessage(event.getActionCommand());
				userText.setText("");
			}
		});

		add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
		setSize(500, 500);
		setVisible(true);
	}

	// connect to server
	public void run() {
		while (true) {
			showMessage("attempting connection ...... ");
			try {
				connection = new Socket("localhost", 5555);
				if (connection.isConnected())
					break;
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block

			}

		}
		showMessage("connected ..... \n " + connection.getInetAddress().getHostName());
	}

	public void startRunning() {

		try {
			// connect to one specific server
			// connectToServer();
			setupStreams();
			whileChatting();
		} catch (EOFException eofException) {
			showMessage("Client terminate connection \n");
		} catch (IOException ioException) {
			showMessage("error");
		} finally {
			closeStream();
		}

	}

	// setup streams to send and recieve message
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("streams are now setup");
	}

	// while chatting with server
	private void whileChatting() throws IOException {
		ableToType(true);
		do {

			try {
				// to read whatever sent as string
				message = (String) input.readObject();
				showMessage("\n " + message);
			} catch (ClassNotFoundException classNotFoundException) {
				showMessage("unknow object type");
			}
		} while (!message.equals("Server :  END"));
	}

	// close the streams and sockets
	private void closeStream() {
		showMessage("\n closing Stream down .....");
		ableToType(false);
		try {

			output.close();
			input.close();
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// send message to server
	private void sendMessage(String message) {
		try {
			output.writeObject("Client : " + message);
			output.flush();
			showMessage("\n Client : " + message);
		} catch (IOException ioException) {
			chatWindow.append("error \n ");
		}
	}

	// update chat window
	private void showMessage(final String text) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				chatWindow.append(text);
			}
		});
	}

	// permession to type in textbox
	private void ableToType(final boolean variable) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				userText.setEditable(variable);
			}
		});
	}
}