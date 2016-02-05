/**
 * Created by wscown on 2/4/16.
 */
public class SavingsAccount extends Account {
    SavingsAccount(String holderName, double balance, String pin){
        super(holderName, balance, pin);
    }

    @Override
    public String toString() {
        return "Savings Account{" +
                "holderName='" + super.getHolderName() + '\'' +
                ", balance=" + super.getBalance() +
                '}';
    }
}
