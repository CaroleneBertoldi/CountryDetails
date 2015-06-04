package historico;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public enum TipoDeOrdenacao {

  BUBBLESORT {
    
    @Override
    <T> void sort(T[] array, Comparator<? super T> comparator) {
      boolean troca = true;
      T aux;
      while (troca) {
        troca = false;
        for (int i = 0; i < array.length - 1; i++) {
          if (comparator.compare(array[i], array[i + 1]) > 0) {
            aux = array[i];
            array[i] = array[i + 1];
            array[i + 1] = aux;
            troca = true;
          }
        }
      }
    }
    
  },

  QUICKSORT {
    
    @Override
    <T> void sort(T[] array, Comparator<? super T> comparator) {
      ordenar(array, 0, array.length - 1, comparator);
    }

    private <T> void ordenar(T[] array, int inicio, int fim, Comparator<? super T> comparator) {
      if (inicio < fim) {
        int posicaoPivo = separar(array, inicio, fim, comparator);
        ordenar(array, inicio, posicaoPivo - 1, comparator);
        ordenar(array, posicaoPivo + 1, fim, comparator);
      }
    }

    private <T> int separar(T[] array, int inicio, int fim, Comparator<? super T> comparator) {
      T pivo = array[inicio];
      int i = inicio + 1, f = fim;
      while (i <= f) {
        if (comparator.compare(array[i], pivo) <= 0) {
          i++;
        } else if (comparator.compare(pivo, array[f]) < 0) {
          f--;
        } else {
          T troca = array[i];
          array[i] = array[f];
          array[f] = troca;
          i++;
          f--;
        }
      }
      array[inicio] = array[f];
      array[f] = pivo;
      return f;
    }
    
  };

  abstract <T> void sort(T[] array, Comparator<? super T> comparator);

  @SuppressWarnings("unchecked")
  public <T> void sort(List<T> list, Comparator<? super T> comparator) {
    T[] array = (T[]) list.toArray();
    sort(array, (Comparator<? super T>) comparator);
    ListIterator<T> iterator = list.listIterator();
    for (int index = 0; index < array.length; index++) {
      iterator.next();
      iterator.set(array[index]);
    }
  }

}