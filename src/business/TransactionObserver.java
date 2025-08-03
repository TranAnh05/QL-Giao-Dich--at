package business;

public interface TransactionObserver {
    void onTransactionUpdated(Object data); // Phương thức nhận thông báo khi giao dịch được cập nhật
}