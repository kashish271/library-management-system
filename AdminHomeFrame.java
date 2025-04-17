import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminHomeFrame extends JFrame implements ActionListener {
    JButton btnMaintenance, btnReports, btnTransactions, btnLogout;
    JLabel lblTitle;

    public AdminHomeFrame() {
        setTitle("Admin Home - Library Management System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        lblTitle = new JLabel("Admin Home Page");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(100, 20, 200, 30);
        add(lblTitle);

        btnMaintenance = new JButton("Maintenance");
        btnMaintenance.setBounds(120, 70, 150, 30);
        btnMaintenance.addActionListener(this);
        add(btnMaintenance);

        btnReports = new JButton("Reports");
        btnReports.setBounds(120, 110, 150, 30);
        btnReports.addActionListener(this);
        add(btnReports);

        btnTransactions = new JButton("Transactions");
        btnTransactions.setBounds(120, 150, 150, 30);
        btnTransactions.addActionListener(this);
        add(btnTransactions);

        btnLogout = new JButton("Log Out");
        btnLogout.setBounds(120, 190, 150, 30);
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
            // You can later replace these with actual frame openings
        }
    }
}
