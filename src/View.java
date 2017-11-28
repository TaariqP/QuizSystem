package consolidation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class View extends JFrame {
	public String qTitle;

	public View() {
		super("Question Viewer");
		JPanel panel = new JPanel();
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JTextArea question = new JTextArea(60, 60);
		panel.add(question);
		add(panel);

	}

	// The Main Function creates the Frame. This is the first function that is
	// called when the program is run
	public static void main(String[] args) {
		View View = new View();
		View.setSize(800, 500);
		View.setLocationRelativeTo(null);
		View.setVisible(true);
		MainApplication mainapplication = new MainApplication();
		// mainapplication.returnqTitle();

	}
}
