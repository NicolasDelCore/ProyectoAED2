package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test10_RegistrarVueloTest {
    private Sistema sistema;
    private Retorno retorno;
    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(6,10);
    }

    @Test
    void noDeberiaRegistrarVueloConParamsIgualesACero() {
        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),0,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,0,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,0,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloConParamsMenoresACero() {
        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),-1,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,-1,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,-1,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloConParamsVacios() {
        retorno = sistema.registrarVuelo(new String(""),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String(""),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String(""),10,10,10,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,10,new String(""));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloConParamsNull() {
        retorno = sistema.registrarVuelo(null,new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),null,new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),null,10,10,10,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,10,null);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloSiAeropuertoOrigenNoExiste() {
        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_3,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloSiAeropuertoDestinoNoExiste() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_4,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloSiAerolineaNoExiste() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_5,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloSiNoExisteConexion() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        sistema.registrarAerolinea("1", "Aerolinea 1");
        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_6,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloSiYaExiste() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        sistema.registrarAerolinea("1", "Aerolinea 1");
        sistema.registrarConexion(new String("1"), new String("2"),10);
        sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_7,retorno.getResultado());
    }

    @Test
    void deberiaRegistrarVuelo() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        sistema.registrarAerolinea("1", "Aerolinea 1");
        sistema.registrarConexion(new String("1"), new String("2"),10);
        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
    }
}
