package presentation;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import persistence.TransactionDTO;

public class TransactionEditDialog extends JDialog {
    private JTextField txtTransactionId;
    private JTextField txtDate;
    private JTextField txtUnitPrice;
    private JTextField txtArea;
    private JTextField txtTransactionType;
    private JTextField txtLandType;
    private JTextField txtHouseType;
    private JTextField txtAddress;
    private JButton btnSave;

    public TransactionEditDialog(Frame parent, String transactionId) {
        super(parent, "Chỉnh sửa giao dịch", true);
        setLayout(new GridLayout(9, 2));
        add(new JLabel("Mã giao dịch:"));
        txtTransactionId = new JTextField(transactionId);
        txtTransactionId.setEditable(false);
        add(txtTransactionId);
        add(new JLabel("Ngày:"));
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

        pack();
        setLocationRelativeTo(parent);
    }

    public JTextField getTxtTransactionId() { return txtTransactionId; }
    public JTextField getTxtDate() { return txtDate; }
    public JTextField getTxtUnitPrice() { return txtUnitPrice; }
    public JTextField getTxtArea() { return txtArea; }
    public JTextField getTxtTransactionType() { return txtTransactionType; }
    public JTextField getTxtLandType() { return txtLandType; }
    public JTextField getTxtHouseType() { return txtHouseType; }
    public JTextField getTxtAddress() { return txtAddress; }

    public TransactionDTO getUpdatedDTO() {
        TransactionDTO dto = new TransactionDTO();
        dto.transactionId = txtTransactionId.getText();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dto.transactionDate = LocalDate.parse(txtDate.getText(), formatter);
        } catch (Exception e) {
            dto.transactionDate = null;
        }
        try {
            dto.unitPrice = Double.parseDouble(txtUnitPrice.getText());
        } catch (Exception e) {
            dto.unitPrice = null;
        }
        try {
            dto.area = Double.parseDouble(txtArea.getText());
        } catch (Exception e) {
            dto.area = null;
        }
        dto.transactionType = txtTransactionType.getText();
        dto.landType = txtLandType.getText();
        dto.houseType = txtHouseType.getText();
        dto.address = txtAddress.getText();
        return dto;
    }

    public JButton getSaveButton() {
        return btnSave;
    }
}