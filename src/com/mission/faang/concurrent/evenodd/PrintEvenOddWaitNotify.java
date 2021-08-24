package com.mission.faang.concurrent.evenodd;

public class PrintEvenOddWaitNotify {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread odd = new Thread(new TaskEvenOdd(printer, 10, false), "Odd");
        Thread even = new Thread(new TaskEvenOdd(printer, 10, true), "Even");
        odd.start();
        even.start();
    }
}

class Printer {
    private volatile boolean isOdd;

    synchronized void printEven(int number) {
        while (!isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + number);
        isOdd = false;
        notify();
    }

    synchronized void printOdd(int number) {
        while (isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + number);
        isOdd = true;
        notify();
    }
}

class TaskEvenOdd implements Runnable {
    private final int max;
    private final Printer printer;
    private final boolean isEven;

    TaskEvenOdd(Printer printer, int max, boolean isEven) {
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
