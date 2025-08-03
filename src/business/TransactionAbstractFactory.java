package business;

import java.time.LocalDate;

import business.entity.HouseTransaction;
import business.entity.LandTransaction;
import business.entity.Transaction;

public class TransactionAbstractFactory implements TransactionFactory {
    @Override
    public Transaction createTransaction(String transactionId, LocalDate transactionDate, double unitPrice, double area,
            String type, String subType, String address) {
        if ("GDĐ".equalsIgnoreCase(type)) {
            return new LandTransaction(transactionId, transactionDate, unitPrice, area, subType);
        } else if ("GDN".equalsIgnoreCase(type)) {
            return new HouseTransaction(transactionId, transactionDate, unitPrice, area, subType, address);
        }
        throw new IllegalArgumentException("Loại giao dịch không hợp lệ: " + type);
    }
}