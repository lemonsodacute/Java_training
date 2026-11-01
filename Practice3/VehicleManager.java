import java.util.ArrayList;

public class VehicleManager {
    private ArrayList<Vehicle> list = new ArrayList<>();
    private String[] manufacturers = {"Honda", "Yamaha", "Toyota", "Suzuki"};

    public boolean add(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Phương tiện không được null");
        }
        if (findByNumber(vehicle.getNumber()) != null) return false;
        for (Vehicle v : list) {
            if (v.getOwner().getIdentityNumber().equals(vehicle.getOwner().getIdentityNumber())) {
                if (!v.getOwner().sameInfo(vehicle.getOwner())) {
                    throw new IllegalArgumentException("CMND/CCCD đã thuộc chủ xe khác");
                }
                vehicle.setOwner(v.getOwner()); // Một người có thể sở hữu nhiều xe.
                break;
            }
        }
        list.add(vehicle);
        return true;
    }

    public Vehicle findByNumber(String number) {
        if (number == null) return null;
        number = number.trim();
        for (Vehicle v : list) {
            if (v.getNumber().equalsIgnoreCase(number)) return v;
        }
        return null;
    }

    public void findByOwnerId(String id) {
        if (id != null) id = id.trim();
        boolean found = false;
        for (Vehicle v : list) {
            if (v.getOwner().getIdentityNumber().equals(id)) {
                System.out.println(v);
                found = true;
            }
        }
        if (!found) System.out.println("Không tìm thấy xe của chủ sở hữu này");
    }

    public int deleteByManufacturer(String manufacturer) {
        if (manufacturer != null) manufacturer = manufacturer.trim();
        int deleted = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getManufacturer().equalsIgnoreCase(manufacturer)) {
                list.remove(i);
                i--; // Sau khi xóa, các phần tử dịch về trước một vị trí.
                deleted++;
            }
        }
        return deleted;
    }

    public int countByManufacturer(String manufacturer) {
        if (manufacturer != null) manufacturer = manufacturer.trim();
        int count = 0;
        for (Vehicle v : list) {
            if (v.getManufacturer().equalsIgnoreCase(manufacturer)) count++;
        }
        return count;
    }

    public void showMostPopularManufacturer() {
        int max = 0;
        for (String manufacturer : manufacturers) {
            if (countByManufacturer(manufacturer) > max) max = countByManufacturer(manufacturer);
        }
        if (max == 0) {
            System.out.println("Chưa có xe");
            return;
        }
        for (String manufacturer : manufacturers) {
            if (countByManufacturer(manufacturer) == max) {
                System.out.println(manufacturer + ": " + max + " xe");
            }
        }
    }

    public void showSortedByManufacturerCount() {
        String[] sorted = manufacturers.clone();
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = i + 1; j < sorted.length; j++) {
                if (countByManufacturer(sorted[i]) < countByManufacturer(sorted[j])) {
                    String temp = sorted[i];
                    sorted[i] = sorted[j];
                    sorted[j] = temp;
                }
            }
        }
        for (String maker : sorted) {
            int count = countByManufacturer(maker);
            System.out.println(maker + ": " + count + " xe");
            for (Vehicle v : list) {
                if (v.getManufacturer().equalsIgnoreCase(maker)) System.out.println("  " + v);
            }
        }
    }

    public void showStatistics() {
        String[] types = {"Ô tô", "Xe máy", "Xe tải"};
        for (String type : types) {
            int count = 0;
            for (Vehicle v : list) {
                if (v.getType().equals(type)) count++;
            }
            System.out.println(type + ": " + count + " xe");
        }
    }

    public void showAll() {
        if (list.isEmpty()) System.out.println("Chưa có xe");
        for (Vehicle v : list) System.out.println(v);
    }
}
