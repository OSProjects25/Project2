package edu.osproject25.sync;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    private final int[] buffer;
    private int in = 0, out = 0;
    private final int capacity;

    private final Semaphore empty;
    private final Semaphore full;
    private final ReentrantLock mutex;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new int[capacity];
        this.empty = new Semaphore(capacity); // slots available
        this.full = new Semaphore(0);         // items available
        this.mutex = new ReentrantLock(true);
    }

    public void produce(int item) throws InterruptedException {
        System.out.println("[Producer] Waiting for empty slot...");
        empty.acquire();

        mutex.lock();
        try {
            buffer[in] = item;
            System.out.println("[Producer] Inserted item " + item + " at index " + in);
            in = (in + 1) % capacity;
        } finally {
            mutex.unlock();
        }

        full.release();
    }

    public int consume(String consumerName) throws InterruptedException {
        System.out.println("[" + consumerName + "] Waiting for item...");
        full.acquire();

        int item;
        mutex.lock();
        try {
            item = buffer[out];
            System.out.println("[" + consumerName + "] Removed item " + item + " from index " + out);
            out = (out + 1) % capacity;
        } finally {
            mutex.unlock();
        }

        empty.release();
        return item;
    }
}
