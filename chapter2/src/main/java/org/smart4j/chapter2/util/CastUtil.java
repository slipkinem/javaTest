package org.smart4j.chapter2.util;

/**
 * Created by slipkinem on 6/27/2017.
 */
public class CastUtil {
    /**
     * 转换字符串
     * @param obj
     * @return
     */
    public static String castString(Object obj) {
        return CastUtil.castString(obj, "");
    }

    public static String castString(Object object, String defaultValue) {
        return object != null ? String.valueOf(object) : defaultValue;
    }

    public static double castDouble(Object object) {
        return castDouble(object, 0);
    }

    public static double castDouble(Object object, double defaultValue) {
        double doubleValue = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                doubleValue = Double.parseDouble(strValue);
            }
        }
        return doubleValue;
    }

    public static long castLong(Object object) {
        return castLong(object, 0);
    }

    public static long castLong(Object object, long defaultValue) {
        long longValue = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                longValue = Long.parseLong(strValue);
            }
        }
        return longValue;
    }

    public static int castInt(Object object) {
        return castInt(object, 0);
    }

    public static int castInt(Object object, int defaultValue) {
        int intValue = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                intValue = Integer.parseInt(strValue);
            }
        }
        return intValue;
    }

    public static boolean castBoolean(Object object) {
        return castBoolean(object, false);
    }

    public static boolean castBoolean(Object object, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (object != null) {
            booleanValue = Boolean.parseBoolean(castString(object));
        }
        return booleanValue;
    }
}
