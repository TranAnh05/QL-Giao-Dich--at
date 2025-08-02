package business;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import business.entity.HouseTransaction;
import business.entity.LandTransaction;
import business.entity.Transaction;
import persistence.TransactionDTO;
import persistence.TransactionListViewDAO;

public class TotalTransactionUseCase
{
    private TotalTransactionGateway gateway;

    public TotalTransactionUseCase(TotalTransactionGateway gateway) 
    {
        this.gateway = gateway;
    }

    public List<TransactionViewItem> execute() throws SQLException, ParseException 
    {
        List<TransactionDTO> listDTO = gateway.getAll();
        List<Transaction> transactions = convertToBusinessObjects(listDTO);
        return convertToTransactionViewItem(transactions);
    }

    public List<TransactionViewItem> getTransactionsByType(String type) throws SQLException 
    {
        List<TransactionDTO> listDTO = gateway.getTransactionsByType(type);
        List<Transaction> transactions = convertToBusinessObjects(listDTO);
        return convertToTransactionViewItem(transactions);
    }
    public int countTransactionsByType(String type) throws SQLException 
    {
        List<TransactionDTO> listDTO = gateway.getTransactionsByType(type);
        return listDTO.size();
    }

    private List<Transaction> convertToBusinessObjects(List<TransactionDTO> listDTO) 
    {
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

    private List<TransactionViewItem> convertToTransactionViewItem(List<Transaction> transactions) 
    {
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
