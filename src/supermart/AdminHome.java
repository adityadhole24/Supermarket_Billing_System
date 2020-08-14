package supermart;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;                  
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
@SuppressWarnings("serial")
public class AdminHome extends JFrame implements ActionListener
{
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	
	private JButton b1,b2,b3,b4,b5,b6,b7,back;
	private JLabel label;
	public AdminHome()
	{
		Container con=getContentPane();
		con.setLayout(new GridLayout(6,2));
		Font f=new Font("ARIAL",Font.BOLD,35);
		Font f1=new Font("Times New Roman",Font.BOLD,25);
		label=new JLabel("  ADMIN HOME PAGE !!");
		label.setFont(f);
		label.setForeground(Color.MAGENTA);
		con.add(label); con.add(new JLabel(" "));
		con.add(new JLabel(" "));con.add(new JLabel(" "));
		b1=new JButton("CUSTOMER DETAILS");
		b1.setFont(f1);
		b2=new JButton("ADD  PRODUCT  ");
		b2.setFont(f1);
		b3=new JButton("DELETE PRODUCT");
		b3.setFont(f1);
		b4=new JButton("MODIFY PRODUCT");
		b4.setFont(f1);
		b5=new JButton("DISPLAY ALL INVENTORY");
		b5.setFont(f1);
		b6=new JButton("DISPLAY ALL ORDERS");
		b6.setFont(f1);
		b7=new JButton("DELETE ORDERS");
		b7.setFont(f1);
		back=new JButton("SIGN OUT");
		back.setFont(f1);
		con.add(b1);
		con.add(b2);
		con.add(b3);
		con.add(b4);
		con.add(b5);
		con.add(b6);
		con.add(b7);
		con.add(back);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				Customerdetails ob=new Customerdetails();
				ob.setVisible(true);
				setVisible(false);
			}
		});
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Add();
				setVisible(false);
			}
		});	
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Remove();
				setVisible(false);
			}
		});	
		b4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Modify();
				setVisible(false);
			}
		});	
		b5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Inventorydetails ob=new Inventorydetails();
				ob.setVisible(true);
				setVisible(false);
			}
		});	
		b6.addActionListener(new ActionListener(){
			@SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent e) {
				Orderdetails ob=new Orderdetails();
				ob.setVisible(true);
				setVisible(false);
			}
		});	
		b7.addActionListener(new ActionListener(){
			@SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent e) {
				Removeorder ob=new Removeorder();
				ob.setVisible(true);
				setVisible(false);
			}
		});
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Main();
				setVisible(false);
				
			}
		});	
		
		setSize(900,550);
		setLocation(200,100);
		setResizable(false); 
		setTitle(" ADMINISTRATOR HOME PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
		
		
		
	}
	public static void main(String args[])
	{
		new AdminHome();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}
