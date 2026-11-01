import java.util.ArrayList;

public class VehicleManager {
    private ArrayList<Vehicle> list = new ArrayList<>();
    private String[] manufacturers = {"Honda", "Yamaha", "Toyota", "Suzuki"};

    public boolean add(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle must not be null");
        }
        if (findByNumber(vehicle.getNumber()) != null) return false;
        for (Vehicle v : list) {
            if (v.getOwner().getIdentityNumber().equals(vehicle.getOwner().getIdentityNumber())) {
                if (!v.getOwner().sameInfo(vehicle.getOwner())) {
                    throw new IllegalArgumentException("Identity number is already registered to a different owner");
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
        if (!found) System.out.println("No vehicles found for this owner");
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
            System.out.println("No vehicles available");
            return;
        }
        for (String manufacturer : manufacturers) {
            if (countByManufacturer(manufacturer) == max) {
                System.out.println(manufacturer + " - Vehicle count: " + max);
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
            System.out.println(maker + " - Vehicle count: " + count);
            for (Vehicle v : list) {
                if (v.getManufacturer().equalsIgnoreCase(maker)) System.out.println("  " + v);
            }
        }
    }

    public void showStatistics() {
        String[] types = {"Car", "Motorcycle", "Truck"};
        for (String type : types) {
            int count = 0;
            for (Vehicle v : list) {
                if (v.getType().equals(type)) count++;
            }
            System.out.println(type + " - Vehicle count: " + count);
        }
    }

    public void showAll() {
        if (list.isEmpty()) System.out.println("No vehicles available");
        for (Vehicle v : list) System.out.println(v);
    }
}
