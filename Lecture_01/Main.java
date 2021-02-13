package com.company.Lecture_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        //Exercise no. 1

        new MyThread().start();
        new MyThread().start();

        //Exercise no. 2

        MyClass mc1 = new MyClass("First Class Instance");
        MyClass mc2 = new MyClass("Second Class Instance");

        //Exercise no. 3

        Thread thread1 = new Thread(mc1, "First Thread");
        Thread thread2 = new Thread(mc2, "Second Thread");

        thread1.start();
        thread2.start();

    }

    //Exercise no. 1
    public static class MyThread extends Thread {
        public void run() {
            System.out.println(this.getName());
        }
    }

    //Exercise no. 2
    static class MyClass implements Runnable {
        private Random rnd;
        private String str;

        String thread = Thread.currentThread().getName();

        public MyClass(String str) {
            this.str = str;
            rnd = new Random();
        }

        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(rnd.nextInt(2500) + 500);
                    System.out.println(thread);
                } catch (InterruptedException e) {
                    System.out.println(str + " " + i);

                }
            }
        }
    }
}
