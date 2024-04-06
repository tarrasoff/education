package com.example.education.javaCollection;

public class CollectionFilter {
    public Object[] filter(Object[] objects, Filter filter){
        Object[] newObjects = new Object[objects.length];
        for (int i = 0; i < objects.length; i++){
            newObjects[i] = filter.apply(objects[i]);
        }
        return newObjects;
    }
}

interface Filter {
    Object apply(Object o);
}

class FilterClass implements Filter {
    @Override
    public Object apply(Object o) {
        return o;
    }
}