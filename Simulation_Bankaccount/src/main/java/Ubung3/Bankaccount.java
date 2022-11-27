package Ubung3;

import java.util.concurrent.ThreadLocalRandom;

public class Bankaccount {
    private float balance;
    private String name;
    private final long bankAccId;
    public Bankaccount(String name) {
        this.name = name;
        this.bankAccId = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
    }
    public void deposit (float amount){
        balance += amount;
    }
    public void withdraw (float amount){
        balance -= amount;
    }
    public float getBalance (){
        return balance;
    }
    public long getBankAccId(){
        return bankAccId;
    }
    public void transfer(float amount,Bankaccount b1,Bankaccount b2){
        b1.withdraw(amount);
        b2.deposit(amount);
    }
}
