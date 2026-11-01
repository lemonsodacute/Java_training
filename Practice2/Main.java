import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in, "UTF-8");

    public static void main(String[] args) throws java.io.UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        DSHH ds = new DSHH();
        int choice = -1;
        do {
            System.out.println("\n=== QUẢN LÝ HÀNG HÓA ===");
            System.out.println("1. Thêm hàng hóa");
            System.out.println("2. Xem danh sách");
            System.out.println("3. Thống kê tồn kho và thuế VAT");
            System.out.println("4. Đánh giá khả năng bán");
            System.out.println("0. Thoát");
            try {
                choice = readInt("Chọn: ");
                switch (choice) {
                    case 1:
                        Goods goods = inputGoods();
                        System.out.println(ds.add(goods) ? "Thêm thành công" : "Trùng mã hoặc danh sách đầy");
                        break;
                    case 2: ds.showAll(); break;
                    case 3: ds.showSummary(); break;
                    case 4: ds.showEvaluation(); break;
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

    static Goods inputGoods() {
        System.out.println("1. Thực phẩm | 2. Điện máy | 3. Sành sứ");
        int type = readInt("Loại hàng: ");
        if (type < 1 || type > 3) throw new IllegalArgumentException("Loại hàng không hợp lệ");
        String code = readText("Mã hàng: ");
        String name = readText("Tên hàng: ");
        int quantity = readInt("Số lượng: ");
        double price = readDouble("Đơn giá: ");

        if (type == 1) {
            LocalDate mfg = readDate("Ngày sản xuất (yyyy-MM-dd): ");
            LocalDate exp = readDate("Hạn sử dụng (yyyy-MM-dd): ");
            String supplier = readText("Nhà cung cấp: ");
            return new Food(code, name, quantity, price, mfg, exp, supplier);
        } else if (type == 2) {
            int warranty = readInt("Bảo hành (tháng): ");
            double power = readDouble("Công suất (kW): ");
            return new Electronics(code, name, quantity, price, warranty, power);
        } else {
            String maker = readText("Nhà sản xuất: ");
            LocalDate date = readDate("Ngày nhập kho (yyyy-MM-dd): ");
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
            catch (NumberFormatException e) { System.out.println("Vui lòng nhập số nguyên"); }
        }
    }

    static double readDouble(String message) {
        while (true) {
            try {
                double value = Double.parseDouble(readText(message));
                if (!Double.isFinite(value)) throw new NumberFormatException();
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hữu hạn");
            }
        }
    }

    static LocalDate readDate(String message) {
        while (true) {
            try { return LocalDate.parse(readText(message)); }
            catch (java.time.format.DateTimeParseException e) {
                System.out.println("Ngày sai định dạng, ví dụ: 2026-10-20");
            }
        }
    }
}
