package edu.osproject25;

import edu.osproject25.sync.BoundedBuffer;
import edu.osproject25.sync.Producer;

import java.util.List;

/**
 * Main entry point for the Producer-Consumer simulation.
 * Loads processes from file and runs them as consumer threads
 * competing for items produced by a single producer thread.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRODUCER-CONSUMER SIMULATION WITH PROCESSES ===");
        
        String processes = "processes.txt";
        List<ProcessThread> processData = ProcessUtils.ReadProcessInfo(processes);
        
        // Calculate total items needed (sum of all burst times)
        int totalItems = processData.stream().mapToInt(ProcessThread::getBurstTime).sum();
        System.out.println("Loaded " + processData.size() + " processes, total burst time: " + totalItems);
        
        // Create shared buffer
        BoundedBuffer buffer = new BoundedBuffer(5);
        
        // Set buffer for each process thread (they act as consumers)
        for (ProcessThread process : processData) {
            process.setBuffer(buffer);
        }
        
        // Create and start producer thread that produces totalItems
        Thread producer = new Thread(new Producer(buffer, totalItems));
        producer.start();
        
        // Start all process threads (consumers)
        for (ProcessThread process : processData) {
            process.start();
        }
        
        // Wait for all threads to complete
        try {
            producer.join();
            for (ProcessThread process : processData) {
                process.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("=== SIMULATION COMPLETE ===");
    }
}