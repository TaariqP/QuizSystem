package consolidation;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class clientTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 127.0.0.1 means localhost
		Client testClient = new Client("127.0.0.1");

		testClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testClient.run();
		testClient.startRunning();
	}

}