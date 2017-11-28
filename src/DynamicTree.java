//This class represents an object that is a custom tree
package consolidation;

//Importing necessary modules
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SpringLayout;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

@SuppressWarnings("serial")
// Creating a Class that inherits from JPanel
class DynamicTree extends JPanel {
	// Declaring private variables that can only be accessed by the class
	private DefaultMutableTreeNode rootNode;
	private DefaultTreeModel treeModel;
	private JTree tree;
	private File file;
	// Toolkit allows the program to interact with hardware
	private Toolkit toolkit = Toolkit.getDefaultToolkit();

	// The constructor of this class is DynamicTree
	public DynamicTree() {
		// Declaring the layout for the Frame as a grid layout
		super(new GridLayout(1, 0));
		// The tree must have a root node that cannot be deleted.
		rootNode = new DefaultMutableTreeNode("Root Node");
		// Creating a TreeNode called treeModel
		treeModel = new DefaultTreeModel(rootNode);
		// Creating a JTree
		tree = new JTree(treeModel);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);

		// A Scroll pane allows the menu to be scrollable. This is added to the
		// frame.
		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane);

	}

	// This function is for viewing results
	public void viewResults() {
		// Creating the frame
		JFrame frame = new JFrame("Results Viewer");
		JTextArea results = new JTextArea(60, 60);
		frame.add(results);
		JFrame.setDefaultLookAndFeelDecorated(false);
		frame.setLocationRelativeTo(null);
		frame.setSize(500, 500);
		frame.setVisible(true);
		// Toolkit is used to set the size and positioning of the screen based
		// on the screen size.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		File dir = new File("F:\\Programming Project\\workspace\\consolidation\\resultsBank\\");
		// This loops through all the files in the directory
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				// It gets the name of the file i.e. the username
				String childName = child.getName();
				System.out.println(childName);
				Scanner sc;
				try {
					// The scanner reads the result stored in the file
					sc = new Scanner(new FileInputStream(child));
					String content = new String();
					content = sc.nextLine();
					// Converts the String "content" into an integer
					int result = Integer.parseInt(content);
					// Removes the File Extension
					if (childName.endsWith(".txt")) {
						childName = childName.substring(0, childName.length() - 4);
					}
					// This outputs to the screen the results of each user
					results.append(childName + " achieved: " + result);
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				}
			}
		}

	}

	public void view() {
		//Creating the GUI and placing the components
		JFrame frame = new JFrame("Question Viewer");
		JFrame.setDefaultLookAndFeelDecorated(false);
		frame.setLocationRelativeTo(null);
		frame.setSize(500, 500);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		frame.add(panel);
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		JTextArea question = new JTextArea(5, 25);
		panel.add(question);
		JTextArea answerBox = new JTextArea(5, 20);
		panel.add(answerBox);
		frame.add(panel);
		JLabel label1 = new JLabel("Question");
		JLabel label2 = new JLabel("Type your answer here: ");
		panel.add(label1);
		panel.add(label2);
		
		
		String solution;
		
		//Getting the screen size and laying out the components
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

		layout.putConstraint(SpringLayout.WEST, label1, 200, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label1, 25, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, label2, 175, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label2, 150, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, question, 100, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, question, 50, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, answerBox, 125, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, answerBox, 175, SpringLayout.NORTH, panel);
		
		//This finds the user's personal file

		file = new File(
				"F:\\Programming Project\\workspace\\consolidation\\resultsBank\\" + Login.getUserName() + ".txt");

		try {
			//Scans all the lines in the file
			//Places each line into a variable
			Scanner sc = new Scanner(new FileInputStream(edit()));
			String content = new String();
			content = sc.nextLine();
			content = sc.nextLine();
			System.out.println(content);
			question.append(content + "\n");
			content = sc.nextLine();
			String correctAnswer = content;
			System.out.println("Answer: is this ... " + correctAnswer);
			content = sc.nextLine();
			System.out.println(content);
			solution = content;
			content = sc.nextLine();
			System.out.println(content);
			String hint = content;
			//closes the file		
			sc.close();

			//Creating the hint button
			JButton hintButton = new JButton("Hint");
			panel.add(hintButton);
			hintButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(frame, hint);
				}
			});

			//Creating the submit button
			JButton submit = new JButton("Submit");
			panel.add(submit);
			submit.addActionListener(new ActionListener() {
				// This function controls what happens when the button is clicked
				public void actionPerformed(ActionEvent e) {
					//if the user does not enter an answer					
					if(answerBox.getText() != null && answerBox.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "Please enter an answer!", "Error Message",
								JOptionPane.ERROR_MESSAGE);
					}
					else {

						//Gets the answer and compares it to the correct answer
					System.out.println(answerBox.getText());
					if (correctAnswer.equals(answerBox.getText())) {
						//if the answer is correct
						System.out.println("Correct");
						ImageIcon icon = new ImageIcon(
								"F:\\Programming Project\\workspace\\consolidation\\src\\images\\correct.png");
						JOptionPane.showMessageDialog(frame, "Well Done. You got it right!", "Correct",
								JOptionPane.INFORMATION_MESSAGE, icon);
						frame.dispose();

						String userName = Login.getUserName();
						System.out.println(userName);
						//calls another function to add a point to the result

						generateResult();
					}

					else {
						//The answer is incorrect
						System.out.println("Incorrect");
						Object[] options = { "Worked Solution", "Try again", "Close" };
						//Provides an incorrect pop up box
						ImageIcon icon = new ImageIcon(
								"F:\\Programming Project\\workspace\\consolidation\\src\\images\\incorrect.png");
						int n = JOptionPane.showOptionDialog(frame, "Sorry, you got this question incorrect!",
								"Incorrect", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon,
								options, options[2]);
						if (n == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(frame, solution);
						} else if (n == JOptionPane.NO_OPTION) {

						} else if (n == JOptionPane.CANCEL_OPTION) {

						}
					}
				}
				}
			});
			
			//Adding constraints
			layout.putConstraint(SpringLayout.WEST, submit, 190, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, submit, 270, SpringLayout.NORTH, panel);

			layout.putConstraint(SpringLayout.WEST, hintButton, 200, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, hintButton, 330, SpringLayout.NORTH, panel);

		}

		//An exception that reports an error
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nProgram terminated Safely...");
		}

	}

	public void generateResult() {
		//The initial result is set to 1
		int result = 1;

		Scanner sc;
		try {
			//The program attempts to find the users original score by opening their record
			sc = new Scanner(new FileInputStream(file));
			String content = new String();
			//The total is set to zero
			int total = 0;
			content = sc.nextLine();
			total = Integer.parseInt(content) + result;
			System.out.println(total);

			try {
				//The total is updated to the text file
				FileWriter out = new FileWriter(file, false);
				out.write(String.valueOf(total));
				out.close();
				//if errors occur then it prints a stack trace
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}

	}
	//This function is to reload the Menu
	public void reload() {
		treeModel.reload();
	}

	// This function removes all nodes except the root node.
	public void clear(File directory, DynamicTree treePanel) {
		//Loops through all the files in the question bank
		File[] listOfFiles = directory.listFiles();
		for (File file : listOfFiles) {
			//If the file is a directory it takes all of the files in it
			if (file.isDirectory()) {
				String fileName = file.getName();
				System.out.println(fileName);
				//creates a new directory
				directory = new File("F:\\Programming Project\\workspace\\consolidation\\questionBank\\" + fileName);
				if (file.list().length > 0) {
					clear(directory, treePanel);
				} else {
					//deletes the file
					file.delete();
				}
			} else {
				//deletes the directory
				file.delete();

			}
		}
		//removes all the nodes in the tree
		rootNode.removeAllChildren();
		// reloads the tree
		treeModel.reload();

	}
	//Finds the path to the searched for String
	public TreePath find(String s) {
		DefaultMutableTreeNode root = rootNode;
		//Creates a depth-first method of traversing the tree
		Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
		//Looping through each element in the tree
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode node = e.nextElement();
			//Compares each question to the question title
			if (node.toString().equalsIgnoreCase(s)) {
				System.out.println(node.toString());
				tree.setSelectionPath(new TreePath(node.getPath()));
				tree.scrollPathToVisible(new TreePath(node.getPath()));
				//returns the path and opens a path to make it visible
				return new TreePath(node.getPath());
			}
		}
		return null;
	}

	public void searchTree() {
		//searches the tree
		TreePath path = tree.getSelectionPath();
		//Makes the path visible to the user
		tree.setSelectionPath(path);
		tree.scrollPathToVisible(path);
	}
	
	//Creates the Edit Form GUI
	public void editForm() {
		//Declaring the necessary variables
		JTextField qtitle;
		JTextArea question;
		JTextArea answer;
		JTextArea hint;
		JTextArea solution;
		JComboBox<String> unitList;
		ButtonGroup group;
		SpringLayout layout;
		JRadioButton one, two, three, four, five;
		//Creating the GUI
		JFrame frame = new JFrame("A window");
		JFrame.setDefaultLookAndFeelDecorated(false);
		frame.setLocationRelativeTo(null);
		frame.setSize(500, 700);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		frame.add(panel);
		layout = new SpringLayout();
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
		qtitle = new JTextField(20);
		panel.add(qtitle);
		question = new JTextArea(5, 20);
		panel.add(question);
		answer = new JTextArea(5, 20);
		panel.add(answer);
		solution = new JTextArea(5, 20);
		panel.add(solution);
		hint = new JTextArea(5, 20);
		panel.add(hint);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

		String[] units = { "Unit 1", "Unit 2", "Unit 3", "Unit 4", "Unit 5" };

		// Create the combo box, select item at index 4.
		// Indices start at 0, so 4 specifies the pig.
		unitList = new JComboBox<String>(units);
		// unitList.setSelectedIndex(4);
		panel.add(unitList);

		one = new JRadioButton("1");
		two = new JRadioButton("2");
		three = new JRadioButton("3");
		four = new JRadioButton("4");
		five = new JRadioButton("5");
	
		// A group of radio buttons
		group = new ButtonGroup();
		group.add(one);
		group.add(two);
		group.add(three);
		group.add(four);
		group.add(five);
		//Creating a panel for the radio buttons

		JPanel radioPanel = new JPanel(new GridLayout(1, 1));
		radioPanel.add(one);
		radioPanel.add(two);
		radioPanel.add(three);
		radioPanel.add(four);
		radioPanel.add(five);

		panel.add(radioPanel);

		JButton addButton = new JButton("Submit");
		panel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			// This function controls what happens when the button is clicked
			public void actionPerformed(ActionEvent e) {

				PrintWriter out;
				try {

					out = new PrintWriter("F:\\Programming Project\\workspace\\consolidation\\questionBank\\"
							+ unitList.getSelectedItem().toString() + "\\" + qtitle.getText() + ".txt");
					out.println(qtitle.getText());
					out.println(question.getText());
					out.println(answer.getText());
					out.println(hint.getText());
					out.println(solution.getText());
					out.println(unitList.getSelectedItem().toString());
					out.close();

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			}
		});

		try {
			Scanner sc = new Scanner(new FileInputStream(edit()));
			String content = new String();
			content = sc.nextLine();
			qtitle.setText(content);
			content = sc.nextLine();
			question.append(content + "\n");
			content = sc.nextLine();
			answer.append(content + "\n");
			content = sc.nextLine();
			solution.append(content + "\n");
			content = sc.nextLine();
			hint.append(content + "\n");
			content = sc.nextLine();
			unitList.setSelectedItem(content);

			sc.close();
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nProgram terminated Safely...");
		}
		//adding constraints to position all the components

		layout.putConstraint(SpringLayout.WEST, label1, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label1, 50, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, label2, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label2, 100, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, label3, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label3, 200, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, qtitle, 180, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, qtitle, 50, SpringLayout.WEST, panel);

		layout.putConstraint(SpringLayout.WEST, question, 180, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, question, 100, SpringLayout.WEST, panel);

		layout.putConstraint(SpringLayout.WEST, addButton, 200, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, addButton, 600, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, label4, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label4, 250, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, unitList, 180, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, unitList, 200, SpringLayout.NORTH, panel);

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

	// This function allows stuff to be edited
	public File edit() {
		//Declaring necessary variables (local)
		String folderName;
		File directory = null;
		//Gets the last selected component to be edited
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		if (node == null)
			// Nothing is selected.
			return directory;
		//If the node is a leaf in the tree (i.e. it has parents)
		if (node.isLeaf()) {
			//Puts the parent node into a variable
			MutableTreeNode parent = (MutableTreeNode) (node.getParent());
			//converts the folder into a string
			folderName = parent.toString();
			//adds this to a variable
			String fileName = node.toString();
			tree.setSelectionPath(new TreePath(node.getPath()));
			System.out.println(fileName);
			//creates a path to the file
			directory = new File("F:\\Programming Project\\workspace\\consolidation\\questionBank\\" + folderName + "\\"
					+ fileName + ".txt");
			System.out.println(directory.toString());
			//returns the directory
			return directory;

		} else {
			//the node is not a leaf i.e. it is a folder not a file
			JOptionPane.showMessageDialog(null, "This is not a question", "Error", JOptionPane.ERROR_MESSAGE);

		}
		return directory;
	}

	/** Remove the currently selected node. */
	public void removeCurrentNode() {
		TreePath currentSelection = tree.getSelectionPath();
		//Checks to see if there has been a selection
		if (currentSelection != null) {
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
			MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
			//Ensures the node to be deleted has a parent node
			if (parent != null) {
				//deletes the node from the tree
				treeModel.removeNodeFromParent(currentNode);
				String folderName = parent.toString();
				//deletes the node from the question bank
				String fileName = currentNode.toString();
				File file = new File("F:\\Programming Project\\workspace\\consolidation\\questionBank\\" + folderName
						+ "\\" + fileName + ".txt");
				file.delete();
				//file has been deleted
				return;
			}
		}

		// Either there was no selection, or the root was selected.
		toolkit.beep();
	}

	// This is the function called by main Application - it takes an Object
	// called child. in this case. child is the title of the question
	public DefaultMutableTreeNode addObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = tree.getSelectionPath();
		if (parentPath == null) {
			parentNode = rootNode;
		} else {
			parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
		}
		return addObject(parentNode, child, true);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
		return addObject(parent, child, false);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

		if (parent == null) {
			parent = rootNode;
		}

		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

		if (shouldBeVisible) {
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		treeModel.reload();
		return childNode;
	}

	//A function to make the file editable
	public void setEditable(boolean b) {
		//Checks to see if the file is editable
		if (tree.isEditable() == true) {
			//Sets it to editable
			tree.setEditable(true);
			//Sets it to editable no matter what the state is originally
		} else {
			tree.setEditable(true);
		}
	}

}
