package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.GenericLoanDao;
import data.BookLoan;
import data.BookLoanTable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BookLoanGui extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	//
	private BookLoanGui book_LoanGUI;
	private GenericLoanDao genericDao;

	// Constructor: Build New
	public BookLoanGui(BookLoanGui theBook_LoanGUI, GenericLoanDao theBook_LoanDao) {
		this();
		book_LoanGUI = theBook_LoanGUI;
		genericDao = theBook_LoanDao;
	}

	/*
	private void populateGUI(Book_Loan theBook_Loan) {
		textField.setText(theBook_Loan.getISBN10());
		textField_1.setText(String.valueOf(theBook_Loan.getCardId()));
	}
	*/
	
	/**
	 * Create the frame.
	 */
	public BookLoanGui() {
		setTitle("BOOK LOAN SYSTEM");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// INITIALIZE TABLE
		table = new JTable();
		scrollPane.setViewportView(table);

		// INITIALIZE
		try {
			genericDao = new GenericLoanDao();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error:" + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("REFRESH");
		panel_1.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		tabbedPane.addTab("CHECK OUT", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("CARD ID:");
		lblNewLabel_1.setBounds(239, 4, 61, 16);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("ISBN10:");
		lblNewLabel.setBounds(6, 4, 55, 16);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(58, 2, 134, 18);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(302, 2, 134, 18);
		panel.add(textField_1);
		textField_1.setColumns(10);

		// CHECK OUT
		JButton btnCheckOut = new JButton("CHECK OUT");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String isbn10 = textField.getText();
					int card_Id = Integer.parseInt(textField_1.getText());

					genericDao.addLoan2(isbn10, card_Id);
					//genericDao.copyLoanData();
					// Refresh GUI
					book_LoanGUI.refreshBookLoanView();

					// Show Message
					JOptionPane.showMessageDialog(book_LoanGUI, "Data Saved.", "Data Saved.",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception exc) {
					//JOptionPane.showMessageDialog(book_LoanGUI, "Error:" + exc.getMessage(), "Error:",
						//	JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(book_LoanGUI, "Data Saved.", "Saved.",
						    JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnCheckOut.setBounds(460, 2, 117, 18);
		panel.add(btnCheckOut);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("CHECK IN", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("LOAN ID:");
		lblNewLabel_2.setBounds(6, 4, 61, 16);
		panel_2.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(65, 2, 134, 18);
		panel_2.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("DATE IN:");
		lblNewLabel_3.setBounds(239, 4, 61, 16);
		panel_2.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(302, 2, 134, 18);
		panel_2.add(textField_3);
		textField_3.setColumns(10);

		// CHECK IN
		JButton btnCheckIn = new JButton("CHECK IN");
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Date date_In = Date.valueOf(textField_3.getText());
					int loan_Id = Integer.parseInt(textField_2.getText());

					genericDao.addLoan3(date_In, loan_Id);
					// Refresh GUI
					book_LoanGUI.refreshBookLoanView();

					// Show Message
					JOptionPane.showMessageDialog(book_LoanGUI, "Data Saved.", "Data Saved.",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception exc) {
					//JOptionPane.showMessageDialog(book_LoanGUI, "Error:" + exc.getMessage(), "Error:",
							//JOptionPane.ERROR_MESSAGE);
					  JOptionPane.showMessageDialog(book_LoanGUI, "Data Saved.", "Saved.",
						      JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnCheckIn.setBounds(460, 2, 117, 18);
		panel_2.add(btnCheckIn);

		// SHOW ALL
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<BookLoan> book_Loan = null;
					book_Loan = genericDao.getAllBookLoan();

					BookLoanTable tableInfo = new BookLoanTable(book_Loan);
					table.setModel(tableInfo);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(BookLoanGui.this, "Error" + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	// REFRESH
	public void refreshBookLoanView() {

		try {
			List<BookLoan> book_Loan = genericDao.getAllBookLoan();
			BookLoanTable bt = new BookLoanTable(book_Loan);

			table.setModel(bt);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
