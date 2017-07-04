package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import data.BookLoan;
import data.Fines;

public class GenericLoanDao {
	private Connection myConn;
	private static String url = "jdbc:mysql://127.0.0.1:3306/Project_1";
	private static String user = "root";
	private static String password = "";

	public GenericLoanDao() throws Exception {
		myConn = DriverManager.getConnection(url, user, password);
	}

	// Get All Loans
	public List<BookLoan> getAllBookLoan() throws Exception {
		List<BookLoan> list = new ArrayList<>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM BookLoans");

			while (myRs.next()) {
				BookLoan book_Loans = convertRowToBook_Loan(myRs);
				list.add(book_Loans);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	// Add Loan 1
	public void addLoan(BookLoan book_Loan) throws Exception {
		PreparedStatement myStmt = null;

		try {
			myStmt = myConn.prepareStatement(
					"INSERT INTO BookLoans (Loan_Id, ISBN10, Card_Id, Date_Out, Due_Date, Date_In) VALUES (?,?,?,?,?,?)");
			myStmt.setInt(1, book_Loan.getLoanId());
			myStmt.setString(2, book_Loan.getISBN10());
			myStmt.setInt(3, book_Loan.getCardId());
			myStmt.setDate(4, book_Loan.getDateOut());
			myStmt.setDate(5, book_Loan.getDueDate());
			myStmt.setDate(6, book_Loan.getDateIn());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}

	}

	// Add Loan 2 CHECK OUT
	public void addLoan2(String ISBN10, int card_Id) throws Exception {
		PreparedStatement myStmt = null;

		try {
			myStmt = myConn.prepareStatement("INSERT INTO BookLoans (ISBN10, Card_Id, Date_Out, Due_Date) VALUES"
					+ " (?,?,CURDATE(),ADDDATE(CURDATE(),INTERVAL 14 DAY))");
			myStmt.setString(1, ISBN10);
			myStmt.setInt(2, card_Id);

			myStmt.executeUpdate();

		} finally {
			close(myStmt);
		}

	}

	// Add Loan 3 CHECK IN
	public void addLoan3(Date date_In, int loan_Id) throws Exception {
		PreparedStatement myStmt = null;

		try {
			myStmt = myConn.prepareStatement("UPDATE BookLoans SET Date_In =? WHERE Loan_Id = ?");
			myStmt.setDate(1, date_In);
			myStmt.setInt(2, loan_Id);
			// myStmt.setInt(3, loan_Id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	// FOR BOOK LOAN LIST ALL
	// Get All Loans
	public List<Fines> getAllBookFine() throws Exception {
		List<Fines> list = new ArrayList<>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM Fines");

			while (myRs.next()) {
				Fines fines = convertRowToFines(myRs);
				list.add(fines);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	/*
	 * // FOR BOOK LOAN RETURN ALL LOANS public List<Fines> getAllBook_Fine()
	 * throws Exception { List<Fines> list = new ArrayList<>();
	 * 
	 * Statement myStmt = null; ResultSet myRs = null;
	 * 
	 * try { myStmt = myConn.createStatement(); myRs = myStmt.executeQuery(
	 * "SELECT * FROM Fines WHERE Amount IS NOT NULL");
	 * 
	 * while (myRs.next()) { Fines fines = convertRowToFines(myRs);
	 * list.add(fines); } return list; } finally { close(myStmt, myRs); } }
	 */

	// FOR BOOK LOAN PAY LOAN
	public void payfine(int loan_Id) throws Exception {
		PreparedStatement myStmt = null;

		try {
			myStmt = myConn.prepareStatement("UPDATE Fines SET Paid = TRUE WHERE Loan_Id = ?");
			myStmt.setInt(1, loan_Id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	// For FINES GET ALL FINES AND AMOUNT
	//@SuppressWarnings("resource")
	public void refreshList() throws Exception {
		PreparedStatement myStmt = null;
		PreparedStatement myStmt1 = null;
		PreparedStatement myStmt2 = null;
		PreparedStatement myStmt3 = null;
		try {
	        //String s1 = "INSERT IGNORE INTO Fines (Loan_id) SELECT Loan_Id from BookLoans WHERE Due_Date < CURDATE()";
	        	
	        String s1 = "INSERT IGNORE INTO Fines (Loan_id) SELECT Loan_Id from BookLoans";

	        String s2 = "UPDATE Fines, BookLoans SET Fines.Amount =DATEDIFF(CURDATE(), BookLoans.Due_Date)*0.25 where BookLoans.loan_Id=FINES.Loan_Id AND Paid = 0 AND Date_In IS NULL";

			String s3 = "UPDATE Fines, BookLoans SET FINES.AMOUNT=Datediff(BookLoans.Date_In,BookLoans.Due_Date)*0.25 WHERE BookLoans.Loan_Id=FINES.Loan_Id AND FINES.Paid=0 AND Date_In IS NOT NULL;";

			String s4 = "UPDATE Fines SET Amount=0.00 WHERE Amount<0.00";
			     myStmt = myConn.prepareStatement(s1);
			     myStmt.executeUpdate(s1);
			     myStmt1 = myConn.prepareStatement(s2);
			     myStmt1.executeUpdate(s2);
			     myStmt2 = myConn.prepareStatement(s3);
			     myStmt2.executeUpdate(s3);
			     myStmt3 = myConn.prepareStatement(s4);
			     myStmt3.executeUpdate(s4);     
			     //JOptionPane.showMessageDialog(null, "Fines Updated");
		} finally {
			close(myStmt);
			close(myStmt1);
			close(myStmt2);
			close(myStmt3);
		}
	}
	
	// FOR BOOK LOAN COPY LOAN DATA
	public void copyLoanData() throws Exception {
		PreparedStatement myStmt = null;

		try {
			myStmt = myConn.prepareStatement("INSERT INTO Fines (Loan_Id) "
					+ "SELECT Loan_Id FROM Book_Loans;");
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	/////////////////////////
	// For Book_Loan
	private BookLoan convertRowToBook_Loan(ResultSet myRs) throws SQLException {
		int loan_Id = myRs.getInt("loan_Id");
		String ISBN10 = myRs.getString("ISBN10");
		int card_Id = myRs.getInt("card_Id");
		Date date_Out = myRs.getDate("date_Out");
		Date due_Date = myRs.getDate("due_Date");
		Date date_In = myRs.getDate("date_In");

		BookLoan book_Loan = new BookLoan(loan_Id, ISBN10, card_Id, date_Out, due_Date, date_In);
		return book_Loan;

	}

	// For Fines
	private Fines convertRowToFines(ResultSet myRs) throws SQLException {
		int loan_Id = myRs.getInt("loan_Id");
		float amount = myRs.getFloat("amount");
		boolean paid = myRs.getBoolean("paid");
		Fines fines = new Fines(loan_Id, amount, paid);
		return fines;
	}

	/////////
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
