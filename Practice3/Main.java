import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in, "UTF-8");

    public static void main(String[] args) throws java.io.UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        VehicleManager manager = new VehicleManager();
        int choice = -1;
        do {
            System.out.println("\n=== VEHICLE MANAGEMENT ===");
            System.out.println("1. Add a vehicle");
            System.out.println("2. Find by license plate");
            System.out.println("3. Find vehicles by owner ID");
            System.out.println("4. Delete vehicles by manufacturer");
            System.out.println("5. Show manufacturers with the most vehicles");
            System.out.println("6. Sort manufacturers by vehicle count (descending)");
            System.out.println("7. Show vehicle counts by type");
            System.out.println("8. View all vehicles");
            System.out.println("0. Exit");
            try {
                choice = readInt("Choose an option: ");
                switch (choice) {
                    case 1:
                        Vehicle v = inputVehicle();
                        System.out.println(manager.add(v) ? "Added successfully" : "License plate already exists");
                        break;
                    case 2:
                        Vehicle found = manager.findByNumber(readText("License plate: "));
                        System.out.println(found == null ? "Not found" : found);
                        break;
                    case 3: manager.findByOwnerId(readText("Owner ID: ")); break;
                    case 4:
                        int removed = manager.deleteByManufacturer(readText("Manufacturer: "));
                        System.out.println("Vehicles removed: " + removed);
                        break;
                    case 5: manager.showMostPopularManufacturer(); break;
                    case 6: manager.showSortedByManufacturerCount(); break;
                    case 7: manager.showStatistics(); break;
                    case 8: manager.showAll(); break;
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

    static Vehicle inputVehicle() {
        System.out.println("1. Car | 2. Motorcycle | 3. Truck");
        int type = readInt("Vehicle type: ");
        if (type < 1 || type > 3) throw new IllegalArgumentException("Invalid vehicle type");
        String number = readText("License plate (5 characters): ");
        String maker = readText("Manufacturer (Honda/Yamaha/Toyota/Suzuki): ");
        int year = readInt("Year of manufacture: ");
        String color = readText("Color: ");
        String id = readText("Owner ID (12 digits): ");
        String fullName = readText("Owner name: ");
        String email = readText("Email: ");
        Owner owner = new Owner(id, fullName, email);

        if (type == 1) {
            int seats = readInt("Number of seats: ");
            String engine = readText("Engine type: ");
            return new Car(number, maker, year, color, owner, seats, engine);
        } else if (type == 2) {
            double capacity = readDouble("Engine capacity (cc): ");
            return new Motorcycle(number, maker, year, color, owner, capacity);
        } else {
            double tonnage = readDouble("Load capacity (tons): ");
            return new Truck(number, maker, year, color, owner, tonnage);
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
}
