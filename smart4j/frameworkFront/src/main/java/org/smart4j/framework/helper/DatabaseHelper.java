package org.smart4j.framework.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.PropsUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by slipkinem on 6/27/2017.
 */
public final class DatabaseHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<Connection>();
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();
    private static final BasicDataSource DATA_SOURCE = new BasicDataSource();

    // 直接执行
    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        String DRIVER = conf.getProperty("jdbc.driver");
        String URL = conf.getProperty("jdbc.url");
        String USERNAME = conf.getProperty("jdbc.username");
        String PASSWORD = conf.getProperty("jdbc.password");
        /* *****
            JDBC连接方式
        try {
            Class.forName(DRIVER); // 动态加载类，然后就能使用类的方法
        } catch (ClassNotFoundException e) {
            LOGGER.error("类没有找到", e.getMessage());
            e.printStackTrace();
        }
         **********/

        /*****************************
         * DBCP连接池连接方式
         * DBCP连接池：方式多次连接和关闭数据库导致的系统开销，用连接池统一进行管理
         */
        DATA_SOURCE.setDriverClassName(DRIVER);
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USERNAME);
        DATA_SOURCE.setPassword(PASSWORD);
    }

    public static Connection getConnection() {
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if (connection == null) {
            try {
                /*
                jdbc方式
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                */
                // dbcp方式
                connection = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("数据库连接失败", e);
            } finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }
        return connection;
    }

    /***
     * 使用dbcp就不需要自己关闭数据库连接了
     public static void closeConnection() {
     Connection connection = CONNECTION_THREAD_LOCAL.get();
     if (connection != null) {
     try {
     connection.close();
     } catch (SQLException e) {
     LOGGER.error("数据库断连失败", e);
     throw new RuntimeException(e);
     } finally {
     CONNECTION_THREAD_LOCAL.remove();
     }
     }
     }
     ****/

    public static <T> List<T> queryEntityList (Class<T> entityClass, String sql, Object... params) {
        List<T> entityList = null;
        Connection connection = getConnection();
        try {
            entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("sql执行失败", sql, e);
            e.printStackTrace();
        }
        return entityList;
    }

    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity = null;
        Connection connection = getConnection();
        try {
            entity = QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("sql执行失败 ", sql, e);
            e.printStackTrace();
        }
        /**
         * dbcp统一管理数据库连接
         finally {
         closeConnection();
         }
         **/
        return entity;
    }

    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
        List<Map<String, Object>> result = null;
        Connection connection = getConnection();
        try {
            result = QUERY_RUNNER.query(connection, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            LOGGER.error("sql执行失败 ", sql, e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 多用途 包括删除，添加，更新
     *
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate (String sql, Object... params) {
        int rows = 0;
        Connection connection = getConnection();
        try {
            rows = QUERY_RUNNER.update(connection, sql, params);
        } catch (SQLException e) {
            LOGGER.error("sql执行失败", sql, e);
            e.printStackTrace();
        }
        return rows;
    }

    /**
     * 将数据库的model => 数据库表名
     *
     * @param clazz
     * @return
     */
    private static String getTableName(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        String tableName;
        StringBuilder stringBuilder = new StringBuilder();

        tableName = String.valueOf(simpleName.charAt(0)).toUpperCase().concat(simpleName.substring(1));
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(tableName);
        while (matcher.find()) {
            String word = matcher.group();
            stringBuilder.append(word.toUpperCase());
            stringBuilder.append(matcher.end() == tableName.length() ? "" : "_");
        }
        return stringBuilder.toString().toLowerCase();
    }

    public static <T> boolean insertEntiry(Class<T> entityClass, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("需要执行的sql数据实体为空");
            return false;
        }
        /**
         * 拼接sql
         * INSERT INTO customer (id, xx) VALUES (?, ?)
         */
        String sql = "INSERT INTO " + getTableName(entityClass);
        StringBuilder columns = new StringBuilder(" (");
        StringBuilder values = new StringBuilder(" (");
        for (String field : fieldMap.keySet()) {
            columns.append(field).append(", ");
            values.append("?, ");
        }
        // 将最后的, 替换为 )
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql += columns + " VALUES " + values;
        System.out.println(sql);
//        过去map的values并转为Array
        Object[] params = fieldMap.values().toArray();
        return 1 == executeUpdate(sql, params);
    }

    public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("要插入的实体数据为null");
            return false;
        }
        /**
         * UPDATE customer SET name = 'xxx', city = 'xx' WHERE id = 'xx'
         */
        String sql = "UPDATE " + getTableName(entityClass) + " SET ";
        StringBuilder payload = new StringBuilder();
        for (String fieldName :
                fieldMap.keySet()) {
            payload.append(fieldName).append("=?, ");
        }
        sql += payload.substring(0, payload.lastIndexOf(", ")) + " WHERE id=?";
        List<Object> paramsList = new ArrayList<Object>();
        paramsList.addAll(fieldMap.values());
        paramsList.add(id);
        Object[] params = paramsList.toArray();
        return 1 == executeUpdate(sql, params);
    }

    public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
        /**
         * DELETE FROM customer WHERE id=?
         */
        String sql = "DELETE FROM " + getTableName(entityClass) + " WHERE id=?";
        return executeUpdate(sql, id) == 1;
    }

    public static void executeSqlFile(String filePath) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String sql;
        try {
            while ((sql = reader.readLine()) != null) {
                executeUpdate(sql);
            }
        } catch (IOException e) {
            LOGGER.error("执行sql语句失败", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 开始事务
     */
    public static void beginTransaction() {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                LOGGER.error("begin transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }
    }

    /**
     * 提交事务
     */
    public static void commitTransaction () {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("commit transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_THREAD_LOCAL.remove();
            }
        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction () {
        Connection connection = getConnection();
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("rollback transaction failure", e);
            throw new RuntimeException(e);
        } finally {
            CONNECTION_THREAD_LOCAL.remove();
        }
    }

}
