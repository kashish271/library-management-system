import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class IssueBookFrame extends JFrame implements ActionListener {
    JLabel lblTitle, lblBookName, lblAuthor, lblIssueDate, lblReturnDate, lblRemarks;
    JTextField txtBookName, txtAuthor, txtIssueDate, txtReturnDate;
    JTextArea txtRemarks;
    JButton btnConfirm, btnClear;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public IssueBookFrame(String bookName, String authorName) {
        setTitle("Issue Book");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblTitle = new JLabel("Issue Book");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(180, 10, 200, 30);
        add(lblTitle);

        lblBookName = new JLabel("Book Name:");
        lblBookName.setBounds(30, 60, 100, 25);
        add(lblBookName);

        txtBookName = new JTextField(bookName);
        txtBookName.setBounds(140, 60, 300, 25);
        add(txtBookName);

        lblAuthor = new JLabel("Author Name:");
        lblAuthor.setBounds(30, 100, 100, 25);
        add(lblAuthor);

        txtAuthor = new JTextField(authorName);
        txtAuthor.setBounds(140, 100, 300, 25);
        txtAuthor.setEditable(false);
        add(txtAuthor);

        lblIssueDate = new JLabel("Issue Date:");
        lblIssueDate.setBounds(30, 140, 100, 25);
        add(lblIssueDate);

        txtIssueDate = new JTextField(LocalDate.now().format(formatter));
        txtIssueDate.setBounds(140, 140, 300, 25);
        add(txtIssueDate);

        lblReturnDate = new JLabel("Return Date:");
        lblReturnDate.setBounds(30, 180, 100, 25);
        add(lblReturnDate);

        txtReturnDate = new JTextField(LocalDate.now().plusDays(15).format(formatter));
        txtReturnDate.setBounds(140, 180, 300, 25);
        add(txtReturnDate);

        lblRemarks = new JLabel("Remarks:");
        lblRemarks.setBounds(30, 220, 100, 25);
        add(lblRemarks);

        txtRemarks = new JTextArea();
        txtRemarks.setBounds(140, 220, 300, 60);
        txtRemarks.setLineWrap(true);
        txtRemarks.setWrapStyleWord(true);
        add(txtRemarks);

        btnConfirm = new JButton("Confirm");
        btnConfirm.setBounds(140, 300, 100, 30);
        btnConfirm.addActionListener(this);
        add(btnConfirm);

        btnClear = new JButton("Clear");
        btnClear.setBounds(260, 300, 100, 30);
        btnClear.addActionListener(this);
        add(btnClear);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConfirm) {
            String book = txtBookName.getText().trim();
            String issueDateStr = txtIssueDate.getText().trim();
            String returnDateStr = txtReturnDate.getText().trim();

            if (book.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Book name is required.");
                return;
            }

            try {
                LocalDate issueDate = LocalDate.parse(issueDateStr, formatter);
                LocalDate returnDate = LocalDate.parse(returnDateStr, formatter);

                if (issueDate.isBefore(LocalDate.now())) {
                    JOptionPane.showMessageDialog(this, "Issue Date cannot be before today.");
                    return;
                }

                long daysBetween = ChronoUnit.DAYS.between(issueDate, returnDate);
                if (daysBetween < 0 || daysBetween > 15) {
                    JOptionPane.showMessageDialog(this, "Return date must be within 15 days from issue date.");
                    return;
                }

                // Simulate issue complete
                JOptionPane.showMessageDialog(this, "Book Issued Successfully!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid dates in YYYY-MM-DD format.");
            }
        } else if (e.getSource() == btnClear) {
            txtBookName.setText("");
            txtRemarks.setText("");
            txtIssueDate.setText(LocalDate.now().format(formatter));
            txtReturnDate.setText(LocalDate.now().plusDays(15).format(formatter));
        }
    }

    public static void main(String[] args) {
        // Dummy values for testing
        new IssueBookFrame("Quantum Physics", "Dr. Singh").setVisible(true);
    }
}
