package com.petrenko.multithreading2;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Hw2Phaser {

    public static void main(String[] args) {
        final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        final Phaser phaser = new Phaser(4);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            executorService.execute(new MyThread(phaser));
        }

        for (int i = 0; i < 3; i++) {
            final int phase = phaser.getPhase();
            phaser.arriveAndAwaitAdvance();
            System.out.println(timeFormatter.format(LocalDateTime.now()) + " "
                    + Thread.currentThread().getName()
                    + " phase " + phase + " is finished");
        }

        phaser.arriveAndDeregister();

        if (phaser.isTerminated()) {
            System.out.println(timeFormatter.format(LocalDateTime.now())
                    + " all phases are finished ");
        }

        executorService.shutdown();

    }

    static class MyThread extends Thread {
        private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        private final Phaser phaser;
        private final Random random = new Random();

        public MyThread(Phaser phaser) {
            this.phaser = phaser;
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(timeFormatter.format(LocalDateTime.now()) + " " +
                    Thread.currentThread().getName() + " start working ");
            for (int i = 0; i < 3; i++) {
                System.out.println(timeFormatter.format(LocalDateTime.now()) + " "
                        + Thread.currentThread().getName() + " "
                        + " phase " + i + " start");
                final int seconds = random.nextInt(10);
                System.out.println(timeFormatter.format(LocalDateTime.now()) + " "
                        + Thread.currentThread().getName()
                        + " is sleeping for " + seconds);
                TimeUnit.SECONDS.sleep(seconds);
                System.out.println(timeFormatter.format(LocalDateTime.now()) + " "
                        + Thread.currentThread().getName()
                        + " stops sleeping");
                phaser.arriveAndAwaitAdvance();

            }
            phaser.arriveAndDeregister();
        }
    }
}
