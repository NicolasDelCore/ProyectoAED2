package dominio.TDD.grafo;

public class Arista {
    public double peso;

    public Arista(double kilometros) {
        this.peso = kilometros;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Arista{" +
                "kilometros=" + peso +
                '}';
    }
}
