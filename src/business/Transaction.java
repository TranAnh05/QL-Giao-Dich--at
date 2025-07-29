package business;

import java.time.LocalDate;

public abstract class Transaction {
    protected String transactionId;
    protected LocalDate transactionDate;
    protected double unitPrice;
    protected double area;
    protected String transactionType;

    public Transaction(String transactionId, LocalDate transactionDate, double unitPrice, double area,
            String transactionType) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.unitPrice = unitPrice;
        this.area = area;
        this.transactionType = transactionType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public abstract double calculateAmount();
}