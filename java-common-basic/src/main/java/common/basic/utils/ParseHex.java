package common.basic.utils;

import java.util.ArrayList;

/**
 * Created by jaebeomi on 8/12/14.
 */
public class ParseHex {

    public static String getHex(String s) {

        ArrayList<String> list = new ArrayList<String>();
        String returnNum = "";
        if(s.contains("-")) {
            returnNum += "-";
            s = s.replace("-", "");
        }
        int num = Integer.parseInt(s);
        int rest;
        int value = num;

        do {

            rest = value % 16;
            value = value / 16;
            list.add(Integer.toString(rest));
        } while(value >= 16);

        if(num > 15) list.add(Integer.toString(value));

        for(int i=0; i<list.size(); i++) {

            switch (Integer.parseInt(list.get(i))) {

                case 10:
                    list.set(i, "A");
                    break;

                case 11:
                    list.set(i, "B");
                    break;

                case 12:
                    list.set(i, "C");
                    break;

                case 13:
                    list.set(i, "D");
                    break;

                case 14:
                    list.set(i, "E");
                    break;

                case 15:
                    list.set(i, "F");
                    break;
            }
        }

        for(int i=list.size()-1; i>=0; i--) {
            returnNum += list.get(i);
        }

        return returnNum;
    }
}
