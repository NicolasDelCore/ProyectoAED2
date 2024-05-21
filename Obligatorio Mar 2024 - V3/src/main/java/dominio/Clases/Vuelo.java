package dominio.Clases;

public class Vuelo implements Comparable<Vuelo> {

    //Atribs
    private String codigoDeVuelo;
    private String codigoAeropuertoOrigen;
    private String codigoAeropuertoDestino;
    private String codigoAerolinea;
    private double combustible;
    private double minutos;
    private double costoEnDolares;

    //Constructor
    public Vuelo(String codigoDeVuelo, String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoAerolinea, double combustible, double minutos, double costoEnDolares) {
        this.codigoDeVuelo = codigoDeVuelo;
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
        this.codigoAerolinea = codigoAerolinea;
        this.combustible = combustible;
        this.minutos = minutos;
        this.costoEnDolares = costoEnDolares;
    }

    //Getters y setters

    public String getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public double getCostoEnDolares() {
        return costoEnDolares;
    }

    public double getMinutos() {
        return minutos;
    }

    public double getCombustible() {
        return combustible;
    }

    public String getCodigoAerolinea() {
        return codigoAerolinea;
    }

    public String getCodigoAeropuertoDestino() {
        return codigoAeropuertoDestino;
    }

    public String getCodigoAeropuertoOrigen() {
        return codigoAeropuertoOrigen;
    }

    @Override
    public int compareTo(Vuelo vuelo){
        return
                (this.codigoDeVuelo.compareTo(vuelo.codigoDeVuelo) +
                this.codigoAeropuertoOrigen.compareTo(vuelo.codigoAeropuertoOrigen) +
                this.codigoAeropuertoDestino.compareTo(vuelo.codigoAeropuertoDestino) ) ; //Si devuelve 0, son iguales, si devuelve positivo, lo que haya a la izq (this.cedula) es mayor
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return this.compareTo(vuelo) == 0;
    }

    @Override
    public String toString(){
        return codigoDeVuelo + ";" + codigoAeropuertoOrigen + ";" + codigoAeropuertoDestino;
    }
}
