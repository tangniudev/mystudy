package com.sensors.mystudy.共享模型之无锁;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/22
 */
class AccountUnsafe implements Account {
    private  Integer balance;
    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }
    @Override
    public Integer getBalance() {
        return balance;
    }
    @Override
    public synchronized void withdraw(Integer amount) {
        balance -= amount;//多线程会产生指令交错，所以需要加锁synchronized
    }

    public static void main(String[] args) {
        Account.demo(new AccountUnsafe(10000));
    }
}
