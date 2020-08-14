package supermart;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
@SuppressWarnings("serial")
public class Customer extends JFrame implements ActionListener{

	private JButton button1,button2,button3,button4;
	JLabel label,l1,l2;
	public Customer()
	{
		Container con=getContentPane();
		con.setLayout(new GridLayout(7,2));
		Font f= new Font("ARIAL",Font.BOLD,35);
		Font f1= new Font("Times New Roman",Font.PLAIN,25);
		
		label=new JLabel(" WELCOME CUSTOMER !! ");
		label.setFont(f); 
		label.setForeground(Color.BLACK);
		l1=new JLabel("               SIGN   IN   :--");
		l1.setFont(f1);
		l1.setForeground(Color.BLUE);
		l2=new JLabel("               REGISTER   :--");
		l2.setFont(f1);
		l2.setForeground(Color.BLUE);
		
		button1=new JButton("LOG IN");
		button2=new JButton("REGISTER");
		button3=new JButton("BACK");
		button4=new JButton("EXIT");
		 button1.setFont(new Font("Tahoma", Font.BOLD,25)); 
			button2.setFont(new Font("Tahoma", Font.BOLD,25));
			 button3.setFont(new Font("Tahoma", Font.BOLD,25));
			 button4.setFont(new Font("Tahoma", Font.BOLD,25));
		con.add(label);con.add(new JLabel(""));
		con.add(new JLabel(""));con.add(new JLabel(""));
		con.add(new JLabel(""));con.add(new JLabel(""));
		con.add(l1); con.add(button1);
		con.add(l2); con.add(button2);
		con.add(new JLabel(""));	con.add(button3);
		con.add(new JLabel("")); con.add(button4);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Customerlog();
				setVisible(false);
			}
		});	
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Customersign();
				setVisible(false);
			}
		});	
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Main();
				setVisible(false);
			}
		});	
		button4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});	
		
		
		setSize(900,550);
		setLocation(200,100);
		setResizable(false); 
		setTitle("CUSTOMER SIGN-IN HOME PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
		
	}
	public static void main(String args[])
	{
		new Customer();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
	}
	
	
	
}

