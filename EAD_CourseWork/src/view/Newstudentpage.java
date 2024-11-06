package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Newstudentpage extends JFrame {

    private JTextField name;
    private JTextField mobilenumber;
    private JTextField email;
    private JTextField guardianname;
    private JTextField guardiantel;
    private JTextField roomnumber;

    public Newstudentpage() {
        setTitle("New Student");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the content pane to our custom panel with a background image
        BackgroundPanel backgroundPanel = new BackgroundPanel("C:\\Users\\sadin\\eclipse-workspace\\EAD_CourseWork\\images\\New Student.png");
        setContentPane(backgroundPanel);

        /*JLabel newstudentlabel = new JLabel("New Student");
        newstudentlabel.setBounds(180, 200, 320, 35);
        newstudentlabel.setFont(new Font("Arial", Font.BOLD, 20));
        backgroundPanel.add(newstudentlabel);*/

        JButton manageroom = new JButton("Manage Room");
        manageroom.setBounds(330, 40, 200, 40);
        manageroom.setBackground(Color.decode("#DA6E6E"));
        manageroom.setFont(new Font("Arial", Font.BOLD, 16));
        manageroom.setForeground(Color.WHITE);
        backgroundPanel.add(manageroom);

        JButton newstudent = new JButton("New Student");
        newstudent.setBounds(550, 40, 200, 40);
        newstudent.setBackground(Color.decode("#DA6E6E"));
        newstudent.setFont(new Font("Arial", Font.BOLD, 16));
        newstudent.setForeground(Color.black);
        backgroundPanel.add(newstudent);

        JButton studentfee = new JButton("Student Fee");
        studentfee.setBounds(770, 40, 200, 40);
        studentfee.setBackground(Color.decode("#DA6E6E"));
        studentfee.setFont(new Font("Arial", Font.BOLD, 16));
        studentfee.setForeground(Color.WHITE);
        backgroundPanel.add(studentfee);

        manageroom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Manageroompage();
            }
        });

        newstudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Newstudentpage();
            }
        });

        studentfee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Studentfeespage();
            }
        });

        name = new JTextField();
        name.setBounds(480, 250, 320, 35);
        backgroundPanel.add(name);

        mobilenumber = new JTextField();
        mobilenumber.setBounds(480, 300, 320, 35);
        backgroundPanel.add(mobilenumber);

        email = new JTextField();
        email.setBounds(480, 350, 320, 35);
        backgroundPanel.add(email);

        guardianname = new JTextField();
        guardianname.setBounds(480, 400, 320, 35);
        backgroundPanel.add(guardianname);

        guardiantel = new JTextField();
        guardiantel.setBounds(480, 450, 320, 35);
        backgroundPanel.add(guardiantel);

        roomnumber = new JTextField();
        roomnumber.setBounds(480, 500, 320, 35);
        backgroundPanel.add(roomnumber);

        JButton save = new JButton("Save");
        save.setBounds(630, 550, 100, 40);
        save.setBackground(new Color(0xDA6E6E));
        save.setForeground(Color.WHITE);
        save.setFont(new Font("Arial", Font.BOLD, 16));
        backgroundPanel.add(save);

        // Action Listener for Save button
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get data from text fields
                String studentName = name.getText();
                String mobileNumber = mobilenumber.getText();
                String emailAddress = email.getText();
                String guardianName = guardianname.getText();
                String guardianTel = guardiantel.getText();
                String roomNumber = roomnumber.getText();

                // Call the method to save data to the database
                saveStudentData(studentName, mobileNumber, emailAddress, guardianName, guardianTel, roomNumber);
            }
        });

        backgroundPanel.setLayout(null);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
        setResizable(false);
    }

    // Method to save student data to the database
    private void saveStudentData(String studentName, String mobileNumber, String emailAddress,
                                  String guardianName, String guardianTel, String roomNumber) {
        String url = "jdbc:mysql://localhost:3306/studentmanagementsystem"; // Update with your database URL
        String user = "root"; // Update with your database username
        String password = ""; // Update with your database password

        String sql = "INSERT INTO students1 (name, mobile_number, email, guardian_name, guardian_tel, room_number) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters
            pstmt.setString(1, studentName);
            pstmt.setString(2, mobileNumber);
            pstmt.setString(3, emailAddress);
            pstmt.setString(4, guardianName);
            pstmt.setString(5, guardianTel);
            pstmt.setString(6, roomNumber);

            // Execute the insert
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student data saved successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage());
        }
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
        SwingUtilities.invokeLater(() -> new Newstudentpage());
    }
}
