package common.basic.utils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumanReadable {
	public static String number(long number)
	{
	    if (number < 1000)
	        return number + "";

	    number /= 1000;
	    if (number < 1000)
	        return number + "K+";

	    number /= 1000;
	    if (number < 1000)
	        return number + "M+";

	    number /= 1000;
	    return number + "B+";
	}

    public static String capacity(double number)
    {
        if (number < 1000)
            return Math.round(number) + "";

        number /= 1000;
        if (number < 1000)
            return Math.round(number * 10) / 10.0 + "KB";

        number /= 1000.0;
        if (number < 1000)
            return Math.round(number * 10) / 10.0 + "MB";

        number /= 1000;
        return Math.round(number * 10) / 10.0 + "GB";
    }

    public static String date(Date date) {
//        final Date dateNow = new Date();
//        final long milliSec = date.getTime();
//        final long milliSecNow = dateNow.getTime();
//        final long milliSecDiff = milliSecNow - milliSec;
        // TODO:

        if(null == date)
            return null;

        return DateUtil.yyyy_MM_dd(date);
    }

}
