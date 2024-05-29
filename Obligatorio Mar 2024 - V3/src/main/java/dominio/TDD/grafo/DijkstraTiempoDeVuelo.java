package dominio.TDD.grafo;
import dominio.Clases.Vuelo;
import dominio.TDD.TuplaTInt;

//Esta clase es SÓLO para separar la implementación de Dijkstra que compara tiempos de Vuelo, para no tener que importar Vuelo en la clase genérica Grafo
public class DijkstraTiempoDeVuelo<T extends Comparable<T>>  {

    //Se toma como peso la cantidad de minutos de vuelo del vuelo más corto de la Arista del grafo "conexiones" de aeropuertos
    public TuplaTInt dijkstraPorMinutos(T origen, T destino, Grafo conexionesAeropuertos) {

        //Importar datos del grafo original
        int posVOrigen = conexionesAeropuertos.obtenerPos(new Vertice(origen));
        int posVDestino = conexionesAeropuertos.obtenerPos(new Vertice(destino));
        int maxVertices = conexionesAeropuertos.getMaxVertices();
        int cantidad = conexionesAeropuertos.getCantidad(); //cantidad de vértices totales
        Arista[][] aristas = conexionesAeropuertos.getAristas();
        Vertice[] vertices = conexionesAeropuertos.getVertices();

        //declaración de arrays
        boolean[] visitados = new boolean[maxVertices];
        int[] costos = new int[maxVertices];
        int[] vengo = new int[maxVertices];

        //inicialización de arrays
        for (int i = 0; i < maxVertices; i++) {
            costos[i] = Integer.MAX_VALUE;
            vengo[i] = -1;
            visitados[i] = false;
        }

        //Inicializar recorridas: Marcamos 0 como distancia en el nodo de origen (al marcar 0 en la dist del nodo de origen, esto inicia el dijkstra porque el nodo de origen es el primero en seleccionarse con obtenerSiguenteVerticeNoVisitadoDeMenorCosto)
        costos[posVOrigen] = 0;

        //Camino desde Origen al resto de los nodos (necesario completar para obtener camino mínimo a destino)
        for (int v = 0; v < cantidad; v++) {
            int pos = conexionesAeropuertos.obtenerSiguenteVerticeNoVisitadoDeMenorCosto(costos, visitados);

            if (pos != -1) {
                visitados[pos] = true;

                for (int i = 0; i < aristas.length; i++) {
                    if (aristas[pos][i] != null && !visitados[i]) {
                        int vueloMasCorto = vueloMasCorto(aristas[pos][i]);
                        if (vueloMasCorto < Integer.MAX_VALUE) {
                            int distanciaNueva = costos[pos] + vueloMasCorto;
                            if (distanciaNueva < costos[i]) {
                                costos[i] = distanciaNueva;
                                vengo[i] = pos;
                            }
                        }
                    }
                }
            }
        }

        //Trazamos el camino con trazarCaminoDijkstra (que nos devuelve hasta el elemento anterior al destino) y agregamos el vértice destino al final
        String caminoRetorno = conexionesAeropuertos.trazarCaminoDijkstra(vengo, posVDestino) + vertices[posVDestino].getDato().toString();

        //Tupla para resultado
        TuplaTInt resultado = new TuplaTInt(caminoRetorno, costos[posVDestino]);

        return resultado;
    }

    private int vueloMasCorto (Arista arista){
        int largo = arista.elementos.largo();
        int menorVuelo = Integer.MAX_VALUE;
        for (int i = 0; i < largo; i++){
            Vuelo vuelo = (Vuelo) arista.elementos.devolverPos(i);
            if (vuelo.getMinutos() < menorVuelo) {
                menorVuelo = (int) vuelo.getMinutos();
            }
        }
        return menorVuelo;
    }

}
