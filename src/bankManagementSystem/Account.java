package bankManagementSystem;

public class Account {

	private int accountNumber;
	private double balance = 0.0;
	private double interest = 0.02;
	private static int numberOfAccounts = 100000;

	Account() {
		accountNumber = numberOfAccounts++;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterest() {
		return interest * 100;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public void withdraw(double amount) {
		if (amount + 5 > balance) {
			System.out.println("You have unsufficient funds.");
			return;
		}
		balance -= amount;
		checkInterest(0);
		System.out.println("You have withdrawn $" + amount + " dollars and incurred a fee of $5 .");
		System.out.println("You now have a balance " + balance);
	}

	public void deposite(double amount) {

		if (amount <= 0) {
			System.out.println("You cannot deposite negative money.");
			return;
		}
		checkInterest(0);
		amount = amount + amount * interest;
		balance -= amount;
		System.out.println(
				"You have deposited $" + amount + " dollars with an interest rate of " + (interest * 100) + "%");
		System.out.println("You now have a balance " + balance);
	}

	public void checkInterest(double amount) {
		if (balance+amount > 1000) {
			interest = 0.05;
		} else {
			interest = 0.02;
		}
	}

}
