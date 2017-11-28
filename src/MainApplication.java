/* This Module represents the Main Application/User Interface 
  which leads to other functionalities */
package consolidation;

//Importing the necessary Java Swing Components/Modules

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Taariq Pala Date: 26/11/2016
 */
// This Class inherits JFrame and also implements the Action Listener
@SuppressWarnings("serial")
public class MainApplication extends JFrame implements ActionListener {

  // Static Variables are given a different naming convention
  private static String ADD_COMMAND = "add";
  private static String REMOVE_COMMAND = "remove";
  private static String CLEAR_COMMAND = "clear";
  private static String EDIT_COMMAND = "edit";
  private static String VIEW_COMMAND = "view";
  private static String CLIENT_COMMAND = "client";
  private static String SERVER_COMMAND = "server";
  private static String RESULTS_COMMAND = "server";
  private static String SEARCH_COMMAND = "search";
  private DynamicTree treePanel;
  private DefaultMutableTreeNode p1; // parent1
  private String searchedFor;
  private String fileName;
  private String qTitle;
  private String command;
  private File directory;
  private JPanel panel;
  private DefaultMutableTreeNode newFile;

  // Creating the constructor
  public MainApplication() {
    // Naming the Form and invoking the constructor
    super("Ultimate Quiz  System");
    directory = new File(
        "F:\\Programming Project\\workspace\\consolidation\\questionBank\\");
    // Creating an object of the DynamicTree Class
    treePanel = new DynamicTree();
    // Calling a function which adds Default nodes to the dynamic tree
    populateTree(directory, treePanel);
    // Laying everything out
    treePanel.setPreferredSize(new Dimension(300, 150));
    add(treePanel, BorderLayout.CENTER);
    // Creating a Panel to allow for GUI components to be placed.
    panel = new JPanel(new GridLayout(0, 3));
    add(panel, BorderLayout.SOUTH);
    // Calling a function to create a tool-bar.
    createToolBar();

    // reloading the tree
    treePanel.reload();
  }

  // This function populates the tree from the folder containing Questions
  public void populateTree(File directory, DynamicTree treePanel) {

    File[] listOfFiles = directory.listFiles();
    // Loops through the files in the folder.
    for (File file : listOfFiles) {
      // If the root folder is empty
      if (file == null) {
        System.out.println("Null directory found ");
        continue;
      }
      // If the File is a folder
      if (file.isDirectory()) {

        // If the folder is empty
        if (file.listFiles() == null) {
          // Skips null files
          continue;
        }

        // Get the fileName
        fileName = file.getName();
        // adds the parentNode to the RootNode
        p1 = treePanel.addObject(null, fileName);
        // Changes the Directory for the purpose of recursion
        directory = new File(
            "F:\\Programming Project\\workspace\\consolidation\\questionBank\\"
                + fileName);
        // Recursively calls this function
        populateTree(directory, treePanel);

      } else {
        // Gets the fileName
        fileName = file.getName();
        // Removes the file extension of ".txt"
        if (fileName.endsWith(".txt")) {
          fileName = fileName.substring(0, fileName.length() - 4);
        }
        // Creates a Node on the tree with this name.
        newFile = new DefaultMutableTreeNode(fileName);
        // Adds the node to the tree under a parent node.
        treePanel.addObject(p1, newFile);

      }
    }

  }

  // Getter for the string store in question title
  public String getqTitle() {
    return qTitle;
  }

  // Function to add a node to the tree menu
  public void addNode() {
    // Creating a dialog box to ask the user for the name of the node.
    qTitle = (String) JOptionPane
        .showInputDialog(panel, "Please enter the Question Title: ",
            "Question Title",
            JOptionPane.PLAIN_MESSAGE);
    // Attempts to find the node within the menu
    if (treePanel.find(qTitle) == null) {
      // If the item does not already exist
      treePanel.addObject(qTitle);
      // Instantiating an object
      addQForm.main(null);
    } else {
      // If the item already exists
      JOptionPane.showMessageDialog(null, "This question already Exists",
          "Error Message",
          JOptionPane.ERROR_MESSAGE);

      System.out.println("This Question Already Exists");
    }
  }

  public void Search() {
    searchedFor = (String) JOptionPane.showInputDialog(panel,
        "Please enter the Title of the question you wish to search for: ",
        "Search", JOptionPane.PLAIN_MESSAGE);
    // Searches the Tree for a node with a matching string
    if (treePanel.find(searchedFor) == null) {
      JOptionPane.showMessageDialog(null, "Cannot be found", "Error Message",
          JOptionPane.ERROR_MESSAGE);
    } else {
      treePanel.find(searchedFor);
    }
  }

  // This function decides what happens when a button is clicked
  public void actionPerformed(ActionEvent e) {
    // Declaring a local variable which decides what event has occurred.
    command = e.getActionCommand();
    // If the ADD button has been clicked
    if (ADD_COMMAND.equals(command)) {
      // Calls the function addNode
      addNode();
    } else if (REMOVE_COMMAND.equals(command)) {
      // Remove button clicked
      treePanel.removeCurrentNode();
    } else if (EDIT_COMMAND.equals(command)) {
      // Calling Functions from the DynamicTree Class
      treePanel.editForm();
      treePanel.setEditable(true);
    } else if (CLEAR_COMMAND.equals(command)) {
      // Clear button clicked.
      treePanel.clear(directory, treePanel);
    } else if (VIEW_COMMAND.equals(command)) {
      // View Button Clicked
      treePanel.view();
    } else if (RESULTS_COMMAND.equals(command)) {
      // View Results Clicked
      treePanel.viewResults();
    } else if (CLIENT_COMMAND.equals(command)) {
      // Client Chat Clicked
      clientTest.main(null);
    } else if (SERVER_COMMAND.equals(command)) {
      // Server Chat Clicked
      ServerTest.main(null);
    } else if (SEARCH_COMMAND.equals(command)) {
      // Calls the Search Function
      Search();
    }
  }

  // Creating the tool-bar that contains buttons for functionalities
  private void createToolBar() {

    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);
    toolbar.setRollover(true);

    // Adding Image Icons to the GUI and adding action listeners for each
    // button
    ImageIcon icon = new ImageIcon(
        "F:\\Programming Project\\workspace\\consolidation\\src\\images\\add.png");
    JButton add = new JButton(icon);
    toolbar.add(add);
    add.setActionCommand(ADD_COMMAND);
    add.addActionListener(this);

    icon = new ImageIcon(
        "F:\\Programming Project\\workspace\\consolidation\\src\\images\\view.png");
    JButton view = new JButton(icon);
    toolbar.add(view);
    view.setActionCommand(VIEW_COMMAND);
    view.addActionListener(this);

    icon = new ImageIcon(
        "F:\\Programming Project\\workspace\\consolidation\\src\\images\\delete.png");
    JButton delete = new JButton(icon);
    toolbar.add(delete);
    delete.setActionCommand(REMOVE_COMMAND);
    delete.addActionListener(this);

    icon = new ImageIcon(
        "F:\\Programming Project\\workspace\\consolidation\\src\\images\\edit.png");
    JButton edit = new JButton(icon);
    toolbar.add(edit);
    edit.setActionCommand(EDIT_COMMAND);
    edit.addActionListener(this);

    icon = new ImageIcon(
        "F:\\Programming Project\\workspace\\consolidation\\src\\images\\results.png");
    JButton results = new JButton(icon);
    toolbar.add(results);
    results.setActionCommand(RESULTS_COMMAND);
    results.addActionListener(this);

    icon = new ImageIcon(
        "F:\\Programming Project\\workspace\\consolidation\\src\\images\\search.png");
    JButton search = new JButton(icon);
    toolbar.add(search);
    search.setActionCommand(SEARCH_COMMAND);
    search.addActionListener(this);

    icon = new ImageIcon(
        "F:\\Programming Project\\workspace\\consolidation\\src\\images\\client.png");
    JButton client = new JButton(icon);
    toolbar.add(client);
    client.setActionCommand(CLIENT_COMMAND);
    client.addActionListener(this);

    icon = new ImageIcon(
        "F:\\Programming Project\\workspace\\consolidation\\src\\images\\server.png");
    JButton server = new JButton(icon);
    toolbar.add(server);
    server.setActionCommand(SERVER_COMMAND);
    server.addActionListener(this);

    icon = new ImageIcon(
        "F:\\Programming Project\\workspace\\consolidation\\src\\images\\clear.png");
    JButton clear = new JButton(icon);
    toolbar.add(clear);
    clear.setActionCommand(CLEAR_COMMAND);
    clear.addActionListener(this);
    // Positioning the Tool-bar
    add(toolbar, BorderLayout.NORTH);
  }

  // The Main Function which creates the JFrame.
  public static void main(String[] args) {
    MainApplication MainApplication = new MainApplication();
    MainApplication.setSize(560, 500);
    MainApplication.setLocationRelativeTo(null);
    MainApplication.setVisible(true);
  }
}
