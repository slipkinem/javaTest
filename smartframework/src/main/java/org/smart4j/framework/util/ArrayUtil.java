package org.smart4j.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by HASEE on 7/2/2017.
 */
public class ArrayUtil {
    private String string = "";
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }

    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }
}
