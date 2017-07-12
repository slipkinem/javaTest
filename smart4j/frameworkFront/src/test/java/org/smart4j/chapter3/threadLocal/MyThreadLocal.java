package org.smart4j.chapter3.threadLocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by slipkinem on 7/12/2017.
 */
public class MyThreadLocal<T> {
    private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<Thread, T>());

    public void set (T value) {
        System.out.println(Thread.currentThread());
        container.put(Thread.currentThread(), value);
    }

    public T get () {
        Thread thread = Thread.currentThread();
        T value = container.get(thread);
        if (value == null && !container.containsKey(thread)) {
            value = initialValue();
            container.put(thread, value);
        }
        return value;
    }

    public void remove () {
        Thread thread = Thread.currentThread();
        if (container.containsKey(thread)) {
            container.remove(thread);
        }
    }

    protected T initialValue () {
        return null;
    }
}
