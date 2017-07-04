package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.BorrowersDao;
import data.Borrowers;
import data.BorrowersTable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class BorrowerGui extends JFrame {

	private JPanel contentPane;
	private BorrowersDao borrowersDao;
	private JTable table;
	
	/**
	 * Create the frame.
	 */
	public BorrowerGui() {
		setTitle("BORROWER MANAGEMENT SYSTEM");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		//INITIALIZE
		try {
			borrowersDao = new BorrowersDao();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error:" + exc, "Error", JOptionPane.ERROR_MESSAGE);

		}
		
		//INITIALIZE TABLE
		table = new JTable();
		scrollPane.setViewportView(table);

		// SHOW ALL
		JButton btnNewButton_3 = new JButton("REFRESH");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Borrowers> borrowers = null;
					borrowers = borrowersDao.getAllBorrowers();

					BorrowersTable tableInfo = new BorrowersTable(borrowers);
					table.setModel(tableInfo);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(BorrowerGui.this, "Error" + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnNewButton_3);

		// ADD
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBorrowerGui addBorrowerGUI = new AddBorrowerGui(BorrowerGui.this, borrowersDao);
				addBorrowerGUI.setVisible(true);
			}
		});
		panel.add(btnNewButton);

		// DELETE
		JButton btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Get Selected
					int row = table.getSelectedRow();
					//Make Sure One is Selected
					if (row < 0) {
						JOptionPane.showMessageDialog(BorrowerGui.this, "Please Select One", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					//Prompt User
					int response = JOptionPane.showConfirmDialog(BorrowerGui.this, "DELETE?", "ARE YOU SURE?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}
					// Get Current
					Borrowers theborrowers = (Borrowers) table.getValueAt(row, BorrowersTable.OBJECT_COL);
					// Delete
					borrowersDao.deleteBorrower(theborrowers.getId());
					// Refresh
					refreshBorrowersView();

					// Show Success Message
					JOptionPane.showMessageDialog(BorrowerGui.this, "Data Deleted", "Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exception) {
					JOptionPane.showMessageDialog(BorrowerGui.this, "ERROR! " + exception.getMessage(), "ERROR!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnNewButton_1);

		// UPDATE
		JButton btnNewButton_2 = new JButton("UPDATE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get Selected
				int row = table.getSelectedRow();
				//Make Sure One is Selected
				if (row < 0) {
					JOptionPane.showMessageDialog(BorrowerGui.this, "Please Select One", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Get Current
				Borrowers theBorrowers = (Borrowers) table.getValueAt(row,
						BorrowersTable.OBJECT_COL);

				AddBorrowerGui addBorrowerGUI = new AddBorrowerGui(BorrowerGui.this, borrowersDao,
						theBorrowers, true);
				addBorrowerGUI.setVisible(true);
			}
		});
		panel.add(btnNewButton_2);

	}
	
	//REFRESH
	public void refreshBorrowersView() {

		try {
			List<Borrowers> borrowers = borrowersDao.getAllBorrowers();
			BorrowersTable bt = new BorrowersTable(borrowers);

			table.setModel(bt);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
