package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;
    public  boolean acctFrozen;
    public String acctId;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance) {
        this.acctFrozen = false;
        this.acctId = "B000";
        if (isEmailValid(email) && isAmountValid(startingBalance)) {
            this.email = email;
            this.balance = startingBalance;
        } else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public String getAcctId(){return acctId;}

    public void setAcctId(String IDin){
        this.acctId = IDin;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * if amount is negative or larger than balance, balance stays the same
     */
    public void withdraw(double amount) {
        if (isAmountValid(amount)) {
            if (!(balance < amount) && amount > 0) {
                balance -= amount;
            }
        }else{
            throw new IllegalArgumentException("withdraw amount: " + amount + " is invalid, amount cannot be withdrawn");
        }
    }

    public void setAcctFrozen(boolean status){
        this.acctFrozen = status;
    }

    public boolean getAcctFrozen(){return acctFrozen;}


    public static boolean isEmailValid(String email) {
        if (email.indexOf('@') == -1) {
            return false;
        }
        if (email.startsWith(".")) {
            return false;
        }
        if (!email.endsWith(".com")) {
            return false;
        }
        if(email.contains("-@")){
            return false;
        }
        if(email.contains("..")){
            return false;
        }
        if(email.contains("--")){
            return false;
        }
        if(email.contains("__")){
            return false;
        }
        if(email.contains("#")){
            return false;
        }
        if(email.contains("##")){
            return false;
        }
        if(!email.substring(email.length()-4,email.length()-1).contains("c")){
            return false;
        }

        else {
            return true;
        }
    }

    /**
     * @param amount
     * @return function returns a boolean telling whether an amount is a valid input
     * valid inputs are positive and 2 decimal points or less
     */
    public static boolean isAmountValid(double amount){
        boolean isValid = true;

        if (amount <= 0){
            isValid = false;
        }
        else {
            String amountString = Double.toString(amount);
            if (amountString.indexOf('.')+2 < amountString.length()-1){
                isValid=false;
            }
        }

        return isValid;
    }

    /**
     * function will add money of specified amount to the balance of the account
     */
    public void deposit (double amount){
        if (isAmountValid(amount)) {
            if (!(balance < amount) && amount > 0) {
                balance += amount;
            }
        }else{
            throw new IllegalArgumentException("deposit amount: " + amount + " is invalid, amount cannot be deposited");
        }
    }

    /**
     * function withdraws valid amount from account and deposits to new account
     * @param amount
     * @param bankAccounta
     */
    public void transfer (double amount, BankAccount bankAccounta){
        if (isAmountValid(amount) && amount < this.balance){
            this.withdraw(amount);
            bankAccounta.balance = bankAccounta.balance + amount;
        }else{
            throw new IllegalArgumentException("transfer amount: " + amount + " is invalid, amount cannot be transfered");
        }
    }
}
