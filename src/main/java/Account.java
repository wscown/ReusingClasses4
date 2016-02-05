/**
 * Created by wscown on 2/4/16.
 */
public abstract class Account {
    private String holderName;
    private double balance;
    private String pin;

    //Default constructor
    public Account() {
    }

    //Account construction with an opening balance provided
    Account(String holderName, double balance, String pin){
        this.holderName = holderName;
        this.balance = balance;
        this.pin = pin;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean checkPin(String check){
        return check.equals(pin);
    }

    @Override
    public String toString() {
        return "Account{" +
                "holderName='" + holderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}