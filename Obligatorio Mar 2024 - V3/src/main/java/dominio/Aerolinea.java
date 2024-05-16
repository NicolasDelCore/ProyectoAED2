package dominio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aerolinea implements Comparable<Aerolinea> {

    //Atribs
    private String nombre;
    private String codigo;

    //Constructor
    public Aerolinea(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    //Getters y setters
    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public int compareTo(Aerolinea a){
        return this.codigo.compareTo(a.getCodigo()); //Si devuelve 0, son iguales, si devuelve positivo, lo que haya a la izq (this.cedula) es mayor
    }

    @Override
    public String toString(){
        return codigo + ";" + nombre;
    }
}
