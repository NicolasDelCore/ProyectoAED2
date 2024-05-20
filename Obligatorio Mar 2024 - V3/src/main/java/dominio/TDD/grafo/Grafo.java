package dominio.TDD.grafo;
import dominio.TDD.Lista;
import dominio.TDD.Cola;

public class Grafo {
    private Vertice[] vertices;
    private Arista[][] aristas;
    private final int maxVertices;
    int cantidad = 0;

    public Grafo(int maxVertices) {
        this.maxVertices = maxVertices;
        vertices = new Vertice[maxVertices];
        aristas = new Arista[maxVertices][maxVertices];
    }

    public void agregarVertice(String nombreVertice) {
        if (cantidad < maxVertices) {
            int posLibre = obtenerPosLibre();
            vertices[posLibre] = new Vertice(nombreVertice);
            cantidad++;
        }
    }

    public void borrarVertice(String nombreVertice) {
        int posVaBorrar = obtenerPos(new Vertice(nombreVertice));

        for (int i = 0; i < aristas.length; i++) {
            aristas[posVaBorrar][i] = null;
            aristas[i][posVaBorrar] = null;
        }
        vertices[posVaBorrar] = null;
        cantidad--;
    }

    public void agregarArista(String vInicial, String vFinal, double kilometros) {
        int posVInicial = obtenerPos(new Vertice(vInicial));
        int posVFinal = obtenerPos(new Vertice(vFinal));

        aristas[posVInicial][posVFinal] = new Arista(kilometros);
    }

    public void borrarArista(String vInicial, String vFinal) {
        int posVInicial = obtenerPos(new Vertice(vInicial));
        int posVFinal = obtenerPos(new Vertice(vFinal));

        aristas[posVInicial][posVFinal] = null;
    }

    public Arista obtenerArista(String vInicial, String vFinal) {
        int posVInicial = obtenerPos(new Vertice(vInicial));
        int posVFinal = obtenerPos(new Vertice(vFinal));

        if (posVInicial == -1 || posVFinal == -1){
            return null;
        }

        return aristas[posVInicial][posVFinal];
    }

    public boolean existeVertice(String nombreVertice) {
        int posABuscar = obtenerPos(new Vertice(nombreVertice));
        return posABuscar >= 0;
    }

    public Lista<Vertice> adyacentes(String nombreVertice) {
        Lista<Vertice> adyacentes = new Lista<>();
        int posV = obtenerPos(new Vertice(nombreVertice));

        for (int i = 0; i < aristas.length; i++) {
            if (aristas[posV][i] != null) {
                adyacentes.insertar(vertices[i]);
            }
        }
        return adyacentes;
    }

    public void dfs(String nombreVertice) {
        boolean[] visitados = new boolean[maxVertices];
        int posV = obtenerPos(new Vertice(nombreVertice));
        dfs(posV, visitados);
    }

    public void bfs(String nombreVertice) {
        int posV = obtenerPos(new Vertice(nombreVertice));
        boolean[] visitados = new boolean[maxVertices];
        Cola<Integer> cola = new Cola<>();
        cola.encolar(posV);
        visitados[posV] = true;
        while (!cola.esVacia()) {
            int pos = cola.desencolar();
            System.out.println(vertices[pos]);
            for (int i = 0; i < aristas.length; i++) {
                if (aristas[posV][i] != null && !visitados[i]) {
                    cola.encolar(i);
                    visitados[i] = true;
                }
            }
        }
    }

    private void dfs(int posV, boolean[] visitados) {
        System.out.println(vertices[posV]);
        visitados[posV] = true;
        for (int i = 0; i < aristas.length; i++) {
            if (aristas[posV][i] != null && !visitados[i]) {
                dfs(i, visitados);
            }
        }
    }

    public int dijkstra(String nombreVOrigen, String nombreVDestino){
        int posVOrigen = obtenerPos(new Vertice(nombreVOrigen));
        int posVDestino = obtenerPos(new Vertice(nombreVDestino));

        boolean[] visitados = new boolean[maxVertices];
        int[] costos = new int[maxVertices];
        String[] vengo = new String[maxVertices];

        for (int i = 0; i < maxVertices; i++) {
            costos[i]= Integer.MAX_VALUE;
            vengo[i]="-";
            visitados[i]=false;
        }

        for (int v = 0; v < cantidad; v++) {
            int pos = obtenerSiguenteVerticeNoVisitadoDeMenorCosto(costos,visitados);

            if(pos!=-1){
                visitados[pos]=true;

                for (int i = 0; i < aristas.length; i++) {
                    if(aristas[pos][i]!=null && !visitados[i]){
                        int distanciaNueva = costos[pos] + (int) aristas[pos][i].peso;
                        if(distanciaNueva<costos[i]){
                            costos[i]=distanciaNueva;
                            vengo[i]=vertices[pos].getNombre();
                        }
                    }
                }
            }
        }
        return costos[posVDestino];
    }

    //Chequea si existe una arista en cualquier dirección entre los vértices: vertice ---- vertice2
    //Usado para evitar el registro de conexiones bidireccionales
    public boolean existeAlgunaAristaEntre(String vertice1, String vertice2){
        Arista vert1a2 = obtenerArista(vertice1, vertice2);
        Arista vert2a1 = obtenerArista(vertice2, vertice1);
        return vert1a2 != null || vert2a1 != null;
    }

    //Chequea si existe una arista en la dirección especificada: vertice1 ---> vertice2
    public boolean existeAristaEntre(String vertice1, String vertice2){
        return obtenerArista(vertice1, vertice2) != null;
    }

    private int obtenerSiguenteVerticeNoVisitadoDeMenorCosto(int[] costos, boolean[] visitados) {
        int posMin = -1;
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < maxVertices; i++) {
            if(!visitados[i] && costos[i]<min){
                min=costos[i];
                posMin=i;
            }
        }
        return posMin;
    }


    private int obtenerPos(Vertice v) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null && vertices[i].equals(v)) {
                return i;
            }
        }
        return -1;
    }

    private int obtenerPosLibre() {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == null) {
                return i;
            }
        }
        return -1;
    }


}
