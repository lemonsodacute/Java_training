import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        VehicleManager manager = new VehicleManager();
        int choice = -1;
        do {
            System.out.println("\n=== QUAN LY PHUONG TIEN ===");
            System.out.println("1. Them phuong tien");
            System.out.println("2. Tim theo bien so");
            System.out.println("3. Tim xe theo CMND/CCCD chu xe");
            System.out.println("4. Xoa xe theo hang san xuat");
            System.out.println("5. Hang co nhieu xe nhat");
            System.out.println("6. Sap xep hang theo so luong xe giam dan");
            System.out.println("7. Thong ke tung loai xe");
            System.out.println("8. Xem tat ca xe");
            System.out.println("0. Thoat");
            try {
                choice = readInt("Chon: ");
                switch (choice) {
                    case 1:
                        Vehicle v = inputVehicle();
                        System.out.println(manager.add(v) ? "Them thanh cong" : "Bien so da ton tai");
                        break;
                    case 2:
                        Vehicle found = manager.findByNumber(readText("Bien so: "));
                        System.out.println(found == null ? "Khong tim thay" : found);
                        break;
                    case 3: manager.findByOwnerId(readText("CMND/CCCD: ")); break;
                    case 4:
                        int removed = manager.deleteByManufacturer(readText("Hang san xuat: "));
                        System.out.println("Da xoa " + removed + " xe");
                        break;
                    case 5: manager.showMostPopularManufacturer(); break;
                    case 6: manager.showSortedByManufacturerCount(); break;
                    case 7: manager.showStatistics(); break;
                    case 8: manager.showAll(); break;
                    case 0: System.out.println("Tam biet!"); break;
                    default: System.out.println("Lua chon khong hop le");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Du lieu sai: " + e.getMessage());
            } catch (java.util.NoSuchElementException e) {
                System.out.println("\nDa het du lieu nhap. Tam biet!");
                return;
            }
        } while (choice != 0);
    }

    static Vehicle inputVehicle() {
        System.out.println("1. Car | 2. Motorcycle | 3. Truck");
        int type = readInt("Loai phuong tien: ");
        if (type < 1 || type > 3) throw new IllegalArgumentException("Loai xe khong hop le");
        String number = readText("Bien so (5 ky tu): ");
        String maker = readText("Hang (Honda/Yamaha/Toyota/Suzuki): ");
        int year = readInt("Nam san xuat: ");
        String color = readText("Mau xe: ");
        String id = readText("CMND/CCCD (12 so): ");
        String fullName = readText("Ten chu xe: ");
        String email = readText("Email: ");
        Owner owner = new Owner(id, fullName, email);

        if (type == 1) {
            int seats = readInt("So cho ngoi: ");
            String engine = readText("Loai dong co: ");
            return new Car(number, maker, year, color, owner, seats, engine);
        } else if (type == 2) {
            double capacity = readDouble("Dung tich (cc): ");
            return new Motorcycle(number, maker, year, color, owner, capacity);
        } else {
            double tonnage = readDouble("Trong tai (tan): ");
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
            catch (NumberFormatException e) { System.out.println("Nhap so nguyen hop le"); }
        }
    }

    static double readDouble(String message) {
        while (true) {
            try {
                double value = Double.parseDouble(readText(message));
                if (!Double.isFinite(value)) throw new NumberFormatException();
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Nhap so huu han hop le");
            }
        }
    }
}
