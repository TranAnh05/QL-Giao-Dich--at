package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import business.TransactionViewItem;
import business.TransactionViewModel;

import java.awt.*;

public class TransactionListViewUI extends JFrame {
    private JTextField txtSearch;
    private JButton btnAdd;
    private JButton btnEdit; // Nút sửa
    private JButton btnDelete; // Nút xóa
    private JTable table;
    private DefaultTableModel model;

    public TransactionListViewUI() {
        // --- Cài đặt cho JFrame ---
        super("Quản lý danh sách giao dịch");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600); 
        setLocationRelativeTo(null); 

        // --- Panel Top: Chứa các nút chức năng và ô tìm kiếm ---
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));

        // Ô tìm kiếm
        txtSearch = new JTextField();

        // Panel cho các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAdd = new JButton("Thêm");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        // Thêm các thành phần vào topPanel
        topPanel.add(txtSearch, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        // Thêm topPanel vào JFrame
        add(topPanel, BorderLayout.NORTH);
        String[] cols = {"STT", "Mã GD", "Loại giao dịch", "Ngày giao dịch", "Đơn giá", "Diện tích", "Thành tiền"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setFillsViewportHeight(true); 
        table.setRowHeight(25);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

     
    public void showList(TransactionViewModel transactionViewModel) {
        model.setRowCount(0);

        for (TransactionViewItem item : transactionViewModel.transactionList) {
            Object[] row = {
                    item.stt,
                    item.transactionId,
                    item.transactionType,
                    item.transactionDate,
                    item.unitPrice,
                    item.area,
                    item.amountTotal
            };
            model.addRow(row);
        }
    }
}
