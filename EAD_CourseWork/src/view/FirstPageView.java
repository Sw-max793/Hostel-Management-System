// View/FirstPageView.java
package view;

import javax.swing.*;
import java.awt.*;

public class FirstPageView extends JFrame {
    public JButton loginButton;
    public JButton signupButton;

    public FirstPageView() {
        setTitle("Welcome Page");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Set the content pane to our custom panel with a background image
        BackgroundPanel backgroundPanel = new BackgroundPanel("C:\\Users\\sadin\\eclipse-workspace\\EAD_CourseWork\\images\\signup.jpg");
        setContentPane(backgroundPanel);
        
        // Initialize buttons
        loginButton = new JButton("LOGIN");
        loginButton.setBounds(1000, 600, 190, 40); // Position and size of the button
        loginButton.setBackground(new Color(0xB04040));
        loginButton.setForeground(Color.WHITE); // Set text color to white
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        backgroundPanel.add(loginButton);
        
        signupButton = new JButton("SIGN UP");
        signupButton.setBounds(1000, 660, 190, 40); // Position and size of the button
        signupButton.setBackground(new Color(0xB04040));
        signupButton.setForeground(Color.WHITE); // Set text color to white
        signupButton.setFont(new Font("Arial", Font.BOLD, 16));
        backgroundPanel.add(signupButton);
        
        backgroundPanel.setLayout(null); // Set layout to null for absolute positioning
        
        setLocationRelativeTo(null); // Center the frame
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
}
