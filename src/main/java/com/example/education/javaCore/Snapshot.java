package com.example.education.javaCore;

import java.util.Stack;

public record Snapshot(String state) {
}

class MyStringBuilder {
    private final Stack<Snapshot> history;
    private final StringBuilder stringBuilder;

    public MyStringBuilder() {
        this.history = new Stack<>();
        this.stringBuilder = new StringBuilder();
    }

    public void append(String str) {
        stringBuilder.append(str);
        saveSnapShot();
    }

    public void delete(int start, int end) {
        stringBuilder.delete(start, end);
        saveSnapShot();
    }

    public String toString() {
        return stringBuilder.toString();
    }

    public void undo() {
        if (!history.isEmpty()) {
            stringBuilder.setLength(0);
            stringBuilder.append(history.pop().state());
        }
    }

    private void saveSnapShot() {
        history.push(new Snapshot(stringBuilder.toString()));
    }

    public static void main(String[] args) {
        MyStringBuilder builder = new MyStringBuilder();

        builder.append("Hello");
        System.out.println(builder.toString());

        builder.append(" World V");
        System.out.println(builder.toString());

        builder.delete(11, 13);
        System.out.println(builder.toString());

        builder.undo();
        System.out.println(builder.toString());
    }
}