package javacommon.base;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jarvis on 2015/9/12.
 */
public class BaseEntity implements java.io.Serializable {
    protected static final String DATE_FORMAT = "yyyy-MM-dd";

    protected static final String TIME_FORMAT = "HH:mm:ss";

    protected static final String HOURMIN_FORMAT = "HH:mm";

    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

    protected static String format(Date date, String dateFormat) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(dateFormat).format(date);
        } catch (Exception e) {
            return null;
        }
    }

    protected static Date parse(String date, String dateFormat) {
        if (dateFormat == null || date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(dateFormat).parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}
