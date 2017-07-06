package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 * Created by HASEE on 7/2/2017.
 */
public class IocHelper {
    /*
     * <example>
     *     @Service
     *     Public boolean Customer () {
     *
     *          @Inject
     *          TableService tableService;
     *     }
     *     1. 获取 {Customer => customerInstance} 这个Map
     *     2. 遍历Map 获取到Customer 和 customerInstance
     *     3. 获取Customer中声明的变量成员 tableService，是一个Field（字段）数组
     *     4. 遍历 Filed[] 获取到所有字段带 @inject 的field
     *     5. 获取此 field 的类型，是一个Class，也就是间接的获取到了TableService
     *     6. 设置该 field 的值 (field.set(field所属的对象， field要赋的值))
     * </example>
     */
    static {
        // 获取到所有的@Service @Controller的类和实例的映射
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        // Map.Entry返回一个此Map的关系表，直接可以使用
        // beanEntry.getKey()获取key，getValue获取value 可以用于迭代中
        for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
            // beanMap中获取类
            Class<?> beanClass = beanEntry.getKey();
            // 获取beanMap中存储的bean实例
            Object beanInstance = beanEntry.getValue();
//            获取所有的成员变量
            Field[] beanFields = beanClass.getDeclaredFields();
//            如果成员变量数组不为空
            if (ArrayUtil.isNotEmpty(beanFields)) {
                // 循环所有成员变量
                for (Field beanField : beanFields) {
//                    如果成员变量里面存在@Inject注解
                    if (beanField.isAnnotationPresent(Inject.class)) {
                        /*
                         *   获取声明变量的类型，也就是获取了要实例化的类
                         *   @Inject
                         *   private Model model;
                         *   model.getType()直接就获取到了Model
                         */
                        Class<?> beanFieldClass = beanField.getType();
                        // 然后通过beanFiledClass获取获取到此类的实例，在beanMap中
                        // 以 beanClass => beanInstance 这样的方式存储着
                        Object beanFieldInstance = beanMap.get(beanFieldClass);
                        if (beanFieldInstance != null) {
                            // 通过反射机制设置beanField的值
                            // beanInstance 成员变量类的实例
                            // beanField 成员变量字段,也就是成员变量类的实例的属性
                            // beanFieldInstance 成员变量类型的实例 值
                            // beanField.set(beanInstance, beanFieldInstance)
                            ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                        }
                    }
                }
            }
        }
    }
}
