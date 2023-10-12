package com.sensors.mystudy.temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/**
 * 2 1
 * 89 38
 * 445 754
 */
public class Meituan {

    public static void main(String[] args) {




        System.out.println("请输入总题数和可复习题数");
        Scanner scanner1 = new Scanner(System.in);
        String totalScanner = scanner1.nextLine();
        String[] s = totalScanner.split(" ");
        int zongshu = Integer.parseInt(s[0]);
        int fuxi = Integer.parseInt(s[1]);

        System.out.println("请输入每道题的期望");
        Scanner scanner2 = new Scanner(System.in);
        String hopeScanner = scanner2.nextLine();
        String[] everyHope = hopeScanner.split(" ");

        System.out.println("请输入每道题的分值");
        Scanner scanner3 = new Scanner(System.in);
        String markScanner = scanner3.nextLine();
        String[] everyMark = markScanner.split(" ");

        long l = System.currentTimeMillis();

        int oldMarks = 0;
        ArrayList<Integer> marks = new ArrayList<>(everyHope.length);
        for (int i = 0; i < everyHope.length; i++) {
            marks.add(Integer.parseInt(everyHope[i]) * Integer.parseInt(everyMark[i]));
            oldMarks = oldMarks + Integer.parseInt(everyHope[i]) * Integer.parseInt(everyMark[i]);
        }
//        Collections.sort(marks);

        ArrayList<Integer> addMark = new ArrayList<>(everyHope.length);
        for (int i = 0; i < everyHope.length; i++) {
            addMark.add((100 - Integer.parseInt(everyHope[i])) * Integer.parseInt(everyMark[i]));
        }
        Collections.sort(addMark);
//        Collections.reverse(addMark);

        int addMarks = 0;
        for (int i = 0; i < fuxi; i++) {
            addMarks = addMarks + addMark.get(addMark.size()-i-1);
        }
        int totalMark = addMarks + oldMarks;
        System.out.println(totalMark/100.00);
        long l1 = System.currentTimeMillis();
        System.out.println("耗费时间"+(l1-l)* 1000);

    }
}
