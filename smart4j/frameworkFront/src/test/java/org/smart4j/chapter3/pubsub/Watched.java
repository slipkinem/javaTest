package org.smart4j.chapter3.pubsub;

/**
 * Created by slipkinem on 7/11/2017.
 */
public interface Watched {
    public void addWatcher(Watcher watcher);
    public void removeWatcher(Watcher watcher);
    public void notifyWatchers(String eventName);
}
