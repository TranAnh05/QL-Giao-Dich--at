package business;

import java.time.LocalDate;

public abstract class Transaction {
    protected String transactionId;
    protected LocalDate transactionDate;
    protected double unitPrice;
    protected double area;

    public Transaction(String transactionId, LocalDate transactionDate, double unitPrice, double area) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.unitPrice = unitPrice;
        this.area = area;
    }

    // Getters
    public String getTransactionId() {
        return transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getArea() {
        return area;
    }

    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setArea(double area) {
        this.area = area;
    }

    // Phương thức trừu tượng để tính thành tiền, mỗi lớp con sẽ tự định nghĩa
    public abstract double calculateAmount();
}