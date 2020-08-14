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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Order extends JFrame implements ActionListener{
	
	private final JButton pdisplay,delete,order,add;
    private final JTable table,cart;
    private final DefaultTableModel tableModel = new DefaultTableModel();
    private final DefaultTableModel tableModel2 = new DefaultTableModel();
    private final JPanel f1,f2;
    int custid;
   
    Vector<String> cart_details = new Vector<String>();
    ResultSetMetaData metaData;
	
	public Order(int custid) {
		
		this.custid=custid;
		
		Container con =  getContentPane();
		con.setLayout(new GridLayout(1,2));
		
		f1 = new JPanel(new BorderLayout());
		f2 = new JPanel(new BorderLayout());
		con.add(f1);
		con.add(f2);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            
        Font f= new Font("Times New Roman",Font.BOLD,20);
        pdisplay= new JButton("Currently Available Items");
        pdisplay.setFont(f);
        pdisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                          
            }
        });
        
        loadData();
                     
        f1.add(pdisplay,BorderLayout.PAGE_START);
        
        table = new JTable(tableModel);
        f1.add(new JScrollPane(table), BorderLayout.CENTER);
        
        initializeCart();
        
        add= new JButton("Add to Cart");
        add.setFont(f);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	addtocart();
            }
        });
        f1.add(add,BorderLayout.PAGE_END);
        
        table.setCellSelectionEnabled(true);
         
        delete= new JButton("Delete from Cart");
        delete.setFont(f);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	deleteFromCart();
            }
        });
        f2.add(delete,BorderLayout.PAGE_START);
        cart = new JTable(tableModel2);
        f2.add(new JScrollPane(cart), BorderLayout.CENTER);
        
        order= new JButton("Generate Bill");
        order.setFont(f);
        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   billGenerate();
                   new Main();
                   setVisible(false);
            }
        });
        f2.add(order,BorderLayout.PAGE_END);
        
        
        setSize(900,550);
		setLocation(200,100);
        setTitle("Order Page");
     
		
	}
	
	 private void loadData() {
	       

	        pdisplay.setEnabled(false);
	        
	        Connection conn=null;
			Statement stmt=null;
			ResultSet rs=null;
			

	        try {
	        	
	        	
	        	Class.forName("com.mysql.jdbc.Driver");
	    		
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sapa","root","tiger");
				
	        
	                 stmt = (Statement) conn.createStatement() ;

	             rs = stmt.executeQuery("select * from products where Stock > 0");
	            metaData = (ResultSetMetaData) rs.getMetaData();

	            // Names of columns
	            Vector<String> columnNames = new Vector<String>();
	            int columnCount = metaData.getColumnCount();
	            for (int i = 1; i <= columnCount; i++) {
	                columnNames.add(metaData.getColumnName(i));
	            }

	            // Data of the table
	            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	            while (rs.next()) {
	                Vector<Object> vector = new Vector<Object>();
	                for (int i = 1; i <= columnCount; i++) {
	                    vector.add(rs.getObject(i));
	                }
	                data.add(vector);
	            }

	            tableModel.setDataVector(data, columnNames);
	        } 
	        catch(SQLException eq) {
	            		JOptionPane.showMessageDialog(null,eq);
	        }
	        catch(ClassNotFoundException ex) {
	          			ex.printStackTrace();
	        }
	        pdisplay.setEnabled(true);

	        
	        
	    }
	 
	 private void addtocart()
	 {
		 int flag = 0;
		 
		 
		
		 
		 int pid[] = new int[10];
         for (int i=0; i<tableModel2.getRowCount();i++) {
             pid[i]= Integer.parseInt(tableModel2.getValueAt(i, 0).toString());
         }
         
         int cur_pid = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(),0).toString());
         
         for(int i=0;i<pid.length;i++)
         {
        	 if(cur_pid==pid[i]) {
        		 JOptionPane.showMessageDialog(this,"Already Added!!");
        		 flag=1;
        		 //Quantity limited to only 1
        	 }
         }
         if(flag==0)
         {
		 Vector<Object> cart_items = new Vector<Object>();
		 for(int i=0;i<cart_details.size();i++)
		 {
			if(i==3)
				cart_items.add(1);
			else
				cart_items.add(tableModel.getValueAt(table.getSelectedRow(), i));
		 }
         
		 
       //  cart_items.add(tableModel.getDataVector().elementAt(table.getSelectedRow()));
         tableModel2.insertRow(0, cart_items);
         
         }
		 
	 }
	 
	 void initializeCart() {
		 
		 try {
		 int columnCount = metaData.getColumnCount();
         for (int i = 1; i <= columnCount; i++) {
             
        	 if(i==4)
        		 cart_details.add("Quantity");
        	 else
        		 cart_details.add(metaData.getColumnName(i));
             
         }
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 
		 tableModel2.setDataVector(null, cart_details);
		 
	 }
	 
	 void deleteFromCart()
	 {
		 int cur_pid = Integer.parseInt(tableModel2.getValueAt(cart.getSelectedRow(),0).toString());
		 
		 for(int i=0 ; i<tableModel2.getRowCount(); i++)
		 {
			 if(cur_pid ==Integer.parseInt(tableModel2.getValueAt(i, 0).toString())) {
				 tableModel2.removeRow(i);
				 break;
			 }
		 }
	 }

	 
	 void billGenerate()
	 {
		 Connection conn=null;
		 PreparedStatement pst=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		
		 int pid;
		 int []products=new int[100];
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 
			 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sapa","root","tiger");
		 
		 
		 Float Amount=0.0f;
		 int row_count = tableModel2.getRowCount();
		 
		 for(int i=0 ; i<row_count ;i++)
		 {
			 Amount+= Float.parseFloat(tableModel2.getValueAt(i, 2).toString());
			 
			 pid = Integer.parseInt(tableModel2.getValueAt(i, 0).toString());
			 products[i]=pid;
			String sql="Update products set Stock=Stock-1 where productid=?" ;
			pst=conn.prepareStatement(sql);
			pst.setInt(1,pid);
			pst.executeUpdate();
		 }
		 
		 int oid=0;
		 //stmt = (Statement) conn.createStatement() ;
         // = stmt.executeQuery("select * from orders order by Orderid desc limit 1");
		 String sq = "select * from orders order by Orderid desc limit 1";
		 pst=conn.prepareStatement(sq);
         rs = pst.executeQuery(sq);
         if(rs.next())
        	 oid=rs.getInt(1)+1;
		 //oid=Integer.parseInt(rs.getObject(1).toString())+1;
		 //oid=6;
		 String sql="INSERT INTO orders (Orderid,ODate,Amount,custid) values(?,?,?,?)";
		 pst=conn.prepareStatement(sql);
		 long millis=System.currentTimeMillis();  
		 java.sql.Date date=new java.sql.Date(millis);  
		 pst.setInt(1, oid);
		 pst.setDate(2, Date.valueOf(java.time.LocalDate.now()) );
		 pst.setFloat(3,Amount);
		 pst.setInt(4, custid);
		 pst.executeUpdate();
		 
		 for(int i=0; i<products.length; i++)
		 {
			 System.out.println(products[i++]);
		 }
		 
		 for(int i=0; i<products.length; i++)
		 {
			 sql="INSERT INTO ordercontains (Orderid,Productid,Quantity) values(?,?,?)";
			 pst=conn.prepareStatement(sql);
			 pst.setInt(1, oid);
			 pst.setInt(2, products[i]);
			 pst.setInt(3, 1);
			 pst.executeUpdate();
			 if(products[i+1]==0)
				 break;
			 
		 }
		 
		 JOptionPane.showMessageDialog(this,"Pay here : "+Amount);
		 }
		 catch(SQLException e) {
			 e.printStackTrace();
		 }
		 catch(ClassNotFoundException e) {
			 e.printStackTrace();
		 }
		 
	 }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String []args)
	{
		new Order(1).setVisible(true);
	}
}
