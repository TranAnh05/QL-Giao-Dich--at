package business;

import java.time.LocalDate;

public class HouseTransaction extends Transaction {
    private String houseType; 
    private String address;

    
   
    public HouseTransaction(String transactionId, LocalDate transactionDate, double unitPrice, double area,
            String transactionType, String houseType, String address) {
        super(transactionId, transactionDate, unitPrice, area, transactionType);
        this.houseType = houseType;
        this.address = address;
    }

    public String getHouseType() {
        return houseType;
    }

    public String getAddress() {
        return address;
    }


    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public double calculateAmount() {
        if ("cao cấp".equalsIgnoreCase(houseType) || "cao cap".equalsIgnoreCase(houseType)) { 
            return area * unitPrice;
        } else if ("thường".equalsIgnoreCase(houseType) || "thuong".equalsIgnoreCase(houseType)) { 
            return area * unitPrice * 0.9; 
        } else {
            throw new IllegalArgumentException("Loại nhà không hợp lệ: " + houseType);
        }
    }
}
