package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.GenericLoanDao;
import data.Fines;
import data.FinesTable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class FinesGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	private FinesGui finesGUI;
	private GenericLoanDao genericDao;

	// Constructor: Build New
	public FinesGui(FinesGui theFinesGUI, GenericLoanDao theGenericDao) {
		this();
		finesGUI = theFinesGUI;
		genericDao = theGenericDao;
	}

	/**
	 * Create the frame.
	 */
	public FinesGui() {
		setTitle("FINES MANAGEMENT");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 54, 588, 331);
		contentPane.add(scrollPane);

		// INITIALIZE TABLE
		table = new JTable();
		scrollPane.setViewportView(table);

		// INITIALIZE
		try {
			genericDao = new GenericLoanDao();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error:" + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}

		JLabel lblNewLabel = new JLabel("LOAN ID:");
		lblNewLabel.setBounds(150, 17, 61, 16);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(220, 11, 159, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("PAY FINES");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int loan_Id = Integer.parseInt(textField.getText());

					genericDao.payfine(loan_Id);
					// Refresh GUI
					finesGUI.refreshFinesFineView();

					// Show Message
					JOptionPane.showMessageDialog(finesGUI, "Data Saved.", "Data Saved.",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception exc) {
					// JOptionPane.showMessageDialog(finesGUI, "Error:" +
					// exc.getMessage(), "Error:",
					// JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(finesGUI, "Data Saved.", "Saved.",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(391, 12, 117, 29);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton = new JButton("REFRESH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Fines> fines = null;
					fines = genericDao.getAllBookFine();

					FinesTable tableInfo = new FinesTable(fines);
					table.setModel(tableInfo);
					
					genericDao.refreshList();

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(FinesGui.this, "Error" + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(240, 394, 117, 29);
		contentPane.add(btnNewButton);
	}

	// REFRESH
	public void refreshFinesFineView() {

		try {
			List<Fines> fines = genericDao.getAllBookFine();
			FinesTable bt = new FinesTable(fines);

			table.setModel(bt);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
