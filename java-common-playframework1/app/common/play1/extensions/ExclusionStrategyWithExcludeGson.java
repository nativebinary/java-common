package common.play1.extensions;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import common.basic.utils.StringUtil;
import common.play1.annotations.ExcludeGson;

public class ExclusionStrategyWithExcludeGson implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        if(StringUtil.equals(fieldAttributes.getName(), "relation"))
            return true;

        if(fieldAttributes.getAnnotation(ExcludeGson.class) != null)
            return true;

        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
