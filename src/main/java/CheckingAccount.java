/**
 * Created by wscown on 2/4/16.
 */
public class CheckingAccount extends Account {
    CheckingAccount(String holderName, double balance, String pin){
        super(holderName, balance, pin);
    }

    @Override
    public String toString() {
        return "Checking Account{" +
                "holderName='" + super.getHolderName() + '\'' +
                ", balance=" + super.getBalance() +
                '}';
    }
}
