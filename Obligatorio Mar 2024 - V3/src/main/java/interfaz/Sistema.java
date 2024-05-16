package interfaz;

/**
 * Provee una interfaz para interactuar con el sistema
 */
public interface Sistema {

    static final int MIN_AEROPUERTOS = 5;
    static final int MIN_AEROLINEAS = 3;

    Retorno inicializarSistema(int maxAeropuertos, int maxAerolineas);

    Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria);

    Retorno buscarPasajero(String cedula);

    Retorno listarPasajerosAscendente();

    Retorno listarPasajerosPorCategoria(Categoria tipo);

    Retorno registrarAerolinea(String codigo, String nombre);

    Retorno listarAerolineasDescendente();

    Retorno registrarAeropuerto(String codigo, String nombre);

    Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros);

    Retorno registrarVuelo(String codigoCiudadOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, String codigoAerolinea);

    Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoOrigen, int cantidad, String codigoAerolinea);

    Retorno viajeCostoMinimoKilometros(String codigoCiudadOrigen, String codigoCiudadDestino);

    Retorno viajeCostoMinimoEnMinutos(String codigoAeropuertoOrigen, String codigoAeropuertoDestino);

}
