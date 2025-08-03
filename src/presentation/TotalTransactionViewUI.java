package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import business.TotalTransactionGateway;
import business.TotalTransactionUseCase;
import business.TransactionViewItem;
import business.TransactionViewModel;
import persistence.TransactionListViewDAO;
import persistence.DBConnection;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class TotalTransactionViewUI extends JFrame {
    private JRadioButton landButton, houseButton;
    private JTable transactionTable;
    private DefaultTableModel model;
    private JTextField totalField;
    private TotalTransactionUseCase totalUseCase;
    private TotalTransactionController controller;

    public TotalTransactionViewUI(TotalTransactionUseCase totalUseCase) {
        this.totalUseCase = totalUseCase;
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

        // Khởi tạo controller với view và useCase
        TransactionViewModel viewModel = new TransactionViewModel();
        this.controller = new TotalTransactionController(this, viewModel, totalUseCase);

        // Sự kiện chọn loại giao dịch
        landButton.addActionListener(e -> controller.loadTransactionsByType("GDĐ"));
        houseButton.addActionListener(e -> controller.loadTransactionsByType("GDN"));

        setVisible(true);
    }

    public void showList(TransactionViewModel viewModel, int total) {
        model.setRowCount(0);

        if (viewModel != null && viewModel.transactionList != null) {
            for (TransactionViewItem item : viewModel.transactionList) {
                Vector<Object> row = new Vector<>();
                row.add(item.stt);
                row.add(item.transactionId);
                row.add(item.transactionType);
                row.add(item.transactionDate);
                row.add(item.unitPrice);
                row.add(item.area);
                row.add(item.amountTotal);

                model.addRow(row);
            }
        }
        totalField.setText(String.valueOf(total));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DBConnection dbConn = new DBConnection();
                TotalTransactionGateway gateway = new TransactionListViewDAO(dbConn.getConnection());
                TotalTransactionUseCase useCase = new TotalTransactionUseCase(gateway);
                new TotalTransactionViewUI(useCase);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}