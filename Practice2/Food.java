import java.time.LocalDate;

public class Food extends Goods {
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    private String supplier;

    public Food(String code, String name, int quantity, double unitPrice,
                LocalDate manufactureDate, LocalDate expiryDate, String supplier) {
        super(code, name, quantity, unitPrice);
        if (manufactureDate == null || expiryDate == null || expiryDate.isBefore(manufactureDate)) {
            throw new IllegalArgumentException("Hạn sử dụng phải >= ngày sản xuất");
        }
        if (supplier == null || supplier.trim().isEmpty()) {
            throw new IllegalArgumentException("Nhà cung cấp không được để trống");
        }
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.supplier = supplier.trim();
    }

    public LocalDate getManufactureDate() { return manufactureDate; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public String getSupplier() { return supplier; }

    @Override
    public String getType() { return "Thực phẩm"; }

    @Override
    public double getVatRate() { return 0.05; }

    @Override
    public String evaluate() {
        if (getQuantity() > 0 && expiryDate.isBefore(LocalDate.now())) {
            return "Khó bán (còn hàng nhưng đã hết hạn)";
        }
        return "Chưa đánh giá";
    }

    @Override
    public String toString() {
        return super.toString() + " | Ngày sản xuất: " + manufactureDate
                + " | Hạn sử dụng: " + expiryDate + " | Nhà cung cấp: " + supplier;
    }
}
