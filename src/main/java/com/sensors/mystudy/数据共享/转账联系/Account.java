package com.sensors.mystudy.数据共享.转账联系;

import java.util.Objects;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/21
 */
class Account {
    private  int money;
    final static Object object = new Object();
    public Account(int money) {
        this.money = money;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public  void  transfer(Account target, int amount) {
            if (this.money > amount) {
                synchronized (object) {
                    this.setMoney(this.getMoney() - amount);
                    target.setMoney(target.getMoney() + amount);
                }
            }

    }
}
