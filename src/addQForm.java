/* This Module represents the AddQuestionForm/User Interface 
 * which saves a question */
package consolidation;
//Importing necesary modules
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.swing.*;

@SuppressWarnings("serial")
//Creating the Class to add a question and declaring inheritance
public class addQForm extends JFrame implements ActionListener {
	//Declaring Local Variables
	private static JTextField QUESTION_TITLE;
	private JTextArea question, answer, hint, solution;
	private JComboBox<String> unitList;
	private String selectedUnit;
	private JRadioButton one, two, three, four, five;
	private ButtonGroup group;
	public addQForm() {
		// Declaring the Constructor
		super("Add a Question");
		// Declaring all the Components that will be used
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
		JLabel label4 = new JLabel("Enter Answer");
		panel.add(label4);
		JLabel label5 = new JLabel("Enter Hint");
		panel.add(label5);
		JLabel label6 = new JLabel("Enter Solution");
		panel.add(label6);
		JLabel label7 = new JLabel("Difficulty Rating");
		panel.add(label7);
		QUESTION_TITLE = new JTextField(20);
		panel.add(QUESTION_TITLE);
		question = new JTextArea(5, 20);
		panel.add(question);
		answer = new JTextArea(5, 20);
		panel.add(answer);
		solution = new JTextArea(5, 20);
		panel.add(solution);
		hint = new JTextArea(5, 20);
		panel.add(hint);
		// Declaring the Radio Buttons
		one = new JRadioButton("1");
		two = new JRadioButton("2");
		three = new JRadioButton("3");
		four = new JRadioButton("4");
		five = new JRadioButton("5");
		// Adding the Radio Buttons to a group so that only one can be selected
		group = new ButtonGroup();
		group.add(one);
		group.add(two);
		group.add(three);
		group.add(four);
		group.add(five);

		// Creating a new panel for the radio buttons so they can be horizontal
		JPanel radioPanel = new JPanel(new GridLayout(1, 1));
		radioPanel.add(one);
		radioPanel.add(two);
		radioPanel.add(three);
		radioPanel.add(four);
		radioPanel.add(five);
		panel.add(radioPanel);

		// Creating the button to save the question
		JButton addButton = new JButton("Submit");
		panel.add(addButton);
		addButton.addActionListener(this);
		// Items to be put in the ComboBox
		String[] units = { "Unit 1", "Unit 2", "Unit 3", "Unit 4", "Unit 5" };
		// Create the combo box
		unitList = new JComboBox<String>(units);
		panel.add(unitList);

		// Adding constraints to make the layout user-friendly
		layout.putConstraint(SpringLayout.WEST, label1, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label1, 50, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, label2, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label2, 100, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, label3, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label3, 200, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, QUESTION_TITLE, 180, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, QUESTION_TITLE, 50, SpringLayout.WEST, panel);

		layout.putConstraint(SpringLayout.WEST, question, 180, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, question, 100, SpringLayout.WEST, panel);

		layout.putConstraint(SpringLayout.WEST, unitList, 180, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, unitList, 200, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, addButton, 200, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, addButton, 600, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, label4, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label4, 250, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, answer, 180, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, answer, 250, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, hint, 180, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, hint, 350, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, label5, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label5, 350, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, label6, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label6, 450, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, solution, 180, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, solution, 450, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, label7, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label7, 550, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, radioPanel, 180, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, radioPanel, 550, SpringLayout.NORTH, panel);
	}

	// Getter to find out which radioButton was selected
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}

	// Controls what occurs when the button is clicked
	public void actionPerformed(ActionEvent e) {
		PrintWriter out;
		if(question.getText() != null && question.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Please enter a Question!", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}
		else {
		try {
			// Converts the selected ComboBox to a String
			selectedUnit = unitList.getSelectedItem().toString();
			// Creates a new folder/directory
			new File("F:\\Programming Project\\workspace\\consolidation\\questionBank\\" + selectedUnit).mkdir();
			System.out.println(selectedUnit);

			// Creates a text file for the question
			out = new PrintWriter("F:\\Programming Project\\workspace\\consolidation\\questionBank\\" + selectedUnit
					+ "\\" + QUESTION_TITLE.getText() + ".txt");
			// Prints out the question to the text file in an acceptable format
			out.println(QUESTION_TITLE.getText());
			out.println(question.getText());
			out.println(answer.getText());
			out.println(hint.getText());
			out.println(solution.getText());
			out.println(selectedUnit);
			out.println(getSelectedButtonText(group));
			// Closes the file
			out.close();
			dispose();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		}
	}

	// Main Function to create the JFrame
	public static void main(String[] args) {
		addQForm addQ = new addQForm();
		addQ.setSize(500, 700);
		addQ.setLocationRelativeTo(null);
		addQ.setVisible(true);

	}

}
