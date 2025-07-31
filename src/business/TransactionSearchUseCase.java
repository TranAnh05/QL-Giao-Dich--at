package business;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import persistence.TransactionListViewDAO;
import persistence.TransactionDTO;

public class TransactionSearchUseCase extends TransactionSubject {
    private TransactionListViewDAO dao;

    public TransactionSearchUseCase(TransactionListViewDAO dao) {
        this.dao = dao;
    }

    public List<TransactionViewItem> search(String keyword) throws SQLException, ParseException {
        List<TransactionDTO> dtos = dao.searchByKeyword(keyword);
        return convertToTransactionViewItem(dtos);
    }

    private List<TransactionViewItem> convertToTransactionViewItem(List<TransactionDTO> dtos) {
        List<TransactionViewItem> items = new ArrayList<>();
        int stt = 1;
        for (TransactionDTO dto : dtos) {
            TransactionViewItem item = new TransactionViewItem();
            item.stt = stt++;
            item.transactionId = dto.transactionId;
            item.transactionDate = dto.transactionDate.toString();
            item.unitPrice = String.valueOf(dto.unitPrice);
            item.area = String.valueOf(dto.area);
            item.transactionType = dto.transactionType;
            item.amountTotal = String.valueOf(
                "GDN".equalsIgnoreCase(dto.transactionType) && dto.houseType != null ?
                new HouseTransaction(dto.transactionId, dto.transactionDate, dto.unitPrice, dto.area, dto.houseType, dto.address).calculateAmount() :
                "GDĐ".equalsIgnoreCase(dto.transactionType) && dto.landType != null ?
                new LandTransaction(dto.transactionId, dto.transactionDate, dto.unitPrice, dto.area, dto.landType).calculateAmount() :
                dto.unitPrice * dto.area
            );
            item.landType = dto.landType;
            item.houseType = dto.houseType;
            item.address = dto.address;
            items.add(item);
        }
        return items;
    }
}