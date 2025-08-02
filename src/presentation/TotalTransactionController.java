package presentation;

import business.TotalTransactionUseCase;
import business.TransactionListViewUseCase;
import business.TransactionViewModel;

public class TotalTransactionController {
    private final TotalTransactionViewUI view;
    private final TransactionViewModel viewModel;
    private final TotalTransactionUseCase useCase;

    public TotalTransactionController(TotalTransactionViewUI view, TransactionViewModel viewModel, TotalTransactionUseCase useCase) 
    {
        this.view = view;
        this.viewModel = viewModel;
        this.useCase = useCase;
    }
    

    public void loadTransactionsByType(String type) 
    {
        try 
        {
            // Gọi UseCase để lấy danh sách giao dịch theo loại
            viewModel.transactionList = useCase.getTransactionsByType(type);
            // Gọi UseCase để đếm số lượng giao dịch theo loại
            int count = useCase.countTransactionsByType(type);
            // Gọi View để hiển thị dữ liệu
            view.showList(viewModel, count);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
