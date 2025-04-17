import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
    JLabel lblTitle, lblUsername, lblPassword, lblRole;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JRadioButton rbAdmin, rbUser;
    JButton btnLogin;
    ButtonGroup roleGroup;

    public LoginFrame() {
        setTitle("Library Management System - Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        lblTitle = new JLabel("Login Page");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(130, 10, 150, 30);
        add(lblTitle);

        lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 60, 100, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 60, 180, 25);
        add(txtUsername);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 100, 100, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 100, 180, 25);
        txtPassword.setEchoChar('*');
        add(txtPassword);

        lblRole = new JLabel("Login as:");
        lblRole.setBounds(50, 140, 100, 25);
        add(lblRole);

        rbAdmin = new JRadioButton("Admin");
        rbUser = new JRadioButton("User");
        rbAdmin.setBounds(150, 140, 70, 25);
        rbUser.setBounds(230, 140, 70, 25);

        roleGroup = new ButtonGroup();
        roleGroup.add(rbAdmin);
        roleGroup.add(rbUser);

        add(rbAdmin);
        add(rbUser);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 190, 100, 30);
        btnLogin.addActionListener(this);
        add(btnLogin);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        boolean isAdmin = rbAdmin.isSelected();
        boolean isUser = rbUser.isSelected();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
        } else if (!isAdmin && !isUser) {
            JOptionPane.showMessageDialog(this, "Please select a role: Admin or User.");
        } else {
            // Dummy logic for now - you can replace with DB validation
            if (username.equals("admin") && password.equals("admin123") && isAdmin) {
                JOptionPane.showMessageDialog(this, "Admin Login Successful!");
                new AdminHomeFrame().setVisible(true);
                this.dispose();
            } else if (username.equals("user") && password.equals("user123") && isUser) {
                JOptionPane.showMessageDialog(this, "User Login Successful!");
                new UserHomeFrame().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}
