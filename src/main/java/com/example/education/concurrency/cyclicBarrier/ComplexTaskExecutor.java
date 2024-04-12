package com.example.education.concurrency.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexTaskExecutor {
    private final int tasks;
    private final ExecutorService executorService;
    private final CyclicBarrier barrier;

    public ComplexTaskExecutor(int tasks) {
        this.tasks = tasks;
        this.executorService = Executors.newFixedThreadPool(tasks);
        this.barrier = new CyclicBarrier(tasks, () -> {
            System.out.println("Все задачи выполнены.");
        });
    }

    public void executeTasks(int tasks) {
        for (int i = 0; i < tasks; i++) {
            executorService.execute(() -> {
                ComplexTask task = new ComplexTask();
                task.execute();
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }
}