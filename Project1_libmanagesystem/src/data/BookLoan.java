package data;

import java.sql.Date;

public class BookLoan {
	private int loan_Id;
	private String ISBN10;
	private int card_Id;
	private Date date_Out;
	private Date due_Date;
	private Date date_In;

	public BookLoan(int loan_Id, String ISBN10, int card_Id, Date date_Out, Date due_Date, Date date_In) {
		this.loan_Id = loan_Id;
		this.ISBN10 = ISBN10;
		this.card_Id = card_Id;
		this.date_Out = date_Out;
		this.due_Date = due_Date;
		this.date_In = date_In;
	}

	public int getLoanId() {
		return loan_Id;
	}

	public void setLoanId(int loan_Id) {
		this.loan_Id = loan_Id;
	}

	public String getISBN10() {
		return ISBN10;
	}

	public void setISBN10(String ISBN10) {
		this.ISBN10 = ISBN10;
	}

	public int getCardId() {
		return card_Id;
	}

	public void setCardId(int card_Id) {
		this.card_Id = card_Id;
	}

	public Date getDateOut() {
		return date_Out;
	}

	public void setDateOut(Date date_Out) {
		this.date_Out = date_Out;
	}

	public Date getDueDate() {
		return due_Date;
	}

	public void setDueDate(Date due_Date) {
		this.due_Date = due_Date;
	}

	public Date getDateIn() {
		return date_In;
	}

	public void setDateIn(Date date_In) {
		this.date_In = date_In;
	}

}
