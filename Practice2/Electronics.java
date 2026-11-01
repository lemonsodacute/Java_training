public class Electronics extends Goods {
    private int warrantyMonths;
    private double powerKW;

    public Electronics(String code, String name, int quantity, double unitPrice,
                       int warrantyMonths, double powerKW) {
        super(code, name, quantity, unitPrice);
        if (warrantyMonths < 0 || !Double.isFinite(powerKW) || powerKW < 0) {
            throw new IllegalArgumentException("Warranty and power must be non-negative; power must be finite");
        }
        this.warrantyMonths = warrantyMonths;
        this.powerKW = powerKW;
    }

    public int getWarrantyMonths() { return warrantyMonths; }
    public double getPowerKW() { return powerKW; }

    @Override
    public String getType() { return "Electronics"; }

    @Override
    public double getVatRate() { return 0.10; }

    @Override
    public String evaluate() {
        if (getQuantity() < 3) {
            return "Considered sold (stock < 3)";
        }
        return "Not evaluated";
    }

    @Override
    public String toString() {
        return super.toString() + " | Warranty: " + warrantyMonths
                + " months | Power: " + powerKW + " kW";
    }
}
