package bankaccountsystem;

/**
 * Abstract implementation of BankAccount.
 * Contains shared logic for balance tracking and frozen state.
 */
public abstract class AbstractBankAccount implements BankAccount {

    /** Current balance of the account. */
    private double balance;

    /** Indicates whether the account is frozen. */
    private boolean isFrozen;

    /**
     * Creates a new AbstractBankAccount with initial balance 0 and not frozen.
     */
    public AbstractBankAccount() {
        balance = 0.0;
        isFrozen = false;
    }

    @Override
    public final void deposit(final double amount) {
        if (isFrozen) {
            throw new IllegalStateException(
                    "Account is frozen. Cannot deposit.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "The deposit amount must be positive.");
        }
        balance += amount;
    }

    @Override
    public final void withdraw(final double amount) {
        if (isFrozen) {
            throw new IllegalStateException(
                    "Account is frozen. Cannot withdraw.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "The withdrawn amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
    }

    @Override
    public final double getBalance() {
        return balance;
    }

    @Override
    public final boolean isFrozen() {
        return isFrozen;
    }

    /**
     * Freezes the account to prevent deposits and withdrawals.
     */
    public void freezeAccount() {
        isFrozen = true;
    }

    /**
     * Unfreezes the account to allow deposits and withdrawals.
     */
    public void unfreezeAccount() {
        isFrozen = false;
    }
}
