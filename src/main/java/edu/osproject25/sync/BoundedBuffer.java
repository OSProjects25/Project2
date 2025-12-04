package edu.osproject25.sync;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Bounded Buffer implementation for the Producer-Consumer problem.
 * Uses semaphores and a mutex (ReentrantLock) for thread-safe access.
 */
public class BoundedBuffer {
    private final int[] buffer;           // Circular buffer to hold items
    private int in = 0, out = 0;          // Indices for producer (in) and consumer (out)
    private final int capacity;

    // Semaphores for synchronization
    private final Semaphore empty;        // Tracks empty slots (producer waits if 0)
    private final Semaphore full;         // Tracks filled slots (consumer waits if 0)
    private final ReentrantLock mutex;    // Mutex for exclusive access to buffer

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new int[capacity];
        this.empty = new Semaphore(capacity); // Initially all slots are empty
        this.full = new Semaphore(0);         // Initially no items available
        this.mutex = new ReentrantLock(true); // Fair lock to prevent starvation
    }

    /**
     * Adds an item to the buffer. Blocks if buffer is full.
     */
    public void produce(int item) throws InterruptedException {
        // Wait for an empty slot
        System.out.println("[Producer] Waiting for empty slot (empty semaphore)...");
        empty.acquire();
        System.out.println("[Producer] Acquired empty semaphore");

        System.out.println("[Producer] Waiting for mutex lock...");
        mutex.lock();
        System.out.println("[Producer] Acquired mutex lock");
        try {
            buffer[in] = item;
            System.out.println("[Producer] Inserted item " + item + " at index " + in);
            in = (in + 1) % capacity;
        } finally {
            mutex.unlock();
            System.out.println("[Producer] Released mutex lock");
        }

        full.release();
        System.out.println("[Producer] Signaled full semaphore");
    }

    /**
     * Removes an item from the buffer. Blocks if buffer is empty.
     */
    public int consume(String consumerName) throws InterruptedException {
        // Wait for an available item
        System.out.println("[" + consumerName + "] Waiting for item (full semaphore)...");
        full.acquire();
        System.out.println("[" + consumerName + "] Acquired full semaphore");

        int item;
        System.out.println("[" + consumerName + "] Waiting for mutex lock...");
        mutex.lock();
        System.out.println("[" + consumerName + "] Acquired mutex lock");
        try {
            item = buffer[out];
            System.out.println("[" + consumerName + "] Removed item " + item + " from index " + out);
            out = (out + 1) % capacity;
        } finally {
            mutex.unlock();
            System.out.println("[" + consumerName + "] Released mutex lock");
        }

        empty.release();
        System.out.println("[" + consumerName + "] Signaled empty semaphore");
        return item;
    }
}
