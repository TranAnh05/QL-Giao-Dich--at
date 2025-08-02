import java.sql.SQLException;
import java.text.ParseException;

import business.TransactionListViewUseCase;
import business.TransactionViewModel;
import persistence.TransactionListViewDAO;
import presentation.TransactionListViewController;
import presentation.TransactionListViewUI;

public class AppTransaction 
{
    public static void main(String[] args) 
    {
        TransactionListViewUI view = new TransactionListViewUI();
        TransactionViewModel model = null;
        TransactionListViewController controller = null;
        TransactionListViewUseCase listViewUseCase = null;

        try {
            TransactionListViewDAO transactionListViewDAO = new TransactionListViewDAO();
            listViewUseCase = new TransactionListViewUseCase(transactionListViewDAO);
            controller = new TransactionListViewController(view, model, listViewUseCase);

            controller.execute();
            view.setVisible(true);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
