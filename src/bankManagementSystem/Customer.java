package bankManagementSystem;

public class Customer {
	private final String firstName;
	private final String lastName;
	private final String ssn;
	private final Account account;

	Customer(String firstName, String lastName, String ssn, Account account) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.account = account;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public String toString() {
		return "Customer Information\n" + "First Name: " + firstName + "\n" + "Last Name: " + lastName + "\n" + "Ssn: "
				+ ssn + "\n" + account;
	}

	public String basicInfo() {
		return "First Name: " + firstName + " Last Name: " + lastName + " Ssn: " + ssn + " Account Number: "
				+ account.getAccountNumber();
	}

	Account getAccount() {

		return account;
	}
}
