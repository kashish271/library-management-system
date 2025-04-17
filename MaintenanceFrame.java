import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MaintenanceFrame extends JFrame implements ActionListener {
    // Labels and text fields for input
    private JLabel lblTitle, lblSerial, lblBookName, lblAuthor, lblCategory;
    private JTextField txtSerial, txtBookName, txtAuthor, txtCategory;
    private JButton btnAddBook, btnUpdateBook, btnRemoveBook, btnClear;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    
    private ArrayList<Book> bookList = new ArrayList<>(); // Simulating a database for books

    public MaintenanceFrame() {
        setTitle("Library Maintenance");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        // Title
        lblTitle = new JLabel("Library Maintenance");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(180, 10, 300, 30);
        add(lblTitle);

        // Serial No.
        lblSerial = new JLabel("Serial No.:");
        lblSerial.setBounds(30, 60, 120, 25);
        add(lblSerial);

        txtSerial = new JTextField();
        txtSerial.setBounds(160, 60, 200, 25);
        add(txtSerial);

        // Book Name
        lblBookName = new JLabel("Book Name:");
        lblBookName.setBounds(30, 100, 120, 25);
        add(lblBookName);

        txtBookName = new JTextField();
        txtBookName.setBounds(160, 100, 200, 25);
        add(txtBookName);

        // Author
        lblAuthor = new JLabel("Author:");
        lblAuthor.setBounds(30, 140, 120, 25);
        add(lblAuthor);

        txtAuthor = new JTextField();
        txtAuthor.setBounds(160, 140, 200, 25);
        add(txtAuthor);

        // Category
        lblCategory = new JLabel("Category:");
        lblCategory.setBounds(30, 180, 120, 25);
        add(lblCategory);

        txtCategory = new JTextField();
        txtCategory.setBounds(160, 180, 200, 25);
        add(txtCategory);

        // Buttons
        btnAddBook = new JButton("Add Book");
        btnAddBook.setBounds(30, 220, 120, 30);
        btnAddBook.addActionListener(this);
        add(btnAddBook);

        btnUpdateBook = new JButton("Update Book");
        btnUpdateBook.setBounds(160, 220, 120, 30);
        btnUpdateBook.addActionListener(this);
        add(btnUpdateBook);

        btnRemoveBook = new JButton("Remove Book");
        btnRemoveBook.setBounds(290, 220, 120, 30);
        btnRemoveBook.addActionListener(this);
        add(btnRemoveBook);

        btnClear = new JButton("Clear");
        btnClear.setBounds(420, 220, 120, 30);
        btnClear.addActionListener(this);
        add(btnClear);

        // Table for displaying book details
        String[] columns = {"Serial No.", "Book Name", "Author", "Category"};
        tableModel = new DefaultTableModel(columns, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.setBounds(30, 270, 500, 150);
        add(scrollPane);

        // Example data
        bookList.add(new Book("SCB001", "Java Programming", "John Doe", "Programming"));
        bookList.add(new Book("SCB002", "Database Systems", "Jane Smith", "Database"));
        updateBookTable();
    }

    private void updateBookTable() {
        tableModel.setRowCount(0);  // Clear existing rows
        for (Book book : bookList) {
            tableModel.addRow(new Object[]{book.getSerialNo(), book.getBookName(), book.getAuthor(), book.getCategory()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddBook) {
            String serial = txtSerial.getText().trim();
            String bookName = txtBookName.getText().trim();
            String author = txtAuthor.getText().trim();
            String category = txtCategory.getText().trim();

            if (serial.isEmpty() || bookName.isEmpty() || author.isEmpty() || category.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            bookList.add(new Book(serial, bookName, author, category));
            updateBookTable();
            JOptionPane.showMessageDialog(this, "Book added successfully!");
            clearFields();
        }

        if (e.getSource() == btnUpdateBook) {
            String serial = txtSerial.getText().trim();
            Book bookToUpdate = findBookBySerial(serial);

            if (bookToUpdate != null) {
                bookToUpdate.setBookName(txtBookName.getText().trim());
                bookToUpdate.setAuthor(txtAuthor.getText().trim());
                bookToUpdate.setCategory(txtCategory.getText().trim());
                updateBookTable();
                JOptionPane.showMessageDialog(this, "Book updated successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Book not found.");
            }
        }

        if (e.getSource() == btnRemoveBook) {
            String serial = txtSerial.getText().trim();
            Book bookToRemove = findBookBySerial(serial);

            if (bookToRemove != null) {
                bookList.remove(bookToRemove);
                updateBookTable();
                JOptionPane.showMessageDialog(this, "Book removed successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Book not found.");
            }
        }

        if (e.getSource() == btnClear) {
            clearFields();
        }
    }

    private Book findBookBySerial(String serial) {
        for (Book book : bookList) {
            if (book.getSerialNo().equals(serial)) {
                return book;
            }
        }
        return null;
    }

    private void clearFields() {
        txtSerial.setText("");
        txtBookName.setText("");
        txtAuthor.setText("");
        txtCategory.setText("");
    }

    public static void main(String[] args) {
        new MaintenanceFrame().setVisible(true);
    }
}

class Book {
    private String serialNo;
    private String bookName;
    private String author;
    private String category;

    public Book(String serialNo, String bookName, String author, String category) {
        this.serialNo = serialNo;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
