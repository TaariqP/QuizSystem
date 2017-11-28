package consolidation;

import javax.swing.JFrame;

public class ServerTest extends JFrame {
	public static void main(String[] args) {
		Server server1 = new Server();
		server1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		server1.startRunning();

	}

}
