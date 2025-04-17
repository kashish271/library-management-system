import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReturnBookFrame extends JFrame implements ActionListener {
    JLabel lblTitle, lblBookName, lblAuthor, lblSerial, lblIssueDate, lblReturnDate, lblRemarks;
    JTextField txtBookName, txtAuthor, txtSerial, txtIssueDate, txtReturnDate;
    JTextArea txtRemarks;
    JButton btnConfirm, btnClear;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ReturnBookFrame(String bookName, String authorName, String issueDateStr, String serialNo) {
        setTitle("Return Book");
        setSize(500, 420);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblTitle = new JLabel("Return Book");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(180, 10, 200, 30);
        add(lblTitle);

        lblBookName = new JLabel("Book Name:");
        lblBookName.setBounds(30, 60, 100, 25);
        add(lblBookName);

        txtBookName = new JTextField(bookName);
        txtBookName.setBounds(140, 60, 300, 25);
        txtBookName.setEditable(false);
        add(txtBookName);

        lblAuthor = new JLabel("Author:");
        lblAuthor.setBounds(30, 100, 100, 25);
        add(lblAuthor);

        txtAuthor = new JTextField(authorName);
        txtAuthor.setBounds(140, 100, 300, 25);
        txtAuthor.setEditable(false);
        add(txtAuthor);

        lblSerial = new JLabel("Serial No:");
        lblSerial.setBounds(30, 140, 100, 25);
        add(lblSerial);

        txtSerial = new JTextField(serialNo);
        txtSerial.setBounds(140, 140, 300, 25);
        add(txtSerial);

        lblIssueDate = new JLabel("Issue Date:");
        lblIssueDate.setBounds(30, 180, 100, 25);
        add(lblIssueDate);

        txtIssueDate = new JTextField(issueDateStr);
        txtIssueDate.setBounds(140, 180, 300, 25);
        txtIssueDate.setEditable(false);
        add(txtIssueDate);

        lblReturnDate = new JLabel("Return Date:");
        lblReturnDate.setBounds(30, 220, 100, 25);
        add(lblReturnDate);

        txtReturnDate = new JTextField(LocalDate.now().format(formatter));
        txtReturnDate.setBounds(140, 220, 300, 25);
        add(txtReturnDate);

        lblRemarks = new JLabel("Remarks:");
        lblRemarks.setBounds(30, 260, 100, 25);
        add(lblRemarks);

        txtRemarks = new JTextArea();
        txtRemarks.setBounds(140, 260, 300, 60);
        txtRemarks.setLineWrap(true);
        txtRemarks.setWrapStyleWord(true);
        add(txtRemarks);

        btnConfirm = new JButton("Confirm");
        btnConfirm.setBounds(140, 340, 100, 30);
        btnConfirm.addActionListener(this);
        add(btnConfirm);

        btnClear = new JButton("Clear");
        btnClear.setBounds(260, 340, 100, 30);
        btnClear.addActionListener(this);
        add(btnClear);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConfirm) {
            String serial = txtSerial.getText().trim();
            String returnDateStr = txtReturnDate.getText().trim();

            if (serial.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Serial number is required.");
                return;
            }

            try {
                LocalDate returnDate = LocalDate.parse(returnDateStr, formatter);
                LocalDate issueDate = LocalDate.parse(txtIssueDate.getText().trim(), formatter);

                if (returnDate.isBefore(issueDate.minusDays(1))) {
                    JOptionPane.showMessageDialog(this, "Return date cannot be before the issue date.");
                    return;
                }

                JOptionPane.showMessageDialog(this, "Book return data accepted. Proceeding to fine payment...");
                // Proceed to fine page (you can open FinePaymentFrame here)
                new FinePaymentFrame(serial, returnDate).setVisible(true);
                this.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid date in YYYY-MM-DD format.");
            }

        } else if (e.getSource() == btnClear) {
            txtSerial.setText("");
            txtRemarks.setText("");
            txtReturnDate.setText(LocalDate.now().format(formatter));
        }
    }

    public static void main(String[] args) {
        new ReturnBookFrame("Quantum Physics", "Dr. Singh", "2025-04-01", "SC(B/M)000001").setVisible(true);
    }
}
