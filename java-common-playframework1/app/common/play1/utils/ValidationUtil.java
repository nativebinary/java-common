package common.play1.utils;

import common.basic.interfaces.ICallbackTransform;
import common.basic.utils.ListUtil;
import common.basic.utils.StringUtil;
import play.data.validation.Error;
import play.data.validation.Validation;
import play.mvc.Scope;

import java.util.List;

public class ValidationUtil {
    public static List<Error> getListError(Scope.Params params) {
        if (!Validation.hasErrors())
            return null;

        params.flash(); // add http parameters to the flash scope
        return Validation.errors();
    }

    public static String getMessageJoined(List<Error> listError) {
        return StringUtil.join(",", ListUtil.transform(listError, new ICallbackTransform<Error, String>() {
            @Override
            public String transform(Error error) {
                return error.message(error.getKey());
            }
        }));
    }

    public static String checkJoined(Scope.Params params) {
        final List<Error> listError = ValidationUtil.getListError(params);
        if(ListUtil.isNullOrEmpty(listError))
            return null;

        return ValidationUtil.getMessageJoined(listError);
    }
}
