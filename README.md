# Repositório com as resoluções da [prova 1](https://drive.google.com/file/d/1ddFqzo4ukxxLWm_2iQjktrBzymDNb1Hj/view) de Programação Concorrente - Estágio 1

# Questão 1:
- Implementação de uma classe Java para gerenciar acesso simultâneo a rotas externas e internas usando semáforos;

# Questão 2:
- Implementação de uma fila com capacidade limitada, utilizando semáforos para gerenciar o acesso concorrente e garantir a exclusão mútua ao acessar a fila;

# Questão 3:
- Implementação de uma solução para o problema de leitores-escritores, onde múltiplos leitores podem ler o recurso simultaneamente, mas apenas um escritor pode modificar o recurso por vez, e escritores têm prioridade sobre leitores;

# Para rodar:
O código já está compilado, então basta rodar o comando:

```
java -cp bin Main
```

Caso você queria fazer alguma alteração no código, rode o seguinte comando após as modificações para que assim você consiga compilar o código corretamente e executá-lo:

```
javac -d bin -sourcepath src src/*.java
```
