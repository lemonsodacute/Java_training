public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        // Chiều rộng và chiều cao của hình tròn bằng đường kính.
        super(radius * 2, radius * 2);
        this.radius = radius;
    }

    public double getRadius() { return radius; }

    public double getArea() { return Math.PI * radius * radius; }
    public double getCircumference() { return 2 * Math.PI * radius; }
}
