public class MenuItem {
    private String name;
    private String foodOrDrink;
    private String category;
    private double price;
    private double discount;

    public String getName() {
        return this.name;
    }

    public String getFoodOrDrink() {
        return foodOrDrink;
    }

    public String getFoodType() {
        return category;
    }

    public double getPrice() {
        return this.price;
    }

    public double getDiscount() {
        return discount;
    }

    private MenuItem(MenuItemBuilder builder) {
        this.name = builder.name;
        this.foodOrDrink = builder.foodOrDrink;
        this.category = builder.category;
        this.price = builder.price;
        this.discount = builder.discount;
    }

    public static class MenuItemBuilder {
        private String name;
        private String foodOrDrink;
        private String category;
        private double price;
        private double discount;

        public MenuItemBuilder(String name, String foodOrDrink, String category, double price) {
            this.name = name;
            this.foodOrDrink = foodOrDrink;
            this.category = category;
            this.price = price;
        }

        public MenuItemBuilder setDiscount(double discount) {
            this.discount = discount;
            return this;
        }

        public MenuItem build() {
            return new MenuItem(this);
        }
    }
}
