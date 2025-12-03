# Operating Systems Project 2 — Thread Simulation & Synchronization

## Part 1 — Process Simulation

This part simulates CPU burst execution using Java threads.

- Each process in `processes.txt` becomes a `ProcessThread`.
- The program reads: `pid arrival burst priority`.
- Each thread “runs” by sleeping for its burst time.
- Output shows when each process starts and finishes.

### Example:
Process 1 started.
Process 1 finished.

---

## Part 2 — Producer–Consumer Synchronization

This project implements the classic **Producer–Consumer** problem using:

- A bounded circular buffer
- Java `Semaphore` for controlling full/empty slots
- `ReentrantLock` for mutual exclusion
- One producer thread
- Two consumer threads

All thread activity is logged to the console as required.

### Synchronization Guarantees:
- Producer waits when the buffer is full.
- Consumers wait when the buffer is empty.
- Mutex ensures only one thread modifies the buffer at a time.
- Items are inserted and removed in FIFO order.

### How to Run:
Run:
ProducerConsumerMain.java

from `src/main/java/edu/osproject25/sync`.

### Example Output:
=== PRODUCER CONSUMER SIMULATION START ===
[Producer] Waiting for empty slot...
[Producer] Inserted item 1 at index 0
[Consumer-1] Waiting for item...
[Consumer-1] Removed item 1 from index 0
...
=== SIMULATION COMPLETE ===


---

## Team Members
- A.Y.
- Raphael
