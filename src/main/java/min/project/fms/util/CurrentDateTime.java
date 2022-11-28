package min.project.fms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateTime {

    public static Date now() {
        return new Date();
    }

    public static String datetimeToString(Date _time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date());
    }

}
