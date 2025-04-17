import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TransactionFrame extends JFrame implements ActionListener {
    private JButton btnIssueBook, btnReturnBook, btnBack;

    public TransactionFrame() {
        setTitle("Transaction Module");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblTitle = new JLabel("Library Transactions");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(100, 20, 250, 30);
        add(lblTitle);

        btnIssueBook = new JButton("Issue Book");
        btnIssueBook.setBounds(120, 70, 150, 30);
        btnIssueBook.addActionListener(this);
        add(btnIssueBook);

        btnReturnBook = new JButton("Return Book");
        btnReturnBook.setBounds(120, 110, 150, 30);
        btnReturnBook.addActionListener(this);
        add(btnReturnBook);

        btnBack = new JButton("Back");
        btnBack.setBounds(120, 150, 150, 30);
        btnBack.addActionListener(this);
        add(btnBack);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIssueBook) {
            IssueBookFrame issueFrame = new IssueBookFrame();
            issueFrame.setVisible(true);
        } else if (e.getSource() == btnReturnBook) {
            ReturnBookFrame returnFrame = new ReturnBookFrame();
            returnFrame.setVisible(true);
        } else if (e.getSource() == btnBack) {
            dispose(); // closes current frame
        }
    }

    public static void main(String[] args) {
        new TransactionFrame().setVisible(true);
    }
}
