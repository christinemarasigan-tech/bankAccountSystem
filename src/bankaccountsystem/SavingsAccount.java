package bankaccountsystem;

/**
 * Represents a savings account owned by a person.
 * Extends AbstractBankAccount to inherit basic functionality.
 */
public class SavingsAccount extends AbstractBankAccount {

    /** Name of the account owner. */
    private final String ownerName;

    /**
     * Creates a new SavingsAccount for the specified owner.
     *
     * @param owner the owner's name
     */
    public SavingsAccount(final String owner) {
        super();
        this.ownerName = owner; // no shadowing
    }


    /**
     * Returns the owner name of this savings account.
     *
     * @return the owner's name
     */
    public String getOwnerName() {
        return ownerName;
    }
}
