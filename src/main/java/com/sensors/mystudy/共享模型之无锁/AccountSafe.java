package com.sensors.mystudy.共享模型之无锁;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/22
 */
class AccountSafe implements Account {
    private AtomicInteger balance;

    public AccountSafe(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        while (true) {
            int prev = balance.get();
            int next = prev - amount;
            if (balance.compareAndSet(prev, next)) {//CAS操作
                break;
            }
        }
        // 可以简化为下面的方法
        // balance.addAndGet(-1 * amount);
    }

    public static void main(String[] args) {
        Account.demo(new AccountSafe(10000));
    }
}
