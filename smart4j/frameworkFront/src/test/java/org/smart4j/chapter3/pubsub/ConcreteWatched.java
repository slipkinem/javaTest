package org.smart4j.chapter3.pubsub;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slipkinem on 7/11/2017.
 */
public class ConcreteWatched implements Watched {
    private List<Watcher> list = new ArrayList<Watcher>();

    public void addWatcher(Watcher watcher) {
        list.add(watcher);
    }

    public void removeWatcher(Watcher watcher) {
        list.remove(watcher);
    }

    public void notifyWatchers(String eventName) {
        for (Watcher watcher : list) {
            watcher.update(eventName);
        }
    }
}
