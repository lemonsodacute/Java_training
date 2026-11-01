public class DSHH {
    private Goods[] list = new Goods[100];
    private int count = 0;

    public boolean add(Goods goods) {
        if (goods == null) {
            throw new IllegalArgumentException("Goods must not be null");
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
        if (count == 0) System.out.println("No goods available");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
    }

    public void showSummary() {
        String[] types = {"Food", "Electronics", "Crockery"};
        for (String type : types) {
            long totalQuantity = 0;
            double totalVat = 0;
            for (int i = 0; i < count; i++) {
                if (list[i].getType().equals(type)) {
                    totalQuantity += list[i].getQuantity();
                    totalVat += list[i].getVatAmount();
                }
            }
            System.out.printf("%s - Total stock: %d, VAT: %.2f%n", type, totalQuantity, totalVat);
        }
    }

    public void showEvaluation() {
        if (count == 0) System.out.println("No goods available");
        for (int i = 0; i < count; i++) {
            System.out.println(list[i].getCode() + ": " + list[i].evaluate());
        }
    }
}
