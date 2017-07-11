package org.smart4j.chapter3.pubsub;

/**
 * Created by slipkinem on 7/11/2017.
 */
public interface Watcher {
    public void update(String eventName);
}
