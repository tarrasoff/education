package com.example.education.javaCollection;

import java.util.HashMap;
import java.util.Map;

public class CountOfElements {
    public Map<Integer, Integer> createMap(int[] integers) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < integers.length; i++) {
            if (!map.containsKey(integers[i])) {
                map.put(integers[i], 1);
            } else {
                map.put(integers[i], map.get(integers[i]) + 1);
            }
        }
        return map;
    }
}