package edu.osproject25.sync;

public class Producer implements Runnable {

    private final BoundedBuffer buffer;
    private int itemCounter = 1;

    public Producer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        System.out.println("[Producer] Started.");

        try {
            while (itemCounter <= 10) { // produce 10 items
                buffer.produce(itemCounter);
                itemCounter++;

                Thread.sleep(500); // small delay to demonstrate concurrency
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[Producer] Finished.");
    }
}
