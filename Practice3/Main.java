import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in, "UTF-8");

    public static void main(String[] args) throws java.io.UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        VehicleManager manager = new VehicleManager();
        int choice = -1;
        do {
            System.out.println("\n=== QUẢN LÝ PHƯƠNG TIỆN ===");
            System.out.println("1. Thêm phương tiện");
            System.out.println("2. Tìm theo biển số");
            System.out.println("3. Tìm xe theo CMND/CCCD chủ xe");
            System.out.println("4. Xóa xe theo hãng sản xuất");
            System.out.println("5. Hãng có nhiều xe nhất");
            System.out.println("6. Sắp xếp hãng theo số lượng xe giảm dần");
            System.out.println("7. Thống kê từng loại xe");
            System.out.println("8. Xem tất cả xe");
            System.out.println("0. Thoát");
            try {
                choice = readInt("Chọn: ");
                switch (choice) {
                    case 1:
                        Vehicle v = inputVehicle();
                        System.out.println(manager.add(v) ? "Thêm thành công" : "Biển số đã tồn tại");
                        break;
                    case 2:
                        Vehicle found = manager.findByNumber(readText("Biển số: "));
                        System.out.println(found == null ? "Không tìm thấy" : found);
                        break;
                    case 3: manager.findByOwnerId(readText("CMND/CCCD: ")); break;
                    case 4:
                        int removed = manager.deleteByManufacturer(readText("Hãng sản xuất: "));
                        System.out.println("Đã xóa " + removed + " xe");
                        break;
                    case 5: manager.showMostPopularManufacturer(); break;
                    case 6: manager.showSortedByManufacturerCount(); break;
                    case 7: manager.showStatistics(); break;
                    case 8: manager.showAll(); break;
                    case 0: System.out.println("Tạm biệt!"); break;
                    default: System.out.println("Lựa chọn không hợp lệ");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Dữ liệu sai: " + e.getMessage());
            } catch (java.util.NoSuchElementException e) {
                System.out.println("\nĐã hết dữ liệu nhập. Tạm biệt!");
                return;
            }
        } while (choice != 0);
    }

    static Vehicle inputVehicle() {
        System.out.println("1. Ô tô | 2. Xe máy | 3. Xe tải");
        int type = readInt("Loại phương tiện: ");
        if (type < 1 || type > 3) throw new IllegalArgumentException("Loại xe không hợp lệ");
        String number = readText("Biển số (5 ký tự): ");
        String maker = readText("Hãng (Honda/Yamaha/Toyota/Suzuki): ");
        int year = readInt("Năm sản xuất: ");
        String color = readText("Màu xe: ");
        String id = readText("CMND/CCCD (12 số): ");
        String fullName = readText("Tên chủ xe: ");
        String email = readText("Địa chỉ thư điện tử: ");
        Owner owner = new Owner(id, fullName, email);

        if (type == 1) {
            int seats = readInt("Số chỗ ngồi: ");
            String engine = readText("Loại động cơ: ");
            return new Car(number, maker, year, color, owner, seats, engine);
        } else if (type == 2) {
            double capacity = readDouble("Dung tích (cc): ");
            return new Motorcycle(number, maker, year, color, owner, capacity);
        } else {
            double tonnage = readDouble("Trọng tải (tấn): ");
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
            catch (NumberFormatException e) { System.out.println("Nhập số nguyên hợp lệ"); }
        }
    }

    static double readDouble(String message) {
        while (true) {
            try {
                double value = Double.parseDouble(readText(message));
                if (!Double.isFinite(value)) throw new NumberFormatException();
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hữu hạn hợp lệ");
            }
        }
    }
}
