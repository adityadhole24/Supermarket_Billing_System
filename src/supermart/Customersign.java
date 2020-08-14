package supermart;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

@SuppressWarnings("serial")
public class Customersign  extends JFrame implements ActionListener{
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	
	JTextField t1, t2, t3, t4;
	JPasswordField p1;
	
	JLabel l1,l2,l3,l4,l5,label;
	JButton register,display,exit,back;
	Container con;
	
	public Customersign(){
		Container con=getContentPane();
		con.setLayout(new GridLayout(8,2));
		Font f= new Font("ARIAL",Font.BOLD,35);
		Font f1= new Font("Times New Roman",Font.PLAIN,25);
		label=new JLabel("    REGISTER YOURSELF : ");
		label.setFont(f);
		label.setForeground(Color.BLACK);
		con.add(label);con.add(new JLabel(" "));
		l1=new JLabel("          NAME :-");
		l1.setFont(f1);
		l1.setForeground(Color.BLUE);
		t1=new JTextField(40);
		t1.setFont(f1);
		con.add(l1);	con.add(t1);
				
		l4=new JLabel("          E-MAIL :-");
		l4.setFont(f1);
		l4.setForeground(Color.BLUE);
		t4=new JTextField(40);
		t4.setFont(f1);
		con.add(l4);	con.add(t4);
		
		l5=new JLabel("          PASSWORD :-");
		l5.setFont(f1);
		l5.setForeground(Color.BLUE);
		p1= new JPasswordField(30);
		con.add(l5); con.add(p1);
		
		 
		
		con.add(new JLabel(""));	con.add(new JLabel(""));
		con.add(new JLabel(""));	con.add(new JLabel(""));
	   
		
		register=new JButton("REGISTER AND LOG IN");
		register.setFont(new Font("Tahoma", Font.BOLD,25)); 
		con.add(new JLabel(" ")); con.add(register);
		register.addActionListener(this);
		
		
		back=new JButton("BACK");
		back.setFont(new Font("Tahoma", Font.BOLD,25));
		con.add(back);
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Customer();
				setVisible(false);
				
			}
		});	
		
		exit=new JButton("EXIT");
		exit.setFont(new Font("Tahoma", Font.BOLD,25));
		con.add(exit);
		exit.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			}
		});
		setSize(900,550);
		setLocation(200,100);
		setResizable(false); 
		setTitle("CUSTOMER REGISTRATION");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
		
		setLayout(new BorderLayout());
		JLabel background=new JLabel(new ImageIcon("/home/neha/dest.jpeg"));
		add(background);
    	}
	public static void main(String[] args) {
		new Customersign();
	}

	public void actionPerformed(ActionEvent e) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		try {
			
			if(t1.getText().equals("")||t4.getText().equals("")||p1.getPassword().equals(""))
				JOptionPane.showMessageDialog(this, "Fields should not be empty");
			
			String EMAIL_PATTERN="^[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}.[a-zA-Z]{2,3}$";
			Pattern pattern=Pattern.compile(EMAIL_PATTERN);
			
			String user=t4.getText();
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sapa","root","tiger");
			String sq="select Email from customer where Email='"+user+"'";
			 pst=(PreparedStatement) conn.prepareStatement(sq);
            rs = pst.executeQuery(sq);
			
            if(rs.next())
            {
            	JOptionPane.showMessageDialog(null,"EMAIL ALREADY EXISTS!!");
            }
            else
            {
			String sql="INSERT INTO customer" + "(CustName,Email,Password)" + "values(?,?,?)";
			
			pst=conn.prepareStatement(sql);
			pst.setString(1,t1.getText());
			pst.setString(2,t4.getText());
			Matcher regexMatcher=pattern.matcher(t4.getText());
			if(!regexMatcher.matches()) {
				JOptionPane.showMessageDialog(null,"EMAIL FORMAT WRONG");
			}
			else {
			pst.setString(3,p1.getText());
			pst.executeUpdate();
			
			 JOptionPane.showMessageDialog(this,"New customer registered \n Note- Your username is  your mail id.");
			
			}
            }
		}catch(SQLException eq) {
			JOptionPane.showMessageDialog(null,eq);
		}
		
		
	}
}
