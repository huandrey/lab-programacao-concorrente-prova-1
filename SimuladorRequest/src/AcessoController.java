package SimuladorRequest.src;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;

import SimuladorRequest.src.services.AcessoRequest;
import SimuladorRequest.src.services.AcessoResponse;
import SimuladorRequest.src.services.AcessoServico;

public class AcessoController {
  Semaphore sem_externo = new Semaphore(4);
  Semaphore sem_interno = new Semaphore(0);
  Semaphore mutex = new Semaphore(1);
  
  int count_externo = 0;

  public CompletableFuture<AcessoResponse> externalRoute(AcessoRequest req) throws InterruptedException {
    sem_externo.acquire();
    mutex.acquire();

    count_externo++;

    if (count_externo % 4 == 0) {
      sem_interno.release();
      count_externo = 0;
    }

    AcessoServico servico = new AcessoServico();
    CompletableFuture<AcessoResponse> response = servico.handle();
    mutex.release();

    return response;
  }

  public CompletableFuture<AcessoResponse> internalRoute(AcessoRequest req) throws InterruptedException {
    sem_interno.acquire();
    AcessoServico servico = new AcessoServico();
    CompletableFuture<AcessoResponse> response = servico.handle();
    sem_externo.release();

    return response;
  }
}
