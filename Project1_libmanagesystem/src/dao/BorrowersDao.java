package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import data.Borrowers;

public class BorrowersDao {
	private Connection myConn;
	private static String url = "jdbc:mysql://127.0.0.1:3306/Project_1";
	private static String user = "root";
	private static String password = "";

	public BorrowersDao() throws Exception {
		myConn = DriverManager.getConnection(url, user, password);
	}

	// Get All Borrowers
	public List<Borrowers> getAllBorrowers() throws Exception {
		List<Borrowers> list = new ArrayList<>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM Borrowers");

			while (myRs.next()) {
				Borrowers borrowers = convertRowToBorrowers(myRs);
				list.add(borrowers);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	// Get Borrowers with specified Tuple
	/*
	 * public List<Borrowers> getBorrowersInfo() throws Exception {
	 * List<Borrowers> list = new ArrayList<>();
	 * 
	 * Statement myStmt = null; ResultSet myRs = null;
	 * 
	 * try { myStmt = myConn.createStatement(); myRs = myStmt.executeQuery(
	 * "SELECT borrower_id, ssn, last_name, address, phone FROM Borrowers");
	 * 
	 * while (myRs.next()) { Borrowers borrowers = convertRowToBorrowers(myRs);
	 * list.add(borrowers); } return list; } finally { close(myStmt, myRs); } }
	 */

	// Add Borrower
	public void addBorrower(Borrowers borrower) throws Exception {
		PreparedStatement myStmt = null;

		try {
			myStmt = myConn.prepareStatement(
					"INSERT INTO Borrowers (borrower_id, ssn, first_name, last_name, email, address, city, state, phone) VALUES (?,?,?,?,?,?,?,?,?)");
			myStmt.setLong(1, borrower.getId());
			myStmt.setString(2, borrower.getSsn());
			myStmt.setString(3, borrower.getFirstName());
			myStmt.setString(4, borrower.getLastName());
			myStmt.setString(5, borrower.getEmail());
			myStmt.setString(6, borrower.getAddress());
			myStmt.setString(7, borrower.getCity());
			myStmt.setString(8, borrower.getState());
			myStmt.setString(9, borrower.getPhone());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}

	}

	// Delete Borrower
	public void deleteBorrower(long borrower_id) throws Exception {
		PreparedStatement myStmt = null;

		try {
			myStmt = myConn.prepareStatement("DELETE FROM Borrowers WHERE borrower_id=?");
			myStmt.setLong(1, borrower_id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}

	}

	// Update Borrower
	public void updateBorrower(Borrowers borrower) throws Exception {
		PreparedStatement myStmt = null;

		try {
			myStmt = myConn.prepareStatement(
					"UPDATE Borrowers SET ssn =?, first_name=? , last_name =?, email=?, address=?, city=?, state =?, phone =? WHERE borrower_Id =?");
			myStmt.setString(1, borrower.getSsn());
			myStmt.setString(2, borrower.getFirstName());
			myStmt.setString(3, borrower.getLastName());
			myStmt.setString(4, borrower.getEmail());
			myStmt.setString(5, borrower.getAddress());
			myStmt.setString(6, borrower.getCity());
			myStmt.setString(7, borrower.getState());
			myStmt.setString(8, borrower.getPhone());
			myStmt.setLong(9, borrower.getId());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	//////////
	private Borrowers convertRowToBorrowers(ResultSet myRs) throws SQLException {
		long borrower_id = myRs.getLong("borrower_id");
		String ssn = myRs.getString("ssn");
		String first_name = myRs.getString("first_name");
		String last_name = myRs.getString("last_name");
		String email = myRs.getString("email");
		String address = myRs.getString("address");
		String city = myRs.getString("city");
		String state = myRs.getString("state");
		String phone = myRs.getString("phone");

		Borrowers borrower = new Borrowers(borrower_id, ssn, first_name, last_name, email, address, city, state, phone);
		return borrower;

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
