package com.zhyzhko.util.date;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 04.07.17.
 */
public class DateUtils {

    private static final Logger LOGGER = Logger.getLogger(DateUtils.class);

    public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String MONTH_DAY_YEAR = "MM-dd-yyyy";

    private SimpleDateFormat simpleDateFormat;

    public DateUtils(String dateFormat) {
        this.simpleDateFormat = new SimpleDateFormat(dateFormat);
    }

    public String format(Date date) {
        return simpleDateFormat.format(date);
    }

    public Date parse(String date) {
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            LOGGER.error("Can't create date", e);
        }
        return null;
    }
}
