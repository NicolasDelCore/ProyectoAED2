package dominio;

public class ABB<T extends Comparable<T>> {

    private NodoABB<T> raiz;

    public boolean estaVacio(){
        return (raiz == null);
    }

    public void insertar(T dato) {
        if (raiz == null) {
            raiz = new NodoABB<>(dato);
        } else {
            insertar(dato, raiz);
        }

    }

    private void insertar(T dato, NodoABB<T> nodo) {
        if (nodo.dato.compareTo(dato) < 0) {
            if (nodo.izq == null) {
                nodo.izq = new NodoABB<>(dato);
            } else {
                insertar(dato, nodo.izq);
            }
        } else {
            if (nodo.der == null) {
                nodo.der = new NodoABB<>(dato);
            } else {
                insertar(dato, nodo.der);
            }
        }
    }

    public boolean pertenece(T dato) {
        return pertenece(dato, raiz);
    }

    private boolean pertenece(T dato, NodoABB nodo) {
        if (nodo != null) {
            int comp = nodo.dato.compareTo(dato);
            if (comp == 0) {
                return true;
            } else if (comp < 0) {
                return pertenece(dato, nodo.izq);
            } else {
                return pertenece(dato, nodo.der);
            }
        }
        return false;
    }

    public TuplaTInt encontrar(T dato) {
        return encontrar(dato, raiz, 0);
    }

    private TuplaTInt encontrar(T dato, NodoABB nodo, int elementosRecorridos) {
        if (nodo != null) {
            int comp = nodo.dato.compareTo(dato);
            if (comp == 0) {
                return new TuplaTInt(nodo.dato, elementosRecorridos);
            } else if (comp < 0) {
                return encontrar(dato, nodo.izq, elementosRecorridos + 1);
            } else {
                return encontrar(dato, nodo.der, elementosRecorridos + 1);
            }
        }
        return new TuplaTInt(null, elementosRecorridos);
    }


    public String listarAscendentes() {
        String resultado = listarAscendentes(raiz);
        if (resultado.length() > 0) {
            return resultado.substring(0, resultado.length() - 1); //hay que pelarle el último "|" para que el test pase
        }
        return "";
    }

    private String listarAscendentes(NodoABB nodo) {
        if (nodo != null){
            return listarAscendentes(nodo.der) + nodo.dato + "|" + listarAscendentes(nodo.izq);
        } else {
            return "";
        }
    }

    public String listarDescendentes() {
        String resultado = listarDescendentes(raiz);
        if (resultado.length() > 0) {
            return resultado.substring(0, resultado.length() - 1);
        }
        return "";
    }

    private String listarDescendentes(NodoABB nodo) {
        if (nodo != null){
            return listarDescendentes(nodo.izq) + nodo.dato + "|" + listarAscendentes(nodo.der);
        } else {
            return "";
        }
    }

    private class NodoABB<T extends Comparable<T>> {
        private T dato;
        private NodoABB<T> izq;
        private NodoABB<T> der;

        public NodoABB(T dato) {
            this.dato = dato;
        }
    }
}
