package data;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BookLoanTable extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
	public static final int LOANID_COL = 0;
	private static final int ISBN10_COL = 1;
	private static final int CARDID_COL = 2;
	private static final int DATEOUT_COL = 3;
	private static final int DUEDATE_COL = 4;
	private static final int DATEIN_COL = 5;

	private String[] columnNames = { "LOAN_ID", "ISBN10", "CARD_ID", "DATE_OUT", "DUE_DATE", "DATE_IN" };
	private List<BookLoan> book_Loan;

	public BookLoanTable(List<BookLoan> thebook_Loan) {
		this.book_Loan = thebook_Loan;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return book_Loan.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		BookLoan thebook_Loan = book_Loan.get(row);

		switch (col) {
		case LOANID_COL:
			return thebook_Loan.getLoanId();
		case ISBN10_COL:
			return thebook_Loan.getISBN10();
		case CARDID_COL:
			return thebook_Loan.getCardId();
		case DATEOUT_COL:
			return thebook_Loan.getDateOut();
		case DUEDATE_COL:
			return thebook_Loan.getDueDate();
		case DATEIN_COL:
			return thebook_Loan.getDateIn();
		case OBJECT_COL:
			return thebook_Loan;

		default:
			return thebook_Loan.getLoanId();
		}

	}

}
