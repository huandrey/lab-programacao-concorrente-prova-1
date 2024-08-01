import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    menu();
  }

  static void menu() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("Escolha uma questão para executar:");
      System.out.println("1. Executar AcessoRequest (questão 1)");
      System.out.println("2. Executar BufferedQueue (questão 2)");
      System.out.println("3. Executar ReadWrite (questao 3)");
      System.out.println("4. Sair");

      int escolha = scanner.nextInt();
      switch (escolha) {
        case 1:
          executaQuestaoUm();
          break;
        case 2:
        executaQuestaoDois();
          break;
        case 3:
          executaQuestaoTres();
          break;
        case 4:
          System.out.println("Saindo...");
          scanner.close();
          System.exit(0);
        default:
          System.out.println("Escolha inválida. Tente novamente.");
      }
    }
  }

  static void executaQuestaoUm() {
    AcessoRequest acesso = new AcessoRequest();

    for (int i = 0; i < 8; i++) {
      int finalI = i;
      new Thread(() -> {
        try {
          acesso.externalRoute(new AcessoRequest.Request());
          System.out.println("External Route " + finalI + " completed");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }

    for (int i = 0; i < 2; i++) {
      int finalI = i;
      new Thread(() -> {
        try {
          acesso.internalRoute(new AcessoRequest.Request());
          System.out.println("Internal Route " + finalI + " completed");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }
  }

  static void executaQuestaoDois() {
    BufferedQueue queue = new BufferedQueue(5);

    for (int i = 0; i < 3; i++) {
      final int producerId = i;
      new Thread(() -> {
        try {
          for (int j = 0; j < 10; j++) {
            queue.enqueue(new BufferedQueue.Item());
            System.out.println("Producer " + producerId + " enqueued item " + j);
            Thread.sleep(500);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }

    for (int i = 0; i < 2; i++) {
      final int consumerId = i;
      new Thread(() -> {
        try {
          for (int j = 0; j < 15; j++) {
            BufferedQueue.Item item = queue.dequeue();
            System.out.println("Consumer " + consumerId + " dequeued item " + j);
            Thread.sleep(1000);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }

  }

  static void executaQuestaoTres() {
    ReadWrite rw = new ReadWrite();
    for (int i = 0; i < 5; i++) {
      final int readerId = i;
      new Thread(() -> {
        try {
          ReadWrite.Data data = rw.wrap_read();
          System.out.println("Reader " + readerId + " read data");
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }

    for (int i = 0; i < 2; i++) {
      final int writerId = i;
      new Thread(() -> {
        try {
          rw.wrap_write(new ReadWrite.Data());
          System.out.println("Writer " + writerId + " wrote data");
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }
  }

}
