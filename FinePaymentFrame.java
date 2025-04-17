import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FinePaymentFrame extends JFrame implements ActionListener {
    JLabel lblTitle, lblBookName, lblIssueDate, lblReturnDate, lblFine, lblPaymentMethod;
    JTextField txtBookName, txtIssueDate, txtReturnDate, txtFineAmount;
    JComboBox<String> cbPaymentMethod;
    JButton btnPay, btnCancel;

    public FinePaymentFrame(String bookName, LocalDate issueDate, LocalDate returnDate) {
        setTitle("Fine Payment");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblTitle = new JLabel("Pay Fine for Book Return");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(150, 10, 250, 30);
        add(lblTitle);

        lblBookName = new JLabel("Book Name:");
        lblBookName.setBounds(30, 60, 100, 25);
        add(lblBookName);

        txtBookName = new JTextField(bookName);
        txtBookName.setBounds(140, 60, 300, 25);
        txtBookName.setEditable(false);
        add(txtBookName);

        lblIssueDate = new JLabel("Issue Date:");
        lblIssueDate.setBounds(30, 100, 100, 25);
        add(lblIssueDate);

        txtIssueDate = new JTextField(issueDate.toString());
        txtIssueDate.setBounds(140, 100, 300, 25);
        txtIssueDate.setEditable(false);
        add(txtIssueDate);

        lblReturnDate = new JLabel("Return Date:");
        lblReturnDate.setBounds(30, 140, 100, 25);
        add(lblReturnDate);

        txtReturnDate = new JTextField(returnDate.toString());
        txtReturnDate.setBounds(140, 140, 300, 25);
        txtReturnDate.setEditable(false);
        add(txtReturnDate);

        lblFine = new JLabel("Fine Amount:");
        lblFine.setBounds(30, 180, 100, 25);
        add(lblFine);

        txtFineAmount = new JTextField(calculateFine(issueDate, returnDate) + " INR");
        txtFineAmount.setBounds(140, 180, 300, 25);
        txtFineAmount.setEditable(false);
        add(txtFineAmount);

        lblPaymentMethod = new JLabel("Payment Method:");
        lblPaymentMethod.setBounds(30, 220, 120, 25);
        add(lblPaymentMethod);

        String[] paymentMethods = {"Cash", "Card"};
        cbPaymentMethod = new JComboBox<>(paymentMethods);
        cbPaymentMethod.setBounds(140, 220, 300, 25);
        add(cbPaymentMethod);

        btnPay = new JButton("Pay");
        btnPay.setBounds(140, 260, 100, 30);
        btnPay.addActionListener(this);
        add(btnPay);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(260, 260, 100, 30);
        btnCancel.addActionListener(this);
        add(btnCancel);
    }

    private double calculateFine(LocalDate issueDate, LocalDate returnDate) {
        long daysLate = ChronoUnit.DAYS.between(issueDate.plusDays(15), returnDate);
        if (daysLate <= 0) {
            return 0;
        } else {
            return daysLate * 5;  // Example fine of 5 INR per day late
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPay) {
            double fineAmount = Double.parseDouble(txtFineAmount.getText().replace(" INR", ""));
            String paymentMethod = (String) cbPaymentMethod.getSelectedItem();

            if (fineAmount > 0) {
                JOptionPane.showMessageDialog(this, "Fine of " + fineAmount + " INR paid via " + paymentMethod + ".");
            } else {
                JOptionPane.showMessageDialog(this, "No fine to pay.");
            }
            dispose();  // Close payment frame after processing
        } else if (e.getSource() == btnCancel) {
            dispose();  // Close without payment
        }
    }

    public static void main(String[] args) {
        // Sample data for testing
        LocalDate issueDate = LocalDate.of(2025, 4, 1);
        LocalDate returnDate = LocalDate.of(2025, 4, 20);  // Late return
        new FinePaymentFrame("Quantum Physics", issueDate, returnDate).setVisible(true);
    }
}
