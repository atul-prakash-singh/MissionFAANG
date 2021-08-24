package com.mission.faang.concurrent.evenodd;

import java.util.concurrent.Semaphore;

public class PrintEvenOddSemaphore {

    public static void main(String[] args) {
        SharedPrinter printer = new SharedPrinter();
        Thread odd = new Thread(new TaskOddEven(printer, 10, false), "Odd");
        Thread even = new Thread(new TaskOddEven(printer, 10, true), "Even");
        odd.start();
        even.start();
    }
}

class SharedPrinter {

    private final Semaphore semEven = new Semaphore(0);
    private final Semaphore semOdd = new Semaphore(1);

    void printEven(int num) {
        try {
            semEven.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + ":" + num);
        semOdd.release();
    }

    void printOdd(int num) {
        try {
            semOdd.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + ":" + num);
        semEven.release();
    }
}

class TaskOddEven implements Runnable {
    private final SharedPrinter printer;
    private final int max;
    private final boolean isEven;

    TaskOddEven(SharedPrinter printer, int max, boolean isEven) {
        this.printer = printer;
        this.max = max;
        this.isEven = isEven;
    }

    @Override
    public void run() {
        int number = isEven ? 2 : 1;
        while (number <= max) {
            if (isEven) {
                printer.printEven(number);
            } else {
                printer.printOdd(number);
            }
            number += 2;
        }
    }
}