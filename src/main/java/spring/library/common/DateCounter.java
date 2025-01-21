package spring.library.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateCounter {
    private static final SimpleDateFormat displayedDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String today() {
        return displayedDateFormat.format(new Date());
    }

    public static String setDueDateByFeature(String feature){
        String today = today();
        return switch (feature) {
            case "관리자" -> plusDays(today,110813);
            case "학생" -> plusDays(today,10);
            case "교직원" -> plusDays(today,30);
            default -> null;
        };
    }

    public static String extendDuration(String currentDueDate){
        return plusDays(currentDueDate,5);
    }

    public static String plusDays(String currentDueDate, int days) {
        Date date = null;
        try{
            date= new SimpleDateFormat("yyyy-MM-dd").parse(currentDueDate);
        }catch(ParseException e){
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DATE, days);
        return displayedDateFormat.format(cal.getTime());
    }
}
