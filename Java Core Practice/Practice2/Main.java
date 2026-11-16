import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in, "UTF-8");

    public static void main(String[] args) throws java.io.UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        DSHH ds = new DSHH();
        int choice = -1;
        do {
            System.out.println("\n=== GOODS MANAGEMENT ===");
            System.out.println("1. Add goods");
            System.out.println("2. View all goods");
            System.out.println("3. Stock and VAT summary");
            System.out.println("4. Evaluate sales");
            System.out.println("0. Exit");
            try {
                choice = readInt("Choose an option: ");
                switch (choice) {
                    case 1:
                        Goods goods = inputGoods();
                        System.out.println(ds.add(goods) ? "Added successfully" : "Duplicate code or list is full");
                        break;
                    case 2: ds.showAll(); break;
                    case 3: ds.showSummary(); break;
                    case 4: ds.showEvaluation(); break;
                    case 0: System.out.println("Goodbye!"); break;
                    default: System.out.println("Invalid choice");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid data: " + e.getMessage());
            } catch (java.util.NoSuchElementException e) {
                System.out.println("\nEnd of input. Goodbye!");
                return;
            }
        } while (choice != 0);
    }

    static Goods inputGoods() {
        System.out.println("1. Food | 2. Electronics | 3. Crockery");
        int type = readInt("Goods type: ");
        if (type < 1 || type > 3) throw new IllegalArgumentException("Invalid goods type");
        String code = readText("Code: ");
        String name = readText("Name: ");
        int quantity = readInt("Quantity: ");
        double price = readDouble("Unit price: ");

        if (type == 1) {
            LocalDate mfg = readDate("Manufacture date (yyyy-MM-dd): ");
            LocalDate exp = readDate("Expiry date (yyyy-MM-dd): ");
            String supplier = readText("Supplier: ");
            return new Food(code, name, quantity, price, mfg, exp, supplier);
        } else if (type == 2) {
            int warranty = readInt("Warranty (months): ");
            double power = readDouble("Power (kW): ");
            return new Electronics(code, name, quantity, price, warranty, power);
        } else {
            String maker = readText("Manufacturer: ");
            LocalDate date = readDate("Arrival date (yyyy-MM-dd): ");
            return new Crockery(code, name, quantity, price, maker, date);
        }
    }

    static String readText(String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }

    static int readInt(String message) {
        while (true) {
            try { return Integer.parseInt(readText(message)); }
            catch (NumberFormatException e) { System.out.println("Please enter an integer"); }
        }
    }

    static double readDouble(String message) {
        while (true) {
            try {
                double value = Double.parseDouble(readText(message));
                if (!Double.isFinite(value)) throw new NumberFormatException();
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a finite number");
            }
        }
    }

    static LocalDate readDate(String message) {
        while (true) {
            try { return LocalDate.parse(readText(message)); }
            catch (java.time.format.DateTimeParseException e) {
                System.out.println("Invalid date. Use yyyy-MM-dd, for example: 2026-10-20");
            }
        }
    }
}
