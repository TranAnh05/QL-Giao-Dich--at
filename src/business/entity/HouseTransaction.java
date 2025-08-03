package business.entity;

import java.time.LocalDate;

public class HouseTransaction extends Transaction {
    private String houseType;
    private String address;

    public HouseTransaction(String transactionId, LocalDate transactionDate, double unitPrice, double area,
            String houseType, String address) {
        super(transactionId, transactionDate, unitPrice, area, "GDN");
        // Gán giá trị mặc định nếu houseType là null hoặc rỗng
        this.houseType = (houseType == null || houseType.trim().isEmpty()) ? "normal" : houseType.trim();
        this.address = address;
    }

    public String getHouseType() { return houseType; }

    public String getAddress() { return address; }

    @Override
    public double calculateAmount() {
        double baseAmount = getArea() * getUnitPrice();
        String normalizedHouseType = houseType.toLowerCase(); // Đã chuẩn hóa trong constructor
        switch (normalizedHouseType) {
            case "luxury":
                return baseAmount;
            case "normal":
                return baseAmount * 0.9;
            default:
                throw new IllegalArgumentException("Loại nhà không hợp lệ: '" + houseType + "'. Chỉ chấp nhận 'luxury' hoặc 'normal'.");
        }
    }
}