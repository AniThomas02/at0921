import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class CheckoutService {

    public RentalAgreement generateRentalAgreement(String toolCode, LocalDate checkoutDate, int rentalDayCount, int discountPercent) {
        ArrayList<String> errMsgs = this.validateCheckoutVariables(rentalDayCount, discountPercent);
        if (errMsgs.size() > 0) {
            throw new IllegalArgumentException("There was an issue with checkout. \n Please fix these issues and try again: \n"
                    + printErrors(errMsgs));
        }

        Tool tool = getTool(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Unable to find a tool with the tool code: "
                    + toolCode + ". Please check the code and try again.");
        }


        LocalDate dueDate = checkoutDate.plusDays(rentalDayCount);
        int chargeDays = computeChargeDays(tool, checkoutDate, dueDate);

        //This would be an agreement that would then be saved to the db, but since I have no db I'm returning the obj
        return new RentalAgreement(tool, rentalDayCount, checkoutDate, dueDate, chargeDays, BigDecimal.valueOf(discountPercent));
    }

    private ArrayList<String> validateCheckoutVariables(int rentalDayCount, int discountPercent) {
        ArrayList<String> errorMsgs = new ArrayList<>();
        //TODO: Check if any values are null?
        if (rentalDayCount < 1) {
            errorMsgs.add("Rental days must be more than 0.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            errorMsgs.add("Discounts must be within the values of 0 and 100.");
        }
        return errorMsgs;
    }

    private String printErrors(ArrayList<String> errMsgs) {
        StringBuilder messages = new StringBuilder();
        for (String error : errMsgs) {
            messages.append(error).append("\n");
        }
        return messages.toString();
    }

    private Tool getTool(String toolCode) {
        /**
         * Generally this would be an API call to the database that grabbed the tool and pricing info.
         * This could also be the time to see if said tool was already checked out
         */
        return Database.getToolFromCode(toolCode);
    }


    private int computeChargeDays(Tool tool, LocalDate checkoutDate, LocalDate dueDate) {
        Pricing toolPricing = tool.getToolPricing();


        //Don't charge first day, so we'll skip it
        LocalDate startDate = checkoutDate.plusDays(1);

        //Get Holidays in timespan
        HolidayService holidays = new HolidayService(checkoutDate, dueDate);

        int chargeDays = 0;
        do {
            // Check for Holidays first, since
            if (holidays.hasHoliday() && holidays.isHoliday(startDate)) {
                if (toolPricing.hasHolidayCharge()) {
                    ++chargeDays;
                }
            } else {
                // Charge Weekdays
                if (toolPricing.hasWeekdayCharge()
                        && startDate.getDayOfWeek() != DayOfWeek.SATURDAY
                        && startDate.getDayOfWeek() != DayOfWeek.SUNDAY
                ) {
                    ++chargeDays;
                } else {
                    // Charge Weekends
                    if (toolPricing.hasWeekendCharge()) {
                        ++chargeDays;
                    }
                }
            }
            startDate = startDate.plusDays(1);
        } while (!startDate.isAfter(dueDate));

        return chargeDays;
    }
}
