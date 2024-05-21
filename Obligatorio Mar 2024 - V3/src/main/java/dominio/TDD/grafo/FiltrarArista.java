package dominio.TDD.grafo;

public interface FiltrarArista<T> {
   boolean debeIncluirArista(Arista<T> arista);
}