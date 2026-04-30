
import java.util.*;

public class createAcc {

    // Stores all created bank accounts in memory.
    public static LinkedList<bankAccount> list = new LinkedList<>();

    public static void creat() {

        // Variables to hold user input while creating an account.
        String name;
        String bankName = "";
        int depo;
        String phNo = "";
        int pin;

        // Reads user input from the console.
        Scanner sc = new Scanner(System.in);

        // Used to generate a random account number.
        Random rn = new Random();
        int accNo = 10_000 + rn.nextInt(90_000);

        // If the generated account number already exists, generate again.
        if (checkValid(accNo)) {
            accNo = 10_000 + rn.nextInt(90_000);
        }

        // Collect user's full name.
        System.out.println("Enter your name :-");
        name = sc.nextLine();

        // Keep asking until the user selects a valid bank option.
        boolean validBankChosen = false;
        while (!validBankChosen) {
            System.out.println("Choose the bank in which you want to open an account :-");
            System.out.println("1. SBI");
            System.out.println("2. Kotak");
            System.out.println("3. IndusInd");
            System.out.println("4. HDFC");
            System.out.print("Enter the number corresponding to your choice :- ");
            String bankChoice = sc.nextLine().trim();
            switch (bankChoice) {
                case "1":
                    bankName = "SBI";
                    validBankChosen = true;
                    break;
                case "2":
                    bankName = "Kotak";
                    validBankChosen = true;
                    break;
                case "3":
                    bankName = "IndusInd";
                    validBankChosen = true;
                    break;
                case "4":
                    bankName = "HDFC";
                    validBankChosen = true;
                    break;
                default:
                    System.out.println("Kindly choose a approprite bank.");
            }
        }

        // Validate phone number length (must be exactly 10 digits as a string).
        boolean validPhNo = false;
        while (!validPhNo) {
            System.out.println("Enter your phone number using which you want to open your account :-");
            phNo = sc.nextLine().trim();
            int len = phNo.length();
            if (len > 10 || len < 10) {
                System.out.println("You have entered invalid phone number .");
            } else {
                validPhNo = true;
            }
        }

        // Ensure the phone number is unique across the stored accounts.
        boolean as = checkValid(phNo);

        if (as == false) {
            System.err.println("A bank account with this phone number already exits ");
            return;
        }

        // Collect deposit amount and pin, both stored as integers.
        System.out.println("Enter the ammount you want to deposit in you bank account :-");
        depo = Integer.parseInt(sc.nextLine().trim());

        System.out.println("Enter the 4 digit pin for your bank account :-");
        pin = Integer.parseInt(sc.nextLine().trim());

        // Ensure pin is exactly 4 digits, prompting until valid input is entered.
        while (true) {
            if (String.valueOf(pin).length() == 4) {
                break;
            } else {
                System.out.println("Invalid pin. Please enter a 4-digit pin for your bank account :-");
                String pinInput = sc.nextLine().trim();
                try {
                    pin = Integer.parseInt(pinInput);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter only digits.");
                    continue;
                }
            }
        }

        // Account successfully created message.
        System.out.println("Your bank account has been successfully created , you account number is " + "---" + accNo + "---");

        // Store the newly created account in the in-memory list.
        list.add(new bankAccount(name, bankName, phNo, accNo, depo, pin));

    }

    // Checks if a phone number is not already used by an existing account.
    public static boolean checkValid(String phNo) {
        for (bankAccount ele : list) {
            if (ele.getPhoneNumber().equals(phNo)) {
                return false;
            }
        }
        return true;
    }

    // Validates login using account number + pin. (Currently prints messages only.)
    public static boolean checkAcc(int acc, int pin) {

        for (bankAccount ele : list) {
            if (ele.getAccNo() == acc) {
                if (ele.getPin() == pin) {
                    System.out.println("You have entered a correct passsword, you have successfully logged into you account.");
                    return true;
                }
                System.out.println("You have entered a wrong passsword .");
                return false;
            }
        }
        return true;
    }

    // Checks if a generated account number already exists in the list.
    public static boolean checkValid(int accNo) {
        for (bankAccount ele : list) {
            if (ele.getAccNo() == accNo) {
                return true;
            }
        }
        return false;
    }

    // Adds money to the deposit (balance) of the account with matching account number.
    public static boolean deposit(int acc, int money) {
        for (bankAccount ele : list) {
            if (ele.getAccNo() == acc) {
                // setBanlance adds (not sets) to the current balance.
                ele.setBanlance(money);
                System.out.println("Your amount " + money + " has been succefully deposited in your bank account .");
                return true;
            }
        }
        System.out.println("Account not found. Deposit failed.");
        return false;
    }

    // Withdraws money by subtracting from the deposit (balance) if enough funds exist.
    public static boolean withdraw(int acc, int money) {
        for (bankAccount ele : list) {
            if (ele.getAccNo() == acc) {
                if (ele.getBalance() >= money) {
                    // Use a negative value to reduce balance.
                    ele.setBanlance(-money);
                    System.out.println("Your amount " + money + " has been successfully withdrawn from your bank account.");
                    return true;
                } else {
                    System.out.println("Insufficient balance. Withdrawal failed.");
                    return false;
                }
            }
        }
        System.out.println("Account not found. Withdrawal failed.");
        return false;
    }

    // Displays the current balance for a given account number.
    public static void checkBal(int accNo){
         for (bankAccount ele : list) {
            if(ele.getAccNo() == accNo){
                System.out.println("Your current balance is "+ ele.getBalance());
            }
         }
    }
}
