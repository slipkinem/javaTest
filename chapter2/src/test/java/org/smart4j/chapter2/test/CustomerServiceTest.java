package org.smart4j.chapter2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.helper.DatabaseHelper;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.service.CustomerService;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slipkinem on 6/26/2017.
 */

/**
 * 运行的test则默认会读取test/resources/config.properties里面的数据
 */
public class CustomerServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceTest.class);
    private final CustomerService customerService = new CustomerService();
    private long id = 1;
//    public CustomerServiceTest(CustomerService customerService) {
//        this.customerService = customerService;
//    }
//
    @Before
    public void init () throws IOException {
        // TODO 初始化数据库
        DatabaseHelper.executeSqlFile("sql/customer_init.sql");
    }

    @Test
    public void getCustomerListTest() {
        List<Customer> customerList = customerService.getCustomerList();
        LOGGER.debug(customerList.toString());
        Assert.assertEquals(2, customerList.size());
    }

    private void print (Object... params) {
        System.out.println(Arrays.toString(params));
    }

    @Test
    public void getCustomerTest() {
//        print(1, 2, 3, 4);
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "HAHA");
//        DatabaseHelper.insertEntiry(Customer.class, map);
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomerTest () {
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name", "customer100");
        fieldMap.put("contact", "john");
        fieldMap.put("telephone", "13456124551");
        boolean result = customerService.createCustomer(fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void updateCustomerTest () {
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("contact", "Eric");
        boolean result = customerService.updateCustomer(id, fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomerTest () {
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }
}
