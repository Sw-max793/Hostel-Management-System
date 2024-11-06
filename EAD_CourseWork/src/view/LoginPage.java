package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends JFrame {

    public LoginPage() {
        setTitle("Login");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Set the content pane to our custom panel with a background image
        BackgroundPanel backgroundPanel = new BackgroundPanel("C:\\Users\\sadin\\eclipse-workspace\\EAD_CourseWork\\images\\Login Page.png");
        setContentPane(backgroundPanel);
        
        JTextField userField = new JTextField();
        userField.setBounds(339, 375, 360, 30);
        backgroundPanel.add(userField);
        
        JPasswordField passField = new JPasswordField();
        passField.setBounds(339, 474, 360, 30);
        passField.setEchoChar('*'); // Mask password input
        backgroundPanel.add(passField);

        // Create a radio button to show/hide the password
        JRadioButton showPasswordButton = new JRadioButton("Show");
        showPasswordButton.setBounds(720, 474, 70, 30); // Position of the radio button
        showPasswordButton.setBackground(new Color(0xB04040));
        showPasswordButton.setForeground(Color.WHITE);
        backgroundPanel.add(showPasswordButton);

        // Action listener to toggle password visibility
        showPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordButton.isSelected()) {
                    passField.setEchoChar((char) 0); // Show password
                } else {
                    passField.setEchoChar('*'); // Mask password
                }
            }
        });

        // Back button
        JButton backButton = new JButton("< BACK");
        backButton.setBounds(300, 580, 180, 40);
        backButton.setBackground(new Color(0xB04040));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backgroundPanel.add(backButton);
        
        // Back button action
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FirstPage(); // Redirect to the first page
            }
        });

        JButton submitButton = new JButton("Login");
        submitButton.setBounds(500, 580, 180, 40); // Position and size of the button
        submitButton.setBackground(new Color(0xB04040));
        submitButton.setForeground(Color.WHITE); // Set text color to white
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        backgroundPanel.add(submitButton);

        // Action listener for submit button to validate login
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (checkLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose();
                    new Homepage(); // Go to the main page after successful login
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Forgot Password label
        JLabel forgotPasswordLabel = new JLabel("<html><u>Forgot Password?</u></html>");
        forgotPasswordLabel.setBounds(339, 640, 150, 30);
        forgotPasswordLabel.setForeground(Color.BLUE);
        forgotPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backgroundPanel.add(forgotPasswordLabel);

        // Signup label
        JLabel signupLabel = new JLabel("<html>Don't have an account? <u>Sign up</u></html>");
        signupLabel.setBounds(339, 680, 300, 30);
        signupLabel.setForeground(Color.BLUE);
        signupLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backgroundPanel.add(signupLabel);

        backgroundPanel.setLayout(null); // Set layout to null for absolute positioning
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    // Method to check login credentials against the database
    private boolean checkLogin(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/studentmanagementsystem"; // Update with your database name
        String dbUser = "root";
        String dbPassword = "";

        String query = "SELECT * FROM students WHERE username = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Returns true if a record is found

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
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
        SwingUtilities.invokeLater(() -> new LoginPage());
    }
}
