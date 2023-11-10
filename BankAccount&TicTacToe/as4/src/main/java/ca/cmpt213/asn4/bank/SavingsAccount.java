package ca.cmpt213.asn4.bank;

public class SavingsAccount extends BankAccount {

    private enum Status {
        ACTIVE, INACTIVE
    };

    private Status status;

    public void deposit(double depositAmount) {

        if (status == Status.INACTIVE && balance + depositAmount >= 25)
            status = Status.ACTIVE;

        super.deposit(depositAmount);

    }

    public void withdraw(double withdrawAmount) {

        if (status == Status.ACTIVE)
            super.withdraw(withdrawAmount);
        else
            System.out.println(
                    "Withdrawal is not allowed. For withdrawals your balance should be at least $25. Your Balace: "
                            + super.getBalance());

    }

    public void monthlyProcess() {

        if (numWithdraws > 4)
            monthServCharge = numWithdraws - 4;

        super.monthlyProcess();

        if (balance < 25)
            status = Status.INACTIVE;

    }

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

}
