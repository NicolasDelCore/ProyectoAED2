package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test03_BuscarPasajeroTest {
    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10,10);

        sistema.registrarPasajero(new String("4.685.375-3"), new String("Juliana"),  new String("1234"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("5.135.139-2"), new String("Juliana"),  new String("3456"), Categoria.PLATINO);
        sistema.registrarPasajero(new String("5.888.365-4"), new String("Alejandra"),  new String("5634"), Categoria.FRECUENTE);
        sistema.registrarPasajero(new String("5.447.365-1"), new String("Gustavo"),  new String("23456"), Categoria.ESTANDAR);
    }

    @Test
    void noDeberiaEncontrarPasajeroConValoresVacios() {
        retorno = sistema.buscarPasajero("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaEncontrarPasajeroConValoresNulos() {
        retorno = sistema.buscarPasajero(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaEncontrarPasajeroConCIInvalida() {

        retorno = sistema.buscarPasajero(".685.375-3");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.buscarPasajero("0.685.375-3");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.buscarPasajero("4.6.85.375-3");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void noDeberiaEncontrarPasajeroInexistente() {

        retorno = sistema.buscarPasajero("7.685.375-3");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void deberiaEncontrarPasajero() {
        retorno = sistema.buscarPasajero("4.685.375-3");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("4.685.375-3;Juliana;1234;Estándar", retorno.getValorString());
        assertEquals(0, retorno.getValorInteger());

        retorno = sistema.buscarPasajero("5.135.139-2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("5.135.139-2;Juliana;3456;Platino", retorno.getValorString());
        assertEquals(1, retorno.getValorInteger());

        retorno = sistema.buscarPasajero("5.888.365-4");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("5.888.365-4;Alejandra;5634;Frecuente", retorno.getValorString());
        assertEquals(2, retorno.getValorInteger());

        retorno = sistema.buscarPasajero("5.447.365-1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("5.447.365-1;Gustavo;23456;Estándar", retorno.getValorString());
        assertEquals(3, retorno.getValorInteger());
    }
}
