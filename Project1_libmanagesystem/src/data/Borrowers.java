package data;

public class Borrowers {
	private long borrower_Id;
	private String ssn;
	private String first_name;
	private String last_name;
	private String email;
	private String address;
	private String city;
	private String state;
	private String phone;

	public Borrowers(long borrower_Id, String ssn, String first_name, String last_name, String email, String address,
			String city, String state, String phone) {
		this.borrower_Id = borrower_Id;
		this.ssn = ssn;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phone = phone;
	}

	public long getId() {
		return this.borrower_Id;
	}

	public void setId(long borrower_Id) {
		this.borrower_Id = borrower_Id;
	}

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFirstName() {
		return this.first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return this.last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
