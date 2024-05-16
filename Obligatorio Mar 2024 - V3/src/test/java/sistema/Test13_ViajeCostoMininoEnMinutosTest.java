package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Test13_ViajeCostoMininoEnMinutosTest {
    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10,10);
    }

    private void cargarSetAeropuertoYConexiones1() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        sistema.registrarAeropuerto(new String("3"), new String("Aeropuerto3"));
        sistema.registrarAeropuerto(new String("4"), new String("Aeropuerto4"));
        sistema.registrarAeropuerto(new String("5"), new String("Aeropuerto5"));
        sistema.registrarAeropuerto(new String("6"), new String("Aeropuerto6"));

        sistema.registrarConexion(new String("1"), new String("2"), 10);
        sistema.registrarConexion(new String("1"), new String("3"), 2);
        sistema.registrarConexion(new String("1"), new String("6"), 14);
        sistema.registrarConexion(new String("2"), new String("4"), 15);
        sistema.registrarConexion(new String("3"), new String("4"), 15);
        sistema.registrarConexion(new String("3"), new String("6"), 1);
        sistema.registrarConexion(new String("3"), new String("5"), 3);
        sistema.registrarConexion(new String("6"), new String("4"), 18);

        sistema.registrarAerolinea("1","Aerolinea1");

        retorno = sistema.registrarVuelo("1", "2", "1", 100, 10, 20, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = sistema.registrarVuelo("2", "4", "2", 100, 10, 20, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarVuelo("1", "3", "3", 100, 10, 20, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = sistema.registrarVuelo("3", "4", "4", 100, 30, 20, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarVuelo("1", "6", "5", 100, 30, 10, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = sistema.registrarVuelo("6", "4", "6", 100, 30, 11, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void noDeberiaDevolverCostoMinimoParamVacio() {
        retorno = sistema.viajeCostoMinimoEnMinutos(new String(""), new String("4"));
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.viajeCostoMinimoEnMinutos(new String("1"), new String(""));
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaDevolverCostoMinimoParamNull() {
        retorno = sistema.viajeCostoMinimoEnMinutos(null, new String("4"));
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.viajeCostoMinimoEnMinutos(new String("1"), null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaDevolverCostoMinimoNoExisteOrigen() {
        cargarSetAeropuertoYConexiones1();

        retorno = sistema.viajeCostoMinimoEnMinutos(new String("10"), new String("4"));
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void noDeberiaDevolverCostoMinimoNoExisteDestino() {
        cargarSetAeropuertoYConexiones1();

        retorno = sistema.viajeCostoMinimoEnMinutos(new String("1"), new String("40"));
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    void deberiaDevolverCostoMinimo() {
        cargarSetAeropuertoYConexiones1();
        retorno = sistema.viajeCostoMinimoEnMinutos(new String("1"), new String("4"));

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(20, retorno.getValorInteger());
        assertEquals("1;Aeropuerto1|2;Aeropuerto2|4;Aeropuerto4", retorno.getValorString());
    }

    @Test
    void deberiaDevolverCostoMinimo1(){
        cargarSetAeropuertoYConexiones1();

        sistema.registrarConexion(new String("5"), new String("4"), 8);
        sistema.registrarVuelo("5","4","7",10,2,45,"1");
        sistema.registrarVuelo("3","5","8",10,7,45,"1");

        retorno = sistema.viajeCostoMinimoEnMinutos(new String("1"), new String("4"));

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(19, retorno.getValorInteger());
        assertEquals("1;Aeropuerto1|3;Aeropuerto3|5;Aeropuerto5|4;Aeropuerto4", retorno.getValorString());
    }

    @Test
    void deberiaDevolverCostoMinimo2(){
        cargarSetAeropuertoYConexiones1();
        retorno = sistema.viajeCostoMinimoEnMinutos(new String("1"), new String("4"));

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(20, retorno.getValorInteger());
        assertEquals("1;Aeropuerto1|2;Aeropuerto2|4;Aeropuerto4", retorno.getValorString());
    }

    @Test
    void nodeberiaDevolverCostoMinimoNoHayCamino(){
        cargarSetAeropuertoYConexiones1();
        retorno = sistema.viajeCostoMinimoEnMinutos(new String("5"), new String("6"));

        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
}
