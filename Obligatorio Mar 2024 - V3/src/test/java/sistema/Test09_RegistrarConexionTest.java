package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test09_RegistrarConexionTest {
    private Sistema sistema;
    private Retorno retorno;
    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(6,10);
    }

    @Test
    void noDeberiaRegistrarConexionParaIgualesACero() {
        retorno = sistema.registrarConexion(new String("1"),new String("2"),0);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarConexionParaMenoresACero() {
        retorno = sistema.registrarConexion(new String("1"),new String("2"),-10);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarConexionParaParamVacios() {
        retorno = sistema.registrarConexion(new String(""),new String("2"),10);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarConexion(new String("1"),new String(""),10);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarConexionParaParamNull() {
        retorno = sistema.registrarConexion(null,new String("2"),10);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarConexion(new String("1"),null,10);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarConexionParaParamAeropuertoOrigenNoExiste() {
        retorno = sistema.registrarConexion(new String("1"),new String("2"),10);
        assertEquals(Retorno.Resultado.ERROR_3,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarConexionParaParamAeropuertoDestinoNoExiste() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        retorno = sistema.registrarConexion(new String("1"),new String("2"),10);
        assertEquals(Retorno.Resultado.ERROR_4,retorno.getResultado());
    }

    @Test
    void deberiaRegistrarConexion(){
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        retorno = sistema.registrarConexion(new String("1"),new String("2"),10);
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarConexionPorCaminoExistente(){
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        sistema.registrarConexion(new String("1"),new String("2"),10);
        retorno = sistema.registrarConexion(new String("1"),new String("2"),10);
        assertEquals(Retorno.Resultado.ERROR_5,retorno.getResultado());
    }
}
