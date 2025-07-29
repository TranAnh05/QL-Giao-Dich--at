package business;

import java.time.LocalDate;

public class LandTransaction extends Transaction {
    private String landType;

    public LandTransaction(String transactionId, LocalDate transactionDate, double unitPrice, double area,
            String transactionType, String landType) {
        super(transactionId, transactionDate, unitPrice, area, transactionType);
        this.landType = landType;
    }


    public String getLandType() {
        return landType;
    }

    
    public void setLandType(String landType) {
        this.landType = landType;
    }

    @Override
    public double calculateAmount() {
        if ("A".equalsIgnoreCase(landType)) {
            return area * unitPrice * 1.5;
        } else if ("B".equalsIgnoreCase(landType) || "C".equalsIgnoreCase(landType)) {
            return area * unitPrice;
        } else {
           throw new IllegalArgumentException("Loại đất không hợp lệ: " + landType);
        }
    }
}
