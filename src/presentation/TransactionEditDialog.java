package presentation;

import javax.swing.*;

import persistence.TransactionDTO;

import java.awt.*;
import java.time.LocalDate;

public class TransactionEditDialog extends JDialog {
    private JTextField txtDate, txtUnitPrice, txtArea, txtTransactionType, txtLandType, txtHouseType, txtAddress;
    private JButton btnSave;

    public TransactionEditDialog(JFrame parent, String transactionId) {
        super(parent, "Sửa giao dịch: " + transactionId, true);
        setLayout(new GridLayout(8, 2, 5, 5));
        setSize(400, 300);
        setLocationRelativeTo(parent);

        add(new JLabel("Ngày giao dịch:"));
        txtDate = new JTextField();
        add(txtDate);

        add(new JLabel("Đơn giá:"));
        txtUnitPrice = new JTextField();
        add(txtUnitPrice);

        add(new JLabel("Diện tích:"));
        txtArea = new JTextField();
        add(txtArea);

        add(new JLabel("Loại giao dịch:"));
        txtTransactionType = new JTextField();
        add(txtTransactionType);

        add(new JLabel("Loại đất:"));
        txtLandType = new JTextField();
        add(txtLandType);

        add(new JLabel("Loại nhà:"));
        txtHouseType = new JTextField();
        add(txtHouseType);

        add(new JLabel("Địa chỉ:"));
        txtAddress = new JTextField();
        add(txtAddress);

        btnSave = new JButton("Lưu");
        add(btnSave);
        add(new JLabel());

        getRootPane().setDefaultButton(btnSave);
    }

    public JTextField getTxtDate() { return txtDate; }
    public JTextField getTxtUnitPrice() { return txtUnitPrice; }
    public JTextField getTxtArea() { return txtArea; }
    public JTextField getTxtTransactionType() { return txtTransactionType; }
    public JTextField getTxtLandType() { return txtLandType; }
    public JTextField getTxtHouseType() { return txtHouseType; }
    public JTextField getTxtAddress() { return txtAddress; }
    public JButton getSaveButton() { return btnSave; }

    public TransactionDTO getUpdatedDTO() throws NumberFormatException {
        TransactionDTO dto = new TransactionDTO();
        try {
            dto.transactionDate = LocalDate.parse(getTxtDate().getText());
            dto.unitPrice = Double.parseDouble(getTxtUnitPrice().getText());
            dto.area = Double.parseDouble(getTxtArea().getText());
            dto.transactionType = getTxtTransactionType().getText();
            dto.landType = getTxtLandType().getText();
            dto.houseType = getTxtHouseType().getText();
            dto.address = getTxtAddress().getText();
        } catch (Exception e) {
            throw new NumberFormatException("Dữ liệu nhập không hợp lệ: " + e.getMessage());
        }
        return dto;
    }
}