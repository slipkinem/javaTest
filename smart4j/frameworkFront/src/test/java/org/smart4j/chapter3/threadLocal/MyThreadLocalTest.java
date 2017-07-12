package org.smart4j.chapter3.threadLocal;

import org.smart4j.chapter3.thread.ClientThread;
import org.smart4j.chapter3.thread.Sequence;

/**
 * Created by slipkinem on 7/12/2017.
 */
public class MyThreadLocalTest implements Sequence {
    private static MyThreadLocal<Integer> numberContainer = new MyThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public int getNumber() {
        numberContainer.set(numberContainer.get() + 1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        Sequence sequence = new MyThreadLocalTest();

        ClientThread clientThread = new ClientThread(sequence);
        ClientThread clientThread1 = new ClientThread(sequence);

        clientThread.start();
        clientThread1.start();
    }
}
