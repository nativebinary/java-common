package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

public class CastTest extends Assert {

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new Cast();
    }

    @Test
    public void testAs(){

        final ChildClass childClass = new ChildClass();

        assertNull(Cast.as(childClass,   MotherClass.class));
        assertNull(Cast.as(MotherClass.class, childClass));

        assertSame(childClass,Cast.as(childClass,   FatherClass.class));
        assertSame(childClass,Cast.as(FatherClass.class, childClass));

    }

    @Test
    public void testIs(){

        final ChildClass childClass = new ChildClass();

        assertFalse(Cast.is(childClass,   MotherClass.class));
        assertFalse(Cast.is(MotherClass.class, childClass));

        assertTrue(Cast.is(childClass,   FatherClass.class));
        assertTrue(Cast.is(FatherClass.class, childClass));

    }

}

class FatherClass {
    FatherClass(){}

}
class MotherClass {
    MotherClass(){}

}

class ChildClass extends FatherClass {
    ChildClass(){}
}

