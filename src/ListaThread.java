import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListaThread {
    private final List<Integer> lista = new ArrayList<>();
    private final Random random = new Random();

    public static void main(String[] args) {
        ListaThread exemplo = new ListaThread();
        exemplo.iniciarThreads();
    }

    public void iniciarThreads() {
        // Duas threads para adicionar valores aleatórios
        Thread adicionar1 = new Thread(() -> adicionarValores());
        Thread adicionar2 = new Thread(() -> adicionarValores());

        // Uma thread para remover o primeiro valor
        Thread remover = new Thread(() -> removerValor());

        // Uma thread para imprimir os valores da lista
        Thread imprimir = new Thread(() -> imprimirValores());

        adicionar1.start();
        adicionar2.start();
        remover.start();
        imprimir.start();
    }

    private void adicionarValores() {
        while (true) {
            synchronized (lista) {
                int valor = random.nextInt(100);
                lista.add(valor);
                System.out.println(Thread.currentThread().getName() + " adicionou: " + valor);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void removerValor() {
        while (true) {
            synchronized (lista) {
                if (!lista.isEmpty()) {
                    int valor = lista.remove(0);
                    System.out.println(Thread.currentThread().getName() + " removeu: " + valor);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void imprimirValores() {
        while (true) {
            synchronized (lista) {
                if (!lista.isEmpty()) {
                    System.out.println("Lista atual: " + lista);
                } else {
                    System.out.println("Lista está vazia.");
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
