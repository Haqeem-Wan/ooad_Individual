public class MenuItem {
    private String name;
    private String foodOrDrink;
    private String category;
    private double price;
    private double discount;
    private int quantity;

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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateFinalPrice();
    }

    private void calculateFinalPrice() {
        double finalPrice = this.price;
        finalPrice *= (100 - this.discount) / 100;
        finalPrice *= this.quantity;
        this.price = finalPrice;
    }

    private MenuItem(MenuItemBuilder builder) {
        this.name = builder.name;
        this.foodOrDrink = builder.foodOrDrink;
        this.category = builder.category;
        this.price = builder.price;
        this.discount = builder.discount;
        this.quantity = builder.quantity;
    }

    public static class MenuItemBuilder {
        private String name;
        private String foodOrDrink;
        private String category;
        private double price;
        private double discount;
        private int quantity = 1;

        public MenuItemBuilder(String name, String foodOrDrink, String category, double price) {
            this.name = name;
            this.foodOrDrink = foodOrDrink;
            this.category = category;
            this.price = price;
        }

        public MenuItemBuilder setDiscount(double discount) {
            this.discount = discount;
            this.price = calculateFinalPrice();
            return this;
        }

        public MenuItemBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            this.price = calculateFinalPrice();
            return this;
        }

        public double calculateFinalPrice() {
            double finalPrice = this.price;
            finalPrice *= (100 - this.discount) / 100;
            finalPrice *= this.quantity;
            return finalPrice;
        }

        public MenuItem build() {
            return new MenuItem(this);
        }
    }
}
