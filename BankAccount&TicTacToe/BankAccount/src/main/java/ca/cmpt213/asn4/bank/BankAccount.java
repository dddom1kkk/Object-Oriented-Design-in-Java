package ca.cmpt213.asn4.bank;

/**
 * BankAccount - abstract class that is being used for different types of accounts like SavingsAccount for example.
 */
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

        assert withdrawAmount >= 0;

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

    public abstract boolean isActive();

    // Constructor
    public BankAccount(double balance, double annInterestRate) {

        assert balance >= 0;
        assert annInterestRate >= 0 && annInterestRate <= 1;

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

        assert balance >= 0;

        this.balance = balance;

    }

    public int getNumDeposits() {
        return numDeposits;
    }

    public void setNumDeposits(int numDeposits) {

        assert numDeposits >= 0;

        this.numDeposits = numDeposits;

    }

    public int getNumWithdraws() {
        return numWithdraws;
    }

    public void setNumWithdraws(int numWithdraws) {

        assert numWithdraws >= 0;

        this.numWithdraws = numWithdraws;

    }

    public double getAnnInterestRate() {
        return annInterestRate;
    }

    public void setAnnInterestRate(double annInterestRate) {

        assert annInterestRate >= 0 && annInterestRate <= 1;

        this.annInterestRate = annInterestRate;

    }

    public double getMonthServCharge() {
        return monthServCharge;
    }

    public void setMonthServCharge(double monthServCharge) {

        assert monthServCharge >= 0;

        this.monthServCharge = monthServCharge;

    }

}
