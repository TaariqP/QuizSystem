//This Class creates the GUI for the Login page
package consolidation;

//Imports all the necessary modules
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

//Creating a class that inherits from the JFrame Class
@SuppressWarnings("serial")
public class Login extends JFrame {
	// Creating a constructor for this class (Object Oriented Programming)
	public static String USER_NAME;

	public Login() {
		// A JFrame Constructor used to title the GUI Frame
		super("Login Screen");
		// The panel component is where the components of the GUI will be placed
		JPanel panel = new JPanel();
		// The spring layout will be used to place components accurately
		SpringLayout layout = new SpringLayout();
		// Setting the layout to the Panel
		panel.setLayout(layout);
		// Making the panel appear on screen
		panel.setVisible(true);
		// Adding the Panel to the Frame
		add(panel);
		// Creating a textfield where the user will enter their username and
		// adding this to the panel
		JTextField username = new JTextField(10);// Length = 10
		panel.add(username);

		// Creating a passwordField where the user will enter their password and
		// adding this to the panel
		JPasswordField password = new JPasswordField(10); // Length = 10
		panel.add(password);

		// Creating a label for the username field
		JLabel label1 = new JLabel("Username");
		panel.add(label1);

		// Creating a label for the password field
		JLabel label2 = new JLabel("Password");
		panel.add(label2);

		// Creating the Login Button
		JButton loginButton = new JButton("Login");
		panel.add(loginButton);

		// Putting Constraints on the different components and setting out the
		// form
		layout.putConstraint(SpringLayout.WEST, label1, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label1, 50, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, label2, 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label2, 100, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, password, 150, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, password, 100, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, username, 150, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, username, 50, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, loginButton, 150, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, loginButton, 175, SpringLayout.NORTH, panel);

		// Adding an Action Listener or an Event Handler to the button
		loginButton.addActionListener(new ActionListener() {
			// This function controls what happens when the button is clicked
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				// Two variables are declared both of which contain the inputted
				// username and password
				String sUser = username.getText();
				String sPass = password.getText();
				if(sUser != null && sUser.length() == 0) {
					JOptionPane.showMessageDialog(null, "Please enter a username!", "Error Message",
							JOptionPane.ERROR_MESSAGE);
				}
				if(sPass != null && sPass.length() == 0) {
					JOptionPane.showMessageDialog(null, "Please enter a Password!", "Error Message",
							JOptionPane.ERROR_MESSAGE);
				}
				// this will try to find the text file containing all the logins
				try {
					Scanner fileScan = new Scanner(
							new File("F:\\Programming Project\\workspace\\consolidation\\src\\logins.txt"));
					// A Boolean Variable is used to check if the username and
					// password match
					boolean founduser = false;
					// a While loop will loop through all of the logins in the
					// text file to check if there is a match
					while (fileScan.hasNextLine()) {
						String input = fileScan.nextLine();
						Login.this.USER_NAME = input.substring(0, input.indexOf(':'));
						String Password = input.substring(input.indexOf(':') + 1, input.length());

						if (USER_NAME.equals(sUser) && (Password.equals(sPass))) {
							// Changes the state of the Boolean
							founduser = true;
							// Ends the loop
							fileScan.close();
							break;
						}

					}

					if (founduser) {
						// Disposes of the current Form and opens the Main
						// Application (access granted)
						dispose();
						MainApplication.main(null);

					}

					else {
						// Tells the user the password or username is incorrect.
						JOptionPane.showMessageDialog(null, "Invalid Username or password. Try again.", "Error Message",
								JOptionPane.ERROR_MESSAGE);

					}

				} catch (FileNotFoundException e1) {
					// This prints an error when the file cannot be found
					JOptionPane.showMessageDialog(null, "No file found. Try again.", "Error Message",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

			}
		});

	}

	// This Function gets the Variable stored in user name.
	public static String getUserName() {
		return USER_NAME;
	}

	// The Main Function creates the Frame. This is the first function that is
	// called when the program is run
	public static void main(String[] args) {
		Login Login = new Login();
		Login.setSize(500, 300);
		Login.setLocationRelativeTo(null);
		Login.setVisible(true);
	}
}