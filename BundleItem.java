import java.util.ArrayList;

public class BundleItem {
    private String name;
    private ArrayList<MenuItem> menuItems;
    private double discount;

    public String getName() {
        return this.name;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    public double getDiscount() {
        return this.discount;
    }

    private BundleItem(BundleItemBuilder builder) {
        this.name = builder.name;
        this.menuItems = builder.menuItems;
        this.discount = builder.discount;
    }

    public static class BundleItemBuilder {
        private String name;
        private ArrayList<MenuItem> menuItems;
        private double discount;
        
        public BundleItemBuilder(String name, ArrayList<MenuItem> menuItems, double discount) {
            this.name = name;
            this.menuItems = menuItems;
            this.discount = discount;
        }

        public BundleItem build() {
            return new BundleItem(this);
        }
    }
}
