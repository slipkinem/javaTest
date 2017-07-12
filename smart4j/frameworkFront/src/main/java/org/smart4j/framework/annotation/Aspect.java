package org.smart4j.framework.annotation;


import java.lang.annotation.*;

/**
 * Aspect 注解
 * Created by slipkinem on 7/10/2017.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
