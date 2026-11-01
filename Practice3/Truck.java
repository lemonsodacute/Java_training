public class Truck extends Vehicle {
    private double tonnage;

    public Truck(String number, String manufacturer, int year, String color,
                 Owner owner, double tonnage) {
        super(number, manufacturer, year, color, owner);
        if (!Double.isFinite(tonnage) || tonnage < 0) {
            throw new IllegalArgumentException("Trong tai phai la so huu han >= 0");
        }
        this.tonnage = tonnage;
    }

    public double getTonnage() { return tonnage; }

    @Override
    public String getType() { return "Truck"; }

    @Override
    public String getExtraInfo() { return "Trong tai: " + tonnage + " tan"; }
}
