public class bankAccount{

    // Basic account details.
    private String name;
    private String bankName;
    private int accountNumber;

    // Current balance amount (named deposit in code, but used like balance).
    private int deposit;
    private String phoneNumber;
    private int pin;

    // Creates a new bank account record with initial values.
    public bankAccount(String name,String bankName,String phoneNumber,int accountNumber,int deposit,int pin) {

        this.name = name;
        this.bankName = bankName;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        this.deposit = deposit;
        this.pin = pin;
        
    }

    // Returns the phone number associated with this account.
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Returns the account number.
    public int getAccNo(){
        return accountNumber;
    }
    
    // Returns the PIN for login verification.
    public int getPin(){
        return pin;
    }

    // Returns the current balance.
    public int getBalance(){
        return deposit;
    }

    // Updates balance by adding the provided amount (or subtracting if negative).
    public void setBanlance(int money){
        this.deposit += money;
    }
}