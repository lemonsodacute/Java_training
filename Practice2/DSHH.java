// Danh sách hàng hóa sử dụng mảng theo yêu cầu bài tập.
public class DSHH {
    private Goods[] list = new Goods[100];
    private int count = 0;

    public boolean add(Goods goods) {
        if (goods == null) {
            throw new IllegalArgumentException("Hàng hóa không được null");
        }
        if (count == list.length) return false;
        for (int i = 0; i < count; i++) {
            if (list[i].getCode().equalsIgnoreCase(goods.getCode())) return false;
        }
        list[count] = goods;
        count++;
        return true;
    }

    public void showAll() {
        if (count == 0) System.out.println("Chưa có hàng hóa");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
    }

    public void showSummary() {
        String[] types = {"Thực phẩm", "Điện máy", "Sành sứ"};
        for (String type : types) {
            long totalQuantity = 0;
            double totalVat = 0;
            for (int i = 0; i < count; i++) {
                if (list[i].getType().equals(type)) {
                    totalQuantity += list[i].getQuantity();
                    totalVat += list[i].getVatAmount();
                }
            }
            System.out.printf("%s - Tổng tồn: %d, Thuế VAT: %.2f%n", type, totalQuantity, totalVat);
        }
    }

    public void showEvaluation() {
        if (count == 0) System.out.println("Chưa có hàng hóa");
        for (int i = 0; i < count; i++) {
            System.out.println(list[i].getCode() + ": " + list[i].evaluate());
        }
    }
}
