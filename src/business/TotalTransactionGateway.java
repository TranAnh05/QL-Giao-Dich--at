package business;

import java.sql.SQLException;
import java.util.List;

import persistence.TransactionDTO;

public interface TotalTransactionGateway {
    List<TransactionDTO> getAll() throws SQLException;

    List<TransactionDTO> getTransactionsByType(String type) throws SQLException;
}