package com.example.education.concurrency.cyclicBarrier;

public class ComplexTask {
    public void execute() {
        System.out.println(Thread.currentThread().getName() + " выполняет часть сложной задачи. ");
    }
}