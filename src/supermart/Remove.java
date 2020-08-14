package supermart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;

//import beans.BusDetails;
@SuppressWarnings("serial")
public class Remove extends JFrame implements ActionListener{

	JTextField pid,pname;
	JLabel l1,l2;
	JButton remove,search,exit,back,reset;
	Container con;
		public Remove(){
		Container con=getContentPane();
		con.setLayout(new GridLayout(5,2));
		Font f1= new Font("Times New Roman",Font.BOLD,25);
		Font f= new Font("Times New Roman",Font.PLAIN,25);
		l1=new JLabel("  Enter the Product ID :-");
		l1.setFont(f1);
		l1.setForeground(Color.DARK_GRAY);
		
		pid=new JTextField(20);
		pid.setFont(f);
		con.add(l1);
		con.add(pid);
		
		l2=new JLabel("  Product Name :-");
		l2.setFont(f1);
		l2.setForeground(Color.DARK_GRAY);
		l2.setVisible(false);
		
		pname=new JTextField(20);
		pname.setFont(f);
		pname.setVisible(false);
		con.add(l2);
		con.add(pname);
		
		search=new JButton("SEARCH");
		search.setFont(f1);
		con.add(search);
		search.addActionListener(this);
		
		remove=new JButton("REMOVE");
		remove.setFont(f1);
		remove.setEnabled(false);
		con.add(remove);
		remove.addActionListener(this);
		reset=new JButton("RESET");
		reset.setFont(f1);
		con.add(reset);
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				pid.setText("");
				pname.setText("");
				l2.setVisible(false);
				pname.setVisible(false);
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
		setTitle("Remove Product");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
		
		setLayout(new BorderLayout());
		JLabel background=new JLabel(new ImageIcon("/home/neha/dest.jpeg"));
		add(background);
    }

	public static void main(String[] args) {
		new Remove();
	}
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	@SuppressWarnings({  "unchecked" })
	@Override
	public void actionPerformed(ActionEvent arg0) {
			
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
				}catch(ClassNotFoundException ex) {
					ex.printStackTrace();
				}
				
				if(arg0.getSource()==search)
				{
					try {
				
					if(pid.getText().equals(""))
						JOptionPane.showMessageDialog(this, "Field should not be empty");
					else
					{
				      String productid=pid.getText().toString();
				      conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sapa","root","tiger");

					String sq="select ItemName from products where productid='"+productid+"'";
					 pst=(PreparedStatement) conn.prepareStatement(sq);
                     rs = pst.executeQuery(sq);
                     
                                
                     if(rs.next())
                     {
                    	 String item = rs.getString(1);
                         pname.setText(item);
                         pname.setVisible(true);
                         l2.setVisible(true);
                         
                    	 JOptionPane.showMessageDialog(this,"Now Press Remove button to remove this Item");
                    	 remove.setEnabled(true);
                     }
                     else
                     {
                    	  JOptionPane.showMessageDialog(this,"NO SUCH PRODUCT EXISTS!!");
                     }
					}
				}catch(SQLException eq) {
						JOptionPane.showMessageDialog(null,eq);
				}
					
                     
				}
				
				if(arg0.getSource()==remove)
				{
				try {
					if(pid.getText().equals(""))
						JOptionPane.showMessageDialog(this, "Field should not be empty");
					else
					{
				      String productid=pid.getText().toString();
				      conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sapa","root","tiger");

					 
					String sql="Delete from products where productid='"+productid+"'" ;
					
					pst=conn.prepareStatement(sql);
					//pst.setString(1,pid.getText());
					pst.executeUpdate();
					
					 JOptionPane.showMessageDialog(this,"DELETED SUCCESSFULLY");
					 
					 remove.setEnabled(false);
					 pid.setText("");
					 pname.setText("");
					 l2.setVisible(false);
					 pname.setVisible(false);
                     }
                     
					
				}catch(SQLException eq) {
					JOptionPane.showMessageDialog(null,eq);
				}
	
				
			
			} 

		}
}
	


