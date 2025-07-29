package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class TransactionListViewDAO {
    private Connection conn;

    public TransactionListViewDAO() throws SQLException, ClassNotFoundException {
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        String username = "root";
        String password = "130405";
        String url = "jdbc:mysql://localhost:3306/transaction?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        conn = DriverManager.getConnection(url, username, password);
    }

    public List<TransactionDTO> getAll() {
        List<TransactionDTO> list = new ArrayList<TransactionDTO>();
        Statement stmt = null;
		ResultSet rs = null;

        

        return list;
    }
}
