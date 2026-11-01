public abstract class Goods {
    private String code;
    private String name;
    private int quantity;
    private double unitPrice;

    public Goods(String code, String name, int quantity, double unitPrice) {
        if (code == null || code.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Code and name must not be blank");
        }
        if (quantity < 0 || !Double.isFinite(unitPrice) || unitPrice < 0) {
            throw new IllegalArgumentException("Quantity and unit price must be non-negative; unit price must be finite");
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
        return code + " | " + name + " | Quantity: " + quantity
                + " | Unit price: " + unitPrice + " | Type: " + getType();
    }
}
