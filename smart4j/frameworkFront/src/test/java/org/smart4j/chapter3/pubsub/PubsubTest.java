package org.smart4j.chapter3.pubsub;

/**
 * Created by slipkinem on 7/11/2017.
 */
public class PubsubTest {
    public static void main(String[] args) {
        Watched girl = new ConcreteWatched();
        Watcher watcher = new ConcreteWatcher();
        Watcher watcher1 = new ConcreteWatcher();

        girl.addWatcher(watcher);
        girl.addWatcher(watcher1);

        girl.notifyWatchers("发出去了");

    }
}
