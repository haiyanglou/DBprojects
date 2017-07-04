package data;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BorrowersTable extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
	public static final int BORROWERID_COL = 0;
	public static final int SSN_COL = 1;
	private static final int FULLNAME_COL = 2;
	private static final int EMAIL_COL = 3;
	public static final int ADDRESS_COL = 4;
	private static final int PHONE_COL = 5;

	private String[] columnNames = { "BORROWER_ID", "SSN", "FULLNAME", "EMAIL", "ADDRESS", "PHONE" };
	private List<Borrowers> borrowers;

	public BorrowersTable(List<Borrowers> theborrowers) {
		this.borrowers = theborrowers;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return borrowers.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		Borrowers theborrowers = borrowers.get(row);

		switch (col) {
		case BORROWERID_COL:
			return theborrowers.getId();
		case SSN_COL:
			return theborrowers.getSsn();
		case FULLNAME_COL:
			return (theborrowers.getFirstName() + " " + theborrowers.getLastName());
		case EMAIL_COL:
			return theborrowers.getEmail();
		case ADDRESS_COL:
			return (theborrowers.getAddress() + " " + theborrowers.getCity() + ", " + theborrowers.getState());
		case PHONE_COL:
			return theborrowers.getPhone();
		case OBJECT_COL:
			return theborrowers;

		default:
			return theborrowers.getId();
		}

	}
}
