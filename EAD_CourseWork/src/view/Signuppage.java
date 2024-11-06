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
import java.sql.Statement;


public class Signuppage extends JFrame {

    private JTextField studentIdField;
    private JTextField usernameField;
    private JPasswordField setPasswordField;
    private JPasswordField confirmPasswordField;
    private JRadioButton showPasswordButton1;
    private JRadioButton showPasswordButton2;

    public Signuppage() {
        setTitle("Student SignUp");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        BackgroundPanel backgroundPanel = new BackgroundPanel("C:\\Users\\sadin\\eclipse-workspace\\EAD_CourseWork\\images\\Sign Up.png");
        setContentPane(backgroundPanel);

        studentIdField = new JTextField();
        studentIdField.setBounds(339, 410, 320, 35);
        studentIdField.setEditable(false);
        backgroundPanel.add(studentIdField);

        String newStudentId = generateStudentId();
        studentIdField.setText(newStudentId);

        usernameField = new JTextField();
        usernameField.setBounds(339, 485, 320, 35);
        backgroundPanel.add(usernameField);

        setPasswordField = new JPasswordField();
        setPasswordField.setBounds(339, 560, 320, 35);
        backgroundPanel.add(setPasswordField);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(339, 635, 320, 35);
        backgroundPanel.add(confirmPasswordField);

        JRadioButton showPasswordButton1 = new JRadioButton("Show");
        showPasswordButton1.setBounds(680, 560, 65, 30);
        showPasswordButton1.setBackground(new Color(0x9C8F85));
        showPasswordButton1.setForeground(Color.WHITE);
        backgroundPanel.add(showPasswordButton1);

        showPasswordButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordButton1.isSelected()) {
                    setPasswordField.setEchoChar((char) 0);
                } else {
                    setPasswordField.setEchoChar('*');
                }
            }
        });

        JRadioButton showPasswordButton2 = new JRadioButton("Show");
        showPasswordButton2.setBounds(680, 635, 65, 30);
        showPasswordButton2.setBackground(new Color(0x9C8F85));
        showPasswordButton2.setForeground(Color.WHITE);
        backgroundPanel.add(showPasswordButton2);

        showPasswordButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordButton2.isSelected()) {
                    confirmPasswordField.setEchoChar((char) 0);
                } else {
                    confirmPasswordField.setEchoChar('*');
                }
            }
        });

        JButton backButton = new JButton("< BACK");
        backButton.setBounds(339, 680, 150, 40);
        backButton.setBackground(new Color(0x9C8F85));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backgroundPanel.add(backButton);

        JButton submitButton = new JButton("NEXT >");
        submitButton.setBounds(508, 680, 150, 40);
        submitButton.setBackground(new Color(0x9C8F85));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        backgroundPanel.add(submitButton);
        backgroundPanel.setLayout(null);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText();
                String username = usernameField.getText();
                String password = new String(setPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (username.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Confirm Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    saveDataToDatabase(studentId, username, password);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FirstPage();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String generateStudentId() {
        String url = "jdbc:mysql://localhost:3306/studentmanagementsystem";
        String user = "root";
        String password = "";

        String lastStudentId = null;
        String newStudentId = null;

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            String query = "SELECT student_id FROM students ORDER BY id DESC LIMIT 1";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                lastStudentId = resultSet.getString("student_id");
            }

            if (lastStudentId != null) {
                int idNumber = Integer.parseInt(lastStudentId.substring(1));
                idNumber++;
                newStudentId = "S" + String.format("%03d", idNumber);
            } else {
                newStudentId = "S001";
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching Student ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return newStudentId;
    }

    private void saveDataToDatabase(String studentId, String username, String password) {
        String url = "jdbc:mysql://localhost:3306/studentmanagementsystem";
        String dbUser = "root";
        String dbPassword = "";

        String insertQuery = "INSERT INTO students (student_id, username, password) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Sign Up Successful!");

            dispose();
            new LoginPage();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Could not save data to database!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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
