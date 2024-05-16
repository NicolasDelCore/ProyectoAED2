package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test02_RegistrarPasajeroTest {
    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10, 10);
    }

    @Test
    void noDeberiaRegistrarConParametrosVacios() {
        retorno = sistema.registrarPasajero(new String(""), new String("Gustavo"), new String("1234"), Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarPasajero(new String("5.647.365-1"), new String(""), new String("1234"), Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarPasajero(new String("5.647.365-1"), new String("Gustavo"), new String(""), Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarConParametrosNulos() {
        retorno = sistema.registrarPasajero(null, new String("Gustavo"), new String("1234"), Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarPasajero(new String("5.647.365-1"), null, new String("1234"), Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarPasajero(new String("5.647.365-1"), new String("Gustavo"), null, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarPasajero(new String("5.647.365-1"), new String("Gustavo"), new String(""), null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarConCedulaInvalida() {
        retorno = sistema.registrarPasajero(new String(".647.365-1"), new String("Gustavo"), new String("1234"), Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        /* //Casos borde no cubiertos en los tests anteriores
        retorno = sistema.registrarPasajero(new String(".2.647.365-1"), new String("Gustavo"), new String("1234"), Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        */

        retorno = sistema.registrarPasajero(new String("0.647.365-1"), new String("Gustavo"), new String("1234"), Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.registrarPasajero(new String("0.6.47.365-1"), new String("Gustavo"), new String("1234"), Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.registrarPasajero(new String("36473651"), new String("Gustavo"), new String("1234"), Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.registrarPasajero(new String("0"), new String("Gustavo"), new String("1234"), Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarPasajeroRepetido() {
        retorno = sistema.registrarPasajero(new String("5.447.365-1"), new String("Gustavo"), new String("1234"), Categoria.FRECUENTE);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = sistema.registrarPasajero(new String("5.447.365-1"), new String("Gustavo"), new String("1234"), Categoria.FRECUENTE);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void deberiaRegistrarPasajero() {
        retorno = sistema.registrarPasajero(new String("5.447.365-1"), new String("Gustavo"), new String("1234"), Categoria.FRECUENTE);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarPasajero(new String("5.888.365-1"), new String("Gustavo"), new String("1234"), Categoria.PLATINO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        /* //Caso no chequeado por los tests anteriores: validar el segundo tipo de patrón
        retorno = sistema.registrarPasajero(new String("647.365-1"), new String("Gustavo"), new String("1234"), Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado()); */
    }
}
