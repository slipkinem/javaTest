package org.smart4j.chapter2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by slipkinem on 6/26/2017.
 */
public class PropsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 读取properties
     * @param fileName
     * @return
     */
    public static Properties loadProps (String fileName) {
        Properties properties = null;
        InputStream inputStream = null;
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new FileNotFoundException(fileName + " file is not defined");
            }
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("load properties file failure", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure", e);
                }
            }
        }
        return properties;
    }

    /**
     * 获取properties文件的key
     * @param properties
     * @param key
     * @return
     */
    public static String getString(Properties properties, String key) {
        return getString(properties, key, "");
    }

    /**
     * 可配置默认值
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties properties, String key, String defaultValue) {
        String value = defaultValue;
        if (properties.containsKey(key)) {
            value = properties.getProperty(key);
        }
        return value;
    }

    /**
     * 获取int类型
     * @param properties
     * @param key
     * @return
     */
    public static int getInt(Properties properties, String key) {
        return getInt(properties, key, 0);
    }

    public static int getInt(Properties properties, String key, int defaultValue) {
        int value = defaultValue;
        if (properties.containsKey(key)) {
            value = CastUtil.castInt(properties.getProperty(key));
        }
        return value;
    }

    /**
     * 获取boolean类型
     * @param properties
     * @param key
     * @return
     */
    public static boolean getBoolean(Properties properties, String key) {
        return getBoolean(properties, key, false);
    }

    public static boolean getBoolean(Properties properties, String key, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (properties.containsKey(key)) {
            booleanValue = CastUtil.castBoolean(properties.getProperty(key));
        }
        return booleanValue;
    }

}
