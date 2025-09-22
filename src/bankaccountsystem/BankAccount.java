package bankaccountsystem;

/**
 * Represents a generic bank account.
 * For deposit, withdrawal, and checking balance and frozen status.
 */
public interface BankAccount {

    /**
     * Deposits a specified amount into the account.
     *
     * @param amount the amount to deposit
     */
    void deposit(double amount);

    /**
     * Withdraws a specified amount from the account.
     *
     * @param amount the amount to withdraw
     */
    void withdraw(double amount);

    /**
     * Returns the current balance of the account.
     *
     * @return the balance
     */
    double getBalance();

    /**
     * Checks whether the account is frozen.
     *
     * @return true if the account is frozen, false otherwise
     */
    boolean isFrozen();
}
