package business;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.text.ParseException;

import persistence.TransactionListViewDAO;
import persistence.TransactionDTO;

public class TransactionListViewUseCase extends TransactionSubject {
    private TransactionListViewDAO listViewDAO;

    public TransactionListViewUseCase(TransactionListViewDAO listViewDAO) {
        this.listViewDAO = listViewDAO;
    }

    public List<TransactionViewItem> execute() throws SQLException, ParseException {
        List<TransactionDTO> listDTO = listViewDAO.getAll();
        List<Transaction> transactions = convertToBusinessObjects(listDTO);
        return convertToTransactionViewItem(transactions);
    }

    private List<Transaction> convertToBusinessObjects(List<TransactionDTO> listDTO) {
        List<Transaction> transactions = new ArrayList<>();
        for (TransactionDTO dto : listDTO) {
            if ("GDĐ".equalsIgnoreCase(dto.transactionType)) {
                transactions.add(new LandTransaction(
                    dto.transactionId, dto.transactionDate,
                    dto.unitPrice != null ? dto.unitPrice : 0,
                    dto.area != null ? dto.area : 0,
                    dto.landType
                ));
            } else if ("GDN".equalsIgnoreCase(dto.transactionType)) {
                transactions.add(new HouseTransaction(
                    dto.transactionId, dto.transactionDate,
                    dto.unitPrice != null ? dto.unitPrice : 0,
                    dto.area != null ? dto.area : 0,
                    dto.houseType,
                    dto.address
                ));
            }
        }
        return transactions;
    }

    private List<TransactionViewItem> convertToTransactionViewItem(List<Transaction> transactions) {
        List<TransactionViewItem> itemList = new ArrayList<>();
        int stt = 1;
        for (Transaction transaction : transactions) {
            TransactionViewItem item = new TransactionViewItem();
            item.stt = stt++;
            item.transactionId = transaction.getTransactionId();
            item.transactionDate = transaction.getTransactionDate().toString();
            item.unitPrice = String.valueOf(transaction.getUnitPrice());
            item.area = String.valueOf(transaction.getArea());
            item.transactionType = transaction.getTransactionType();
            item.amountTotal = String.valueOf(transaction.calculateAmount());
            item.landType = transaction instanceof LandTransaction ? ((LandTransaction) transaction).getLandType() : null;
            item.houseType = transaction instanceof HouseTransaction ? ((HouseTransaction) transaction).getHouseType() : null;
            item.address = transaction instanceof HouseTransaction ? ((HouseTransaction) transaction).getAddress() : null;
            itemList.add(item);
        }
        return itemList;
    }
}