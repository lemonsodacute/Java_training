import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Crockery extends Goods {
    private String manufacturer;
    private LocalDate arrivalDate;

    public Crockery(String code, String name, int quantity, double unitPrice,
                    String manufacturer, LocalDate arrivalDate) {
        super(code, name, quantity, unitPrice);
        if (manufacturer == null || manufacturer.trim().isEmpty() || arrivalDate == null) {
            throw new IllegalArgumentException("Manufacturer and arrival date are required");
        }
        this.manufacturer = manufacturer.trim();
        this.arrivalDate = arrivalDate;
    }

    public String getManufacturer() { return manufacturer; }
    public LocalDate getArrivalDate() { return arrivalDate; }

    @Override
    public String getType() { return "Crockery"; }

    @Override
    public double getVatRate() { return 0.10; }

    @Override
    public String evaluate() {
        long days = ChronoUnit.DAYS.between(arrivalDate, LocalDate.now());
        if (getQuantity() > 50 && days > 10) {
            return "Slow selling (stock > 50 and stored for more than 10 days)";
        }
        return "Not evaluated";
    }

    @Override
    public String toString() {
        return super.toString() + " | Manufacturer: " + manufacturer + " | Arrival date: " + arrivalDate;
    }
}
