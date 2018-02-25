package com.example.sensoraverage;

public class Accumulator {

    private int count;

    private int totalValue;

    public Accumulator(int count, int totalValue) {
        this.count = count;
        this.totalValue = totalValue;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the totalValue
     */
    public int getTotalValue() {
        return totalValue;
    }

    /**
     * @param totalValue the totalValue to set
     */
    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }
}