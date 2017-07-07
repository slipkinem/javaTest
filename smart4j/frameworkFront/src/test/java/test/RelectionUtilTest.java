package test;

import org.junit.Test;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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

    @Test
    public void invokeMethod () {
        try {
            Class<?> cls = Class.forName("org.smart4j.chapter3.controller.InvokeTest");
            Object instance = cls.newInstance();
            Method m = cls.getMethod("getInteger");
//            Method method = InvokeTest.class.getDeclaredMethod("getInteger");
            System.out.println(ReflectionUtil.invokeMethod(instance, m, 10));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("获取方法失败");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
