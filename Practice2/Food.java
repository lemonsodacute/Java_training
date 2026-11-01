import java.time.LocalDate;

public class Food extends Goods {
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    private String supplier;

    public Food(String code, String name, int quantity, double unitPrice,
                LocalDate manufactureDate, LocalDate expiryDate, String supplier) {
        super(code, name, quantity, unitPrice);
        if (manufactureDate == null || expiryDate == null || expiryDate.isBefore(manufactureDate)) {
            throw new IllegalArgumentException("Expiry date must be on or after the manufacture date");
        }
        if (supplier == null || supplier.trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier must not be blank");
        }
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.supplier = supplier.trim();
    }

    public LocalDate getManufactureDate() { return manufactureDate; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public String getSupplier() { return supplier; }

    @Override
    public String getType() { return "Food"; }

    @Override
    public double getVatRate() { return 0.05; }

    @Override
    public String evaluate() {
        if (getQuantity() > 0 && expiryDate.isBefore(LocalDate.now())) {
            return "Hard to sell (expired items still in stock)";
        }
        return "Not evaluated";
    }

    @Override
    public String toString() {
        return super.toString() + " | Manufacture date: " + manufactureDate
                + " | Expiry date: " + expiryDate + " | Supplier: " + supplier;
    }
}
