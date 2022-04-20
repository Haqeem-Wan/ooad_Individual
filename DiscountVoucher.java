public class DiscountVoucher {
    private String name;
    private String voucherCode;
    private double discount;

    public String getName() {
        return this.name;
    }

    public String getVoucherCode() {
        return this.voucherCode;
    }

    public double getDiscount() {
        return this.discount;
    }

    private DiscountVoucher(DiscountVoucherBuilder builder) {
        this.name = builder.name;
        this.voucherCode = builder.voucherCode;
        this.discount = builder.discount;
    }

    public static class DiscountVoucherBuilder {
        private String name;
        private String voucherCode;
        private double discount;

        public DiscountVoucherBuilder(String name, String voucherCode, double discount) {
            this.name = name;
            this.voucherCode = voucherCode;
            this.discount = discount;
        }

        public DiscountVoucher build() {
            return new DiscountVoucher(this);
        }
    }
}
