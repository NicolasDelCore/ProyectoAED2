package dominio.TDD;

import java.util.Iterator;

public class Lista<T> {

    private NodoLista<T> inicio;
    private int largo;

    public Lista() {
        this.inicio = null;
        this.largo = 0;
    }

    public void insertar(T dato) {
        inicio = new NodoLista<T>(dato, inicio);
        largo++;
    }

    public int largo() {
        return largo;
    }

    public boolean existe(T dato) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.dato.equals(dato)) {
                return true;
            }
            aux = aux.sig;
        }
        return false;
    }

    public T recuperar(T dato) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.dato.equals(dato)) {
                return aux.dato;
            }
            aux = aux.sig;
        }
        return null;
    }

    public boolean esVacia() {
        return largo == 0;
    }


    public void imprimirDatos() {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.sig != null){
                System.out.print(aux.dato + " -> ");
            }else{
                System.out.print(aux.dato);
            }
            aux = aux.sig;
        }
        System.out.println();
    }


    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoLista<T> aux = inicio;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T dato = aux.dato;
                aux = aux.sig;
                return dato;
            }

            @Override
            public void remove() {
            }

        };
    }

    public T devolverPos(int posicion){
        if (posicion == 0){
            return inicio.dato;
        }
        NodoLista<T> nodo = inicio.sig;
        for(int i = 1; i < this.largo() && i <= posicion; i++){
            if (i == posicion){
                return nodo.dato;
            }
        }
        return null;
    }

    private class NodoLista<Q> {
        private Q dato;
        private NodoLista<Q> sig;

        public NodoLista(Q dato) {
            this.dato = dato;
            this.sig = null;
        }

        public NodoLista(Q dato, NodoLista<Q> sig) {
            this.dato = dato;
            this.sig = sig;
        }

        @Override
        public String toString() {
            return dato.toString();
        }
    }
}
