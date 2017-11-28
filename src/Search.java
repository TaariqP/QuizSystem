package consolidation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Position;
import javax.swing.tree.TreePath;

public class Search extends JFrame implements ActionListener {
	int startRow = 0;
	String prefix = "b";
	private JTextField searchedForInput;
	private JButton search;
	private String searchFor;

	public Search() {
		super("Search Tool");
		FlowLayout layout = new FlowLayout();
		JPanel panel = new JPanel();
		searchedForInput = new JTextField(20);
		search = new JButton("search");
		panel.add(searchedForInput);
		panel.add(search);
		search.addActionListener(this);
		add(panel);
	}

	public void actionPerformed(ActionEvent e) {
		searchFor = searchedForInput.getText();
		MainApplication mainapplication = new MainApplication();
		DynamicTree treePanel = new DynamicTree();
		// treePanel.find(searchFor);
		treePanel.find("Child 1");
		// mainapplication.Search(searchFor);
		// TreePath path = treePanel.getNextMatch(prefix, startRow,
		// Position.Bias.Forward);
	}

	public static void main(String[] args) {
		Search search = new Search();
		search.setSize(500, 700);
		search.setLocationRelativeTo(null);
		search.setVisible(true);

	}

}