package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frmCsProject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmCsProject.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCsProject = new JFrame();
		frmCsProject.setResizable(false);
		frmCsProject.setTitle("CS6360 PROJECT 1 - LIBRARY SYSTEM");
		frmCsProject.setBounds(100, 100, 520, 315);
		frmCsProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCsProject.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("BOOK SEARCH SYSTEM");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BooksGui booksGUI = new BooksGui();
				booksGUI.setVisible(true);
			}
		});
		btnNewButton.setBounds(145, 64, 202, 29);
		frmCsProject.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BORRWOERS MANAGEMENT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrowerGui borrowerGUI = new BorrowerGui();
				borrowerGUI.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(145, 119, 202, 29);
		frmCsProject.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("BOOK LOANS");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookLoanGui bookLoanGUI = new BookLoanGui();
				bookLoanGUI.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(145, 170, 202, 29);
		frmCsProject.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("FINES MANAGEMENT");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinesGui finesGUI = new FinesGui();
				finesGUI.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(145, 221, 202, 29);
		frmCsProject.getContentPane().add(btnNewButton_3);
	}
}
