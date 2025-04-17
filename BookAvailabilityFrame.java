import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class BookAvailabilityFrame extends JFrame implements ActionListener {
    JLabel lblTitle, lblSearch;
    JTextField txtSearch;
    JButton btnSearch, btnConfirm;
    JTable table;
    DefaultTableModel model;
    ButtonGroup radioGroup;

    public BookAvailabilityFrame() {
        setTitle("Book Availability");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        lblTitle = new JLabel("Search Available Books");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(230, 10, 300, 30);
        add(lblTitle);

        lblSearch = new JLabel("Search (Title/Category):");
        lblSearch.setBounds(30, 60, 160, 25);
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(190, 60, 300, 25);
        add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(500, 60, 100, 25);
        btnSearch.addActionListener(this);
        add(btnSearch);

        // Table setup
        String[] columns = {"Code", "Title", "Author", "Category", "Available", "Select"};
        model = new DefaultTableModel(null, columns) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 5) ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(30, 100, 620, 180);
        add(scroll);

        btnConfirm = new JButton("Confirm Selection");
        btnConfirm.setBounds(250, 300, 180, 30);
        btnConfirm.addActionListener(this);
        add(btnConfirm);

        loadDummyData();
    }

    private void loadDummyData() {
        // Dummy rows
        model.setRowCount(0); // Clear table
        model.addRow(new Object[]{"SC(B/M)000001", "Quantum Physics", "Dr. Singh", "Science", "Yes", false});
        model.addRow(new Object[]{"EC(B/M)000002", "Microeconomics", "Prof. Mehta", "Economics", "Yes", false});
        model.addRow(new Object[]{"FC(B/M)000003", "Mystery Island", "A. Doyle", "Fiction", "No", false});
        model.addRow(new Object[]{"CH(B/M)000004", "The Jungle Book", "R. Kipling", "Children", "Yes", false});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            String keyword = txtSearch.getText().trim().toLowerCase();
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a keyword to search.");
                return;
            }

            // Simple search filter (for demo, no DB)
            for (int i = 0; i < model.getRowCount(); i++) {
                String title = model.getValueAt(i, 1).toString().toLowerCase();
                String category = model.getValueAt(i, 3).toString().toLowerCase();
                boolean match = title.contains(keyword) || category.contains(keyword);
                table.setRowHeight(i, match ? 25 : 0); // Hide unmatched rows
            }
        } else if (e.getSource() == btnConfirm) {
            int selectedRow = -1;
            int countSelected = 0;

            for (int i = 0; i < model.getRowCount(); i++) {
                Boolean isSelected = (Boolean) model.getValueAt(i, 5);
                if (isSelected != null && isSelected) {
                    selectedRow = i;
                    countSelected++;
                }
            }

            if (countSelected == 0) {
                JOptionPane.showMessageDialog(this, "Please select a book using the radio button.");
            } else if (countSelected > 1) {
                JOptionPane.showMessageDialog(this, "Please select only ONE book.");
            } else {
                String bookCode = model.getValueAt(selectedRow, 0).toString();
                String bookTitle = model.getValueAt(selectedRow, 1).toString();
                JOptionPane.showMessageDialog(this, "You selected: " + bookTitle + " (" + bookCode + ")");
                // You can redirect to Issue Book frame with this data
            }
        }
    }

    public static void main(String[] args) {
        new BookAvailabilityFrame().setVisible(true);
    }
}
