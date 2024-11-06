package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

	public class Studentfeespage extends JFrame {

		
	    public Studentfeespage() {
	        setTitle("Student Fees");
	        setSize(1300, 800);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Set the content pane to our custom panel with a background image
	        BackgroundPanel backgroundPanel = new BackgroundPanel("C:\\Users\\sadin\\eclipse-workspace\\EAD_CourseWork\\images\\student fee.png");
	        setContentPane(backgroundPanel);
	        
	        /*JLabel newstudentlabel = new JLabel("student fees");
	        newstudentlabel.setBounds(180, 200, 320, 35);
	        newstudentlabel.setFont(new Font("Arial", Font.BOLD, 20));        
	        backgroundPanel.add(newstudentlabel);*/
	        
	        JButton manageroom = new JButton("manage room");
	        manageroom.setBounds(330, 40, 200, 40);
	        manageroom.setBackground(Color.decode("#DA6E6E"));
	        manageroom.setFont(new Font("Arial", Font.BOLD, 16));
	        manageroom.setForeground(Color.WHITE);
	        backgroundPanel.add(manageroom);
	        
	        JButton newstudent = new JButton("new student");
	        newstudent.setBounds(550, 40, 200, 40);
	        newstudent.setBackground(Color.decode("#DA6E6E"));
	        newstudent.setFont(new Font("Arial", Font.BOLD, 16));
	        newstudent.setForeground(Color.WHITE);
	        backgroundPanel.add(newstudent);
	        
	        JButton studentfee = new JButton("student fee");
	        studentfee.setBounds(770, 40, 200, 40);
	        studentfee.setBackground(Color.decode("#DA6E6E"));
	        studentfee.setFont(new Font("Arial", Font.BOLD, 16));
	        studentfee.setForeground(Color.black);
	        backgroundPanel.add(studentfee);
	        
	        manageroom.addActionListener(new ActionListener() {
	            
	            public void actionPerformed(ActionEvent e) {
	            	dispose();
	            	new Manageroompage();
	            }
	        });

	        newstudent.addActionListener(new ActionListener() {
	    
	        	public void actionPerformed(ActionEvent e) {
	        		dispose();
	        		new Newstudentpage();
	        	}	
	        });

	        studentfee.addActionListener(new ActionListener() {
	    
	        	public void actionPerformed(ActionEvent e) {
	        		//dispose();
	        		new Studentfeespage();
	        	}
	        });
	        
	        JTextField mobilenumber = new JTextField();
	        mobilenumber.setBounds(430, 215, 320, 30); // Position and size of the text field
	        backgroundPanel.add(mobilenumber);
	        
	        JTextField name = new JTextField();
	        name.setBounds(430, 300, 320, 30); // Position and size of the text field
	        backgroundPanel.add(name);	        

	        JTextField email = new JTextField();
	        email.setBounds(430, 340, 320, 30); // Position and size of the text field
	        backgroundPanel.add(email);

	        JTextField roomnumber = new JTextField();
	        roomnumber.setBounds(430, 380, 320, 30); // Position and size of the text field
	        backgroundPanel.add(roomnumber);

	        JTextField month = new JTextField();
	        month.setBounds(430, 420, 320, 30); // Position and size of the text field
	        backgroundPanel.add(month);

	        JTextField amounttotobepay = new JTextField();
	        amounttotobepay.setBounds(430, 460, 320, 30); // Position and size of the text field
	        backgroundPanel.add(amounttotobepay);
	        
	        JButton search = new JButton("search");
	        search.setBounds(780, 215, 150, 40); // Position and size of the button  
	        search.setBackground(new Color(0xDA6E6E));
	        search.setForeground(Color.WHITE); // Set text color to white
	        search.setFont(new Font("Arial", Font.BOLD, 16));
	        backgroundPanel.add(search);

	        
	        JButton save = new JButton("save");
	        save.setBounds(780, 460, 150, 40); // Position and size of the button  
	        save.setBackground(new Color(0xDA6E6E));
	        save.setForeground(Color.WHITE); // Set text color to white
	        save.setFont(new Font("Arial", Font.BOLD, 16));
	        backgroundPanel.add(save);
	        	    
	        backgroundPanel.setLayout(null);
	        setLocationRelativeTo(null); // Center the frame
	        setVisible(true);
	        setResizable(false);
	    }

	    // Custom JPanel to display background image
	    class BackgroundPanel extends JPanel {
	        private Image backgroundImage;

	        public BackgroundPanel(String imagePath) {
	            try {
	                backgroundImage = new ImageIcon(imagePath).getImage();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            if (backgroundImage != null) {
	                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	            }
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> new Studentfeespage());
	    }
	}

