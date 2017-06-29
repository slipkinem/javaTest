package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by slipkinem on 6/27/2017.
 */
public class StringUtil {
    public static boolean isEmpty (String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isNotEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
