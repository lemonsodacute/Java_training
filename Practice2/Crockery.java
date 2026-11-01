import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Crockery extends Goods {
    private String manufacturer;
    private LocalDate arrivalDate;

    public Crockery(String code, String name, int quantity, double unitPrice,
                    String manufacturer, LocalDate arrivalDate) {
        super(code, name, quantity, unitPrice);
        if (manufacturer == null || manufacturer.trim().isEmpty() || arrivalDate == null) {
            throw new IllegalArgumentException("Thiếu nhà sản xuất hoặc ngày nhập");
        }
        this.manufacturer = manufacturer.trim();
        this.arrivalDate = arrivalDate;
    }

    public String getManufacturer() { return manufacturer; }
    public LocalDate getArrivalDate() { return arrivalDate; }

    @Override
    public String getType() { return "Sành sứ"; }

    @Override
    public double getVatRate() { return 0.10; }

    @Override
    public String evaluate() {
        long days = ChronoUnit.DAYS.between(arrivalDate, LocalDate.now());
        if (getQuantity() > 50 && days > 10) {
            return "Bán chậm (tồn > 50 và lưu kho > 10 ngày)";
        }
        return "Chưa đánh giá";
    }

    @Override
    public String toString() {
        return super.toString() + " | Nhà sản xuất: " + manufacturer + " | Ngày nhập: " + arrivalDate;
    }
}
