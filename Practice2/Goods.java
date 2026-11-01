// Lop cha cho 3 loai hang hoa
public abstract class Goods {
    private String code;
    private String name;
    private int quantity;
    private double unitPrice;

    public Goods(String code, String name, int quantity, double unitPrice) {
        if (code == null || code.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma hang va ten hang khong duoc de trong");
        }
        if (quantity < 0 || !Double.isFinite(unitPrice) || unitPrice < 0) {
            throw new IllegalArgumentException("So luong va don gia phai >= 0, don gia phai huu han");
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

    // Lop con se tu cai dat muc VAT va cach danh gia
    public abstract double getVatRate();
    public abstract String evaluate();
    public abstract String getType();

    public double getVatAmount() {
        return quantity * unitPrice * getVatRate();
    }

    @Override
    public String toString() {
        return code + " | " + name + " | SL: " + quantity
                + " | Don gia: " + unitPrice + " | Loai: " + getType();
    }
}
