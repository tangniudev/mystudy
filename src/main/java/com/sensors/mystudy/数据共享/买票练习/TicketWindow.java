package com.sensors.mystudy.数据共享.买票练习;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/21
 */
class TicketWindow {
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }
}
