/* This Module represents the form to edit a question
 * and overwrites the existing question */
package consolidation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class EditQForm extends JFrame implements ActionListener {
	private JTextField qtitle;
	private JTextArea q;
	private JComboBox<String> unitList;
	private String sUnit;
	private String content;
	private String qTitle;

	public EditQForm() {
		super("Edit a Question");
		JPanel panel = new JPanel();
		add(panel);
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JLabel label1 = new JLabel("Enter Question Title");
		panel.add(label1);
		JLabel label2 = new JLabel("Enter Question");
		panel.add(label2);
		JLabel label3 = new JLabel("Select Unit:");
		panel.add(label3);
		qtitle = new JTextField(20);
		panel.add(qtitle);
		q = new JTextArea(5, 20);
		panel.add(q);
		JButton addButton = new JButton("Submit");
		panel.add(addButton);
		addButton.addActionListener(this);

		String[] units = { "Unit 1", "Unit 2", "Unit 3", "Unit 4", "Unit 5" };

		// Create the combo box, select item at index 4.
		// Indices start at 0, so 4 specifies the pig.
		JComboBox<String> unitList = new JComboBox<String>(units);
		unitList.setSelectedIndex(4);
		unitList.addActionListener(this);
		panel.add(unitList);

		layout.putConstraint(SpringLayout.WEST, label1, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label1, 50, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, label2, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label2, 100, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, label3, 100, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label3, 200, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, qtitle, 180, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, qtitle, 50, SpringLayout.WEST, panel);

		layout.putConstraint(SpringLayout.WEST, q, 180, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, q, 100, SpringLayout.WEST, panel);

		layout.putConstraint(SpringLayout.WEST, unitList, 200, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, unitList, 200, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, addButton, 200, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, addButton, 300, SpringLayout.NORTH, panel);
		sUnit = (String) unitList.getSelectedItem();
		JOptionPane.showMessageDialog(null, qTitle, "Error Message", JOptionPane.ERROR_MESSAGE);
		// addFields();

	}


	public void actionPerformed(ActionEvent e) {
		PrintWriter out;
		try {
			qTitle = qtitle.getText();
			out = new PrintWriter(qtitle.getText() + ".txt");
			out.println(qtitle.getText());
			out.println(q.getText());
			out.println(sUnit);
			out.close();
			dispose();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	public static void main(String[] args) {
		EditQForm EditQForm = new EditQForm();
		EditQForm.setSize(500, 700);
		EditQForm.setLocationRelativeTo(null);
		EditQForm.setVisible(true);

	}

}
