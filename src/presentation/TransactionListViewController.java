package presentation;

import java.sql.SQLException;
import java.text.ParseException;

import business.TransactionListViewUseCase;
import business.TransactionSearchUseCase;
import business.TransactionUpdateUseCase;
import business.TransactionViewItem;
import business.TransactionViewModel;
import persistence.TransactionDTO;
import business.TransactionObserver;

public class TransactionListViewController {
    private TransactionListViewUI view;
    private TransactionViewModel transactionViewModel;
    private TransactionListViewUseCase listViewUseCase;
    private TransactionSearchUseCase searchUseCase;
    private TransactionUpdateUseCase updateUseCase;

    public TransactionListViewController(TransactionListViewUI view, TransactionViewModel transactionViewModel,
            TransactionListViewUseCase listViewUseCase, TransactionSearchUseCase searchUseCase,
            TransactionUpdateUseCase updateUseCase) {
        this.view = view;
        this.transactionViewModel = transactionViewModel;
        this.listViewUseCase = listViewUseCase;
        this.searchUseCase = searchUseCase;
        this.updateUseCase = updateUseCase;
        view.setController(this);
        listViewUseCase.addObserver((TransactionObserver) view);
        searchUseCase.addObserver((TransactionObserver) view);
        updateUseCase.addObserver((TransactionObserver) view);
    }


    public void execute() {
        try {
            transactionViewModel.transactionList = listViewUseCase.execute();
            listViewUseCase.notifyObservers(transactionViewModel);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void editTransaction(int rowIndex) {
        if (rowIndex >= 0 && transactionViewModel.transactionList != null && rowIndex < transactionViewModel.transactionList.size()) {
            TransactionViewItem item = transactionViewModel.transactionList.get(rowIndex);
            TransactionEditDialog dialog = new TransactionEditDialog(view, item.transactionId);
            dialog.getTxtDate().setText(item.transactionDate);
            dialog.getTxtUnitPrice().setText(item.unitPrice);
            dialog.getTxtArea().setText(item.area);
            dialog.getTxtTransactionType().setText(item.transactionType);
            dialog.getTxtLandType().setText(item.landType != null ? item.landType : "");
            dialog.getTxtHouseType().setText(item.houseType != null ? item.houseType : "");
            dialog.getTxtAddress().setText(item.address != null ? item.address : "");
            dialog.getSaveButton().addActionListener(e -> {
                try {
                    TransactionDTO updatedDto = dialog.getUpdatedDTO();
                    updateUseCase.updateTransaction(item.transactionId, updatedDto);
                    execute(); // Làm mới giao diện
                } catch (SQLException | ParseException ex) {
                    ex.printStackTrace();
                }
            });
            dialog.setVisible(true);
        }
    }

    public void searchTransaction(String keyword) {
        try {
            System.out.println("Searching for: '" + keyword + "'");
            if (keyword == null || keyword.trim().isEmpty()) {
                execute(); // Tải lại toàn bộ danh sách nếu không có từ khóa
            } else {
                transactionViewModel.transactionList = searchUseCase.search(keyword.trim());
                searchUseCase.notifyObservers(transactionViewModel);
                System.out.println("Search completed, results count: " + (transactionViewModel.transactionList != null ? transactionViewModel.transactionList.size() : 0));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
}

