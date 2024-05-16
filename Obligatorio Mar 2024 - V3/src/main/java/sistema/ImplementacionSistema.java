package sistema;

import interfaz.*;
import dominio.*;

import java.util.Objects;

public class ImplementacionSistema implements Sistema {

    private int maxAeropuertos;
    private int maxAerolineas;
    private int aerolineasRegistradas;
    private int aeropuertosRegistrados;
    ABB<Pasajero> pasajeros = new ABB<Pasajero>();
    ABB<Pasajero> pasajerosPlatino = new ABB<Pasajero>();
    ABB<Pasajero> pasajerosFrecuente = new ABB<Pasajero>();
    ABB<Pasajero> pasajerosEstandar = new ABB<Pasajero>();
    ABB<Aerolinea> aerolineas = new ABB<Aerolinea>();
    ABB<Aeropuerto> aeropuertos = new ABB<Aeropuerto>();

    @Override
    public Retorno inicializarSistema(int maxAeropuertos, int maxAerolineas) {

        if (maxAeropuertos <= 5){
            return Retorno.error(Retorno.Resultado.ERROR_1, "Error 1: No se permiten 5 o menos aeropuertos.");
        }

        if (maxAerolineas <= 3){
            return Retorno.error(Retorno.Resultado.ERROR_2, "Error 2: No se permiten 3 o menos aerolineas.");
        }

        this.maxAerolineas = maxAerolineas;
        this.maxAeropuertos = maxAeropuertos;

        return Retorno.ok();
    }

    @Override
    public Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria) {

        if (Objects.equals(cedula, "") || cedula == null || Objects.equals(nombre, "") || nombre == null || Objects.equals(telefono, "") || telefono == null || categoria == null){
            return Retorno.error(Retorno.Resultado.ERROR_1, "Error 1: Parámetro vacío. Debe rellenar cédula, nombre, teléfono y categoría.");
        }

        Pasajero p = new Pasajero(cedula, nombre, telefono, categoria);

        if(!p.esCedulaValida()) {
            return Retorno.error(Retorno.Resultado.ERROR_2, "Error 2: Cédula inválida.");
        }

        if (pasajeros.pertenece(p)){
            return Retorno.error(Retorno.Resultado.ERROR_3, "Error 3: Ya existe un pasajero con esa cédula. Las cédulas deben ser únicas en el sistema.");
        }

        pasajeros.insertar(p); //System.out.println(pasajeros.listarAscendentes());

        switch(categoria){
            case ESTANDAR:
                pasajerosEstandar.insertar(p);
                break;
            case FRECUENTE:
                pasajerosFrecuente.insertar(p);
                break;
            case PLATINO:
                pasajerosPlatino.insertar(p);
                break;
        }

        return Retorno.ok();
    }

    @Override
    public Retorno buscarPasajero(String cedula) {

        if (Objects.equals(cedula, "") || cedula == null) {
            return Retorno.error(Retorno.Resultado.ERROR_1, "Error 1: Debe proveer una cédula para buscar un pasajero.");
        }

        Pasajero aBuscar = new Pasajero(cedula);

        if (!aBuscar.esCedulaValida()){
            return Retorno.error(Retorno.Resultado.ERROR_2, "Error 2: Cédula en formato inválida.");
        }

        TuplaTInt encontrado = pasajeros.encontrar(aBuscar);

        if (encontrado.getDato() == null){
            return Retorno.error(Retorno.Resultado.ERROR_3, "Error 3: No existe un pasajero con esa cédula.");
        }

        return Retorno.ok(encontrado.getNumero(), encontrado.getDato().toString());
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        return Retorno.ok(pasajeros.listarAscendentes());
    }

    @Override
    public Retorno listarPasajerosPorCategoria(Categoria categoria) {
        switch(categoria){
            case ESTANDAR:
                return Retorno.ok( pasajerosEstandar.listarAscendentes() );
            case FRECUENTE:
                return Retorno.ok( pasajerosFrecuente.listarAscendentes() );
            case PLATINO:
                return Retorno.ok( pasajerosPlatino.listarAscendentes() );
            default:
                return Retorno.ok( "" );
        }
    }

    @Override
    public Retorno registrarAerolinea(String codigo, String nombre) {

        if (aerolineasRegistradas >= maxAerolineas){
            return Retorno.error(Retorno.Resultado.ERROR_1, "Error 1: Se ha llegado al máximo registro de aerolíneas. No se pueden añadir nuevas aerolíneas.");
        }

        if (Objects.equals(codigo, "") || codigo == null || Objects.equals(nombre, "") || nombre == null ){
            return Retorno.error(Retorno.Resultado.ERROR_2, "Error 2: Parámetro vacío. Debe ingresar NOMBRE y CÓDIGO de nueva Aerolínea.");
        }

        Aerolinea a = new Aerolinea(nombre, codigo);

        if (aerolineas.pertenece(a)){
            return Retorno.error(Retorno.Resultado.ERROR_3, "Error 3: Ya existe una aerolínea con ese código. Los códigos de aerolínea deben ser únicos en el sistema.");
        }

        aerolineas.insertar(a);
        aerolineasRegistradas++;

        return Retorno.ok();
    }

    @Override
    public Retorno listarAerolineasDescendente() {
        return Retorno.ok(aerolineas.listarDescendentes());
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {
        if (aeropuertosRegistrados >= maxAeropuertos){
            return Retorno.error(Retorno.Resultado.ERROR_1, "Error 1: Se ha llegado al máximo registro de aeropuertos. No se pueden añadir nuevos aeropuertos.");
        }

        if (Objects.equals(codigo, "") || codigo == null || Objects.equals(nombre, "") || nombre == null ){
            return Retorno.error(Retorno.Resultado.ERROR_2, "Error 2: Parámetro vacío. Debe ingresar NOMBRE y CÓDIGO del nuevo Aeropuerto.");
        }

        Aeropuerto a = new Aeropuerto(nombre, codigo);

        if (aeropuertos.pertenece(a)){
            return Retorno.error(Retorno.Resultado.ERROR_3, "Error 3: Ya existe un aeropuerto con ese código. Los códigos de aeropuertos deben ser únicos en el sistema.");
        }

        aeropuertos.insertar(a);
        aeropuertosRegistrados++;

        return Retorno.ok();
    }

    @Override
    public Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, String codigoAerolinea) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoOrigen, int cantidad, String codigoAerolinea) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCiudadOrigen, String codigoCiudadDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoEnMinutos(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        return Retorno.noImplementada();
    }


}
