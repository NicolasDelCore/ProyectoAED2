package dominio;

import interfaz.Categoria;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TuplaTInt<T> {

    //Atribs
    private T dato;
    private int numero;

    //Constructor
    public TuplaTInt(T dato, int numero) {
        this.dato = dato;
        this.numero = numero;
    }

    public T getDato() {
        return this.dato;
    }

    public int getNumero() {
        return this.numero;
    }

}
