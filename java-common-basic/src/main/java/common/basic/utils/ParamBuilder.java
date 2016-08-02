package common.basic.utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParamBuilder extends ArrayList<NameValuePair> {

    public static ParamBuilder create(String key, String value) {
        return new ParamBuilder().append(key, value);
    }

    public ParamBuilder append(String key, String value) {
        super.add(new BasicNameValuePair(key, value));
        return this;
    }

    public ParamBuilder append(String key, int value) {
        super.add(new BasicNameValuePair(key, Integer.toString(value)));
        return this;
    }

    public ParamBuilder append(String key, long value) {
        super.add(new BasicNameValuePair(key, Long.toString(value)));
        return this;
    }

    public static ParamBuilder create() {
        return new ParamBuilder();
    }

    public static ParamBuilder fromObject(Object object) {
        final ParamBuilder paramBuilder = create();

        final Map<String, Object> map = ReflectionUtil.toMap(object);
        for (String key : map.keySet()) {
            final Object objectInMap = map.get(key);
            if (null == objectInMap)
                continue;

            final List list = Cast.as(objectInMap, List.class);
            if(list != null){
                for (Object objectInList : list) {
                    paramBuilder.append(key, StringUtil.toString(objectInList));
                }
                continue;
            }

            paramBuilder.append(key, StringUtil.toString(objectInMap));
        }

        return paramBuilder;
    }

    public static ParamBuilder fromObjectWithArrayBracket(Object object) {
        final ParamBuilder paramBuilder = create();

        final Map<String, Object> map = ReflectionUtil.toMap(object);
        for (String key : map.keySet()) {
            final Object objectInMap = map.get(key);

            if (null == objectInMap)
                continue;

            final List list = Cast.as(objectInMap, List.class);
            if(list != null){
                for (Object objectInList : list) {
                    paramBuilder.append(key + "[]", StringUtil.toString(objectInList));
                }
                continue;
            }

            paramBuilder.append(key, StringUtil.toString(objectInMap));
        }

        return paramBuilder;
    }

    public ParamBuilder append(String key, List<String> listValue) {
        for (String value : listValue)
            super.add(new BasicNameValuePair(key, value));

        return this;
    }

    public String toUrlEncoded() throws UnsupportedEncodingException {
        List<String> listKeyValue = new ArrayList<String>();
        for (NameValuePair pair : this) {
            listKeyValue.add(URLEncoder.encode(pair.getName(), "UTF-8") + "=" + URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return StringUtil.join("&", listKeyValue);
    }
}
