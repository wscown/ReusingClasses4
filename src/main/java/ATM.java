import java.util.ArrayList;
/**
 * Created by wscown on 2/4/16.
 */

enum ACType{
    CHECKING, SAVINGS, BUSINESS
}

public class ATM {
    private ArrayList<Account> checkingAccounts;
    private ArrayList<SavingsAccount> savingsAccounts;
    private ArrayList<BusinessAccount> businessAccounts;

    public static void main(String[] args) {
        ATM atm = new ATM();

        //Testing of account creation
        System.out.println("Testing account creation\n");

        System.out.println(atm.createAccount("William Scown", 10, ACType.SAVINGS, "1234"));
        System.out.println(atm.createAccount("William Scown", 20, ACType.BUSINESS, "1234"));
        System.out.println(atm.createAccount("William Scown", 30, ACType.CHECKING, "1234"));

        System.out.println(atm.createAccount("Catherine Russell", 40, ACType.SAVINGS, "1234"));
        System.out.println(atm.createAccount("Catherine Russell", 50, ACType.BUSINESS, "1234"));
        System.out.println(atm.createAccount("Catherine Russell", 60, ACType.CHECKING, "1234"));

        System.out.println(atm.createAccount("Jon Ford", 70, ACType.SAVINGS, "1234"));
        System.out.println(atm.createAccount("Jon Ford", 80, ACType.BUSINESS, "1234"));
        System.out.println(atm.createAccount("Jon Ford", 90, ACType.CHECKING, "1234"));

        System.out.println(atm.createAccount("Greg Paulfry", 100, ACType.SAVINGS, "1234"));
        System.out.println(atm.createAccount("Greg Paulfry", 110, ACType.BUSINESS, "1234"));
        System.out.println(atm.createAccount("Greg Paulfry", 120, ACType.CHECKING, "1234"));

        //Testing of account deletion
        System.out.println("\nTesting account deletion\n");

        System.out.println(atm.deleteAccount(ACType.CHECKING, 1));
        System.out.println(atm.deleteAccount(ACType.BUSINESS, 2));
        System.out.println(atm.deleteAccount(ACType.SAVINGS, 0));

        //Testing that deleted account are in fact deleted
        System.out.println("\nTesting that deleted account are in fact deleted\n");
        System.out.println(atm.deleteAccount(ACType.CHECKING, 1));
        System.out.println(atm.deleteAccount(ACType.BUSINESS, 2));
        System.out.println(atm.deleteAccount(ACType.SAVINGS, 0));

        //Testing adding account puts them into the avilable free slot
        System.out.println("\nTesting that adding accounts puts them into the available free slot\n");
        System.out.println(atm.createAccount("William Scown", 10, ACType.SAVINGS, "1234"));
        System.out.println(atm.createAccount("Catherine Russell", 60, ACType.CHECKING, "1234"));
        System.out.println(atm.createAccount("Jon Ford", 80, ACType.BUSINESS, "1234"));

        //Testing withdrawal of money
        System.out.println("\nTesting the withdrawal of money from an account");
        System.out.println(atm.withdrawMoney(ACType.BUSINESS, 2, "1234", 10));
        System.out.println(atm.withdrawMoney(ACType.BUSINESS, 2, "1234", 10));
        System.out.println(atm.withdrawMoney(ACType.BUSINESS, 2, "1234", 10));
        System.out.println(atm.withdrawMoney(ACType.BUSINESS, 2, "1234", 10));

        //Testing the depositing of money into an account
        System.out.println("\nTesting the depositing of money into an account");
        System.out.println(atm.depositMoney(ACType.BUSINESS, 2, "1234", 10));
        System.out.println(atm.depositMoney(ACType.BUSINESS, 2, "1234", 10));
        System.out.println(atm.depositMoney(ACType.BUSINESS, 2, "1234", 10));
        System.out.println(atm.depositMoney(ACType.BUSINESS, 2, "1234", 10));

        //Test that the pin numbcer system actually works
        System.out.println("\nTesting the pin number system");
        System.out.println(atm.depositMoney(ACType.BUSINESS, 2, "1134", 10));
        System.out.println(atm.depositMoney(ACType.BUSINESS, 2, "1134", 10));
    }

    ATM(){
        checkingAccounts = new ArrayList<>();
        savingsAccounts = new ArrayList<>();
        businessAccounts = new ArrayList<>();
    }

    //Check an arraylist type for the first index that does not currently hold an account
    //Returns -1 if the arraylist is empty or if there are no free spots
    public int firstUnusedAccountNo(ACType acType){
        switch(acType){
            case SAVINGS: return firstUAN(savingsAccounts);
            case BUSINESS: return firstUAN(businessAccounts);
            case CHECKING: return firstUAN(checkingAccounts);
            default: return 0;
        }
    }

    public int firstUAN(ArrayList<? extends Account> accounts){
        int i = 0;
        if(accounts.size() == 0){
            return -1;
        }else{
            while(i < accounts.size()){
                if(accounts.get(i) == null){
                    return i;
                }else{
                    i++;
                }
            }
            return -1;
        }
    }

    public String createAccount(String holderName, double balance, ACType acType, String pin){

        String returnStatement = "The following account was successully created:\n";
        int position = firstUnusedAccountNo(acType);

        switch(acType){
            case SAVINGS:
                if(position == -1 || position == savingsAccounts.size()){
                    savingsAccounts.add(new SavingsAccount(holderName, balance, pin));
                    returnStatement += savingsAccounts.get(savingsAccounts.size() - 1).toString() + " Account number: " + (savingsAccounts.size() - 1);
                }else {
                    savingsAccounts.add(position, new SavingsAccount(holderName, balance, pin));
                    returnStatement += savingsAccounts.get(position).toString() + " Account number: " + position;
                }
                return returnStatement;
            case BUSINESS:
                if(position == -1 || position == savingsAccounts.size()) {
                    businessAccounts.add(new BusinessAccount(holderName, balance, pin));
                    returnStatement += businessAccounts.get(savingsAccounts.size() - 1).toString() + " Account number: " + (savingsAccounts.size() - 1);
                }else{
                    businessAccounts.add(position, new BusinessAccount(holderName, balance, pin));
                    returnStatement += businessAccounts.get(position).toString() + " Account number: " + position;
                }
                return returnStatement;
            case CHECKING:
                if(position == -1 || position == savingsAccounts.size()) {
                    checkingAccounts.add(new CheckingAccount(holderName, balance, pin));
                    returnStatement += checkingAccounts.get(savingsAccounts.size() - 1).toString() + " Account number: " + (savingsAccounts.size() - 1);
                }else{
                    checkingAccounts.add(position, new CheckingAccount(holderName, balance, pin));
                    returnStatement += checkingAccounts.get(position).toString() + " Account number: " + position;;
                }
                return returnStatement;
        }
        return "";
    }

    public String deleteAccount(ACType acType, int accountNo){

        String returnStatement = "The following account was successully deleted:\n";
        ArrayList<? extends Account> temp;

        switch(acType){
            case SAVINGS: temp = savingsAccounts;
                break;
            case BUSINESS:temp = businessAccounts;
                break;
            case CHECKING:temp = checkingAccounts;
                break;
            default: temp = new ArrayList<Account>();
                break;
        }

        if(temp.get(accountNo) == null || temp.size() < accountNo){
            return "The account you attempted to delete does not exist";
        }else {
            returnStatement += temp.get(accountNo).toString();
            temp.remove(accountNo);
            temp.add(accountNo, null);
            return returnStatement;
        }
    }

    public String withdrawMoney(ACType acType, int accountNo, String pin, double value) {

        ArrayList<? extends Account> temp;

        switch (acType) {
            case SAVINGS:
                temp = savingsAccounts;
                break;
            case BUSINESS:
                temp = businessAccounts;
                break;
            case CHECKING:
                temp = checkingAccounts;
                break;

            default:
                temp = new ArrayList<Account>();
                break;
        }

        //Subtract the ammount from the bank account
        if(temp.get(accountNo) == null || temp.size() < accountNo){
            return "The account you attempted to withdraw from does not exist";
        }else{
            if(temp.get(accountNo).checkPin(pin)) {
                temp.get(accountNo).setBalance(temp.get(accountNo).getBalance() - value);

                return "$" + value + " was withdrawn from " + temp.get(accountNo).getHolderName() + "'s " + acType + " account. New balance is $" + temp.get(accountNo).getBalance();
            }else{
                return "Incorrect pin supplied.";
            }
        }
    }

    public String depositMoney(ACType acType, int accountNo, String pin, double value){
        ArrayList<? extends Account> temp;

        switch (acType) {
            case SAVINGS:
                temp = savingsAccounts;
                break;
            case BUSINESS:
                temp = businessAccounts;
                break;
            case CHECKING:
                temp = checkingAccounts;
                break;

            default:
                temp = new ArrayList<Account>();
        }

        //Subtract the ammount from the bank account
        if(temp.get(accountNo) == null || temp.size() < accountNo){
            return "The account you attempted to deposit into does not exist";
        }else {
            if(temp.get(accountNo).checkPin(pin)) {
                temp.get(accountNo).setBalance(temp.get(accountNo).getBalance() + value);

                String returnStatement = "$" + value + " was deposited int " + temp.get(accountNo).getHolderName() + "'s " + acType + " account. New balance is $" + temp.get(accountNo).getBalance();

                return returnStatement;
            }else{
                return "Incorrect pin supplied.";
            }
        }
    }

    public String printAC(ACType acType, int account){
        switch(acType){
            case SAVINGS: return savingsAccounts.get(account).toString();
            case BUSINESS: return businessAccounts.get(account).toString();
            case CHECKING: return checkingAccounts.get(account).toString();
            default: return null;
        }
    }

    //Method used for testing
    public ArrayList<? extends Account> getAccount(ACType acType){
        switch(acType){
            case SAVINGS: return savingsAccounts;
            case BUSINESS: return businessAccounts;
            case CHECKING: return checkingAccounts;
            default: return null;
        }
    }
}
