package ca.cmpt213.asn4.bank;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests all the methods from SavingsAccount class, makes sure all the methods work as the should.
 */
public class SavingsAccountTest {

    BankAccount account;

    @Test
    public void constructorTest() {

        System.out.println();

        try {
            account = new SavingsAccount(0.1, 0.1);
            System.out.println("Constructor Test 1 Passed: Arguments are positive");
            assertTrue(true);
        } catch (AssertionError e) {
            assertTrue("Constructor Test 1 Failed: Arguments are positive", false);
        }

        try {
            account = new SavingsAccount(0.1, -0.1);
            assertTrue("Constructor Test 2 Failed: Negative Annual Interest Rate", false);
        } catch (AssertionError e) {
            System.out.println("Constructor Test 2 Passed: Negative Annual Interest Rate");
            assertTrue(true);
        }

        try {
            account = new SavingsAccount(-0.1, 0.1);
            assertTrue("Constructor Test 3 Failed: Negative Balance", false);
        } catch (AssertionError e) {
            System.out.println("Constructor Test 3 Passed: Negative Balance");
            assertTrue(true);
        }

        try {
            account = new SavingsAccount(-0.1, -0.1);
            assertTrue("Constructor Test 4 Failed: Arguments are negative", false);
        } catch (AssertionError e) {
            System.out.println("Constructor Test 4 Passed: Arguments are negative");
            assertTrue(true);
        }

        try {
            account = new SavingsAccount(0.1, 1.1);
            assertTrue("Constructor Test 5 Failed: Annual Interest Rate is out of Bounds", false);
        } catch (AssertionError e) {
            System.out.println("Constructor Test 5 Passed: Annual Interest Rate is out of Bounds");
            assertTrue(true);
        }

        account = new SavingsAccount(24, 1);
        assertFalse("Constructor Test 6 Failed: Account is Inactive", account.isActive());
        System.out.println("Constructor Test 6 Passed: Account is Inactive");

        account = new SavingsAccount(25, 1);
        assertTrue("Constructor Test 7 Failed: Account is Active", account.isActive());
        System.out.println("Constructor Test 7 Passed: Account is Active");

    }

    @Test
    public void depositTest() {

        System.out.println();

        account = new SavingsAccount(0, 0.4);
        try {
            account.deposit(-0.1);
            assertTrue("Deposit Test 1 Failed: Deposit is Negative", false);
        } catch (AssertionError e) {
            System.out.println("Deposit Test 1 Passed: Deposit is Negative");
            assertTrue(true);
        }

        account.deposit(25);
        assertTrue("Deposit Test 2 Failed: Deposit is Successful", 25 == account.getBalance());
        System.out.println("Deposit Test 2 Passed: Deposit is Successful");

        assertTrue("Deposit Test 3 Failed: Account is Active after Deposit", account.isActive());
        System.out.println("Deposit Test 3 Passed: Account is Active after Deposit");

        account = new SavingsAccount(0, 0.4);
        account.deposit(24);
        assertFalse("Deposit Test 4 Failed: Account is Inactive after Deposit", account.isActive());
        System.out.println("Deposit Test 4 Passed: Account is Inactive after Deposit");

    }

    @Test
    public void withdrawTest() {

        System.out.println();

        account = new SavingsAccount(25, 0.4);
        try {
            account.withdraw(-0.1);
            assertTrue("Withdraw Test 1 Failed: Withdraw is Negative", false);
        } catch (AssertionError e) {
            System.out.println("Withdraw Test 1 Passed: Withdraw is Negative");
            assertTrue(true);
        }

        account.withdraw(1);
        assertTrue("Withdraw Test 2 Failed: Withdraw is Successful", 24 == account.getBalance());
        System.out.println("Withdraw Test 2 Passed: Withdraw is Successful");

        assertFalse("Withdraw Test 3 Failed: Account is Inactive after Withdraw", account.isActive());
        System.out.println("Withdraw Test 3 Passed: Account is Inactive after Withdraw");

        account = new SavingsAccount(26, 0.4);
        account.withdraw(1);
        assertTrue("Withdraw Test 4 Failed: Account is Active after Withdraw", account.isActive());
        System.out.println("Withdraw Test 4 Passed: Account is Active after Withdraw");


        try {
            account = new SavingsAccount(25, 0.4);
            account.withdraw(26);
            assertTrue("Withdraw Test 5 Passed: Withdraw is More Than Balance", false);
        } catch (IllegalArgumentException e) {
            System.out.println("Withdraw Test 5 Passed: Withdraw is More Than Balance");
            assertTrue(true);
        }

        account = new SavingsAccount(24, 0.4);
        account.withdraw(1);
        assertTrue("Withdraw Test 6 Failed: Balance is not changed", 24 == account.getBalance());
        System.out.println("Withdraw Test 6 Passed: Withdraw is More Than Balance");

    }

    @Test
    public void monthlyProcessTest() {

        System.out.println();

        account = new SavingsAccount(25, 0.6);

        for (int i = 0; i < 10; i++)
            account.withdraw(0);
        account.monthlyProcess();
        assertTrue("Monthly Process Test 1 Failed: Balance after Monthly Process is Right", account.getBalance() == 19.95);
        System.out.println("Monthly Process Test 1 Passed: Balance after Monthly Process is Right");

        assertFalse("Monthly Process Test 2 Failed: Account is Inactive", account.isActive());
        System.out.println("Monthly Process Test 2 Passed: Account is Inactive");

        account = new SavingsAccount(100, 0.6);

        for (int i = 0; i < 54; i++)
            account.withdraw(0);
        account.monthlyProcess();
        assertTrue("Monthly Process Test 3 Failed: Balance after Monthly Process Amount is Right", account.getBalance() == 52.5);
        System.out.println("Monthly Process Test 3 Passed: Balance after Monthly Process Amount is Right");

        assertTrue("Monthly Process Test 4 Failed: Account is Active", account.isActive());
        System.out.println("Monthly Process Test 4 Passed: Account is Active");

        account.monthlyProcess();
        assertTrue("Monthly Process Test 3 Failed: Balance after Monthly Process is Right", account.getBalance() == 55.125);
        System.out.println("Monthly Process Test 3 Passed: Balance after Monthly Process Amount is Right");

    }

}