package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test08_RegistrarAeropuertoTest {
    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(6,6);
    }

    @Test
    void noDeberiaRegistrarAeropuertoVacio() {
        retorno = sistema.registrarAeropuerto(new String(""), new String("Aeropuerto1"));
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.registrarAeropuerto(new String("1"), new String(""));
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarAeropuertoNull() {
        retorno = sistema.registrarAeropuerto(null, new String("Aeropuerto1"));
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.registrarAeropuerto(new String("1"), null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void deberiaRegistrarAeropuerto() {
        retorno = sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAeropuerto(new String("3"), new String("Aeropuerto3"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAeropuerto(new String("4"), new String("Aeropuerto4"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAeropuerto(new String("5"), new String("Aeropuerto5"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAeropuerto(new String("6"), new String("Aeropuerto6"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarAeropuertoPorMaxCant() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        sistema.registrarAeropuerto(new String("3"), new String("Aeropuerto3"));
        sistema.registrarAeropuerto(new String("4"), new String("Aeropuerto4"));
        sistema.registrarAeropuerto(new String("5"), new String("Aeropuerto5"));
        sistema.registrarAeropuerto(new String("6"), new String("Aeropuerto6"));
        retorno = sistema.registrarAeropuerto(new String("7"), new String("Aeropuerto7"));
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }
}
