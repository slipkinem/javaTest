package org.smart4j.chapter3.thread;

/**
 * Created by slipkinem on 7/11/2017.
 */
public class SequenceTest implements Sequence {
    private static int number = 0;
    public int getNumber() {
        return ++number;
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceTest();
        ClientThread thread = new ClientThread(sequence);
        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);

        thread.start();
        thread2.start();
        thread1.start();
    }

}
