package bankaccountsystem;

/**
 * Utility class to demonstrate bank account operations.
 * Contains main method to run sample operations.
 */
public final class Main {

    // Private constructor to prevent instantiation
    private Main() {
        // Utility class
    }

    // Constants for deposit/withdraw amounts
    /** Deposit amount for demonstration: Php 1000. */
    private static final double DEPOSIT_1000 = 1000.0;

    /** Deposit amount for demonstration when account is frozen: Php 11500. */
    private static final double DEPOSIT_11500 = 11500.0;

    /** Withdrawal amount for demonstration: Php 500. */
    private static final double WITHDRAW_500 = 500.0;

    /** For demonstration after unfreezing account: Php 100. */
    private static final double WITHDRAW_100 = 100.0;


    /**
     * Main method to demonstrate SavingsAccount operations.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        final SavingsAccount account = new SavingsAccount("Alice");

        System.out.println("Owner Name: " + account.getOwnerName());
        try {
            account.deposit(DEPOSIT_1000);
            System.out.println("Deposited: Php " + DEPOSIT_1000 + ".");
            account.withdraw(WITHDRAW_500);
            System.out.println("Withdrawn: Php " + WITHDRAW_500 + ".");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        account.freezeAccount();
        System.out.println("Account has been frozen.");
        try {
            account.deposit(DEPOSIT_11500);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        account.unfreezeAccount();
        System.out.println("Account has been unfrozen.");
        try {
            account.withdraw(WITHDRAW_100);
            System.out.println("Withdrawn: Php " + WITHDRAW_100 + ".");
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Balance: Php " + account.getBalance() + ".");
    }
}
