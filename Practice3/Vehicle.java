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
            throw new IllegalArgumentException("Bien so phai dung 5 ky tu");
        }
        if (manufacturer == null || !(manufacturer.trim().equalsIgnoreCase("Honda")
                || manufacturer.trim().equalsIgnoreCase("Yamaha")
                || manufacturer.trim().equalsIgnoreCase("Toyota")
                || manufacturer.trim().equalsIgnoreCase("Suzuki"))) {
            throw new IllegalArgumentException("Hang xe: Honda, Yamaha, Toyota, Suzuki");
        }
        if (year <= 2000 || year > Year.now().getValue()) {
            throw new IllegalArgumentException("Nam san xuat phai > 2000 va <= nam hien tai");
        }
        if (color == null || color.trim().isEmpty() || owner == null) {
            throw new IllegalArgumentException("Mau xe va chu xe khong duoc thieu");
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
        if (owner == null) throw new IllegalArgumentException("Chu xe khong duoc null");
        this.owner = owner;
    }

    public abstract String getType();
    public abstract String getExtraInfo();

    @Override
    public String toString() {
        return getType() + " | " + number + " | " + manufacturer + " | " + year
                + " | " + color + " | " + getExtraInfo() + " | Chu xe: " + owner;
    }
}
