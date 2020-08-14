package supermart;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener{

	private JButton button1,button2,button3;
	JLabel label,l1,l,l2,l3;
	private String sysdate,systime;
	public Main()
	{
		
		Container con=getContentPane();
		con.setLayout(new GridLayout(7,2));
		
		Font f=new Font("Times New Roman",Font.CENTER_BASELINE,35);
		Font f1=new Font("Serif",Font.BOLD,30);
		l=new JLabel("Welcome to SAPA MART!!");
		l.setFont(f1);
		l.setForeground(Color.BLUE);
		
		
		button1=new JButton("CUSTOMER");
		button2=new JButton("ADMINISTRATOR");
		button3=new JButton("EXIT");
		Calendar cal = Calendar.getInstance();
		String cday = ""+cal.get(Calendar.DATE);
		int x =cal.get(Calendar.MONTH);
		String cmonth = ""+(x+1);
		String cyear =""+cal.get(Calendar.YEAR); 
		sysdate = cday+"/"+cmonth+"/"+cyear;
		l2=new JLabel("                 DATE:    "+sysdate); 		
		
		Font f3=new Font("Times New Roman",Font.CENTER_BASELINE,20);
		l2.setFont(f3);
		con.add(l);con.add(l2);
		con.add(new JLabel(""));
		con.add(new JLabel(""));con.add(new JLabel(""));
		con.add(new JLabel(""));con.add(new JLabel(""));
		con.add(new JLabel(""));
		con.add(new JLabel(""));
	
	    button1.setForeground(Color.BLACK);
	    button1.setFocusPainted(false);
	    button1.setFont(new Font("Tahoma", Font.BOLD,30));


          button1.setOpaque(false);
          button1.setContentAreaFilled(false);


	
	    button2.setForeground(Color.BLACK);
	    button2.setFocusPainted(false);
	    button2.setFont(new Font("Tahoma", Font.BOLD,30));
	    button2.setOpaque(false);
	    button2.setContentAreaFilled(false);
	
	   button3.setBackground(Color.WHITE);
	    button3.setForeground(Color.BLACK);
	    button3.setFocusPainted(false);
	    button3.setFont(new Font("Tahoma", Font.BOLD,30));
	    button3.setOpaque(false);
	    button3.setContentAreaFilled(false);
	    con.add(new JLabel(""));	con.add(button1);
		con.add(button2);	con.add(button3);
		
		button1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			new Customer();
				setVisible(false);
			}
		});	
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Admin();
				setVisible(false);
			}
		});	
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});	
		
			setSize(900,550);
			setLocation(200,100);
			setResizable(false); 
			setTitle("SUPERMARKET BILLING SYSTEM");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			setVisible(true);
		
			setLayout(new BorderLayout());
			JLabel background=new JLabel(new ImageIcon(new ImageIcon("/Users/Pratyush mandal/Pictures/pencil.jpg").getImage().getScaledInstance(900, 550, Image.SCALE_DEFAULT)));
			add(background);
			
			
			
	}
	public static void main(String args[])
	{
		new Main();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
}

