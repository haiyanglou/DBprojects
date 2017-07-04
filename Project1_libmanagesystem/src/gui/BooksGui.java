package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import dao.BooksDao;
import data.Books;
import data.BooksTable;

import javax.swing.JLabel;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import javax.swing.JButton;

public class BooksGui extends JFrame {

	private JPanel contentPane;
	private BooksDao booksDao;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public BooksGui() {
		setTitle("BOOK MANAGEMENT SYSTEM");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("TITLE:");
		panel.add(lblTitle);

		TextField textField = new TextField();
		panel.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("AUTHOR:");
		panel.add(label_1);

		TextField textField_1 = new TextField();
		textField_1.setColumns(10);
		panel.add(textField_1);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		//Initialize Table
		table = new JTable();
		scrollPane.setViewportView(table);

		// Initialize
		try {
			booksDao = new BooksDao();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error:" + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		//Search with one item
		/*
		 * Button button = new Button("SEARCH"); button.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) { try {
		 * String title = textField.getText(); List<Books> books = null; if
		 * (title != null && title.trim().length() > 0) { books =
		 * booksDao.searchBooks(title); } else { books = booksDao.getAllBooks();
		 * }
		 * 
		 * BooksTable tableInfo = new BooksTable(books);
		 * table.setModel(tableInfo);
		 * 
		 * } catch (Exception exc) {
		 * JOptionPane.showMessageDialog(BooksGUI.this, "Error" + exc, "Error",
		 * JOptionPane.ERROR_MESSAGE); } } }); panel.add(button); JScrollPane
		 * scrollPane = new JScrollPane(); contentPane.add(scrollPane,
		 * BorderLayout.CENTER); table = new JTable();
		 * scrollPane.setViewportView(table);
		 */

		//Search with more than 1 item
		JButton button_1 = new JButton("SEARCH");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String title = textField.getText();
					String author = textField_1.getText();
					List<Books> books = null;
					if ((title != null && title.trim().length() > 0)
							|| (author != null && author.trim().length() > 0)) {
						books = booksDao.searchBooks2(title, author);
					} else {
						books = booksDao.getAllBooks();
					}

					BooksTable tableInfo = new BooksTable(books);
					table.setModel(tableInfo);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(BooksGui.this, "Error" + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(button_1);
		
	}

	// Refresh
	public void refreshBooksView() {

		try {
			List<Books> books = booksDao.getAllBooks();
			BooksTable bt = new BooksTable(books);
			table.setModel(bt);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}
