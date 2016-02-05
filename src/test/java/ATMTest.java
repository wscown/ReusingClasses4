import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wscown on 2/4/16.
 */
public class ATMTest {

    ATM atm;

    @Before
    public void initialise() {
        //With
        atm = new ATM();
    }

    @Test
    public void testFirstUnusedAccountNo() throws Exception {
        //Then
        assertEquals("SavingsAccount should be empty. Specification says it should return 0", -1, atm.firstUnusedAccountNo(ACType.SAVINGS));
        assertEquals("BusinessAccount should be empty. Specification says it should return 0", -1, atm.firstUnusedAccountNo(ACType.BUSINESS));
        assertEquals("CheckingAccount should be empty. Specification says it should return 0", -1, atm.firstUnusedAccountNo(ACType.CHECKING));

        //When
        atm.createAccount("William Scown", 0, ACType.SAVINGS, "1234");
        atm.createAccount("William Scown", 0, ACType.BUSINESS, "1234");
        atm.createAccount("William Scown", 0, ACType.CHECKING, "1234");

        atm.createAccount("Catherine Russell", 0, ACType.SAVINGS, "1234");
        atm.createAccount("Catherine Russell", 0, ACType.BUSINESS, "1234");
        atm.createAccount("Catherine Russell", 0, ACType.CHECKING, "1234");

        atm.createAccount("Jon Ford", 0, ACType.SAVINGS, "1234");
        atm.createAccount("Jon Ford", 0, ACType.BUSINESS, "1234");
        atm.createAccount("Jon Ford", 0, ACType.CHECKING, "1234");

        atm.createAccount("Greg Paulfry", 0, ACType.SAVINGS, "1234");
        atm.createAccount("Greg Paulfry", 0, ACType.BUSINESS, "1234");
        atm.createAccount("Greg Paulfry", 0, ACType.CHECKING, "1234");

        //Then
        assertEquals("SavingsAccount should be empty. Specification says it should return -1", -1, atm.firstUnusedAccountNo(ACType.SAVINGS));
        assertEquals("BusinessAccount should be empty. Specification says it should return -1", -1, atm.firstUnusedAccountNo(ACType.BUSINESS));
        assertEquals("CheckingAccount should be empty. Specification says it should return -1", -1, atm.firstUnusedAccountNo(ACType.CHECKING));

        //When
        atm.deleteAccount(ACType.SAVINGS, 0);
        atm.deleteAccount(ACType.BUSINESS, 1);
        atm.deleteAccount(ACType.CHECKING, 2);

        //Then
        assertEquals("SavingsAccount should be empty. Specification says it should return -1", 0, atm.firstUnusedAccountNo(ACType.SAVINGS));
        assertEquals("BusinessAccount should be empty. Specification says it should return -1", 1, atm.firstUnusedAccountNo(ACType.BUSINESS));
        assertEquals("CheckingAccount should be empty. Specification says it should return -1", 2, atm.firstUnusedAccountNo(ACType.CHECKING));
    }

    @Test
    public void testCreateAccount() throws Exception {
        //When
        atm.createAccount("William Scown", 0, ACType.SAVINGS, "1234");
        atm.createAccount("William Scown", 0, ACType.BUSINESS, "1234");
        atm.createAccount("William Scown", 0, ACType.CHECKING, "1234");

        atm.createAccount("Catherine Russell", 0, ACType.SAVINGS, "1234");
        atm.createAccount("Catherine Russell", 0, ACType.BUSINESS, "1234");
        atm.createAccount("Catherine Russell", 0, ACType.CHECKING, "1234");

        atm.createAccount("Jon Ford", 0, ACType.SAVINGS, "1234");
        atm.createAccount("Jon Ford", 0, ACType.BUSINESS, "1234");
        atm.createAccount("Jon Ford", 0, ACType.CHECKING, "1234");

        atm.createAccount("Greg Paulfry", 0, ACType.SAVINGS, "1234");
        atm.createAccount("Greg Paulfry", 0, ACType.BUSINESS, "1234");
        atm.createAccount("Greg Paulfry", 0, ACType.CHECKING, "1234");

        assertEquals("Four savings accounts were created but this is not reflected in the size of the array", 4, atm.getAccount(ACType.SAVINGS).size());
        assertEquals("Four business accounts were created but this is not reflected in the size of the array", 4, atm.getAccount(ACType.BUSINESS).size());
        assertEquals("Four checking accounts were created but this is not reflected in the size of the array", 4, atm.getAccount(ACType.CHECKING).size());
    }

    @Test
    public void testDeleteAccount() throws Exception {
        //When
        atm.createAccount("William Scown", 0, ACType.SAVINGS, "1234");
        atm.createAccount("William Scown", 0, ACType.BUSINESS, "1234");
        atm.createAccount("William Scown", 0, ACType.CHECKING, "1234");

        atm.createAccount("Catherine Russell", 0, ACType.SAVINGS, "1234");
        atm.createAccount("Catherine Russell", 0, ACType.BUSINESS, "1234");
        atm.createAccount("Catherine Russell", 0, ACType.CHECKING, "1234");

        atm.createAccount("Jon Ford", 0, ACType.SAVINGS, "1234");
        atm.createAccount("Jon Ford", 0, ACType.BUSINESS, "1234");
        atm.createAccount("Jon Ford", 0, ACType.CHECKING, "1234");

        atm.createAccount("Greg Paulfry", 0, ACType.SAVINGS, "1234");
        atm.createAccount("Greg Paulfry", 0, ACType.BUSINESS, "1234");
        atm.createAccount("Greg Paulfry", 0, ACType.CHECKING, "1234");

        atm.deleteAccount(ACType.CHECKING, 0);
        atm.deleteAccount(ACType.SAVINGS, 1);
        atm.deleteAccount(ACType.BUSINESS, 2);

        //Then
        //ArrayList should not have changed in size but the entry for that position will now be null
        assertEquals("Four checking accounts were created but this is not reflected in the size of the array", 4, atm.getAccount(ACType.CHECKING).size());
        assertEquals("Four business accounts were created but this is not reflected in the size of the array", 4, atm.getAccount(ACType.BUSINESS).size());
        assertEquals("Four savings accounts were created but this is not reflected in the size of the array", 4, atm.getAccount(ACType.SAVINGS).size());

        assertEquals("Four checking accounts were created and one was deleted. That entry in the array should be null but was not.", null, atm.getAccount(ACType.CHECKING).get(0));
        assertEquals("Four savings accounts were created and one was deleted. That entry in the array should be null but was not.", null, atm.getAccount(ACType.SAVINGS).get(1));
        assertEquals("Four business accounts were created and one was deleted. That entry in the array should be null but was not.", null, atm.getAccount(ACType.BUSINESS).get(2));
    }

    @Test
    public void testWithdrawMoney() throws Exception {
        //When
        atm.createAccount("William Scown", 10, ACType.SAVINGS, "1234");
        atm.createAccount("William Scown", 20, ACType.BUSINESS, "1234");
        atm.createAccount("William Scown", 30, ACType.CHECKING, "1234");

        atm.createAccount("Catherine Russell", 40, ACType.SAVINGS, "1234");
        atm.createAccount("Catherine Russell", 50, ACType.BUSINESS, "1234");
        atm.createAccount("Catherine Russell", 60, ACType.CHECKING, "1234");

        atm.createAccount("Jon Ford", 70, ACType.SAVINGS, "1234");
        atm.createAccount("Jon Ford", 80, ACType.BUSINESS, "1234");
        atm.createAccount("Jon Ford", 90, ACType.CHECKING, "1234");

        atm.createAccount("Greg Paulfry", 100, ACType.SAVINGS, "1234");
        atm.createAccount("Greg Paulfry", 110, ACType.BUSINESS, "1234");
        atm.createAccount("Greg Paulfry", 120, ACType.CHECKING, "1234");

        //Withdraw money from various accounts
        atm.withdrawMoney(ACType.SAVINGS, 0, "1234", 10);
        atm.withdrawMoney(ACType.BUSINESS, 1, "1234", 20);
        atm.withdrawMoney(ACType.CHECKING, 2, "1234", 30);

        //Check the new balance
        assertTrue("A withdrawal of 10 was made on William Scown's Savings account but the new account balance does not reflect this", 0 == atm.getAccount(ACType.SAVINGS).get(0).getBalance());
        assertTrue("A withdrawal of 20 was made on Catherine Russels's Business account but the new account balance does not reflect this", 30 == atm.getAccount(ACType.BUSINESS).get(1).getBalance());
        assertTrue("A withdrawal of 30 was made on Jon Fords's Checking account but the new account balance does not reflect this", 60 == atm.getAccount(ACType.CHECKING).get(2).getBalance());
    }

    @Test
    public void testDepositMoney() throws Exception {
        //When
        atm.createAccount("William Scown", 10, ACType.SAVINGS, "1234");
        atm.createAccount("William Scown", 20, ACType.BUSINESS, "1234");
        atm.createAccount("William Scown", 30, ACType.CHECKING, "1234");

        atm.createAccount("Catherine Russell", 40, ACType.SAVINGS, "1234");
        atm.createAccount("Catherine Russell", 50, ACType.BUSINESS, "1234");
        atm.createAccount("Catherine Russell", 60, ACType.CHECKING, "1234");

        atm.createAccount("Jon Ford", 70, ACType.SAVINGS, "1234");
        atm.createAccount("Jon Ford", 80, ACType.BUSINESS, "1234");
        atm.createAccount("Jon Ford", 90, ACType.CHECKING, "1234");

        atm.createAccount("Greg Paulfry", 100, ACType.SAVINGS, "1234");
        atm.createAccount("Greg Paulfry", 110, ACType.BUSINESS, "1234");
        atm.createAccount("Greg Paulfry", 120, ACType.CHECKING, "1234");

        //Withdraw money from various accounts
        atm.depositMoney(ACType.SAVINGS, 0, "1234", 10);
        atm.depositMoney(ACType.BUSINESS, 1, "1234", 20);
        atm.depositMoney(ACType.CHECKING, 2, "1234", 30);

        //Check the new balance
        assertTrue("A deposit of 10 was made on William Scown's Savings account but the new account balance does not reflect this", 20 == atm.getAccount(ACType.SAVINGS).get(0).getBalance());
        assertTrue("A deposit of 20 was made on Catherine Russels's Business account but the new account balance does not reflect this", 70 == atm.getAccount(ACType.BUSINESS).get(1).getBalance());
        assertTrue("A deposit of 30 was made on Jon Fords's Checking account but the new account balance does not reflect this", 120 == atm.getAccount(ACType.CHECKING).get(2).getBalance());
    }
}