package com.company.Lecture_2;

import java.util.concurrent.locks.ReentrantLock;

class Class implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++)
            Class2.counter++;
    }
}

class Class2 {
    public static int counter = 0;
}

class ClassVolatile {
    private int x = 1;
    private volatile boolean i = false;

    public void setX(int x) {
        this.x = x;
        i = true;
    }

    public int getX(int x) {
        if (i == true)
            return x;
        return 0;
    }
}


public class Main {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Class());
        Thread thread2 = new Thread(new Class());
        ClassVolatile classInstance = new ClassVolatile();


        System.out.println(classInstance.getX(2));
        classInstance.setX(0);
        System.out.println(classInstance.getX(2));

        ReentrantLock L = new ReentrantLock();
        L.lock();
        try {
        } finally {
            L.unlock();
        }
    }
}
