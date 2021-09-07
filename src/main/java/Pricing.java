import java.math.BigDecimal;

public class Pricing {
    private final BigDecimal dailyCharge;
    private final Boolean weekdayCharge;
    private final Boolean weekendCharge;
    private final Boolean holidayCharge;

    public Pricing(BigDecimal dailyCharge, Boolean weekdayCharge, Boolean weekendCharge, Boolean holidayCharge) {
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public Boolean hasWeekdayCharge() {
        return weekdayCharge;
    }

    public Boolean hasWeekendCharge() {
        return weekendCharge;
    }

    public Boolean hasHolidayCharge() {
        return holidayCharge;
    }
}
