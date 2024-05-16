package interfaz;

import java.util.Arrays;
import java.util.Objects;

public enum Categoria {
    PLATINO(0, "Platino"),
    FRECUENTE(1, "Frecuente"),
    ESTANDAR(2, "Estándar");

    private final int indice;
    private final String texto;

    Categoria(int indice, String texto) {
        this.indice = indice;
        this.texto = texto;
    }

    public int getIndice() {
        return indice;
    }

    public String getTexto() {
        return texto;
    }

    public static Categoria fromTexto(String texto) {
        return Arrays.stream(Categoria.values())
                .filter(a -> Objects.equals(a.texto, texto))
                .findFirst()
                .orElse(null);
    }

}
