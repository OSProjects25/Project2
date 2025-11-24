package edu.osproject25;

import java.lang.Thread;
/**
 * Model representing a process in the scheduling simulation.
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
    
    public ProcessThread(int pid, int arrivalTime, int burstTime, int processPriority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.processPriority = processPriority;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.completionTime = 0;
    }

    @Override
    public void run() {
        System.out.println("Process " + pid + " started.");
        try {
            Thread.sleep(burstTime * 1000);
        } catch (InterruptedException e) {}
        System.out.println("Process " + pid + " finished.");
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
