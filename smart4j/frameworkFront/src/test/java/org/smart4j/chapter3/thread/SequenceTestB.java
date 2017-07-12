package org.smart4j.chapter3.thread;

/**
 * 排序测试类
 * Created by slipkinem on 7/11/2017.
 */
public class SequenceTestB implements Sequence {
    private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue () {
            return 0;
        }
    };

    public int getNumber () {
        numberContainer.set(numberContainer.get() + 1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceTestB();

        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
