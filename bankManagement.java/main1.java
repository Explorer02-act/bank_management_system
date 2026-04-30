
import java.util.*;

public class main1 {

    public static void main(String[] args) {

        // Stores transaction history messages for the current login session.
        LinkedList<String> hist = new LinkedList();

        // Reads user input from the console.
        Scanner sc = new Scanner(System.in);

        // Outer menu loop: keeps showing options until user chooses exit.
        while (true) {
            System.out.println("Welcome to the bank portal :-");
            System.out.println("Select 1 to creat a bank account :-");
            System.out.println("Select 2 to login into you existing account :-");
            System.out.println("Select 3 to exit :-");
            System.out.print("Enter your choice: ");

            // Read the user's menu choice as a trimmed string.
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":

                    // Create a new account by calling createAcc.creat().
                    createAcc.creat();
                    break;

                case "2":

                    // Helper object to access the shared account list and account operations.
                    createAcc a1 = new createAcc();

                    // If no accounts exist yet, block login.
                    if (a1.list.size() == 0) {
                        System.out.println("No details to access .");
                        break;
                    }
                    // Read login details: account number and pin.
                    System.out.println("Enter the account number of wich you want to login :- ");
                    int accNo = sc.nextInt();

                    System.out.println("Enter the pin for your bank account :-");
                    int pass = sc.nextInt();

                    // Validate credentials (prints success/failure messages).
                    boolean b1 = a1.checkAcc(accNo, pass);

                    // Account menu loop: continues until user selects Logout.
                    if (b1) {
                        boolean bol = true;
                        while (bol) {
                            System.out.println("\n === Account Menu ===");
                            System.out.println(" 1. Deposit \n 2. Withdraw \n 3. Transfer \n 4. Check Balance \n 5. Transaction History \n 6. Logout");
                            System.out.println("Choose any of those options above :-");
                            int ask = sc.nextInt();

                            // Run the selected account operation.
                            switch (ask) {
                                case 1:
                                    // Deposit money into the current logged-in account.
                                    System.out.println("Enter the amount you want to deposit in the bank :-");
                                    int money = sc.nextInt();
                                    boolean depositOk = a1.deposit(accNo, money);
                                    if (depositOk) {
                                        hist.add("Deposited " + money + " into account number ---" + accNo + "---");
                                    }
                                    break;
                                case 2:
                                    // Withdraw money from the current logged-in account.
                                    System.out.println("Enter the amount you want to withdraw from your bank :-");
                                    money = sc.nextInt();
                                    boolean withdrawOk = a1.withdraw(accNo, money);
                                    if (withdrawOk) {
                                        hist.add("Withdrew " + money + " from account number ---" + accNo + "---");
                                    }
                                    break;
                                case 3:
                                    // Transfer money: credit target account and debit current account.
                                    // INSERT_YOUR_CODE
                                    // Prompt the user for the target account number (excluding transferring to same acc).
                                    System.out.println("Enter the bank account to which you want to transfer your money :-"); 
                                    int accNu = sc.nextInt();

                                    // Make sure not transferring to self
                                    if (accNu == accNo) {
                                        System.out.println("Cannot transfer to the same account. Please enter a different account.");
                                        break;
                                    }

                                    // Validate the target account exists
                                    boolean validity = a1.checkValid(accNu);
                                    if (validity == false) {
                                        System.out.println("No such account exits , enter a valid account number");
                                        break;
                                    }

                                    // Prompt user for transfer amount
                                    System.out.println("Enter the amount you want to transfer :-");
                                    money = sc.nextInt();

                                    // Get current balance of source account
                                    int currentBalance = 0;
                                    for (bankAccount ele : a1.list) {
                                        if (ele.getAccNo() == accNo) {
                                            currentBalance = ele.getBalance();
                                            break;
                                        }
                                    }

                                    // Check for sufficient balance before proceeding
                                    if (currentBalance < money) {
                                        System.out.println("Insufficient funds. Transfer failed.");
                                        break;
                                    }

                                    // Withdraw from source, then deposit to destination if withdrawal succeeds.
                                    boolean sourceDebited = a1.withdraw(accNo, money);
                                    if (!sourceDebited) {
                                        System.out.println("Transfer failed while debiting source account.");
                                        break;
                                    }

                                    boolean targetCredited = a1.deposit(accNu, money);
                                    if (!targetCredited) {
                                        // Best-effort rollback if target account unexpectedly fails.
                                        a1.deposit(accNo, money);
                                        System.out.println("Transfer failed while crediting target account. Amount rolled back.");
                                        break;
                                    }

                                    // Record successful transfer
                                    System.out.println("Successfully transferred " + money + " to account with account number" + "---" + accNu + "---");
                                    hist.add("Transferred " + money + " from account number ---" + accNo + "--- to account with account number ---" + accNu + "---");
                                    break;
                                case 4:
                                    // Show the current balance of the current logged-in account.
                                    a1.checkBal(accNo);
                                    break;
                                case 5:
                                    // Show transaction history messages stored during the session.
                                    System.out.println("Transcation history is as follows :-");
                                    System.out.println(hist);
                                    break;
                                case 6:
                                    // Stop the account menu and return to the main menu.
                                    System.out.println("You have successfully logged out.");
                                    bol = false;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    // Consume the newline left behind by the last nextInt() call
                    // so the next outer-loop nextLine() doesn't read "".
                    sc.nextLine();
                    break;
                case "3":
                    // Exit the application entirely.
                    System.out.println("Goodbye.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

            // Add a blank line to separate menu screens.
            System.out.println();
        }
    }
}
