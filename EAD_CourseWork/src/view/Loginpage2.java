package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Loginpage2 extends JPanel {
    private Image backgroundImage;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel forgotPasswordLabel;

    // Constructor to load the image
    public Loginpage2(String filePath) {
        this.backgroundImage = new ImageIcon(filePath).getImage();

        // Set layout to null for absolute positioning
        setLayout(null);

        // Initialize and position the username field
        usernameField = new JTextField();
        usernameField.setBounds(339, 375, 360, 30); // Position and size
        add(usernameField);

        // Initialize and position the password field
        passwordField = new JPasswordField();
        passwordField.setBounds(339, 474, 360, 30); // Position and size
        add(passwordField);

        // Initialize and customize the login button
        loginButton = new JButton("Login");
        loginButton.setBounds(400, 580, 180, 40); // Position and size
        loginButton.setBackground(new Color(0xDA6E6E)); // Set button color
        loginButton.setForeground(Color.WHITE); // Set text color to white
        loginButton.setFocusPainted(false); // Remove focus border
        loginButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and size

        // Set rounded corners by customizing the button's border
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(0xDA6E6E), 0, true));
        loginButton.setContentAreaFilled(false);
        loginButton.setOpaque(true);

        // Custom paint for rounded button
        loginButton.setUI(new RoundedButtonUI());
        add(loginButton);

        // Initialize and position the "Forgot Password" label
        forgotPasswordLabel = new JLabel("Forgot Password?");
        forgotPasswordLabel.setBounds(339, 520, 180, 30); // Position and size
        forgotPasswordLabel.setForeground(Color.BLUE.darker()); // Link-like color
        forgotPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hand cursor on hover

        // Add mouse listener to simulate clicking
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Forgot Password clicked!"); // Placeholder action
                // Implement your forgot password logic here
            }
        });
        add(forgotPasswordLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        // Create an instance of Loginpage with the image file path
        Loginpage2 panel = new Loginpage2("C:\\Users\\sadin\\eclipse-workspace\\EAD_CourseWork\\images\\Login Page.png");
        frame.add(panel);

        frame.setVisible(true);
        frame.setResizable(false);
    }
}

// Custom Button UI to achieve rounded corners
class RoundedButtonUI extends javax.swing.plaf.basic.BasicButtonUI {
    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        JButton button = (JButton) c;
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        JButton button = (JButton) c;
        int width = button.getWidth();
        int height = button.getHeight();

        // Fill rounded background
        g2.setColor(button.getBackground());
        g2.fillRoundRect(0, 0, width, height, 30, 30);

        // Draw button text
        super.paint(g2, c);
    }
}
