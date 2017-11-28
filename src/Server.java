package consolidation;

//Importing the necessary Java Swing Components/Modules
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Creating a class that inherits from the JFrame Class
public class Server extends JFrame {
	// Declaring Local Variables
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;

	// Declaring the constructor
	public Server() {
		// Creating the title of the Form
		super("Ultimate Quiz System instant messenger (Server)");
		// Creating the text field that the user will type messages into
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sendMessage(event.getActionCommand());
				userText.setText("");
			}
		});

		add(userText, BorderLayout.SOUTH);
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow));
		setSize(300, 150);
		setVisible(true);
	}

	// Setting up and running the Server
	public void startRunning() {
		try {
			/*
			 * The program tries to create a server socket and binds it to the
			 * local Port 5555, with the maximum length of the queue of incoming
			 * connections
			 */
			server = new ServerSocket(5555, 100);
			while (true) {
				try {
					waitForConnection();
					setupStreams();
					whileChatting();
				} catch (EOFException eofException) {
					showMessage("\n Server ended the connection!");
				} finally {
					closeConnections();
				}
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		;
	}

	// wait for connection, then display connection info
	public void waitForConnection() throws IOException {
		showMessage("waiting for someone to connect...\n");
		connection = server.accept();
		showMessage("Now connected to " + connection.getInetAddress().getHostName());
	}

	// get stream to send and receive data
	public void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams are now setup! \n");
	}

	// during the chat conversation
	private void whileChatting() throws IOException {
		String message = "you are now connected";
		sendMessage(message);
		ableToType(true);
		do {
			try {
				message = (String) input.readObject();
				showMessage("\n" + message);
			} catch (ClassNotFoundException classNotFoundException) {
				showMessage("\n idn wtf the user sent");
			}
		} while (!message.equals("CLIENT - END"));
	}

	// close streams and methods after you are done chatting
	public void closeConnections() {
		showMessage("\n Closing connections ...");
		ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// send message to client
	private void sendMessage(String message) {
		try {
			output.writeObject("SERVER - " + message);
			output.flush();
			showMessage("\nSERVER - " + message);
		} catch (IOException ioException) {
			chatWindow.append("\n ERROR: DUDE I CAN'T SEND THAT MESSAGE!");
		}
	}

	// updates chatWindow
	private void showMessage(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				chatWindow.append(text);
			}
		});
	}

	// able to type
	private void ableToType(final boolean tof) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				userText.setEditable(tof);
			}
		});
	}
}