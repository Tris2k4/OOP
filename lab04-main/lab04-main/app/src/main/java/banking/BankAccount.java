package banking;

public class BankAccount {
    private double balance;

    public BankAccount(double balance) throws Exception {
        if (balance < 0) {
            throw new Exception("Balance must be >=0");
        }
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * preconditon: amount > 0 and balance >= amount and amount > 0
     * @param amount
     * postcondition: balance -= amount and balance >= 0
     */
    public void withDrawnMoney(double amount) {
        if (this.getBalance() >= amount && this.getBalance() > 0 && amount > 0) {
            setBalance(this.getBalance() - amount);
        }
    }

    /**
     * precondition: amount > 0
     * @param amount
     * postcondition: balance > 0 and balance += amount
     */
    public void deposit(double amount) {
        setBalance(this.getBalance() + amount);
    }
}
