import java.time.Year;
import java.util.Locale;

public abstract class Vehicle {
    private String number;
    private String manufacturer;
    private int year;
    private String color;
    private Owner owner;

    public Vehicle(String number, String manufacturer, int year, String color, Owner owner) {
        if (number == null || number.trim().length() != 5) {
            throw new IllegalArgumentException("License plate must contain exactly 5 characters");
        }
        if (manufacturer == null || !(manufacturer.trim().equalsIgnoreCase("Honda")
                || manufacturer.trim().equalsIgnoreCase("Yamaha")
                || manufacturer.trim().equalsIgnoreCase("Toyota")
                || manufacturer.trim().equalsIgnoreCase("Suzuki"))) {
            throw new IllegalArgumentException("Manufacturer must be Honda, Yamaha, Toyota or Suzuki");
        }
        if (year <= 2000 || year > Year.now().getValue()) {
            throw new IllegalArgumentException("Year must be after 2000 and no later than the current year");
        }
        if (color == null || color.trim().isEmpty() || owner == null) {
            throw new IllegalArgumentException("Color and owner are required");
        }
        this.number = number.trim().toUpperCase(Locale.ROOT);
        this.manufacturer = manufacturer.trim();
        this.year = year;
        this.color = color.trim();
        this.owner = owner;
    }

    public String getNumber() { return number; }
    public String getManufacturer() { return manufacturer; }
    public int getYear() { return year; }
    public String getColor() { return color; }
    public Owner getOwner() { return owner; }
    public void setOwner(Owner owner) {
        if (owner == null) throw new IllegalArgumentException("Owner must not be null");
        this.owner = owner;
    }

    public abstract String getType();
    public abstract String getExtraInfo();

    @Override
    public String toString() {
        return getType() + " | " + number + " | " + manufacturer + " | " + year
                + " | " + color + " | " + getExtraInfo() + " | Owner: " + owner;
    }
}
