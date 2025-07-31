import business.TransactionListViewUseCase;
import business.TransactionSearchUseCase;
import business.TransactionUpdateUseCase;
import business.TransactionViewModel;
import persistence.TransactionListViewDAO;
// import persistence.TransactionUpdateDAO;
import presentation.TransactionListViewController;
import presentation.TransactionListViewUI;

public class AppTransaction {
    public static void main(String[] args) {
        TransactionListViewUI view = new TransactionListViewUI();
        TransactionViewModel model = new TransactionViewModel();
        TransactionListViewController controller = null;
        TransactionListViewUseCase listViewUseCase = null;
        TransactionSearchUseCase searchUseCase = null;
        TransactionUpdateUseCase updateUseCase = null;

        try {
            TransactionListViewDAO listDao = new TransactionListViewDAO();
            // TransactionUpdateDAO updateDao = new TransactionUpdateDAO(listDao); // Truyền listDao
            listViewUseCase = new TransactionListViewUseCase(listDao);
            searchUseCase = new TransactionSearchUseCase(listDao);
            updateUseCase = new TransactionUpdateUseCase(listDao);
            controller = new TransactionListViewController(view, model, listViewUseCase, searchUseCase, updateUseCase);

            controller.execute();
            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}