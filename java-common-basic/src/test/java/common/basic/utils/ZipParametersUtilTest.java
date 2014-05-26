package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

public class ZipParametersUtilTest extends Assert {

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new ZipParametersUtil();
    }

   @Test
    public void testCreateStore(){
         //Todo
//       ZipParameters zipParametersMimeType = new ZipParameters();
//       assertEquals(zipParametersMimeType,ZipParametersUtil.createStore("mimetype"));
    }

}
