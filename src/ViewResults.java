package consolidation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ViewResults extends JFrame {
	public ViewResults() {
		JTextArea results = new JTextArea(60, 60);
		add(results);

		File dir = new File("F:\\Programming Project\\workspace\\consolidation\\resultsBank\\");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				// Do something with child
				Scanner sc;
				try {
					sc = new Scanner(new FileInputStream(child));
					String content = new String();
					content = sc.nextLine();
					int result = Integer.parseInt(content);
					//results.append(Login.userName + "achieved: " + result);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}

		} else {
			// Handle the case where dir is not really a directory.
			// Checking dir.isDirectory() above would not be sufficient
			// to avoid race conditions with another process that deletes
			// directories.
		}
	}
}
