package dominio.TDD.grafo;
import dominio.TDD.Lista;

public class Arista<T> {
    public double peso;
    public Lista<T> elementos = new Lista<>();

    public Arista(double peso) {
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void agregarALista(T dato) {
        elementos.insertar(dato);
    }

    public boolean estaEnLista(T dato){
        return elementos.existe(dato);
    }

    @Override
    public String toString() {
        return "Arista{" +
                "kilometros=" + peso +
                '}';
    }
}
