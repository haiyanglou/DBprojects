package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.BorrowersDao;
import gui.BorrowerGui;
import data.Borrowers;
import data.Borrowers;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBorrowerGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	private BorrowerGui borrowerGUI;
	private BorrowersDao borrowersDao;

	private Borrowers previousBorrowers = null;
	private boolean updateMode = false;

	// Constructor 1 : Update
	public AddBorrowerGui(BorrowerGui theBorrowerGUI, BorrowersDao theBorrowersDao, Borrowers thePreviousInfo,
			boolean theUpdateMode) {
		this();
		borrowersDao = theBorrowersDao;
		borrowerGUI = theBorrowerGUI;
		previousBorrowers = thePreviousInfo;
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Borrowers");
			populateGUI(previousBorrowers);
		}
	}

	// Constructor 2: Build New
	public AddBorrowerGui(BorrowerGui theBorrowerGUI, BorrowersDao theBorrowersDao) {
		this(theBorrowerGUI, theBorrowersDao, null, false);
	}

	// Populate Method
	private void populateGUI(Borrowers theBorrowers) {
		// Or textField.setText(Integer.toString(theBorrowers.getborrower_id()));
		textField.setText(String.valueOf(theBorrowers.getId()));
		textField_1.setText(theBorrowers.getSsn());
		textField_2.setText(theBorrowers.getFirstName());
		textField_3.setText(theBorrowers.getLastName());
		textField_4.setText(theBorrowers.getEmail());
		textField_5.setText(theBorrowers.getAddress());
		textField_6.setText(theBorrowers.getCity());
		textField_7.setText(theBorrowers.getState());
		textField_8.setText(theBorrowers.getPhone());

	}

	/**
	 * Create the frame.
	 */
	public AddBorrowerGui() {
		setResizable(false);
		setTitle("ADD NEW BORROWER");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("BORROWER_ID:");
		lblNewLabel.setBounds(37, 61, 109, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("SSN:");
		lblNewLabel_1.setBounds(37, 102, 109, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("FIRST NAME:");
		lblNewLabel_2.setBounds(37, 145, 109, 16);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("LAST NAME:");
		lblNewLabel_3.setBounds(37, 189, 109, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("EMAIL:");
		lblNewLabel_4.setBounds(37, 231, 109, 16);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("ADDRESS:");
		lblNewLabel_5.setBounds(37, 271, 109, 16);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("CITY:");
		lblNewLabel_6.setBounds(37, 315, 109, 16);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("STATE:");
		lblNewLabel_7.setBounds(37, 357, 109, 16);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("PHONE:");
		lblNewLabel_8.setBounds(37, 398, 109, 16);
		contentPane.add(lblNewLabel_8);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(175, 55, 346, 28);
		contentPane.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(175, 96, 346, 28);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setBounds(175, 139, 346, 28);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(175, 183, 346, 28);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(175, 225, 346, 28);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(175, 265, 346, 28);
		contentPane.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(175, 309, 346, 28);
		contentPane.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(175, 351, 346, 28);
		contentPane.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(175, 392, 346, 28);
		contentPane.add(textField_8);

		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveBorrower();
			}
		});
		btnNewButton.setBounds(533, 218, 117, 29);
		contentPane.add(btnNewButton);
	}

	// Methods for Adding/Updating and Saving
	public void saveBorrower() {
		long borrower_id = Integer.parseInt(textField.getText());
		String ssn = textField_1.getText();
		String first_name = textField_2.getText();
		String last_name = textField_3.getText();
		String email = textField_4.getText();
		String address = textField_5.getText();
		String city = textField_6.getText();
		String state = textField_7.getText();
		String phone = textField_8.getText();

		Borrowers theBorrowers = null;

		if (updateMode) {
			theBorrowers = previousBorrowers;
			theBorrowers.setId(borrower_id);
			theBorrowers.setSsn(ssn);
			theBorrowers.setFirstName(first_name);
			theBorrowers.setLastName(last_name);
			theBorrowers.setEmail(email);
			theBorrowers.setAddress(address);
			theBorrowers.setCity(city);
			theBorrowers.setState(state);
			theBorrowers.setPhone(phone);
		} else {
			theBorrowers = new Borrowers(borrower_id, ssn, first_name, last_name, email, address, city, state, phone);
		}

		try {
			// Save to Database
			if (updateMode) {
				borrowersDao.updateBorrower(theBorrowers);
			} else {
				borrowersDao.addBorrower(theBorrowers);
			}
			// Close Dialog
			setVisible(false);
			dispose();

			// Refresh GUI
			borrowerGUI.refreshBorrowersView();

			// Show Message
			JOptionPane.showMessageDialog(borrowerGUI, "Data Saved.", "Data Saved.", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(borrowerGUI, "Error:" + exc.getMessage(), "Error:",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
