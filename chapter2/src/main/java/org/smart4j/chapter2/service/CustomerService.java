package org.smart4j.chapter2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.helper.DatabaseHelper;
import org.smart4j.chapter2.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by slipkinem on 6/26/2017.
 */
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    /**
     * 查询所有客户
     * @return
     */
    public List<Customer> getCustomerList() {
        // TODO
        Connection connection = null;

        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try {
            connection = DatabaseHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setContact(resultSet.getString("contact"));
                customer.setName(resultSet.getString("name"));
                customer.setTelephone(resultSet.getString("telephone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setRemark(resultSet.getString("remark"));
                list.add(customer);
            }
            return list;
        } catch (SQLException e) {
            LOGGER.error("sql " + sql + " 执行错误");
            e.printStackTrace();
        } finally {
            DatabaseHelper.closeConnection(connection);
        }

        return null;
    }

    /**
     * 查询某一个客户
     * @param id
     * @return
     */
    public Customer getCustomer(long id) {
        // TODO
        return null;
    }

    /**
     * 创建用户
     * @param fieldMap
     * @return
     */
    public boolean createCustomer (Map<String, Object> fieldMap) {
        // TODO
        return false;
    }

    /**
     * 更新客户
     * @param id
     * @param fieldMap
     * @return
     */
    public boolean updateCustomer (long id, Map<String, Object> fieldMap) {
//        TODO
        return false;
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    public boolean deleteCustomer(long id) {
        // TODO
        return false;
    }
}
