public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        // Circle ke thua width, height; duong kinh = 2 * ban kinh
        super(radius * 2, radius * 2);
        this.radius = radius;
    }

    public double getRadius() { return radius; }

    public double getArea() { return Math.PI * radius * radius; }
    public double getCircumference() { return 2 * Math.PI * radius; }
}
