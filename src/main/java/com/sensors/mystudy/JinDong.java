package com.sensors.mystudy;

import java.util.Scanner;

public class JinDong {
    public static void main(String[] args) {
        System.out.println("请输入字符串的长度");
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();
        System.out.println(num);
        int total = getTotal(Integer.parseInt(num));
        System.out.println(total);

    }

    private static int getTotal(int num) {
        if (num < 6)
            return 0;
        int other = num - 6;//剩余字符个数
        int split = num - 6 + 1;//漂亮串可以放置的位置个数

        int otherTotle = (int) Math.pow(26,other);
        int total = (int) (( otherTotle * (split + (split * other)/2) )% (Math.pow(10,9)+7));
        return total;
    }
}
