public class MenuItem {
    private String name;
    private String foodOrDrink;
    private String category;
    private double price;
    private double discount;
    private int quantity;
    private double priceAfterDiscount;

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

    public int getQuantity() {
        return quantity;
    }

    public double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculatePriceAfterDiscount();
    }

    private void calculatePriceAfterDiscount() {
        double priceAfterDiscount = this.price;
        priceAfterDiscount *= (100 - this.discount) / 100;
        this.priceAfterDiscount = priceAfterDiscount;
    }

    private MenuItem(MenuItemBuilder builder) {
        this.name = builder.name;
        this.foodOrDrink = builder.foodOrDrink;
        this.category = builder.category;
        this.price = builder.price;
        this.discount = builder.discount;
        this.quantity = builder.quantity;
        this.priceAfterDiscount = builder.priceAfterDiscount;
    }

    public static class MenuItemBuilder {
        private String name;
        private String foodOrDrink;
        private String category;
        private double price;
        private double discount;
        private int quantity;
        private double priceAfterDiscount;

        public MenuItemBuilder(String name, String foodOrDrink, String category, double price) {
            this.name = name;
            this.foodOrDrink = foodOrDrink;
            this.category = category;
            this.price = price;
        }

        public MenuItemBuilder setDiscount(double discount) {
            this.discount = discount;
            this.priceAfterDiscount = calculatePriceAfterDiscount();
            return this;
        }

        public MenuItemBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public double calculatePriceAfterDiscount() {
            double priceAfterDiscount = this.price;
            priceAfterDiscount *= (100 - this.discount) / 100;
            return priceAfterDiscount;
        }

        public MenuItem build() {
            return new MenuItem(this);
        }
    }
}
