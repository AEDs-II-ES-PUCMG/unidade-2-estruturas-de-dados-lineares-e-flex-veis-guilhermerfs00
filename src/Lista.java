import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lista<E> implements Iterable<E> {

    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public Lista() {
        Celula<E> sentinela = new Celula<>();
        primeiro = ultimo = sentinela;
        tamanho = 0;
    }

    public boolean vazia() {
        return primeiro == ultimo;
    }

    public int tamanho() {
        return tamanho;
    }

    public void inserirFinal(E item) {
        Celula<E> nova = new Celula<>(item);
        ultimo.setProximo(nova);
        ultimo = nova;
        tamanho++;
    }

    public void inserirInicio(E item) {
        Celula<E> nova = new Celula<>(item, primeiro.getProximo());
        if (vazia()) ultimo = nova;
        primeiro.setProximo(nova);
        tamanho++;
    }

    public E removerInicio() {
        if (vazia()) throw new NoSuchElementException("Lista vazia!");
        Celula<E> aux = primeiro.getProximo();
        primeiro.setProximo(aux.getProximo());
        if (primeiro == ultimo) aux = primeiro;
        aux.setProximo(null);
        tamanho--;
        return primeiro.getItem();
    }

    public void imprimir() {
        if (vazia()) {
            System.out.println("A lista está vazia!");
        } else {
            Celula<E> aux = primeiro.getProximo();
            while (aux != null) {
                System.out.println(aux.getItem());
                aux = aux.getProximo();
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Celula<E> atual = primeiro.getProximo();

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E item = atual.getItem();
                atual = atual.getProximo();
                return item;
            }
        };
    }

    public E buscarPor(Comparator<E> criterioDeBusca, E item) {
        for (E atual : this) {
            if (criterioDeBusca.compare(atual, item) == 0) {
                return atual;
            }
        }
        return null;
    }

    public double somarMultiplicacoes(Function<E, Double> extratorValor, Function<E, Integer> extratorFator){

        double soma = 0.0;
        for (E atual : this) {
            soma += extratorValor.apply(atual) * extratorFator.apply(atual);
        }
        return soma;
    }

    public Lista<E> filtrar(Predicate<E> condicional) {
        if (vazia()) throw new IllegalStateException("Lista vazia!");

        Lista<E> filtrada = new Lista<>();
        for (E atual : this) {
            if (condicional.test(atual)) {
                filtrada.inserirFinal(atual);
            }
        }
        return filtrada;
    }
}
