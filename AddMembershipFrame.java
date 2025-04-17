import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddMembershipFrame extends JFrame implements ActionListener {
    // Labels
    private JLabel lblTitle, lblMemberName, lblAddress, lblPhone, lblEmail,
                   lblDuration, lblJoinDate, lblExpiryDate;
    // Input fields
    private JTextField txtMemberName, txtPhone, txtEmail, txtJoinDate, txtExpiryDate;
    private JTextArea txtAddress;
    // Duration radio buttons
    private JRadioButton rb6Months, rb1Year, rb2Years;
    private ButtonGroup durationGroup;
    // Buttons
    private JButton btnConfirm, btnClear;
    // Date formatter
    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AddMembershipFrame() {
        setTitle("Add Membership");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        // Title
        lblTitle = new JLabel("Add New Membership");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(180, 10, 300, 30);
        add(lblTitle);

        // Member Name
        lblMemberName = new JLabel("Member Name:");
        lblMemberName.setBounds(30, 60, 120, 25);
        add(lblMemberName);

        txtMemberName = new JTextField();
        txtMemberName.setBounds(160, 60, 380, 25);
        add(txtMemberName);

        // Address
        lblAddress = new JLabel("Address:");
        lblAddress.setBounds(30, 100, 120, 25);
        add(lblAddress);

        txtAddress = new JTextArea();
        txtAddress.setLineWrap(true);
        txtAddress.setWrapStyleWord(true);
        JScrollPane spAddress = new JScrollPane(txtAddress);
        spAddress.setBounds(160, 100, 380, 80);
        add(spAddress);

        // Phone
        lblPhone = new JLabel("Phone No.:");
        lblPhone.setBounds(30, 200, 120, 25);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(160, 200, 200, 25);
        add(txtPhone);

        // Email
        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 240, 120, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(160, 240, 200, 25);
        add(txtEmail);

        // Duration
        lblDuration = new JLabel("Duration:");
        lblDuration.setBounds(30, 280, 120, 25);
        add(lblDuration);

        rb6Months = new JRadioButton("6 Months", true);
        rb1Year   = new JRadioButton("1 Year");
        rb2Years  = new JRadioButton("2 Years");
        rb6Months.setBounds(160, 280, 100, 25);
        rb1Year.setBounds(270, 280, 80, 25);
        rb2Years.setBounds(360, 280, 100, 25);

        durationGroup = new ButtonGroup();
        durationGroup.add(rb6Months);
        durationGroup.add(rb1Year);
        durationGroup.add(rb2Years);

        add(rb6Months);
        add(rb1Year);
        add(rb2Years);

        // Join Date
        lblJoinDate = new JLabel("Join Date:");
        lblJoinDate.setBounds(30, 320, 120, 25);
        add(lblJoinDate);

        txtJoinDate = new JTextField(LocalDate.now().format(fmt));
        txtJoinDate.setBounds(160, 320, 200, 25);
        txtJoinDate.setEditable(false);
        add(txtJoinDate);

        // Expiry Date
        lblExpiryDate = new JLabel("Expiry Date:");
        lblExpiryDate.setBounds(30, 360, 120, 25);
        add(lblExpiryDate);

        txtExpiryDate = new JTextField();
        txtExpiryDate.setBounds(160, 360, 200, 25);
        txtExpiryDate.setEditable(false);
        add(txtExpiryDate);

        // Buttons
        btnConfirm = new JButton("Confirm");
        btnConfirm.setBounds(160, 410, 120, 30);
        btnConfirm.addActionListener(this);
        add(btnConfirm);

        btnClear = new JButton("Clear");
        btnClear.setBounds(300, 410, 120, 30);
        btnClear.addActionListener(this);
        add(btnClear);

        // Update expiry based on default selection
        updateExpiryDate();

        // Add listeners to radio buttons to recalc expiry
        rb6Months.addActionListener(e -> updateExpiryDate());
        rb1Year.addActionListener(e -> updateExpiryDate());
        rb2Years.addActionListener(e -> updateExpiryDate());
    }

    private void updateExpiryDate() {
        LocalDate join = LocalDate.now();
        if (rb6Months.isSelected()) {
            txtExpiryDate.setText(join.plusMonths(6).format(fmt));
        } else if (rb1Year.isSelected()) {
            txtExpiryDate.setText(join.plusYears(1).format(fmt));
        } else {
            txtExpiryDate.setText(join.plusYears(2).format(fmt));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConfirm) {
            // Validate mandatory fields
            if (txtMemberName.getText().trim().isEmpty() ||
                txtAddress.getText().trim().isEmpty() ||
                txtPhone.getText().trim().isEmpty() ||
                txtEmail.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please fill in all mandatory fields.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Simulate saving to database...
            JOptionPane.showMessageDialog(this,
                "Membership added!\nExpires on: " + txtExpiryDate.getText(),
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } else if (e.getSource() == btnClear) {
            txtMemberName.setText("");
            txtAddress.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            rb6Months.setSelected(true);
            updateExpiryDate();
        }
    }

    public static void main(String[] args) {
        new AddMembershipFrame().setVisible(true);
    }
}
