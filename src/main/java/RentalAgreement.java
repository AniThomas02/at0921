import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {
    private final Tool tool;
    private final int rentalDays;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final int chargeDays;
    private final BigDecimal preDiscountCharge;
    private final BigDecimal discountPercent;
    private final BigDecimal discountAmount;
    private final BigDecimal finalCharge;


    public RentalAgreement(Tool tool, int rentalDays, LocalDate checkoutDate, LocalDate dueDate, int chargeDays,
                           BigDecimal discountPercent) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.chargeDays = chargeDays;

        this.preDiscountCharge = BigDecimal.valueOf(chargeDays).multiply(tool.getToolPricing().getDailyCharge());
        this.discountPercent = discountPercent;
        BigDecimal discountPercentDec = discountPercent.divide(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP));
        this.discountAmount = preDiscountCharge.multiply(discountPercentDec).setScale(2, RoundingMode.HALF_UP);
        this.finalCharge = preDiscountCharge.subtract(discountAmount);
    }

    public void PrintAgreement() {
        System.out.println("Tool code: " + tool.getToolCode());
        System.out.println("Tool type: " + tool.getToolType());
        System.out.println("Tool brand: " + tool.getToolBrand());
        System.out.println("Rental days: " + getRentalDays());
        System.out.println("Check out date: " + dateFormat(getCheckoutDate()));
        System.out.println("Due Date: " + dateFormat(getDueDate()));
        System.out.println("Daily rental charge: " + currencyFormat(tool.getToolPricing().getDailyCharge()));
        System.out.println("Charge days: " + getChargeDays());
        System.out.println("Pre-discount charge: " + currencyFormat(getPreDiscountCharge()));
        System.out.println("Discount percent: " + getDiscountPercent() + "%");
        System.out.println("Discount Amount: " + currencyFormat(getDiscountAmount()));
        System.out.println("Final Charge: " + currencyFormat(getFinalCharge()));
    }

    public String currencyFormat(BigDecimal money) {
        return NumberFormat.getCurrencyInstance().format(money);
    }

    public String dateFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MM/dd/yy"));
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }
}
