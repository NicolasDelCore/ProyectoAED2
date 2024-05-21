package dominio.Clases;

import java.util.Objects;

public class Aeropuerto implements Comparable<Aeropuerto> {

    //Atribs
    private String nombre;
    private String codigo;

    //Constructor
    public Aeropuerto(String nombre, String codigo) {
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
    public int compareTo(Aeropuerto a){
        return this.codigo.compareTo(a.getCodigo()); //Si devuelve 0, son iguales, si devuelve positivo, lo que haya a la izq (this.cedula) es mayor
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aeropuerto aeropuerto = (Aeropuerto) o;
        return Objects.equals(codigo, aeropuerto.codigo);
    }

    @Override
    public String toString(){
        return codigo + ";" + nombre;
    }
}
