package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import data.Books;

public class BooksDao {
	private Connection myConn;
	private static String url = "jdbc:mysql://127.0.0.1:3306/Project_1";
	private static String user = "root";
	private static String password = "";

	public BooksDao() throws Exception {
		myConn = DriverManager.getConnection(url, user, password);
	}

	// Get All Books
	public List<Books> getAllBooks() throws Exception {
		List<Books> list = new ArrayList<>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM Books");

			while (myRs.next()) {
				Books books = convertRowToBooks(myRs);
				list.add(books);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	// Search Books with TITLE
	/*
	public List<Books> searchBooks(String title) throws Exception {
		List<Books> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			title += "%";
			myStmt = myConn.prepareStatement("SELECT * FROM Books WHERE title LIKE ?");

			myStmt.setString(1, title);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Books books = convertRowToBooks(myRs);
				list.add(books);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}
	*/

	// Search Books with MORE THAN 1 COLUMNS
	public List<Books> searchBooks2(String title, String author) throws Exception {
		List<Books> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			title += "%";
			author +="%";
			myStmt = myConn.prepareStatement("SELECT * FROM Books WHERE title LIKE ? AND author LIKE ?");

			myStmt.setString(1, title);
			myStmt.setString(2, author);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Books books = convertRowToBooks(myRs);
				list.add(books);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	//////////
	private Books convertRowToBooks(ResultSet myRs) throws SQLException {
		String ISBN10 = myRs.getString("ISBN10");
		String ISBN13 = myRs.getString("ISBN13");
		String title = myRs.getString("title");
		String author = myRs.getString("author");
		String cover = myRs.getString("cover");
		String publisher = myRs.getString("publisher");
		int pages = myRs.getInt("pages");

		Books books = new Books(ISBN10, ISBN13, title, author, cover, publisher, pages);
		return books;

	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {

		}

		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);
	}

	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);
	}

}
