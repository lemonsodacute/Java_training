public class Electronics extends Goods {
    private int warrantyMonths;
    private double powerKW;

    public Electronics(String code, String name, int quantity, double unitPrice,
                       int warrantyMonths, double powerKW) {
        super(code, name, quantity, unitPrice);
        if (warrantyMonths < 0 || !Double.isFinite(powerKW) || powerKW < 0) {
            throw new IllegalArgumentException("Bao hanh va cong suat phai >= 0, cong suat phai huu han");
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
            return "Duoc coi la da ban (ton kho < 3)";
        }
        return "Chua danh gia";
    }

    @Override
    public String toString() {
        return super.toString() + " | Bao hanh: " + warrantyMonths
                + " thang | Cong suat: " + powerKW + " kW";
    }
}
