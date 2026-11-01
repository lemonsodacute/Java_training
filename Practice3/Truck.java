public class Truck extends Vehicle {
    private double tonnage;

    public Truck(String number, String manufacturer, int year, String color,
                 Owner owner, double tonnage) {
        super(number, manufacturer, year, color, owner);
        if (!Double.isFinite(tonnage) || tonnage < 0) {
            throw new IllegalArgumentException("Trọng tải phải là số hữu hạn >= 0");
        }
        this.tonnage = tonnage;
    }

    public double getTonnage() { return tonnage; }

    @Override
    public String getType() { return "Xe tải"; }

    @Override
    public String getExtraInfo() { return "Trọng tải: " + tonnage + " tấn"; }
}
