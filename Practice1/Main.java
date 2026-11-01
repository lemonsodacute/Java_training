public class Main {
    public static void main(String[] args) throws java.io.UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        Shape shape = new Shape(4, 5);
        Rectangle rectangle = new Rectangle(4, 5);
        Circle circle = new Circle(3);

        System.out.println("Kích thước hình: " + shape.getWidth() + " x " + shape.getHeight());
        System.out.println("Diện tích hình chữ nhật: " + rectangle.getArea());
        System.out.println("Chu vi hình chữ nhật: " + rectangle.getPerimeter());
        System.out.printf("Diện tích hình tròn: %.2f%n", circle.getArea());
        System.out.printf("Chu vi hình tròn: %.2f%n", circle.getCircumference());
    }
}
