/**
 * Created by wscown on 2/4/16.
 */
public class BusinessAccount extends Account{
    BusinessAccount(String holderName, double balance, String pin){
        super(holderName, balance, pin);
    }

    @Override
    public String toString() {
        return "Business Account{" +
                "holderName='" + super.getHolderName() + '\'' +
                ", balance=" + super.getBalance() +
                '}';
    }
}
