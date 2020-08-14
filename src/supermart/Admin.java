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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
@SuppressWarnings("serial")
public class Admin extends JFrame implements ActionListener{
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	
	JTextField username;
	JPasswordField pass;
	JLabel l1,l2,label;
	JButton login,exit,reset,back;
	Container con;
	public Admin(){
		Container con=getContentPane();
		con.setLayout(new GridLayout(6,2));
		Font f=new Font("ARIAL",Font.BOLD,35);
		Font f2=new Font("ARIAL",Font.PLAIN,25);
		Font f1=new Font("Times New Roman",Font.CENTER_BASELINE,25);
		label=new JLabel("  WELCOME ADMIN !!");
		label.setFont(f);
		label.setForeground(Color.BLACK);
		l1=new JLabel("            ENTER USERNAME :-");
		username=new JTextField(30);
		username.setFont(f2);
		l1.setFont(f1);
		l1.setForeground(Color.BLUE);
		l2=new JLabel("            ENTER PASSWORD :-");
		l2.setFont(f1);
		l2.setForeground(Color.BLUE);
		pass=new JPasswordField(30);
		con.add(label); con.add(new JLabel(" "));
		con.add(l1); con.add(username);
		con.add(l2); con.add(pass);
		login=new JButton("LOG IN");
		login.setFont(f1);
		con.add(new JLabel(" "));  con.add(login);
		login.addActionListener(this);
		reset=new JButton("RESET");
		reset.setFont(f1);
		con.add(new JLabel(" ")); con.add(reset);
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				pass.setText("");
			}
		});	
		back=new JButton("BACK");
		back.setFont(f1);
		con.add(back);
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Main();
				setVisible(false);
			}
		});	
		exit=new JButton("EXIT");
		exit.setFont(f1);
		con.add(exit);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});	
		
		setSize(900,550);
		setLocation(200,100);
		setResizable(false); 
		setTitle(" ADMINISTRATOR  LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
		
		setLayout(new BorderLayout());
		JLabel background=new JLabel(new ImageIcon("/home/neha/dest.jpeg"));
		add(background);
    }

	public static void main(String[] args) {
		new Admin();
	}

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
			
			rs=st.executeQuery("Select Name from admin where AdminID='"+uid+"' and Password='"+pwd+"'");
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(this,"LOGIN SUCCESSFULL!!!");
				
				new AdminHome();
			}
			else {
			JOptionPane.showMessageDialog(this,"INVALID USERNAME OR PASSWORD!!");
		}
			
			
			}
		catch(SQLException eq) {
				JOptionPane.showMessageDialog(null,eq);
			}
	}
}
	