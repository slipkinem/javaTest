package test;

import org.junit.Test;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.util.ArrayUtil;

import java.lang.reflect.Field;

/**
 * Created by HASEE on 7/2/2017.
 */

public class RelectionUtilTest {
    private ArrayUtil configConstant = new ArrayUtil();

    @Test
    public void setFieldTest() throws NoSuchFieldException, IllegalAccessException {
        Field field =
                configConstant.getClass().getDeclaredField("string");
        field.setAccessible(true);
        field.set(configConstant, "121");
        System.out.println(configConstant.getClass());
        System.out.println(field);
        System.out.println(configConstant + "哈哈");
    }

    @Test
    public void testGetServiceClassSet () {
        System.out.println(ClassHelper.getServiceClassSet());
    }

    @Test
    public void testGetClassSet () {
        System.out.println(ClassHelper.getBeanClassSet());
    }
}
