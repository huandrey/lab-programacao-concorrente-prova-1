import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class BufferedQueue {
    int capacity;
    private final Queue<Item> queue;
    Semaphore mutex;
    Semaphore slots_vazios;
    Semaphore slots_cheios;

    BufferedQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.mutex = new Semaphore(1);
        this.slots_vazios = new Semaphore(capacity);
        this.slots_cheios = new Semaphore(0);
    }

    void enqueue(Item item) throws InterruptedException {
        slots_vazios.acquire();
        mutex.acquire();

        queue.add(item);
        System.out.println("Item enqueued");

        mutex.release();
        slots_cheios.release();
    }

    Item dequeue() throws InterruptedException {
        slots_cheios.acquire();
        mutex.acquire();

        Item item = queue.poll();
        System.out.println("Item dequeued");

        mutex.release();
        slots_vazios.release();

        return item;
    }

    static class Item {
        Item() {}
    }
}
