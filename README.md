# Operating Systems Project 2 — Thread Simulation & Synchronization

## Part 1 — Process Simulation

This part simulates CPU burst execution using Java threads.

- Each process in `processes.txt` becomes a `ProcessThread`.
- The program reads: `pid arrival burst priority`.
- Each process thread acts as a **consumer** in the Producer-Consumer simulation.
- Processes consume items from a shared buffer based on their burst time.
- Output shows when each process starts, acquires locks, and finishes.

### Example:
Process 1 started.
Process 1 finished.

---

## Part 2 — Producer–Consumer Synchronization

This project implements the classic **Producer–Consumer** problem using:

- A bounded circular buffer (size 5)
- Java `Semaphore` for controlling full/empty slots
- `ReentrantLock` for mutual exclusion
- One producer thread
- Five consumer threads (the 5 processes from `processes.txt`)

All thread activity is logged to the console as required.

### Synchronization Guarantees:
- Producer waits when the buffer is full.
- Consumers wait when the buffer is empty.
- Mutex ensures only one thread modifies the buffer at a time.
- Items are inserted and removed in FIFO order.

### How to Run:
Run `Main.java` from `src/main/java/edu/osproject25/`.

Or use PowerShell:
```powershell
.\run.ps1
```

### Example Output:
```
=== PRODUCER-CONSUMER SIMULATION WITH PROCESSES ===
Loaded 5 processes, total burst time: 20
[Process-1] Started (Priority: 2)
[Producer] Waiting for empty slot (empty semaphore)...
[Producer] Acquired empty semaphore
[Producer] Waiting for mutex lock...
[Producer] Acquired mutex lock
[Producer] Inserted item 1 at index 0
[Producer] Released mutex lock
[Process-1] Waiting for item (full semaphore)...
[Process-1] Acquired mutex lock
[Process-1] Removed item 1 from index 0
[Process-1] Released mutex lock
...
=== SIMULATION COMPLETE ===
```


---

## Team Members
- A.Y.
- Raphael
