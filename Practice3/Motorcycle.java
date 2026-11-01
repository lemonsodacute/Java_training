public class Motorcycle extends Vehicle {
    private double capacity;

    public Motorcycle(String number, String manufacturer, int year, String color,
                      Owner owner, double capacity) {
        super(number, manufacturer, year, color, owner);
        if (!Double.isFinite(capacity) || capacity < 0) {
            throw new IllegalArgumentException("Dung tích phải là số hữu hạn >= 0");
        }
        this.capacity = capacity;
    }

    public double getCapacity() { return capacity; }

    @Override
    public String getType() { return "Xe máy"; }

    @Override
    public String getExtraInfo() { return "Dung tích: " + capacity + " cc"; }
}
