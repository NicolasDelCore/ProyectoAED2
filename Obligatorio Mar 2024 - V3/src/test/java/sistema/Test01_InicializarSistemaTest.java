package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test01_InicializarSistemaTest {
    Retorno retorno;

    @Test
    void noDeberiaInicializarSistemaConMaxAeropuertosMenorA5() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(Sistema.MIN_AEROPUERTOS-1,Sistema.MIN_AEROLINEAS+1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.inicializarSistema(0,Sistema.MIN_AEROLINEAS+1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.inicializarSistema(-1,Sistema.MIN_AEROLINEAS+1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaInicializarSistemaConMaxAeropuertosIgualA5() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(Sistema.MIN_AEROPUERTOS,Sistema.MIN_AEROLINEAS+1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void deberiaInicializarSistemaMaxAeropuertosMayorA5() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(Sistema.MIN_AEROPUERTOS+1,Sistema.MIN_AEROLINEAS+1);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void noDeberiaInicializarSistemaConMaxAerolineasMenorA3() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(Sistema.MIN_AEROPUERTOS+1,Sistema.MIN_AEROLINEAS-1);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.inicializarSistema(Sistema.MIN_AEROPUERTOS+1,0);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.inicializarSistema(Sistema.MIN_AEROPUERTOS+1,-1);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void noDeberiaInicializarSistemaConMaxAerolineasIgualA3() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(Sistema.MIN_AEROPUERTOS+1,Sistema.MIN_AEROLINEAS);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void deberiaInicializarSistemaMaxAerolineasMayorA3() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(Sistema.MIN_AEROPUERTOS+1,Sistema.MIN_AEROLINEAS+1);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
}
