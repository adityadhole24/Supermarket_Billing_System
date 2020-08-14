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


@SuppressWarnings("serial")
public class Removeorder extends JFrame implements ActionListener{

	JTextField oid,oamt,odate;
	JLabel l1,l2,l3;
	JButton remove,search,exit,back,reset;
	Container con;
		public Removeorder(){
		Container con=getContentPane();
		con.setLayout(new GridLayout(6,2));
		Font f1= new Font("Times New Roman",Font.BOLD,25);
		Font f= new Font("Times New Roman",Font.PLAIN,25);
		l1=new JLabel("  Enter the Order ID :-");
		l1.setFont(f1);
		l1.setForeground(Color.DARK_GRAY);
		
		oid=new JTextField(20);
		oid.setFont(f);
		con.add(l1);
		con.add(oid);
		
		l2=new JLabel("  Order Amount :-");
		l2.setFont(f1);
		l2.setForeground(Color.DARK_GRAY);
		l2.setVisible(false);
		
		oamt=new JTextField(20);
		oamt.setFont(f);
		oamt.setVisible(false);
		con.add(l2);
		con.add(oamt);
		
		l3=new JLabel("  Order Date :-");
		l3.setFont(f1);
		l3.setForeground(Color.DARK_GRAY);
		l3.setVisible(false);
		
		odate=new JTextField(20);
		odate.setFont(f);
		odate.setVisible(false);
		con.add(l3);
		con.add(odate);
		
		//con.add(new JLabel(" "));con.add(new JLabel(" "));
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
				oid.setText("");
				oamt.setText("");
				l2.setVisible(false);
				oamt.setVisible(false);
				odate.setText("");
				l3.setVisible(false);
				odate.setVisible(false);
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
		setTitle("Delete order");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
		
    }

	public static void main(String[] args) {
		new Removeorder();
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
				
					if(oid.getText().equals(""))
						JOptionPane.showMessageDialog(this, "Field should not be empty");
					else
					{
				      String orderid=oid.getText().toString();
				      conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sapa","root","tiger");

					String sq="select Amount,ODate from orders where Orderid='"+orderid+"'";
					 pst=(PreparedStatement) conn.prepareStatement(sq);
                     rs = pst.executeQuery(sq);
                     
                                
                     if(rs.next())
                     {
                    	 String amt = rs.getString(1);
                         oamt.setText(amt);
                         oamt.setVisible(true);
                         l2.setVisible(true);
                         String date= rs.getDate(2).toString();
                         odate.setText(date);
                         odate.setVisible(true);
                         l3.setVisible(true);
                         
                    	 JOptionPane.showMessageDialog(this,"Now Press REMOVE button to remove this Order");
                    	 remove.setEnabled(true);
                     }
                     else
                     {
                    	  JOptionPane.showMessageDialog(this,"NO SUCH ORDER EXISTS!!");
                     }
					}
				}catch(SQLException eq) {
						JOptionPane.showMessageDialog(null,eq);
				}
					
                     
				}
				
				if(arg0.getSource()==remove)
				{
				try {
					if(oid.getText().equals(""))
						JOptionPane.showMessageDialog(this, "Field should not be empty");
					else
					{
				      String orderid=oid.getText().toString();
				      conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sapa","root","tiger");

					 
					String sql="Delete from orders where Orderid='"+orderid+"'" ;
					
					pst=conn.prepareStatement(sql);
					//pst.setString(1,oid.getText());
					pst.executeUpdate();
					
					 JOptionPane.showMessageDialog(this,"DELETED SUCCESSFULLY");
					 
					 remove.setEnabled(false);
					 oid.setText("");
					 oamt.setText("");
					 l2.setVisible(false);
					 oamt.setVisible(false);
					 odate.setText("");
					 l3.setVisible(false);
					 odate.setVisible(false);
                     }
                     
					
				}catch(SQLException eq) {
					JOptionPane.showMessageDialog(null,eq);
				}
	
				
			
			} 

		}
}
	

