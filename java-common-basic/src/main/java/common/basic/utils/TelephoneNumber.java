package common.basic.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelephoneNumber {

	static Pattern pattern = Pattern.compile("(02|\\d\\d\\d)-?(\\d\\d\\d\\d?)-?(\\d\\d\\d\\d)");
	
	public String n1;
	public String n2;
	public String n3;

	
	public static TelephoneNumber parse(String number) {
		if (null == number)
			return null;
		
		Matcher matcher = pattern.matcher(number);
		if(!matcher.matches())
			return null;
		
		TelephoneNumber tn = new TelephoneNumber();
		tn.n1 = matcher.group(1);
		tn.n2 = matcher.group(2);
		tn.n3 = matcher.group(3);
		return tn;
	}

    /*
            if (!TelephoneNumber.parse("0113334444").n1.equals("011"))
            renderText("error");
        if (!TelephoneNumber.parse("01144444444").n1.equals("011"))
            renderText("error");

        if (!TelephoneNumber.parse("023334444").n1.equals("02"))
            renderText("error");

        if (!TelephoneNumber.parse("0244444444").n1.equals("02"))
            renderText("error");

        if (!TelephoneNumber.parse("023334444").n2.equals("333"))
            renderText("error");

        if (!TelephoneNumber.parse("0260053000").n1.equals("02"))
            renderText("error");

        if (!TelephoneNumber.parse("0260053000").n2.equals("6005"))
            renderText("error");



     */
}
