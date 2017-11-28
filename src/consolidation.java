package consolidation;
//Importing the necessary Java Swing Components/Modules

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

//Entrance to my program
public class consolidation extends JFrame {

  //Declaring Variables Local to this class
  private JButton loginB;
  private JButton lottery;
  private JButton credits;
  private JButton exit;

  //Creating the constructor for this Class
  public consolidation() {
    super("Consolidation Task");
    //Setting the Layout allowing me to place GUI Components.
    setLayout(new FlowLayout());
    //Creating the Login Buttton
    loginB = new JButton("Login");
    add(loginB);
    LoginHandlerClass loginHandler = new LoginHandlerClass();
    loginB.addActionListener(loginHandler);
    //Creating the Lottery Button
    lottery = new JButton("Lottery");
    add(lottery);
    LotteryHandlerClass lotteryHandler = new LotteryHandlerClass();
    lottery.addActionListener(lotteryHandler);
    //Creating the Credits Button
    credits = new JButton("Credits");
    add(credits);
    CreditsHandlerClass creditsHandler = new CreditsHandlerClass();
    credits.addActionListener(creditsHandler);
    //Creating the Exit Button
    exit = new JButton("Exit");
    add(exit);
    ExitHandlerClass ExitHandler = new ExitHandlerClass();
    exit.addActionListener(ExitHandler);

  }

  //Creating the Exit Handler Class
  private class ExitHandlerClass implements ActionListener {

    public void actionPerformed(ActionEvent event) {

      int n = JOptionPane
          .showConfirmDialog(exit, "Are you sure you wish to exit?");
      if (n == JOptionPane.YES_OPTION) {
        dispose();
      } else if (n == JOptionPane.NO_OPTION) {

      } else {

      }

    }
  }

  private class CreditsHandlerClass implements ActionListener {

    public void actionPerformed(ActionEvent event) {
      JOptionPane.showMessageDialog(null, String
          .format("Credits\n Taariq Pala - 17/09/16",
              event.getActionCommand()));
    }
  }

  private class LotteryHandlerClass implements ActionListener {

    public void actionPerformed(ActionEvent event) {
      lottery lottery = new lottery();
      lottery.setSize(500, 300);
      lottery.setVisible(true);
      lottery.setLocationRelativeTo(null);
    }
  }

  private class LoginHandlerClass implements ActionListener {

    public void actionPerformed(ActionEvent event) {
      //login.main(null);
      clientTest.main(null);

    }

  }

  public static void main(String[] args) {

    //Create and show the GUI.
    consolidation consolidation = new consolidation();
    consolidation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    consolidation.setLocationRelativeTo(null);
    consolidation.setSize(500, 300);
    consolidation.setVisible(true);
  }
}



		
	
