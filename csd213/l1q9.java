import java.util.Scanner;

class BankAccount {
    // Data members
    private String name;
    private long accountNumber;
    private String accountType;
    private double balance;

    // Method to assign initial values
    public void bankInit() {
        @SuppressWarnings("resource")
        Scanner scn1 = new Scanner(System.in);
        System.out.print("Enter name: ");
        this.name = scn1.nextLine();
        System.out.print("Enter type: ");
        this.accountType = scn1.nextLine();
        System.out.print("Enter account number: ");
        this.accountNumber = scn1.nextLong();
        System.out.print("Enter balance: ");
        this.balance = scn1.nextDouble();
        scn1.nextLine(); // Consume the remaining newline character
    }

    // Method to deposit an amount
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.printf("Deposited: $%.2f%n", amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw an amount
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > this.balance) {
            System.out.println("Insufficient funds.");
        } else {
            this.balance -= amount;
            System.out.printf("Withdrew: $%.2f%n", amount);
        }
    }

    // Method to display account details
    public void display() {
        System.out.println("A/C holder name: " + this.name);
        System.out.println("A/C number: " + this.accountNumber);
        System.out.println("A/C type: " + this.accountType);
        System.out.println("A/C balance: ₹" + this.balance);
    }
}

public class l1q9 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        BankAccount account = new BankAccount();

        while (true) {
            // Display menu options
            System.out.println("\nEnter operation choice:");
            System.out.println("1: Assign initial values");
            System.out.println("2: Deposit");
            System.out.println("3: Withdraw");
            System.out.println("4: Display Account Data");
            System.out.println("5: Exit");

            int inputNum = scn.nextInt();
            scn.nextLine(); // Consume the remaining newline character

            switch (inputNum) {
                case 1:
                    account.bankInit();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scn.nextDouble();
                    scn.nextLine(); // Consume the remaining newline character
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scn.nextDouble();
                    scn.nextLine(); // Consume the remaining newline character
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    account.display();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scn.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        }
    }
}
