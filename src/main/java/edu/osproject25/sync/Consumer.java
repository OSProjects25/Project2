package edu.osproject25.sync;

public class Consumer implements Runnable {

    private final BoundedBuffer buffer;
    private final String name;

    public Consumer(BoundedBuffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("[" + name + "] Started.");

        try {
            for (int i = 0; i < 5; i++) { // each consumer consumes 5 items
                buffer.consume(name);
                Thread.sleep(700); // slow down to show concurrency clearly
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[" + name + "] Finished.");
    }
}
