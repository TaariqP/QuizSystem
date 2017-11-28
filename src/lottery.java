package consolidation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class lottery extends JFrame {
	private JButton generate;
	private JLabel number;
	public lottery() {
        setLayout(new FlowLayout());
        
        generate = new JButton ("Generate");
		add(generate);
		GenerateHandlerClass generateHandler = new GenerateHandlerClass();
		generate.addActionListener(generateHandler);
		
		number = new JLabel ("");
		add(number);
		
	}
		
	private class GenerateHandlerClass implements ActionListener{
			public void actionPerformed(ActionEvent event){
			String string = ("" + (int)(Math.random() * 50 ) + (int)(Math.random() * 50 ) +(int)(Math.random() * 50 ) + (int)(Math.random() * 50 ) +(int)(Math.random() * 50 )+(int)(Math.random() * 50 ));
			number.setText(string);
		}
	}
	public static void main(String[] args){
		
	}
}
