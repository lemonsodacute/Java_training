public abstract class Goods {
    private String code;
    private String name;
    private int quantity;
    private double unitPrice;

    public Goods(String code, String name, int quantity, double unitPrice) {
        if (code == null || code.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã hàng và tên hàng không được để trống");
        }
        if (quantity < 0 || !Double.isFinite(unitPrice) || unitPrice < 0) {
            throw new IllegalArgumentException("Số lượng và đơn giá phải >= 0, đơn giá phải hữu hạn");
        }
        this.code = code.trim();
        this.name = name.trim();
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }

    public abstract double getVatRate();
    public abstract String evaluate();
    public abstract String getType();

    public double getVatAmount() {
        return quantity * unitPrice * getVatRate();
    }

    @Override
    public String toString() {
        return code + " | " + name + " | Số lượng: " + quantity
                + " | Đơn giá: " + unitPrice + " | Loại: " + getType();
    }
}
