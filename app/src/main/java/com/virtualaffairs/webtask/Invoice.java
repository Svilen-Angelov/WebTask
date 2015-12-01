package com.virtualaffairs.webtask;


public class Invoice {

    private int id;
    private String date;
    private double amount;

    public Invoice(int id,String date, double amount){

        this.id=id;
        this.date=date;
        this.amount=amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }
}
