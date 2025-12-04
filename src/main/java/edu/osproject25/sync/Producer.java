package edu.osproject25.sync;

public class Producer implements Runnable {

    private final BoundedBuffer buffer;
    private final int itemsToProduce;
    private int itemCounter = 1;

    public Producer(BoundedBuffer buffer) {
        this(buffer, 10); // default to 10 items
    }
    
    public Producer(BoundedBuffer buffer, int itemsToProduce) {
        this.buffer = buffer;
        this.itemsToProduce = itemsToProduce;
    }

    @Override
    public void run() {
        System.out.println("[Producer] Started. Will produce " + itemsToProduce + " items.");

        try {
            while (itemCounter <= itemsToProduce) {
                buffer.produce(itemCounter);
                itemCounter++;

                Thread.sleep(100); // small delay to demonstrate concurrency
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[Producer] Finished.");
    }
}
