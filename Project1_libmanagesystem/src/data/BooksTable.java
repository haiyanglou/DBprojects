package data;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BooksTable extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
	public static final int ISBN10_COL = 0;
	private static final int TITLE_COL = 1;
	private static final int AUTHOR_COL = 2;
	private static final int PUBLISHER_COL = 3;
	private static final int AVAILABILITY_COL = 4;

	private String[] columnNames = { "ISBN10", "TITLE", "AUTHOR", "PUBLISHER","AVAILABILITY" };
	private List<Books> books;

	public BooksTable(List<Books> thebooks) {
		this.books = thebooks;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return books.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		Books thebooks = books.get(row);

		switch (col) {
		case ISBN10_COL:
			return thebooks.getISBN10();
		case TITLE_COL:
			return thebooks.getBookTitle();
		case AUTHOR_COL:
			return thebooks.getAuthor();
		case PUBLISHER_COL:
			return thebooks.getPublisher();
		case AVAILABILITY_COL:
			return ("1");
			//return (thebooks.getavailability() ! thefines.isAvailable().equals(true));
		case OBJECT_COL:
			return thebooks;

		default:
			return thebooks.getISBN10();
		}

	}

}
