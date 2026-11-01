import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DSHH ds = new DSHH();
        int choice = -1;
        do {
            System.out.println("\n=== QUAN LY HANG HOA ===");
            System.out.println("1. Them hang hoa");
            System.out.println("2. Xem danh sach");
            System.out.println("3. Thong ke ton kho va VAT");
            System.out.println("4. Danh gia kha nang ban");
            System.out.println("0. Thoat");
            try {
                choice = readInt("Chon: ");
                switch (choice) {
                    case 1:
                        Goods goods = inputGoods();
                        System.out.println(ds.add(goods) ? "Them thanh cong" : "Trung ma hoac danh sach day");
                        break;
                    case 2: ds.showAll(); break;
                    case 3: ds.showSummary(); break;
                    case 4: ds.showEvaluation(); break;
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

    static Goods inputGoods() {
        System.out.println("1. Food | 2. Electronics | 3. Crockery");
        int type = readInt("Loai hang: ");
        if (type < 1 || type > 3) throw new IllegalArgumentException("Loai hang khong hop le");
        String code = readText("Ma hang: ");
        String name = readText("Ten hang: ");
        int quantity = readInt("So luong: ");
        double price = readDouble("Don gia: ");

        if (type == 1) {
            LocalDate mfg = readDate("Ngay san xuat (yyyy-MM-dd): ");
            LocalDate exp = readDate("Han su dung (yyyy-MM-dd): ");
            String supplier = readText("Nha cung cap: ");
            return new Food(code, name, quantity, price, mfg, exp, supplier);
        } else if (type == 2) {
            int warranty = readInt("Bao hanh (thang): ");
            double power = readDouble("Cong suat kW: ");
            return new Electronics(code, name, quantity, price, warranty, power);
        } else {
            String maker = readText("Nha san xuat: ");
            LocalDate date = readDate("Ngay nhap kho (yyyy-MM-dd): ");
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
            catch (NumberFormatException e) { System.out.println("Vui long nhap so nguyen"); }
        }
    }

    static double readDouble(String message) {
        while (true) {
            try {
                double value = Double.parseDouble(readText(message));
                if (!Double.isFinite(value)) throw new NumberFormatException();
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so huu han");
            }
        }
    }

    static LocalDate readDate(String message) {
        while (true) {
            try { return LocalDate.parse(readText(message)); }
            catch (java.time.format.DateTimeParseException e) {
                System.out.println("Ngay sai dinh dang, vd: 2026-10-20");
            }
        }
    }
}
