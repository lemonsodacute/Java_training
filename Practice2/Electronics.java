public class Electronics extends Goods {
    private int warrantyMonths;
    private double powerKW;

    public Electronics(String code, String name, int quantity, double unitPrice,
                       int warrantyMonths, double powerKW) {
        super(code, name, quantity, unitPrice);
        if (warrantyMonths < 0 || !Double.isFinite(powerKW) || powerKW < 0) {
            throw new IllegalArgumentException("Bảo hành và công suất phải >= 0, công suất phải hữu hạn");
        }
        this.warrantyMonths = warrantyMonths;
        this.powerKW = powerKW;
    }

    public int getWarrantyMonths() { return warrantyMonths; }
    public double getPowerKW() { return powerKW; }

    @Override
    public String getType() { return "Điện máy"; }

    @Override
    public double getVatRate() { return 0.10; }

    @Override
    public String evaluate() {
        if (getQuantity() < 3) {
            return "Được coi là đã bán (tồn kho < 3)";
        }
        return "Chưa đánh giá";
    }

    @Override
    public String toString() {
        return super.toString() + " | Bảo hành: " + warrantyMonths
                + " tháng | Công suất: " + powerKW + " kW";
    }
}
