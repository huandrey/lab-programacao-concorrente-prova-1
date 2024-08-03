package SimuladorRequest.src.services;

import java.util.concurrent.CompletableFuture;

public class AcessoServico {
  
  public CompletableFuture<AcessoResponse> handle() throws InterruptedException {
      System.out.println(Thread.currentThread().getName() + " - Iniciando a requisição...");
      CompletableFuture<AcessoResponse> futureResponse = new CompletableFuture<>();
      new Thread(() -> {
        try {
          Thread.sleep(2000);
          System.out.println(Thread.currentThread().getName() + " - Resposta recebida");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        AcessoResponse response = new AcessoResponse(StatusCodeResponse.SUCCESS);
        futureResponse.complete(response);
      }).start();
      System.out.println(Thread.currentThread().getName() + " - Requisição completada");
      return futureResponse;
    }
}
