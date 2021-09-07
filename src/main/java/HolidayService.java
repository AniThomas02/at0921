import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class HolidayService {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private final ArrayList<LocalDate> holidayList = new ArrayList<>();

    public HolidayService(LocalDate startCal, LocalDate endCal) {
        this.generateHolidays(startCal, endCal);
    }

    private void generateHolidays(LocalDate startDate, LocalDate endDate) {
        for (int year = startDate.getYear(); year <= endDate.getYear(); year++) {
            ArrayList<LocalDate> holidaysToAdd = new ArrayList<>();
            holidaysToAdd.add(generateIndependenceDay(year));
            holidaysToAdd.add(generateLaborDay(year));
            for (LocalDate holiday : holidaysToAdd) {
                if ((startDate.isBefore(holiday) || startDate.isEqual(holiday))
                        && (endDate.isAfter(holiday) || endDate.isEqual(holiday))) {
                    holidayList.add(holiday);
                }
            }
        }
    }

    private LocalDate generateIndependenceDay(int calYear) {
        LocalDate independenceDay = LocalDate.parse(calYear + "-07-04");

        //Adjust date based on when it will be observed
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            // If Sunday, observe Monday
            return independenceDay.plusDays(1);
        }

        return independenceDay;
    }

    private LocalDate generateLaborDay(int calYear) {
        LocalDate september = LocalDate.parse(calYear + "-09-01");
        return september.with(firstInMonth(DayOfWeek.MONDAY));
    }

    public boolean hasHoliday() {
        return holidayList.size() > 0;
    }

    public boolean isHoliday(LocalDate startDate) {
        for (LocalDate holiday : holidayList) {
            if (startDate.isEqual(holiday)) {
                holidayList.remove(holiday);
                return true;
            }
        }
        return false;
    }
}