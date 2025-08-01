package presentation;

import business.TransactionListViewUseCase;
import business.TransactionViewModel;

public class TotalTransactionController {
    private final TotalTransactionViewUI view;
    private final TransactionViewModel viewModel;
    private final TransactionListViewUseCase useCase;

    public TotalTransactionController(TotalTransactionViewUI view, TransactionViewModel viewModel, TransactionListViewUseCase useCase) 
    {
        this.view = view;
        this.viewModel = viewModel;
        this.useCase = useCase;
    }

    public void loadTransactionsByType(String type) 
    {
        try {
            // Gọi UseCase để lấy danh sách giao dịch theo loại
            viewModel.transactionList = useCase.getTransactionsByType(type);

            // Gọi View để hiển thị dữ liệu
            view.showList(viewModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
