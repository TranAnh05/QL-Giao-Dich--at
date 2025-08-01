package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import business.TransactionListViewUseCase;
import business.TransactionViewItem;
import business.TransactionViewModel;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class TotalTransactionUI extends JFrame implements TotalTransactionViewUI
{
    private JRadioButton landButton, houseButton;
    private JTable transactionTable;
    private DefaultTableModel model;
    private JTextField totalField;

    public TotalTransactionUI() {
        setTitle("Transaction Total Viewer");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel trên: Radio buttons
        JPanel topPanel = new JPanel();
        landButton = new JRadioButton("TransactionLand");
        houseButton = new JRadioButton("TransactionHouse");

        ButtonGroup group = new ButtonGroup();
        group.add(landButton);
        group.add(houseButton);

        topPanel.add(landButton);
        topPanel.add(houseButton);
        add(topPanel, BorderLayout.NORTH);

        // Cấu trúc cột cho bảng
        String[] cols = {"STT", "Mã GD", "Loại giao dịch", "Ngày giao dịch", "Đơn giá", "Diện tích", "Thành tiền"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa cell
            }
        };

        transactionTable = new JTable(model);
        add(new JScrollPane(transactionTable), BorderLayout.CENTER);

        // Panel dưới: Hiển thị tổng
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(new JLabel("Total:"));
        totalField = new JTextField(10);
        totalField.setEditable(false);
        bottomPanel.add(totalField);
        add(bottomPanel, BorderLayout.SOUTH);

        // Sự kiện chọn loại giao dịch
        landButton.addActionListener(e -> showLandTransactions());
        houseButton.addActionListener(e -> showHouseTransactions());

        setVisible(true);
    }
    @Override
    public void showList(TransactionViewModel viewModel) 
    {
        model.setRowCount(0);
        int count = 0;

        for (TransactionViewItem item : viewModel.transactionList) 
        {
            Vector<Object> row = new Vector<>();
            row.add(item.stt);
            row.add(item.transactionId);
            row.add(item.transactionType);
            row.add(item.transactionDate);
            row.add(item.unitPrice);
            row.add(item.area);
            row.add(item.amountTotal);

            model.addRow(row);
            count++;
        }
        totalField.setText(String.valueOf(count));
    }

    private void showLandTransactions() 
    {
        try 
        {
            TransactionViewModel viewModel = new TransactionViewModel();
            TransactionListViewUseCase useCase = new TransactionListViewUseCase(new persistence.TransactionListViewDAO());
            TotalTransactionController controller = new TotalTransactionController(this, viewModel, useCase);
            controller.loadTransactionsByType("GDĐ"); // phân loại đúng giao dịch đất
        } catch (Exception ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải giao dịch đất: " + ex.getMessage());
        }
    }


    private void showHouseTransactions() 
    {
        try 
        {
            TransactionViewModel viewModel = new TransactionViewModel();
            TransactionListViewUseCase useCase = new TransactionListViewUseCase(new persistence.TransactionListViewDAO());
            TotalTransactionController controller = new TotalTransactionController(this, viewModel, useCase);

            controller.loadTransactionsByType("GDN");
        } catch (Exception ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải giao dịch nhà: " + ex.getMessage());
        }
    }


    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> new TotalTransactionUI());
    }
}
