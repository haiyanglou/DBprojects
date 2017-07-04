package data;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class FinesTable extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
	public static final int LOANID_COL = 0;
	private static final int AMOUNT_COL = 1;
	private static final int PAID_COL = 2;

	private String[] columnNames = { "LOAN_ID", "FINE AMOUNT", "PAID" };
	private List<Fines> fines;

	public FinesTable(List<Fines> thefines) {
		this.fines = thefines;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return fines.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		Fines thefines = fines.get(row);

		switch (col) {
		case LOANID_COL:
			return thefines.getLoanId();
		case AMOUNT_COL:
			return thefines.getAmount();
		case PAID_COL:
			return thefines.isPaid();

		case OBJECT_COL:
			return thefines;

		default:
			return thefines.getLoanId();
		}

	}

}
