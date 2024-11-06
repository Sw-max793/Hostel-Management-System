package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Manageroompage extends JFrame {
    private JTextField addRoomNumber;
    private JCheckBox addRoomActive;
    private JTextField updateDeleteRoomNumber;
    private JCheckBox updateDeleteCheckBox;
    private JTable roomTable;
    private DefaultTableModel tableModel;

    public Manageroompage() {
        setTitle("Manage Room");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with background image
        JPanel mainPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("C:\\Users\\sadin\\eclipse-workspace\\EAD_CourseWork\\images\\Manage Room.png").getImage(); // Path to your background image

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        mainPanel.setLayout(null); // Absolute positioning for custom layout  
        
        JButton manageroom = new JButton("manage room");
        manageroom.setBounds(330, 40, 200, 40);
        manageroom.setBackground(Color.decode("#DA6E6E"));
        manageroom.setFont(new Font("Arial", Font.BOLD, 16));
        manageroom.setForeground(Color.black);
        mainPanel.add(manageroom);
        
        JButton newstudent = new JButton("new student");
        newstudent.setBounds(550, 40, 200, 40);
        newstudent.setBackground(Color.decode("#DA6E6E"));
        newstudent.setFont(new Font("Arial", Font.BOLD, 16));
        newstudent.setForeground(Color.WHITE);
        mainPanel.add(newstudent);
        
        JButton studentfee = new JButton("student fee");
        studentfee.setBounds(770, 40, 200, 40);
        studentfee.setBackground(Color.decode("#DA6E6E"));
        studentfee.setFont(new Font("Arial", Font.BOLD, 16));
        studentfee.setForeground(Color.WHITE);
        mainPanel.add(studentfee);
        
        manageroom.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	//dispose();
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
        		dispose();
        		new Studentfeespage();
        	}
        });

        /*JLabel newstudentlabel = new JLabel("Manage Room");
        newstudentlabel.setBounds(180, 200, 320, 35);
        newstudentlabel.setFont(new Font("Arial", Font.BOLD, 20));        
        mainPanel.add(newstudentlabel);*/

        // Adding Room Section
        addRoomNumber = new JTextField();
        addRoomNumber.setBounds(350, 300, 100, 25);
        mainPanel.add(addRoomNumber);        

        addRoomActive = new JCheckBox("Active");
        addRoomActive.setBounds(800, 300, 25, 25);
        mainPanel.add(addRoomActive);

        JButton addRoomSave = new JButton("Save");
        addRoomSave.setBounds(900, 300, 80, 25);
        addRoomSave.setBackground(Color.decode("#DA6E6E"));
        addRoomSave.setForeground(Color.WHITE);
        mainPanel.add(addRoomSave);
        
        // Update/Delete Section
        updateDeleteRoomNumber = new JTextField();
        updateDeleteRoomNumber.setBounds(350, 410, 100, 25);
        mainPanel.add(updateDeleteRoomNumber);

        JButton search = new JButton("Search");
        search.setBounds(470, 410, 80, 25);
        search.setBackground(Color.decode("#DA6E6E"));
        search.setForeground(Color.WHITE);
        mainPanel.add(search);        

        updateDeleteCheckBox = new JCheckBox("Active");
        updateDeleteCheckBox.setBounds(940, 410, 25, 25);
        mainPanel.add(updateDeleteCheckBox);

        JButton update = new JButton("Update");
        update.setBounds(430, 700, 200, 40);
        update.setBackground(Color.decode("#DA6E6E"));
        update.setForeground(Color.WHITE);
        mainPanel.add(update);

        JButton delete = new JButton("Delete");
        delete.setBounds(670, 700, 200, 40);
        delete.setBackground(Color.decode("#DA6E6E"));
        delete.setForeground(Color.WHITE);
        mainPanel.add(delete);

        // Table to display room data
        String[] columnNames = {"Room Number", "Active"};
        tableModel = new DefaultTableModel(columnNames, 0);
        roomTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setBounds(100, 500, 1100, 150);
        mainPanel.add(scrollPane);

        // Button Actions
        addRoomSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveRoom();
            }
        });

        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchRoom();
            }
        });

        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateRoom();
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRoom();
            }
        });

        // Add main panel to frame
        add(mainPanel);
        setVisible(true);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        setResizable(false);
    }

    private void saveRoom() {
        String roomNumber = addRoomNumber.getText();
        boolean isActive = addRoomActive.isSelected();
        // Database connection and insert logic
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagementsystem", "root", "")) {
            String sql = "INSERT INTO rooms (room_number, active) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, roomNumber);
                preparedStatement.setBoolean(2, isActive);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Room added successfully");
                addRoomNumber.setText("");
                addRoomActive.setSelected(false);
                loadRooms(); // Refresh the room table
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding room: " + ex.getMessage());
        }
    }

    private void searchRoom() {
        String roomNumber = updateDeleteRoomNumber.getText();
        // Database connection and search logic
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagementsystem", "root", "")) {
            String sql = "SELECT * FROM rooms WHERE room_number = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, roomNumber);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    updateDeleteRoomNumber.setText(resultSet.getString("room_number"));
                    updateDeleteCheckBox.setSelected(resultSet.getBoolean("active"));
                } else {
                    JOptionPane.showMessageDialog(this, "Room not found");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching room: " + ex.getMessage());
        }
    }

    private void updateRoom() {
        String roomNumber = updateDeleteRoomNumber.getText();
        boolean isActive = updateDeleteCheckBox.isSelected();
        // Database connection and update logic
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagementsystem", "root", "")) {
            String sql = "UPDATE rooms SET active = ? WHERE room_number = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setBoolean(1, isActive);
                preparedStatement.setString(2, roomNumber);
                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Room updated successfully");
                    loadRooms(); // Refresh the room table
                } else {
                    JOptionPane.showMessageDialog(this, "Room not found");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating room: " + ex.getMessage());
        }
    }

    private void deleteRoom() {
        String roomNumber = updateDeleteRoomNumber.getText();
        // Database connection and delete logic
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagementsystem", "root", "")) {
            String sql = "DELETE FROM rooms WHERE room_number = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, roomNumber);
                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Room deleted successfully");
                    loadRooms(); // Refresh the room table
                } else {
                    JOptionPane.showMessageDialog(this, "Room not found");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting room: " + ex.getMessage());
        }
    }

    private void loadRooms() {
        // Clear existing data
        tableModel.setRowCount(0);
        // Database connection and load logic
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagementsystem", "root", "")) {
            String sql = "SELECT * FROM rooms";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(resultSet.getString("room_number"));
                    row.add(resultSet.getBoolean("active"));
                    tableModel.addRow(row);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading rooms: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Manageroompage());
    }
}
