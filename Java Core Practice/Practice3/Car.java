public class Car extends Vehicle {
    private int seats;
    private String engineType;

    public Car(String number, String manufacturer, int year, String color,
               Owner owner, int seats, String engineType) {
        super(number, manufacturer, year, color, owner);
        if (seats <= 0 || engineType == null || engineType.trim().isEmpty()) {
            throw new IllegalArgumentException("Seat count must be positive and engine type must not be blank");
        }
        this.seats = seats;
        this.engineType = engineType.trim();
    }

    public int getSeats() { return seats; }
    public String getEngineType() { return engineType; }

    @Override
    public String getType() { return "Car"; }

    @Override
    public String getExtraInfo() { return seats + " seats | Engine: " + engineType; }
}
