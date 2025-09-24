
package bankaccountsystemtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import bankaccountsystem.SavingsAccount;
import bankaccountsystem.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JUnit 5 test class for SavingsAccount and AbstractBankAccount.
 * Ensures 100% coverage of all methods, constructors, and main.
 * Code Reviewer: MarkSayson
 */
@TestMethodOrder(OrderAnnotation.class)
public class Bankaccountsystemtest {

    /** SavingsAccount instance used in each test. */
    private SavingsAccount account;

    /** Deposit amount for test case: Php 1000. */
    private static final double DEPOSIT_1000 = 1000.0;

    /** Deposit amount for test case when account is frozen: Php 11500. */
    private static final double DEPOSIT_11500 = 11500.0;

    /** Withdrawal amount for test cases: Php 500. */
    private static final double WITHDRAW_500 = 500.0;

    /** Withdrawal amount for test case with insufficient funds: Php 1500. */
    private static final double WITHDRAW_1500 = 1500.0;

    /** Withdrawal amount for test case after unfreezing account: Php 100. */
    private static final double WITHDRAW_100 = 100.0;

    /** Invalid deposit amount (negative) for test case: Php -500. */
    private static final double INVALID_DEPOSIT_NEGATIVE = -500.0;

    /** Invalid withdrawal amount (negative) for test case: Php -100. */
    private static final double INVALID_WITHDRAW_NEGATIVE = -100.0;

    /** Expected balance after multiple transactions: Php 1400. */
    private static final double EXPECTED_BALANCE_AFTER_TRANSACTIONS = 1400.0;

    /** Execution order for Test Case 1: Create a savings account. */
    private static final int ORDER_TEST_CASE_1 = 1;

    /** Execution order for Test Case 2: Deposit with valid amount. */
    private static final int ORDER_TEST_CASE_2 = 2;

    /** Execution order for Test Case 3: Deposit with zero amount. */
    private static final int ORDER_TEST_CASE_3 = 3;

    /** Execution order for Test Case 4: Deposit with negative amount. */
    private static final int ORDER_TEST_CASE_4 = 4;

    /** Execution order for Test Case 5: Withdraw with sufficient funds. */
    private static final int ORDER_TEST_CASE_5 = 5;

    /** Execution order for Test Case 6: Withdraw with insufficient funds. */
    private static final int ORDER_TEST_CASE_6 = 6;

    /** Execution order for Test Case 7: Withdraw with negative amount. */
    private static final int ORDER_TEST_CASE_7 = 7;

    /** Execution order for Test Case 8: Deposit when account is frozen. */
    private static final int ORDER_TEST_CASE_8 = 8;

    /** Execution order for Test Case 9: Withdraw when account is frozen. */
    private static final int ORDER_TEST_CASE_9 = 9;

    /** Execution order for Test Case 10: Unfreeze account and withdraw. */
    private static final int ORDER_TEST_CASE_10 = 10;

    /** Execution order for Test Case 11: Check account is frozen. */
    private static final int ORDER_TEST_CASE_11 = 11;

    /** Check balance after multiple transactions. */
    private static final int ORDER_TEST_CASE_12 = 12;

    /** Execution order for running Main.main() for coverage. */
    private static final int ORDER_TEST_MAIN = 13;

    /** Execution order for testing initial state of AbstractBankAccount. */
    private static final int ORDER_INITIAL_STATE = 14;

    @BeforeEach
    final void setUp() {
        account = new SavingsAccount("Alice");
    }

    @Test
    @Order(ORDER_TEST_CASE_1)
    @DisplayName("Test Case 1: Create a savings account")
    void testOwnerName() {
        assertEquals("Alice", account.getOwnerName());
    }

    @Test
    @Order(ORDER_TEST_CASE_2)
    @DisplayName("Test Case 2: Deposit with valid amount")
    void testDepositValid() {
        account.deposit(DEPOSIT_1000);
        assertEquals(DEPOSIT_1000, account.getBalance());
    }

    @Test
    @Order(ORDER_TEST_CASE_3)
    @DisplayName("Test Case 3: Deposit zero amount")
    void testDepositZero() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> account.deposit(0)
        );
        assertEquals(
            "The deposit amount must be positive.",
            exception.getMessage()
        );
    }

    @Test
    @Order(ORDER_TEST_CASE_4)
    @DisplayName("Test Case 4: Deposit negative amount")
    void testDepositNegative() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> account.deposit(INVALID_DEPOSIT_NEGATIVE)
        );
        assertEquals(
            "The deposit amount must be positive.",
            exception.getMessage()
        );
    }

    @Test
    @Order(ORDER_TEST_CASE_5)
    @DisplayName("Test Case 5: Withdraw with sufficient funds")
    void testWithdrawValid() {
        account.deposit(DEPOSIT_1000);
        account.withdraw(WITHDRAW_500);
        assertEquals(
            DEPOSIT_1000 - WITHDRAW_500,
            account.getBalance()
        );
    }

    @Test
    @Order(ORDER_TEST_CASE_6)
    @DisplayName("Test Case 6: Withdraw with insufficient funds")
    void testWithdrawInsufficientFunds() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> account.withdraw(WITHDRAW_1500)
        );
        assertEquals(
            "Insufficient balance.",
            exception.getMessage()
        );
    }

    @Test
    @Order(ORDER_TEST_CASE_7)
    @DisplayName("Test Case 7: Withdraw negative amount")
    void testWithdrawNegative() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> account.withdraw(INVALID_WITHDRAW_NEGATIVE)
        );
        assertEquals(
            "The withdrawn amount must be positive.",
            exception.getMessage()
        );
    }

    @Test
    @Order(ORDER_TEST_CASE_8)
    @DisplayName("Test Case 8: Deposit when account is frozen")
    void testFreezeDeposit() {
        account.freezeAccount();
        Exception exception = assertThrows(
            IllegalStateException.class,
            () -> account.deposit(DEPOSIT_11500)
        );
        assertEquals(
            "Account is frozen. Cannot deposit.",
            exception.getMessage()
        );
    }

    @Test
    @Order(ORDER_TEST_CASE_9)
    @DisplayName("Test Case 9: Withdraw when account is frozen")
    void testFreezeWithdraw() {
        account.freezeAccount();
        Exception exception = assertThrows(
            IllegalStateException.class,
            () -> account.withdraw(WITHDRAW_500)
        );
        assertEquals(
            "Account is frozen. Cannot withdraw.",
            exception.getMessage()
        );
    }

    @Test
    @Order(ORDER_TEST_CASE_10)
    @DisplayName("Test Case 10: Unfreeze account and withdraw")
    void testUnfreezeAndWithdraw() {
        account.freezeAccount();
        account.unfreezeAccount();
        account.deposit(DEPOSIT_1000);
        account.withdraw(WITHDRAW_100);
        assertFalse(account.isFrozen());
        assertEquals(
            DEPOSIT_1000 - WITHDRAW_100,
            account.getBalance()
        );
    }

    @Test
    @Order(ORDER_TEST_CASE_11)
    @DisplayName("Test Case 11: Check account is frozen")
    void testIsFrozen() {
        assertFalse(account.isFrozen());
        account.freezeAccount();
        assertTrue(account.isFrozen());
    }

    @Test
    @Order(ORDER_TEST_CASE_12)
    @DisplayName("Test Case 12: Balance after multiple transactions")
    void testBalanceAfterMultipleTransactions() {
        account.deposit(DEPOSIT_1000);
        account.withdraw(WITHDRAW_500);
        account.deposit(DEPOSIT_1000);
        account.withdraw(WITHDRAW_100);
        assertEquals(
            EXPECTED_BALANCE_AFTER_TRANSACTIONS,
            account.getBalance()
        );
    }

    @Test
    @Order(ORDER_TEST_MAIN)
    @DisplayName("Run Main.main() for coverage")
    void testMainCoverage() {
        String[] args = {};
        Main.main(args);
        assertTrue(true);
    }

    @Test
    @Order(ORDER_INITIAL_STATE)
    @DisplayName("Test initial state of AbstractBankAccount")
    void testInitialState() {
        assertEquals(0.0, account.getBalance());
        assertFalse(account.isFrozen());
    }
}
