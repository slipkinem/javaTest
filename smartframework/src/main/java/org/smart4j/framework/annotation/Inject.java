package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by slipkinem on 6/30/2017.
 */

/**
 * Target 注解作用的目标
 *      - CONSTRUCTOR 构造器
 *      - FIELD 成员变量，对象，属性 包括enum实例
 *      - LOCAL_VARIABLE 局部变量
 *      - METHOD 描述方法
 *      - PACKAGE 描述包
 *      - PARAMETER 描述参数
 *      - TYPE 描述类，接口，enum声明
 */
@Target(ElementType.FIELD)
/**
 * retention 注解生命周期，表示注解在什么时候编译到字节码，什么时候丢弃
 *      - SOURCE 编译阶段丢弃 javac
 *      - CLASS 类加载的时候丢弃 缺省值
 *      - RUNTIME 始终不会丢弃，可以使用反射机制读取该注解信息
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
