import java.util.concurrent.Semaphore;

class AcessoRequest {
    Semaphore sem_externo = new Semaphore(4);
    Semaphore sem_interno = new Semaphore(0);
    Semaphore mutex = new Semaphore(1);

    int count_externo = 0;

    private void handle() {
        System.out.println("do something");
    }

    public void externalRoute(Request req) throws InterruptedException {
        sem_externo.acquire();
        mutex.acquire();

        count_externo++;

        if (count_externo % 4 == 0) {
            sem_interno.release();
            count_externo = 0;
        }

        mutex.release();
        handle();
    }

    public void internalRoute(Request req) throws InterruptedException {
        sem_interno.acquire();
        handle();
        sem_externo.release();
    }

    static class Request {
        Request() {}
    }
}
