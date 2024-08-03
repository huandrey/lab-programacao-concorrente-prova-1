package SimuladorRequest.src;

import SimuladorRequest.src.services.AcessoRequest;

public class Main {
  public static void main(String[] args) {
    AcessoController acesso = new AcessoController();

    for (int i = 0; i < 8; i++) {
      int finalI = i;
      new Thread(() -> {
        try {
          AcessoRequest request = new AcessoRequest();
          acesso.externalRoute(request).thenAccept(response -> {
            System.out.println("External Route " + finalI + " completed");
          });
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }

    for (int i = 0; i < 2; i++) {
      int finalI = i;
      new Thread(() -> {
        try {
          AcessoRequest request = new AcessoRequest();
          acesso.internalRoute(request).thenAccept(response -> {
            System.out.println("Internal Route " + finalI + " completed");
          });
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }
  }
}
