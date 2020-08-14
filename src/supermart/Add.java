package supermart;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import javax.swing.*;


@SuppressWarnings("serial")
public class Add extends JFrame implements ActionListener{
	JTextField name,mrp,stock,route,pid;
	@SuppressWarnings("rawtypes")
	JComboBox ptype;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
	JButton add,display,reset,back,exit;
	Container con;
	String type[]={"Grocery","Electronics","Home and Furniture","Personal Care","Fashion"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Add(){
		Container con=getContentPane();
		con.setLayout(new GridLayout(9,2));
		Font f1= new Font("Times New Roman",Font.BOLD,25);
		Font f= new Font("Times New Roman",Font.PLAIN,25);
		setTitle("ADD NEW PRODUCT");
		
		con.add(new JLabel(""));
		con.add(new JLabel(""));
		
		l5=new JLabel("Product ID :-");
		l5.setFont(f1);
		l5.setForeground(Color.BLACK);
		pid=new JTextField(27);
		pid.setFont(f);
		con.add(l5);		con.add(pid);
		
		l1=new JLabel("Product Name :-");
		l1.setFont(f1);
		l1.setForeground(Color.BLACK);
		name=new JTextField(27);
		name.setFont(f);
		con.add(l1);		con.add(name);
		
		
		l3=new JLabel("MRP :-");
		l3.setFont(f1);
		l3.setForeground(Color.BLACK);
		mrp=new JTextField(27);
		mrp.setFont(f);
		con.add(l3);		con.add(mrp);
		
		
		l4=new JLabel("Stock :-");
		l4.setFont(f1);
		l4.setForeground(Color.BLACK);
		stock=new JTextField(27);
		stock.setFont(f);
		con.add(l4);		con.add(stock);
		

		l2=new JLabel("Product Type :-");
		l2.setFont(f1);
		l2.setForeground(Color.BLACK);
		ptype=new JComboBox(type);
		ptype.setFont(f);
		con.add(l2); 	con.add(ptype);
		
		con.add(new JLabel(""));
		con.add(new JLabel(""));
		
		
		
		add=new JButton("ADD");
		add.setFont(f1);
		con.add(add);
		add.addActionListener(this);
		
		reset=new JButton("RESET");
		reset.setFont(f1);
		con.add(reset);
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				name.setText("");
				pid.setText("");
				mrp.setText("");
				stock.setText("");
				ptype.setSelectedIndex(0);
			
			}
		});	
		
		back=new JButton("BACK");
		back.setFont(f1);
		con.add(back);
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new AdminHome();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
		
		setLayout(new BorderLayout());
		JLabel background=new JLabel(new ImageIcon("/home/neha/dest.jpeg"));
		add(background);

	}

	public static void main(String[] args) {
		new Add();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		String sql="INSERT INTO products (productid,ItemName,MRP,Stock,Type) values(?,?,?,?,?)";
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sapa","root","tiger");
		pst=conn.prepareStatement(sql);
		
		pst.setInt(1,Integer.parseInt(pid.getText()));
		pst.setString(2,name.getText());
		pst.setFloat(3,Float.parseFloat(mrp.getText()));
		pst.setInt(4,Integer.parseInt(stock.getText()));
		pst.setString(5,(String) ptype.getSelectedItem());
		
		pst.executeUpdate();
		
		 JOptionPane.showMessageDialog(this,"NEW PRODUCT ADDED!!");

		
	}catch(SQLException eq) {
		JOptionPane.showMessageDialog(null,eq);
	}
	
	
		
}

}