package data;

public class Fines {
	private int loan_Id;
	private float amount;
	private boolean paid;
	
	public Fines(int loan_Id,float amount, boolean paid){
	this.loan_Id = loan_Id;
	this.amount = amount;
	this.paid = paid;
	}
	
	public int getLoanId() {
		return loan_Id;
	}

	public void setLoanId(int loan_Id) {
		this.loan_Id = loan_Id;
	}
	
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
}
