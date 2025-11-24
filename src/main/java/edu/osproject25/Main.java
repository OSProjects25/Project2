package edu.osproject25;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String processes = "processes.txt";
        List<ProcessThread> processData = ProcessUtils.ReadProcessInfo(processes);
        for (ProcessThread thread : processData) {
            thread.start();
        }
    }
}