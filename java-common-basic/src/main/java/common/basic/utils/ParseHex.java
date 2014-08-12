package common.basic.utils;

import java.util.ArrayList;

/**
 * Created by jaebeomi on 8/12/14.
 */
public class ParseHex {

    public static String getHexStr(String s) {

        ArrayList<String> list = new ArrayList<String>();
        boolean check = true;

        for(int i=0; i<s.length(); i++) {

            int ch = (int)s.charAt(i);

            if(ch >= 48 && ch < 58 || ch >= 65 && ch < 71 || ch >= 97 && ch < 103) {
                list.add(getHex(Integer.toString(ch)));
            } else {
                check = false;
                break;
            }
        }

        if(!check) return "16진수 변환에 실피해였습니다. 입력 값을 확인해 주세요.";

        String makeHaxNum = makeHex(s);
        String checkHaxNum = getHex(makeHaxNum);

        if(!checkHaxNum.equals(s)) return "16진수가 아닙니다. 입력 값을 확인해 주세요.";

        return makeHaxNum;
    }

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

    public static String makeHex(String s) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        int sum = 0;

        for(int i=0; i<s.length(); i++) {

            switch (s.charAt(i)) {

                case 'A':
                case 'a':
                    list.add(10);
                    break;

                case 'B':
                case 'b':
                    list.add(11);
                    break;

                case 'C':
                case 'c':
                    list.add(12);
                    break;

                case 'D':
                case 'd':
                    list.add(13);
                    break;

                case 'E':
                case 'e':
                    list.add(14);
                    break;

                case 'F':
                case 'f':
                    list.add(15);
                    break;

                default:
                    list.add(Integer.parseInt(Character.toString(s.charAt(i))));
            }
        }

        for(int i=0; i<list.size(); i++) {

            sum += list.get(i) * (int)Math.pow(16, list.size() - 1 - i);
        }

        return Integer.toString(sum);
    }
}
