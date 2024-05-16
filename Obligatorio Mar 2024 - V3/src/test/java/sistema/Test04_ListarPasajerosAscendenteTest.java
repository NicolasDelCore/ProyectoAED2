package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Retorno.Resultado;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test04_ListarPasajerosAscendenteTest {
    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10,10);

        sistema.registrarPasajero(new String("4.685.375-3"), new String("Juliana"),  new String("1234"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("5.135.139-2"), new String("Gaston"),  new String("3456"), Categoria.PLATINO);
        sistema.registrarPasajero(new String("5.888.365-4"), new String("Alejandra"),  new String("5634"), Categoria.FRECUENTE);
        sistema.registrarPasajero(new String("5.447.365-1"), new String("Gustavo"),  new String("23456"), Categoria.ESTANDAR);
    }

    @Test
    void listarAscendente() {
        retorno = sistema.listarPasajerosAscendente();
        String resEsperado = "4.685.375-3;Juliana;1234;Estándar|5.135.139-2;Gaston;3456;Platino|5.447.365-1;Gustavo;23456;Estándar|5.888.365-4;Alejandra;5634;Frecuente";
        assertEquals(Resultado.OK, retorno.getResultado());
        assertEquals(resEsperado, retorno.getValorString());
    }

    @Test
    void listarAscendenteVacio() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10,10);
        retorno = sistema.listarPasajerosAscendente();
        assertEquals(Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }
}
