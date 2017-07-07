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
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    // 通过标识符切割字符串为数组
    public static String[] splitString(String source, char separatorChar) {
        return StringUtils.split(source, separatorChar);
    }
}
