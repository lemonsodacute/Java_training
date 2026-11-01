public class Main {
    public static void main(String[] args) {
        Shape shape = new Shape(4, 5);
        Rectangle rectangle = new Rectangle(4, 5);
        Circle circle = new Circle(3);

        System.out.println("Shape: " + shape.getWidth() + " x " + shape.getHeight());
        System.out.println("Rectangle area: " + rectangle.getArea());
        System.out.println("Rectangle perimeter: " + rectangle.getPerimeter());
        System.out.printf("Circle area: %.2f%n", circle.getArea());
        System.out.printf("Circle circumference: %.2f%n", circle.getCircumference());
    }
}
