import java.util.ArrayList;

public class BundleItem {
    private String name;
    private ArrayList<MenuItem> menuItems;
    private double discount;
    private double finalPrice;
    private double quantity;

    public String getName() {
        return this.name;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    public double getDiscount() {
        return this.discount;
    }

    public double getFinalPrice() {
        return this.finalPrice;
    }

    public double getQuantity() {
        return this.quantity;
    }

    private BundleItem(BundleItemBuilder builder) {
        this.name = builder.name;
        this.menuItems = builder.menuItems;
        this.discount = builder.discount;
        this.finalPrice = builder.finalPrice;
        this.quantity = builder.quantity;
    }

    public static class BundleItemBuilder {
        private String name;
        private ArrayList<MenuItem> menuItems;
        private double discount;
        private double finalPrice;
        private int quantity = 1;
        
        public BundleItemBuilder(String name, ArrayList<MenuItem> menuItems, double discount) {
            this.name = name;
            this.menuItems = menuItems;
            this.discount = discount;
            this.finalPrice = calculateFinalPrice();
        }

        public BundleItemBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            this.finalPrice = calculateFinalPrice();
            return this;
        }

        public double calculateFinalPrice() {
            double finalPrice = 0;

            for(int i = 0; i < this.menuItems.size(); i++) {
                finalPrice += this.menuItems.get(i).getPrice();
            }
            finalPrice *= (100 - this.discount) / 100;
            finalPrice *= this.quantity;
            
            return finalPrice;
        }

        public BundleItem build() {
            return new BundleItem(this);
        }
    }
}
