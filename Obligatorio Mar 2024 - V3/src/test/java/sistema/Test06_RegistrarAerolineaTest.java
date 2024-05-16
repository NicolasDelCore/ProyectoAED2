package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test06_RegistrarAerolineaTest {
    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10, 4);
    }

    @Test
    void noDeberiaRegistrarPorMaxCant() {
        retorno = sistema.registrarAerolinea(new String("A"), new String("Aerolinea 1"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("B"), new String("Aerolinea 2"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("C"), new String("Aerolinea 3"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("D"), new String("Aerolinea 4"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("E"), new String("Aerolinea 5"));
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }


    @Test
    void noDeberiaRegistrarConParametrosVacios() {
        retorno = sistema.registrarAerolinea(new String(""), new String("Aerolinea 1"));
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("A"), new String(""));
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarConParametrosNulos() {
        retorno = sistema.registrarAerolinea(null, new String("Aerolinea 1"));
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("A"), null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarAerolineaRepetida() {
        retorno = sistema.registrarAerolinea(new String("A"), new String("Aerolinea 1"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("A"), new String("Aerolinea 2"));
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void deberiaRegistrarPasajero() {
        retorno = sistema.registrarAerolinea(new String("A"), new String("Aerolinea 1"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("B"), new String("Aerolinea 2"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("C"), new String("Aerolinea 3"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("D"), new String("Aerolinea 4"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
}
