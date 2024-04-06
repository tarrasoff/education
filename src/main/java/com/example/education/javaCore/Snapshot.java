package com.example.education.javaCore;

import java.util.ArrayList;
import java.util.List;

public record Snapshot(String state) {
}

class MyStringBuilder{
    private Snapshot snapshot;
    private final List<Snapshot> history;
    private final StringBuilder stringBuilder;

    public MyStringBuilder() {
        this.history = new ArrayList<>();
        this.stringBuilder = new StringBuilder();
    }

    public void append(String str){
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
            stringBuilder.append(history.get(history.size() - 1).state());
            history.remove(history.size() - 1);
        }
    }

    private void saveSnapShot() {
        snapshot = new Snapshot(stringBuilder.toString());
        history.add(snapshot);
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