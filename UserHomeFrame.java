import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserHomeFrame extends JFrame implements ActionListener {
    JButton btnReports, btnTransactions, btnLogout;
    JLabel lblTitle;

    public UserHomeFrame() {
        setTitle("User Home - Library Management System");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        lblTitle = new JLabel("User Home Page");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(110, 20, 200, 30);
        add(lblTitle);

        btnReports = new JButton("Reports");
        btnReports.setBounds(120, 70, 150, 30);
        btnReports.addActionListener(this);
        add(btnReports);

        btnTransactions = new JButton("Transactions");
        btnTransactions.setBounds(120, 110, 150, 30);
        btnTransactions.addActionListener(this);
        add(btnTransactions);

        btnLogout = new JButton("Log Out");
        btnLogout.setBounds(120, 150, 150, 30);
        btnLogout.addActionListener(this);
        add(btnLogout);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogout) {
            new LoginFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "This feature will be implemented soon.");
            // Later link to Reports or Transactions frame
        }
    }
}
