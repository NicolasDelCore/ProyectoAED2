package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Retorno.Resultado;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test05_ListarPasajerosPorCategoriaTest {
    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10,10);

        sistema.registrarPasajero(new String("4.685.375-3"), new String("Juliana"),  new String("1234"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("5.135.139-2"), new String("Gaston"),  new String("3456"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("5.888.365-4"), new String("Alejandra"),  new String("5634"), Categoria.PLATINO);
        sistema.registrarPasajero(new String("5.447.365-1"), new String("Gustavo"),  new String("23456"), Categoria.PLATINO);
    }

    @Test
    void deberiaListarJugadoresPorTipo(){
        retorno = sistema.listarPasajerosPorCategoria(Categoria.ESTANDAR);
        assertEquals(Resultado.OK,retorno.getResultado());
        assertEquals("4.685.375-3;Juliana;1234;Estándar|5.135.139-2;Gaston;3456;Estándar",retorno.getValorString());

        retorno = sistema.listarPasajerosPorCategoria(Categoria.PLATINO);
        assertEquals(Resultado.OK,retorno.getResultado());
        assertEquals("5.447.365-1;Gustavo;23456;Platino|5.888.365-4;Alejandra;5634;Platino",retorno.getValorString());
    }

    @Test
    void deberiaListarJugadoresPorTipoVacio(){
        retorno = sistema.listarPasajerosPorCategoria(Categoria.FRECUENTE);
        assertEquals(Resultado.OK,retorno.getResultado());
        assertEquals("",retorno.getValorString());
    }
}
