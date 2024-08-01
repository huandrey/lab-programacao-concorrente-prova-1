import java.util.concurrent.Semaphore;

public class ReadWrite {
    Semaphore read_count_lock = new Semaphore(1);
    Semaphore write_lock = new Semaphore(1);
    Semaphore priority_lock = new Semaphore(1);
    int read_count = 0;

    Data read() {
        System.out.println("Reading data");
        return new Data();
    }

    void write() {
        System.out.println("Writing data");
    }

    Data wrap_read() throws InterruptedException {
        priority_lock.acquire();
        priority_lock.release();

        read_count_lock.acquire();
        read_count++;
        if (read_count == 1) {
            write_lock.acquire();
        }
        read_count_lock.release();

        Data data = read();

        read_count_lock.acquire();
        read_count--;
        if (read_count == 0) {
            write_lock.release();
        }
        read_count_lock.release();

        return data;
    }

    void wrap_write(Data data) throws InterruptedException {
        priority_lock.acquire();
        write_lock.acquire();

        write();

        write_lock.release();
        priority_lock.release();
    }

    static class Data {}
}
