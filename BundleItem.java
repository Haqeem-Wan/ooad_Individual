import java.util.ArrayList;

public class BundleItem {
    private String name;
    private ArrayList<MenuItem> menuItems;
    private double discount;
    private double finalPrice;

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

    private BundleItem(BundleItemBuilder builder) {
        this.name = builder.name;
        this.menuItems = builder.menuItems;
        this.discount = builder.discount;
        this.finalPrice = builder.finalPrice;
    }

    public static class BundleItemBuilder {
        private String name;
        private ArrayList<MenuItem> menuItems;
        private double discount;
        private double finalPrice;
        
        public BundleItemBuilder(String name, ArrayList<MenuItem> menuItems, double discount) {
            this.name = name;
            this.menuItems = menuItems;
            this.discount = discount;
            this.finalPrice = calculateFinalPrice();
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
