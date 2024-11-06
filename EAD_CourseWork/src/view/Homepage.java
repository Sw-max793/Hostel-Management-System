package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame {

    public Homepage() {
        // Set frame properties
        setTitle("Home Page");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null); // Absolute positioning for buttons

        // Create buttons
        JButton Manageroom = new JButton("Manage Room");
        JButton Newstudent = new JButton("New Student");
        JButton Studentfess = new JButton("Student Fess");

        // Set button positions
        Manageroom.setBounds(158, 120, 275, 40);
        Newstudent.setBounds(510, 120, 275, 40);
        Studentfess.setBounds(862, 120, 275, 40);

        // Set button colors
        Color buttonColor = new Color(0xDA6E6E);
        Manageroom.setBackground(buttonColor);
        Newstudent.setBackground(buttonColor);
        Studentfess.setBackground(buttonColor);

        // Add buttons to the panel
        backgroundPanel.add(Manageroom);
        backgroundPanel.add(Newstudent);
        backgroundPanel.add(Studentfess);
        
        Manageroom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Manageroompage();
                
            }
        });
        
        Newstudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Newstudentpage();
            }
        });
        
        Studentfess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Studentfeespage();
            }
        });

        setContentPane(backgroundPanel);
        setVisible(true);
        setResizable(false);
        
        
    }

    // Custom JPanel to add background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = new ImageIcon("C:\\Users\\sadin\\eclipse-workspace\\EAD_CourseWork\\images\\Home Page.png").getImage();
                if (backgroundImage == null) {
                    System.out.println("Background image could not be loaded.");
                }
            } catch (Exception ex) {
                System.out.println("Image not found!");
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
        SwingUtilities.invokeLater(() -> new Homepage());
    }
}
