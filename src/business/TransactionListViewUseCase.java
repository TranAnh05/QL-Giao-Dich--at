package business;

import java.util.List;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import persistence.TransactionListViewDAO;
import persistence.TransactionDTO;

public class TransactionListViewUseCase {
    private TransactionListViewDAO listViewDAO;

    public TransactionListViewUseCase(TransactionListViewDAO listViewDAO) {
        // super();
        this.listViewDAO = listViewDAO;
    }

    public List<TransactionViewItem> execute() throws SQLException, ParseException{
        List<TransactionDTO> listDTO = null;
        List<Transaction> transactions  = null;
        
        listDTO = listViewDAO.getAll();

        transactions = convertToBusinessObjects(listDTO);

        return convertToTransactionViewItem(transactions);
    }

    private List<Transaction> convertToBusinessObjects(List<TransactionDTO> listDTO) {
        List<Transaction> transactions = new ArrayList<>();

        for(TransactionDTO dto : listDTO) {
            if("GDĐ".equalsIgnoreCase(dto.transactionType)) {
                transactions.add(new LandTransaction(
                    dto.transactionId, dto.transactionDate,
                    dto.unitPrice != null ? dto.unitPrice : 0,
                    dto.area != null ? dto.area : 0,
                    dto.landType
                ));
            }
            else if("GDN".equalsIgnoreCase(dto.transactionType)) {
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
        List<TransactionViewItem> itemList = new ArrayList<TransactionViewItem>();

        int stt = 1;
        for(Transaction transaction : transactions) {
            TransactionViewItem item = new TransactionViewItem();

            item.stt = stt++;
            item.transactionId = transaction.getTransactionId();
            item.transactionDate = transaction.getTransactionDate().toString();
            item.unitPrice = String.valueOf(transaction.getUnitPrice());
            item.area = String.valueOf(transaction.getArea());
            item.transactionType = transaction.getTransactionType();
            item.amountTotal = String.valueOf(transaction.calculateAmount());
            itemList.add(item);
        }

        return itemList;
    }
}
