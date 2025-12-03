package edu.osproject25.sync;

public class ProducerConsumerMain {
    public static void main(String[] args) {

        System.out.println("=== PRODUCER CONSUMER SIMULATION START ===");

        BoundedBuffer buffer = new BoundedBuffer(5);

        Thread producer = new Thread(new Producer(buffer));
        Thread consumer1 = new Thread(new Consumer(buffer, "Consumer-1"));
        Thread consumer2 = new Thread(new Consumer(buffer, "Consumer-2"));

        producer.start();
        consumer1.start();
        consumer2.start();

        try {
            producer.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("=== SIMULATION COMPLETE ===");
    }
}
