package edu.osproject25;

import edu.osproject25.sync.BoundedBuffer;

/**
 * Model representing a process in the scheduling simulation.
 * Acts as a Consumer in the Producer-Consumer pattern.
 * Includes identifiers, input parameters (arrival, burst, priority), and computed fields
 * (waiting, turnaround, completion times).
 */
public class ProcessThread extends Thread {
    private int pid;
    private int arrivalTime;
    private int burstTime;
    private int processPriority;
    private int waitingTime;
    private int turnaroundTime;
    private int completionTime;
    private BoundedBuffer buffer;
    
    public ProcessThread(int pid, int arrivalTime, int burstTime, int processPriority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.processPriority = processPriority;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.completionTime = 0;
    }
    
    public void setBuffer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    /**
     * Thread execution: simulates process arrival and consumes items from buffer.
     * Number of items consumed equals the process burst time.
     */
    @Override
    public void run() {
        try {
            // Simulate arrival time delay before process starts
            if (arrivalTime > 0) {
                Thread.sleep(arrivalTime * 100);
            }
            
            System.out.println("[Process-" + pid + "] Started (Priority: " + processPriority + ")");
            
            // Consume items from buffer based on burst time
            for (int i = 0; i < burstTime; i++) {
                int item = buffer.consume("Process-" + pid);
                Thread.sleep(100); // Processing delay
            }
            
            System.out.println("[Process-" + pid + "] Finished.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getProcessPriority() {
        return processPriority;
    }

    public void setProcessPriority(int priority) {
        this.processPriority = priority;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "pid=" + pid +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                '}';
    }
}
