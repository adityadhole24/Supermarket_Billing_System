package supermart;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

//import com.mysql.jdbc.ResultSetMetaData;
//import com.mysql.jdbc.Statement;

@SuppressWarnings("serial")
public class Inventorydetails extends JFrame{
	
	
		

		    public static void main(String[] args) {
		     
		                new Inventorydetails().setVisible(true);
		    }
          
		    private final JButton button;
		    private final JButton b1;
		    private final JTable table;
		    private final DefaultTableModel tableModel = new DefaultTableModel();

		    public Inventorydetails() throws HeadlessException {

		        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		        //Container con=getContentPane();
				//con.setLayout(new GridLayout(6,2));
		        table = new JTable(tableModel);
		        add(new JScrollPane(table), BorderLayout.CENTER);
		        
		       // final ImageIcon im=new ImageIcon("/home/neha/dest.jpeg");
		        
		        
		        Font f= new Font("Times New Roman",Font.BOLD,20);
                b1=new JButton("BACK");
		        button = new JButton("PRODUCT DETAILS");
		        button.setFont(f);
		        b1.setFont(f);
		        button.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                          
		            }
		        });
		        
		        loadData();
		        
		        b1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						
						new AdminHome();
						setVisible(false);
					}
				});
		        
		        add(button, BorderLayout.PAGE_START);
		        add(b1, BorderLayout.PAGE_END);
		       
                
		        setSize(900,550);
				setLocation(200,100);
		        setTitle("Product Inventory");
		     
		    }
            
		    private void loadData() {
		       

		        button.setEnabled(false);
		        
		        Connection conn=null;
				Statement stmt=null;
				ResultSet rs=null;
				

		        try {
		        	
		        	
		        	Class.forName("com.mysql.jdbc.Driver");
		    		
					conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sapa","root","tiger");
					
		        
		                 stmt = (Statement) conn.createStatement() ;

		             rs = stmt.executeQuery("select * from products");
		            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

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
		        button.setEnabled(true);

		        
		        
		    }
}