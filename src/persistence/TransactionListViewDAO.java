package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionListViewDAO implements TransactionGateway {
    private Connection conn;

    public TransactionListViewDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<TransactionDTO> getAll() throws SQLException {
        List<TransactionDTO> list = new ArrayList<>();
        String sql = "SELECT id, date, unitPrice, area, transactionType, landType, houseType, address FROM transaction";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TransactionDTO dto = new TransactionDTO();
                dto.transactionId = rs.getString("id");
                dto.transactionDate = rs.getObject("date", LocalDate.class);
                dto.unitPrice = rs.getDouble("unitPrice");
                dto.area = rs.getDouble("area");
                dto.transactionType = rs.getString("transactionType");
                dto.landType = rs.getString("landType");
                dto.houseType = rs.getString("houseType");
                dto.address = rs.getString("address");
                list.add(dto);
            }
        }
        return list;
    }

    @Override
    public void updateTransaction(TransactionDTO dto) throws SQLException {
        String sql = "UPDATE transaction SET date = ?, unitPrice = ?, area = ?, transactionType = ?, landType = ?, houseType = ?, address = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, dto.transactionDate);
            stmt.setDouble(2, dto.unitPrice);
            stmt.setDouble(3, dto.area);
            stmt.setString(4, dto.transactionType);
            stmt.setString(5, dto.landType);
            stmt.setString(6, dto.houseType);
            stmt.setString(7, dto.address);
            stmt.setString(8, dto.transactionId);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<TransactionDTO> searchByKeyword(String keyword) throws SQLException {
        List<TransactionDTO> list = new ArrayList<>();
        String sql = "SELECT id, date, unitPrice, area, transactionType, landType, houseType, address FROM transaction WHERE id LIKE ? OR address LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TransactionDTO dto = new TransactionDTO();
                    dto.transactionId = rs.getString("id");
                    dto.transactionDate = rs.getObject("date", LocalDate.class);
                    dto.unitPrice = rs.getDouble("unitPrice");
                    dto.area = rs.getDouble("area");
                    dto.transactionType = rs.getString("transactionType");
                    dto.landType = rs.getString("landType");
                    dto.houseType = rs.getString("houseType");
                    dto.address = rs.getString("address");
                    list.add(dto);
                }
            }
            System.out.println("Search for '" + keyword + "' returned " + list.size() + " records");
        }
        return list;
    }
}