package ca.cmpt213.asn4.bank;

public abstract class BankAccount {

    protected double balance;
    protected int numDeposits;
    protected int numWithdraws;
    protected double annInterestRate;
    protected double monthServCharge;

    public void deposit(double depositAmount) {

        assert depositAmount >= 0;

        balance += depositAmount;
        numDeposits++;

    }

    public void withdraw(double withdrawAmount) {

        if (balance - withdrawAmount < 0) {
            throw new IllegalArgumentException();
        }
        balance -= withdrawAmount;
        numWithdraws++;

    }

    public void calcInterest() {

        balance += (balance * (annInterestRate / 12));

    }

    public void monthlyProcess() {

        balance -= monthServCharge;
        calcInterest();
        numDeposits = 0;
        numWithdraws = 0;
        monthServCharge = 0;

    }

    // Constructor
    public BankAccount(double balance, double annInterestRate) {

        assert (balance >= 0 && annInterestRate >= 0);

        this.balance = balance;
        this.annInterestRate = annInterestRate;
        this.numDeposits = 0;
        this.numWithdraws = 0;
        this.monthServCharge = 0;

    }

    // Getters & Setters
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getNumDeposits() {
        return numDeposits;
    }

    public void setNumDeposits(int numDeposits) {
        this.numDeposits = numDeposits;
    }

    public int getNumWithdraws() {
        return numWithdraws;
    }

    public void setNumWithdraws(int numWithdraws) {
        this.numWithdraws = numWithdraws;
    }

    public double getAnnInterestRate() {
        return annInterestRate;
    }

    public void setAnnInterestRate(double annInterestRate) {
        this.annInterestRate = annInterestRate;
    }

    public double getMonthServCharge() {
        return monthServCharge;
    }

    public void setMonthServCharge(double monthServCharge) {
        this.monthServCharge = monthServCharge;
    }

}
