package dominio.TDD;

public class Cola<T> {

	private NodoCola<T> inicio;
	private NodoCola<T> fin;
	private int largo;

	public void encolar(T dato) {
		if (this.inicio == null) {
			inicio = new NodoCola<T>(dato);
			fin = inicio;
		} else {
			fin.setSig(new NodoCola<T>(dato));
			fin = fin.getSig();
		}
		this.largo++;
	}

	// Pre: !esVacia()
	public T desencolar() {
		T dato = this.inicio.getDato();
		inicio = inicio.getSig();
		this.largo--;
		if(this.inicio == null) {
			fin = null;
		}
		return dato;
	}

	public boolean esVacia() {
		return this.largo == 0;
	}


	private class NodoCola<Q> {
		private Q dato;
		private NodoCola<Q> sig;

		public NodoCola(Q dato, NodoCola<Q> sig) {
			this.dato = dato;
			this.sig = sig;
		}

		public NodoCola(Q dato) {
			this.dato = dato;
		}

		public Q getDato() {
			return dato;
		}

		public void setDato(Q dato) {
			this.dato = dato;
		}

		public NodoCola<Q> getSig() {
			return sig;
		}

		public void setSig(NodoCola<Q> sig) {
			this.sig = sig;
		}

		@Override
		public String toString() {
			return dato + "";
		}

	}

}
