import java.util.ArrayList;

public class BundleItem {
    private String name;
    private ArrayList<MenuItem> menuItems;
    private double price;
    private double discount;
    private int quantity;

    public String getName() {
        return this.name;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    public double getPrice() {
        return this.price;
    }

    public double getDiscount() {
        return this.discount;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private BundleItem(BundleItemBuilder builder) {
        this.name = builder.name;
        this.menuItems = builder.menuItems;
        this.price = builder.price;
        this.discount = builder.discount;
        this.quantity = builder.quantity;
    }

    public static class BundleItemBuilder {
        private String name;
        private ArrayList<MenuItem> menuItems;
        private double price;
        private double discount;
        private int quantity;
        
        public BundleItemBuilder(String name, ArrayList<MenuItem> menuItems, double discount) {
            this.name = name;
            this.menuItems = menuItems;
            this.discount = discount;
            this.price = calculateFinalPrice();
        }

        public BundleItemBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public double calculateFinalPrice() {
            double finalPrice = 0;

            for(int i = 0; i < this.menuItems.size(); i++) {
                finalPrice += this.menuItems.get(i).getPrice();
            }
            finalPrice *= (100 - this.discount) / 100;

            return finalPrice;
        }

        public BundleItem build() {
            return new BundleItem(this);
        }
    }
}
