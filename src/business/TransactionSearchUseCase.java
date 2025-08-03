package business;

import java.text.DecimalFormat;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import persistence.TransactionListViewDAO;
import persistence.TransactionDTO;

public class TransactionSearchUseCase extends TransactionSubject {
    private TransactionListViewDAO dao;
    private TransactionFactory factory;

    public TransactionSearchUseCase(TransactionListViewDAO dao, TransactionFactory factory) {
        this.dao = dao;
        this.factory = factory;
    }

    public List<TransactionViewItem> search(String keyword) throws SQLException, ParseException {
        List<TransactionDTO> dtos = dao.searchByKeyword(keyword);
        List<TransactionViewItem> result = convertToTransactionViewItem(dtos);
        notifyObservers(result); // Thông báo cho Subscriber
        return result;
    }

    private List<TransactionViewItem> convertToTransactionViewItem(List<TransactionDTO> dtos) {
        List<TransactionViewItem> items = new ArrayList<>();
        int stt = 1;
        DecimalFormat df = new DecimalFormat("#,###.##");
        for (TransactionDTO dto : dtos) {
            TransactionViewItem item = new TransactionViewItem();
            item.stt = stt++;
            item.transactionId = dto.transactionId;
            item.transactionDate = dto.transactionDate.toString();
            item.unitPrice = String.valueOf(dto.unitPrice);
            item.area = String.valueOf(dto.area);
            item.transactionType = dto.transactionType;
            item.landType = dto.landType;
            item.houseType = dto.houseType;
            item.address = dto.address;

            Transaction transaction = factory.createTransaction(
                dto.transactionId, dto.transactionDate,
                dto.unitPrice != null ? dto.unitPrice : 0,
                dto.area != null ? dto.area : 0,
                dto.transactionType,
                dto.transactionType.equals("GDĐ") ? dto.landType : dto.houseType,
                dto.address
            );
            item.amountTotal = df.format(transaction.calculateAmount());
            items.add(item);
        }
        return items;
    }
}