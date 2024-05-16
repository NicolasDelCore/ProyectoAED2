package dominio;
import interfaz.Categoria;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Objects;

public class Pasajero implements Comparable<Pasajero> {

    //Atribs
    private String cedula;
    private int cedulaInt;
    private String nombre;
    private String telefono;
    private Categoria categoria;

    //Constructor
    public Pasajero(String cedula, String nombre, String telefono, Categoria categoria) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.categoria = categoria;
        this.cedulaInt = cedulaIntParse(cedula);
        //System.out.println(cedulaInt); //log parseo de cédula a int
    }

    //Constructor para buscar
    public Pasajero(String cedula){
        this.cedula = cedula;
        this.cedulaInt = cedulaIntParse(cedula);
    }

    //Getters y setters
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getCedula() {
        return cedula;
    }

    public int getCedulaInt() {
        return cedulaInt;
    }

    /*
    //Comentado para no confundir, por ahora no tiene uso, pero en teoría funciona (si se necesita un setCedula por algún req, testear bien)
    public void setCedula(String ced) {
        String cedOrig = this.cedula;
        this.cedula = ced;
        if (!this.esCedulaValida()){
            this.cedula = cedOrig;
            System.out.println("No se pudo cambiar cédula: formato inválido.");
        } else {
            this.cedulaInt = cedulaIntParse(cedula);
        }
    }
    */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //Métodos privados
    //post: parsea a int cédulas válidas, remueve puntos, guiones y dig verificiador, o devuelve -1 si la cédula es inválida
    private int cedulaIntParse(String ced){
        if (this.esCedulaValidaPrivate()) {
            return Integer.parseInt( ced.substring(0, ced.length()-1).replaceAll("[.-]", "") );
        }
        return -1;
    }

    //post: confirma si el formato del atributo string cedula es válido
    private boolean esCedulaValidaPrivate(){
        //NOTAS SOBRE REGEX, porque REGEX es confuso:
        // ^[1-9] el primer caracter debe entre 1 y 9 (sino, .1.111.111-1 sería válido)
        // [0-9]{3}- los siguientes 3 caracteres deben validar [0-9] y luego debe venir un guión
        //Reglas cédula: 1. No puede comenzar por 0, 2. debe validar formatos 111.111-1 o 1.111.111-1

        Pattern pattern = Pattern.compile("(^[1-9]\\.)([0-9]{3}\\.)([0-9]{3}-)([0-9])");
        Matcher matcher = pattern.matcher(this.cedula);
        boolean resultado = matcher.find();

        Pattern pattern2 = Pattern.compile("(^[1-9][0-9]{2}\\.)([0-9]{3}-)[0-9]");
        Matcher matcher2 = pattern2.matcher(this.cedula);
        boolean resultado2 = matcher2.find();

        //System.out.println(this.cedula + " m1 " + resultado + " m2 " + resultado2); //log para troubleshootear
        return resultado || resultado2;
    }

    //Métodos públicos y overrides

    //Este método se expone públicamente sólo por motivos de legibilidad en otras clases
    public boolean esCedulaValida(){
        return this.cedulaInt != -1;
    }

    @Override
    public int compareTo(Pasajero p){
        //return this.cedula.compareTo(p.getCedula()); //Si devuelve 0, son iguales, si devuelve positivo, lo que haya a la izq (this.cedula) es mayor
        return this.cedulaInt - p.getCedulaInt();
    }

    @Override
    public String toString(){
        return cedula + ";" + nombre + ";" + telefono + ";" + categoria.getTexto();
    }
}
