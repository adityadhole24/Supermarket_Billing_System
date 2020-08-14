package supermart;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*; 
//import beans.CustomerDetails;
@SuppressWarnings("serial")

public class Customerlog extends JFrame implements ActionListener{
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	
	JTextField username;
	JPasswordField pass;
	JLabel l1,l2,label;
	JButton login,exit,reset,back;
	Container con;
	public Customerlog(){
		Container con=getContentPane();
		con.setLayout(new GridLayout(7,2));
		Font f= new Font("ARIAL",Font.BOLD,35);
		Font f1= new Font("Times New Roman",Font.PLAIN,25);
		label=new JLabel("  LOG IN : ");
		label.setFont(f);
		label.setForeground(Color.BLACK);
		l1=new JLabel("  ENTER E-MAIL ID :-");
		username=new JTextField(30);
		username.setFont(f1);
		l1.setFont(f1);
		l1.setForeground(Color.BLUE);
		l2=new JLabel("  ENTER PASSWORD :-");
		l2.setFont(f1);
		l2.setForeground(Color.BLUE);
		pass=new JPasswordField(30);
		login=new JButton("LOG IN");
		reset=new JButton("RESET");
		back=new JButton("BACK");
		exit=new JButton("EXIT");
		exit.setFont(new Font("Tahoma", Font.BOLD,25));
		login.setFont(new Font("Tahoma", Font.BOLD,25));
		reset.setFont(new Font("Tahoma", Font.BOLD,25));
		back.setFont(new Font("Tahoma", Font.BOLD,25));
		con.add(label); con.add(new JLabel(""));
		con.add(new JLabel(""));con.add(new JLabel(""));
		con.add(l1);con.add(username);
		con.add(l2);con.add(pass);
		con.add(new JLabel(""));con.add(login);
		con.add(new JLabel(""));con.add(reset);
		con.add(back);con.add(exit);
		login.addActionListener(this);
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				pass.setText("");
			}
		});	
			back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Customer();
				setVisible(false);
			}
		});
			exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});	
		
			setSize(900,550);
			setLocation(200,100);
			setResizable(false); 
			setTitle("SIGN IN");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			setVisible(true);
			setLayout(new BorderLayout());
			JLabel background=new JLabel(new ImageIcon("/home/neha/dest.jpeg"));
			add(background);
   }

	public static void main(String[] args) {
		new Customerlog();
	}

	@SuppressWarnings({  "unchecked" })
	@Override
	public void actionPerformed(ActionEvent e) {
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		try {
		if(username.getText().equals("")||pass.getPassword().equals(""))
			JOptionPane.showMessageDialog(this, "Fields should not be empty");
		
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sapa","root","tiger");
		
		Statement st=conn.createStatement();
		String uid=username.getText().toString();
		String pwd=pass.getText().toString();
		
		rs=st.executeQuery("Select custid from customer where Email='"+uid+"' and Password='"+pwd+"'");
		
		if(rs.next()) {
			JOptionPane.showMessageDialog(this,"LOGIN SUCCESSFULL!!!");
			
			
			new Order(rs.getInt(1)).setVisible(true);;
			setVisible(false);
		}
		else {
			JOptionPane.showMessageDialog(this,"INVALID USERNAME OR PASSWORD!!");
		}
		
		
		}catch(SQLException eq) {
			JOptionPane.showMessageDialog(null,eq);
		}
	}
}
		