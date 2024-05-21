package sistema;

import dominio.Clases.*;
import dominio.TDD.*;
import dominio.TDD.grafo.*;
import interfaz.*;

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
    ABB<Vuelo> vuelos = new ABB<Vuelo>();
    Grafo<Aeropuerto> conexiones = new Grafo(1000);

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

        if (conexiones.existeVertice(a)){
            return Retorno.error(Retorno.Resultado.ERROR_3, "Error 3: Ya existe un aeropuerto con ese código. Los códigos de aeropuertos deben ser únicos en el sistema.");
        }

        conexiones.agregarVertice(a);
        aeropuertosRegistrados++;

        return Retorno.ok();
    }

    @Override
    public Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {

        if (kilometros <= 0) {
            return Retorno.error(Retorno.Resultado.ERROR_1, "Error 1: Debe ingresar más de 0 Kilómetros.");
        }

        if (Objects.equals(codigoAeropuertoOrigen, "") || codigoAeropuertoOrigen == null || Objects.equals(codigoAeropuertoDestino, "") || codigoAeropuertoDestino == null) {
            return Retorno.error(Retorno.Resultado.ERROR_2, "Error 2: Parámetro vacío. Debe ingresar NOMBRE y CÓDIGO del nuevo Aeropuerto.");
        }

        Aeropuerto aOrigen = new Aeropuerto("", codigoAeropuertoOrigen);
        if (!conexiones.existeVertice(aOrigen)) {
            return Retorno.error(Retorno.Resultado.ERROR_3, "Error 3: No se encontró aeropuerto de origen. Revise el CÓDIGO proveído del Aeropuerto.");
        }

        Aeropuerto aDestino = new Aeropuerto("", codigoAeropuertoDestino);
        if (!conexiones.existeVertice(aDestino)) {
            return Retorno.error(Retorno.Resultado.ERROR_4, "Error 4: No se encontró aeropuerto de destino. Revise el CÓDIGO proveído del Aeropuerto.");
        }

        if ( conexiones.existeAlgunaAristaEntre(aOrigen, aDestino) ){
            return Retorno.error(Retorno.Resultado.ERROR_5, "Error 5: Ya hay una conexión registrada entre esos dos aeropuertos. Sólo se permite una conexión entre aeropuertos.");
        }

        conexiones.agregarArista(aOrigen, aDestino, kilometros);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, String codigoAerolinea) {

        //1. Si alguno de los parámetros double es menor o igual a 0.
        if (combustible <= 0 || minutos <= 0 || costoEnDolares <= 0) {
            return Retorno.error(Retorno.Resultado.ERROR_1, "Error 1: Por favor verifique que Combustible, Minutos y Costo En Dólares deben sean mayores a 0.");
        }

        //2. Si alguno de los parámetros String es vacío o null.
        if (Objects.equals(codigoAeropuertoDestino, "") || codigoAeropuertoDestino == null ||
                Objects.equals(codigoAeropuertoOrigen, "") || codigoAeropuertoOrigen == null ||
                Objects.equals(codigoDeVuelo, "") || codigoDeVuelo == null ||
                Objects.equals(codigoAerolinea, "") || codigoAerolinea == null) {
            return Retorno.error(Retorno.Resultado.ERROR_2, "Error 2: Parámetro vacío. Debe ingresar codigoAeropuertoDestino, codigoCiudadOrigen, codigoDeVuelo y codigoAerolinea.");
        }

        //3. Si no existe el aeropuerto de origen.
        Aeropuerto aOrigen = new Aeropuerto("", codigoAeropuertoOrigen);
        if (!conexiones.existeVertice(aOrigen)) {
            return Retorno.error(Retorno.Resultado.ERROR_3, "Error 3: No se encontró aeropuerto de origen. Revise el CÓDIGO proveído del Aeropuerto.");
        }

        //4. Si no existe el aeropuerto de destino.
        Aeropuerto aDestino = new Aeropuerto("", codigoAeropuertoDestino);
        if (!conexiones.existeVertice(aDestino)) {
            return Retorno.error(Retorno.Resultado.ERROR_4, "Error 4: No se encontró aeropuerto de destino. Revise el CÓDIGO proveído del Aeropuerto.");
        }

        //5. Si no existe la aerolínea indicada.
        Aerolinea aerolinea = new Aerolinea("", codigoAerolinea);
        if (!aerolineas.pertenece(aerolinea)) {
            return Retorno.error(Retorno.Resultado.ERROR_5, "Error 5: No se encontró la aerolínea. Revise el CÓDIGO proveído de la Aerolínea.");
        }

        Arista miConexion = conexiones.obtenerArista(aOrigen, aDestino);

        //6. Si no existe una conexión entre origen y destino
        if (miConexion == null) {
            return Retorno.error(Retorno.Resultado.ERROR_6, "Error 6: No hay una conexión registrada entre esos dos aeropuertos. Por favor, verifique los códigos ingresados o conecte los aeropuertos.");
        }

        //Crear vuelo
        Vuelo miVuelo = new Vuelo(codigoDeVuelo, codigoAeropuertoOrigen, codigoAeropuertoDestino, codigoAerolinea, combustible, minutos, costoEnDolares);

        //7. Si ya existe un vuelo con ese código en esa conexión
        if (miConexion.estaEnLista(miVuelo)) {
            return Retorno.error(Retorno.Resultado.ERROR_7, "Error 7: Ese vuelo ya se encuentra registrado. Por favor verifique detalles del vuelo a registrar.");
        }

        //Completar registro
        miConexion.agregarALista(miVuelo); //Para mantener la relación de vida de Vuelos con Conexiones, Arista tiene una Lista de elementos que contendrá los vuelos
        return Retorno.ok();
    }

    @Override
    public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoOrigen, int cantidad, String codigoAerolinea) {

        //Errores
        //1. Si la cantidad es menor que cero.
        if (cantidad < 0) {
            return Retorno.error(Retorno.Resultado.ERROR_1, "Error 1: Cantidad no puede ser menor a 0.");
        }

        //2. Si el aeropuerto no está registrado en el sistema.
        Aeropuerto aOrigen = new Aeropuerto("", codigoAeropuertoOrigen);
        if (!conexiones.existeVertice(aOrigen)) {
            return Retorno.error(Retorno.Resultado.ERROR_2, "Error 2: No se encontró el aeropuerto. Revise el CÓDIGO proveído del Aeropuerto.");
        }
        //3. Si la aerolínea no está registrada en el sistema.
        Aerolinea aerolinea = new Aerolinea("", codigoAerolinea);
        if (!aerolineas.pertenece(aerolinea)) {
            return Retorno.error(Retorno.Resultado.ERROR_3, "Error 3: No se encontró la aerolínea. Revise el CÓDIGO proveído de la Aerolínea.");
        }

        //Consideraciones:
        //1. Debe devolver aeropuertos encontrados HASTA "cantidad" de conexiones.
        //2. Debe devolver aeropuertos con VUELOS de la AEROLINEA especificada.
        //3. Debe devolver aeropuertos ORDENADOS POR CÓDIGO ASCENDENTE

        return Retorno.ok(conexiones.bfsLimitadoOrdenado(aOrigen, cantidad, new FiltrarVuelo(codigoAerolinea)));
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
