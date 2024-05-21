package dominio.TDD.grafo;

import java.util.Objects;

public class Vertice<T> {
    private T dato;

    public Vertice(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public void setDato (T dato) {
        this.dato = dato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertice vertice = (Vertice) o;
        return Objects.equals(dato, vertice.dato);
    }

    @Override
    public String toString() {
        return "Vertice{" +
                "dato='" + dato + '\'' +
                '}';
    }

}