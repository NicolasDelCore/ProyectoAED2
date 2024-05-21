package dominio.Clases;
import dominio.TDD.grafo.Arista;
import dominio.TDD.grafo.FiltrarArista;
import java.util.Objects;

public class FiltrarVuelo implements FiltrarArista<Aeropuerto> {

    private String codigoAerolinea;

    public FiltrarVuelo(String codigoAerolinea) {
        this.codigoAerolinea = codigoAerolinea;
    }

    @Override
    public boolean debeIncluirArista(Arista<Aeropuerto> arista) {
        for (int k = 0; k < arista.elementos.largo(); k++) {
            Vuelo vuelo = (Vuelo) arista.elementos.devolverPos(k);
            if (Objects.equals(vuelo.getCodigoAerolinea(), codigoAerolinea)) {
                return true;
            }
        }
        return false;
    }
}
