package business;

import java.time.LocalDate;

public interface TransactionFactory {
    Transaction createTransaction(String transactionId, LocalDate transactionDate, double unitPrice, double area, String type, String subType, String address);
}