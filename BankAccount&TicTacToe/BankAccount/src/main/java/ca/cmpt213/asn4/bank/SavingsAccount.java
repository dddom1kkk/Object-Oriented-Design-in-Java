package ca.cmpt213.asn4.bank;

/**
 * SavingsAccount - class that extends BankAccount and represents Savings account in the bank. Overrides all the methods from its superclass.
 */
public class SavingsAccount extends BankAccount {

    private enum Status {
        ACTIVE, INACTIVE
    };

    private Status status;

    @Override
    public void deposit(double depositAmount) {

        if (status == Status.INACTIVE && balance + depositAmount >= 25)
            status = Status.ACTIVE;

        super.deposit(depositAmount);

    }

    @Override
    public void withdraw(double withdrawAmount) {

        if (status == Status.ACTIVE) {
            super.withdraw(withdrawAmount);
            if (balance < 25)
                status = Status.INACTIVE;
        }
        else
            System.out.println("Withdrawal is not allowed. For withdrawals your balance should be at least $25. Your Balace: " + super.getBalance());

    }

    @Override
    public void monthlyProcess() {

        if (numWithdraws > 4)
            monthServCharge = numWithdraws - 4;
        else
            monthServCharge = 0;

        super.monthlyProcess();

        if (balance < 25)
            status = Status.INACTIVE;

    }

    @Override
    public boolean isActive() {

        if (status == Status.ACTIVE)
            return true;

        return false;

    }

    // Constructor
    SavingsAccount(double balance, double annInterestRate) {

        super(balance, annInterestRate);

        if (balance < 25)
            status = Status.INACTIVE;
        else
            status = Status.ACTIVE;

    }

    // Getters & Setters
    @Override
    public void setBalance(double balance) {

        super.setBalance(balance);

        if (balance < 25)
            status = Status.INACTIVE;
        else
            status = Status.ACTIVE;

    }

}
