public class Car extends Vehicle {
    private int seats;
    private String engineType;

    public Car(String number, String manufacturer, int year, String color,
               Owner owner, int seats, String engineType) {
        super(number, manufacturer, year, color, owner);
        if (seats <= 0 || engineType == null || engineType.trim().isEmpty()) {
            throw new IllegalArgumentException("Số ghế hoặc loại động cơ không hợp lệ");
        }
        this.seats = seats;
        this.engineType = engineType.trim();
    }

    public int getSeats() { return seats; }
    public String getEngineType() { return engineType; }

    @Override
    public String getType() { return "Ô tô"; }

    @Override
    public String getExtraInfo() { return seats + " chỗ | Động cơ: " + engineType; }
}
