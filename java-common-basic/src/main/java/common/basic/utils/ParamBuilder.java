package common.basic.utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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

    public static ParamBuilder create() {
        return new ParamBuilder();
    }

    public static ParamBuilder fromObject(Object object) {
        final ParamBuilder paramBuilder = create();

        final Map<String, Object> map = ReflectionUtil.toMap(object);
        for (String key : map.keySet()) {
            final Object objectInMap = map.get(key);
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

    public ParamBuilder append(String key, List<String> listValue) {
        for (String value : listValue)
            super.add(new BasicNameValuePair(key, value));

        return this;
    }
}
