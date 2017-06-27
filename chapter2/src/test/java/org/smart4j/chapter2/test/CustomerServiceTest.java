package org.smart4j.chapter2.test;

import org.junit.Assert;
import org.junit.Test;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slipkinem on 6/26/2017.
 */
public class CustomerServiceTest {
    private final CustomerService customerService = new CustomerService();

//    public CustomerServiceTest(CustomerService customerService) {
//        this.customerService = customerService;
//    }
//
//    @Before
//    public void init () {
//        // TODO 初始化数据库
//    }

    @Test
    public void getCustomerListTest() {
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(4, customerList.size());
    }

    @Test
    public void getCustomerTest() {
        long id = 1;
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
        long id = 1;
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("contact", "Eric");
        boolean result = customerService.updateCustomer(id, fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomerTest () {
        long id = 1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }
}
