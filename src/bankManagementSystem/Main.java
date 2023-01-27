package bankManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	Scanner input = new Scanner(System.in);
	boolean exit = false;
	Bank bank = new Bank();

	public static void main(String[] args) {

		Main menu = new Main();
		menu.runMenu();

	}

	public void runMenu() {
		printHeader();
		while (!exit) {
			printMenu();
			int choice = getInput();
			performAction(choice);
		}
	}

	private void performAction(int choice) {
		switch (choice) {
		case 1:
			createAcount();
			break;
		case 2:
			makeDeposite();
			break;
		case 3:
			makeWithdraw();
			break;
		case 4:
			ListAcountBalances();
			break;
		case 5:
			System.out.println("Thank you for using our Application");
			System.exit(0);
			break;
		default:
			System.out.println("Unkown error has occured");
			break;
		}

	}

	private void createAcount() {
		String Fname, Lname, Ssn, accountType = "";
		double initialDeposite = 0.0;
		boolean valid = false;
		while (!valid) {
			System.out.println("Please enter your account type Checking/Savings");
			accountType = input.nextLine();
			if (accountType.contentEquals("Checking") || accountType.contentEquals("Savings")) {
				valid = true;
			} else {
				System.out.println("Invalid account type selected, Please enter Checking or Savings.");
			}
		}
		System.out.println("Please enter your first name: ");
		Fname = input.nextLine();
		System.out.println("Please enter your last name: ");
		Lname = input.nextLine();
		System.out.println("Please enter your social security number: ");
		Ssn = input.nextLine();
		valid = false;
		while (!valid) {
			System.out.println("Please enter an initial deposite : ");
			try {
				initialDeposite = input.nextDouble();
			} catch (NumberFormatException e) {
				System.out.println("Deposite must be a number");
			}
			if (accountType.contentEquals("Checking")) {
				if (initialDeposite < 100) {
					System.out.println("Checking account require a minimum $100 to open");
				} else {
					valid = true;
				}
			} else if (accountType.contentEquals("Savings")) {
				if (initialDeposite < 50) {
					System.out.println("Savings account require a minimum $50 to open");
				} else {
					valid = true;
				}
			}
		}

		// now we can create account
		Account newAccount=null;
		if (accountType.contentEquals("Checking")) {
			newAccount = new Checking(initialDeposite);
		} else if (accountType.contentEquals("Savings")) {
			newAccount = new Savings(initialDeposite);
		}
		Customer customer = new Customer(Fname, Lname, Ssn, newAccount);
		bank.addCustomer(customer);
	}

	private void makeDeposite() {

		int account = selectAccount();
		if (account >= 0) {
			System.out.println("How much would you like to deposite?: ");
			double amount = 0;
			try {
				amount = Double.parseDouble(input.nextLine());
			} catch (NumberFormatException e) {
				amount = 0;
			}

			bank.getCustomer(account).getAccount().deposite(amount);
		}
	}

	private int selectAccount() {
		ArrayList<Customer> customers = bank.getCustomers();
		if (customers.size() <= 0) {
			System.out.println("No customers in your account.");
			return -1;
		}
		System.out.println("Select an Account");
		for (int i = 0; i < customers.size(); i++) {
			System.out.println((i + 1) + ") " + customers.get(i).basicInfo());
		}
		int account = 0;
		System.out.println("Please enter your selection: ");
		try {
			account = Integer.parseInt(input.nextLine()) - 1;
		} catch (NumberFormatException e) {
			account = -1;
		}
		if (account < 0 || account > customers.size()) {
			account = -1;
			System.out.println("Invalid account selection");
		}
		return account;
	}

	private void makeWithdraw() {
		int account = selectAccount();
		if (account >= 0) {
			System.out.println("How much would you like to Withdraw?: ");
			double amount = 0;
			try {
				amount = Double.parseDouble(input.nextLine());
			} catch (NumberFormatException e) {
				amount = 0;
			}

			bank.getCustomer(account).getAccount().withdraw(amount);
		}

	}

	private void ListAcountBalances() {
		int account = selectAccount();
		if (account >= 0) {
			System.out.println(bank.getCustomer(account).getAccount());
		}

	}

	private int getInput() {
		 int choice = 0;
	        do{
	            boolean valid = false;
	            while (!valid){
	                System.out.print("Enter a valid choice ["+1+" : " + 5 + "]: ");
	                try{
	                    choice =Integer.parseInt(input.nextLine());

	                    valid = true;
	                }
	                catch(Exception e){
	                    input.nextLine();
	                    System.out.println("\nInvalid input. Please try again.\n");
	                }
	            }

	            if (choice < 1 || choice > 5)
	                System.out.println("Invalid choice. Please try again\n");
	            else
	                break;
	        }while(true);

	        return choice;
	}

	private void printMenu() {
		System.out.println("Please make a selection :");
		System.out.println("1) Create a new account");
		System.out.println("2) Make a deposite");
		System.out.println("3) Make a withdraw");
		System.out.println("4) List acount balance");
		System.out.println("5) Exit");
	}

	private void printHeader() {
		System.out.println();
		System.out.println("\t Welcom to Bank System \t");
		System.out.println();
	}
}
