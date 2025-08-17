import java.util.Scanner;


class BankAccount {
    private String name;
    private double balance;

    public BankAccount(String name, double initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

// ATM Machine Class
class ATM {
    private BankAccount account;
    private Scanner input;

    public ATM(BankAccount account) {
        this.account = account;
        this.input = new Scanner(System.in);
    }

    public void runATM() {
        int choice = -1;

        while (choice != 4) {
            System.out.println("\n======= ATM MENU =======");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                input.next(); 
                continue;
            }

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    withdrawMoney();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Exiting ATM... Have a nice day!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1-4.");
            }
        }
    }

    private void withdrawMoney() {
        System.out.print("Enter amount to withdraw: ");
        double amount = input.nextDouble();
        if (account.withdraw(amount)) {
            System.out.println("✅ Withdrawal successful. Remaining Balance: ₹" + account.getBalance());
        } else {
            System.out.println("❌ Withdrawal failed. Either insufficient balance or invalid amount.");
        }
    }

    private void depositMoney() {
        System.out.print("Enter amount to deposit: ");
        double amount = input.nextDouble();
        account.deposit(amount);
        System.out.println("✅ Deposit successful. Current Balance: ₹" + account.getBalance());
    }

    private void checkBalance() {
        System.out.println("👤 Account Holder: " + account.getName());
        System.out.println("💰 Current Balance: ₹" + account.getBalance());
    }
}


public class ATMInterface {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("Ayush Jain", 5000.0);
        ATM atm = new ATM(myAccount);
        atm.runATM();
    }
}