package com.example.testepekus;

public class Calculation {
    private int id;
    private double valueA;
    private double valueB;
    private String operation;
    private double result;
    private String timestamp;

    public Calculation() {
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValueA() {
        return valueA;
    }

    public void setValueA(double valueA) {
        this.valueA = valueA;
    }

    public double getValueB() {
        return valueB;
    }

    public void setValueB(double valueB) {
        this.valueB = valueB;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // toString() method
    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Value A: " + valueA + "\n" +
                "Value B: " + valueB + "\n" +
                "Operation: " + operation + "\n" +
                "Result: " + result + "\n" +
                "Timestamp: " + timestamp;
    }
}