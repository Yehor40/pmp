package com.example.uk4;


import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private double price;
    private int count;
    private boolean done;

    public Item(String name, double price, int count, boolean done) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", done=" + done +
                '}';
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
