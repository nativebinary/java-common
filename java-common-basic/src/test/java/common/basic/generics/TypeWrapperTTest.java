package common.basic.generics;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import common.basic.logs.Logger;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeWrapperTTest {

    public static class AAA<T> {
        T t;
    }

    public static class Some {
        public int intValue;
        public String strValue;
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

        return objectMapper;
    }

    @Test
    public void testToJsonString() throws Exception {
        Logger.e("TODO: fix this test below.");
        if(false) {
            Class<?> declaringClass = AAA.class.getDeclaringClass();
            TypeWrapperT<AAA<Some>> typeWrapperT = new TypeWrapperT<AAA<Some>>(){};

            //Class<List<Some>> listClass = typeWrapperT.getClassT();
            //List<Integer>.class;

            AAA<Some> instance = new AAA<Some>();
            Class<? extends AAA> aClass1 = instance.getClass();

            Logger.e(typeWrapperT);

            ParameterizedType parameterizedType = (ParameterizedType) typeWrapperT._type;
            Type rawType = parameterizedType.getRawType();

            Class<? extends Type> aClass = typeWrapperT._type.getClass();

            String json = "{t:{intValue:1, strValue:\"str\"}}";

            ObjectMapper objectMapper = createObjectMapper();

            JavaType javaType = objectMapper.getTypeFactory().constructType(typeWrapperT.getType());
            Type type = objectMapper.readValue(json, aClass);
            Logger.e(type);
        }
    }
}