package spring.library.libraryClock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateCounter {
    private static final SimpleDateFormat displayDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static String today() {
        return displayDateFormat.format(LocalDate.now());

    }

    public static String setDueDateByFeature(String feature) throws ParseException {
        String today = today();
        return switch (feature) {
            case "관리자" -> plusDays(today,110813);
            case "학생" -> plusDays(today,10);
            case "교직원" -> plusDays(today,30);
            default -> null;
        };
    }

    public static String ExtendTheDuration(String currentDueDate, int additionalPeriod) throws ParseException {
        String newLoanDate = plusDays(currentDueDate,additionalPeriod);
        return displayDateFormat.format(newLoanDate);
    }

    public static String plusDays(String currentDate, int days) throws ParseException {
        SimpleDateFormat preprocessForDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date newDate = preprocessForDateFormat.parse(preprocessForDateFormat.format(currentDate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(newDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return displayDateFormat.format(calendar.getTime());
    }
}
