package presentation;

import java.sql.SQLException;
import java.text.ParseException;

import business.TransactionListViewUseCase;
import business.TransactionViewModel;

public class TransactionListViewController {
    private TransactionListViewUI  view;
    private TransactionViewModel transactionViewModel;
    private TransactionListViewUseCase usecase;

    public TransactionListViewController(TransactionListViewUI view, TransactionViewModel transactionViewModel,
            TransactionListViewUseCase usecase) {
        this.view = view;
        this.transactionViewModel = transactionViewModel;
        this.usecase = usecase;
    }

    public void execute() throws SQLException, ParseException 
    {
        transactionViewModel.transactionList = usecase.execute();

        view.showList(transactionViewModel);
    }
}
