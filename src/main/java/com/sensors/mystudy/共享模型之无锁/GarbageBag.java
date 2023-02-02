package com.sensors.mystudy.共享模型之无锁;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/22
 */
public class GarbageBag {
    String desc;
    public GarbageBag(String desc) {
        this.desc = desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    @Override
    public String toString() {
        return super.toString() + " " + desc;
    }
}
