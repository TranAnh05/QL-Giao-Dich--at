package business;

import java.util.ArrayList;
import java.util.List;

public class TransactionViewModel {
    public List<TransactionViewItem> transactionList = new ArrayList<>();

    public List<TransactionViewItem> getItems() {
        return transactionList;
    }

    public void setItems(List<TransactionViewItem> items) {
        this.transactionList = items;
    }

    public void clearItems() {
        transactionList.clear();
    }

    public void addItem(TransactionViewItem item) {
        transactionList.add(item);
    }
}
