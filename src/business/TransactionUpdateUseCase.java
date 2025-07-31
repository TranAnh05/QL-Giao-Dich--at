package business;

import java.sql.SQLException;
import java.text.ParseException;

import persistence.TransactionListViewDAO;
import persistence.TransactionDTO;

public class TransactionUpdateUseCase extends TransactionSubject {
    private TransactionListViewDAO dao;

    public TransactionUpdateUseCase(TransactionListViewDAO dao) {
        this.dao = dao;
    }

    public void updateTransaction(String transactionId, TransactionDTO updatedDto) throws SQLException, ParseException {
        updatedDto.transactionId = transactionId;
        dao.updateTransaction(updatedDto);
        notifyObservers();
    }
}