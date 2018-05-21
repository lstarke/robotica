package t3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra {

	//private HashMap<Integer, Nodo> grafo = new HashMap<Integer, Nodo>();// nome,Nodo
	private ArrayList<Nodo> grafo = new ArrayList<Nodo>();
	private ArrayList<Nodo> Q = new ArrayList<Nodo>();
	
	private ArrayList<Nodo> fila;

	public Dijkstra(ArrayList<Nodo> nodoList) {		
		grafo = nodoList;

	}
	
	/*

	public HashMap<Integer, Nodo> getGrafo() {
		return grafo;
	}
	
	public void setGrafo(HashMap<Integer, Nodo> grafo) {
		this.grafo = grafo;
	}
	
	*/


	public int getOrdem() {
		return grafo.size();
	}


	

 double calculaDistancia(Nodo A, Nodo B) {

		int x1 = A.getI();
		int y1 = A.getJ();

		int x2 = B.getI();
		int y2 = B.getJ();

		// raiz quadrada de (x2-x1)^2+(y2-y1)^2
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	
	
/***
 *  nInicializa todos com a distancia maxima
 */
	private void initializeSingleSource(Nodo s) {
			for (Nodo n : grafo) {
				n.setDistancia(99);
				n.setPai(null);
			
		}
		s.setDistancia(0);
	}

	
	
	private ArrayList<Nodo> addGrafoNaLista() {
				
		for (Nodo n : grafo) {
			if (n.getNodosAdjacentes().size() != 0)
				Q.add(n);
		}

		return Q;
	}

	
	private Nodo extractMin() {
		Nodo u = null;
		for (Nodo Nodo : Q) {
			if (u == null) {
				u = Nodo;
			} else {
				if (Nodo.getDistancia() < u.getDistancia()) {
					u = Nodo;
				}
			}
		}
		Q.remove(u);

		return u;
	}
	
	

	private void relax(Nodo u, Nodo v) {
		//NumberFormat nf = new DecimalFormat("#0.00");
		double w = calculaDistancia(u, v);

		if (v.getDistancia() > (u.getDistancia() + w)) {
			v.setDistancia(u.getDistancia() + w);
			v.setPai(u);
		} else {
		}

	}

	private void dijkstra(Nodo origem) {
		Nodo u = null;

		initializeSingleSource(origem);
		Q = addGrafoNaLista();
		while (!Q.isEmpty()) {
			u = extractMin();
			//logging("dijkstra", "[" + u.getNome() + "]");
			for (Nodo v : u.getNodosAdjacentes()) {
				relax(u, v);
			}
		}
	}

	public void dijkstra(Nodo origem, Nodo destino, boolean imprime) {
		fila =new ArrayList<Nodo>();
		dijkstra(origem);
	
		filaDijkstra(destino);
		if (imprime) {
		String str = "";	
		str += imprimeDijkstra(destino);
		str += "\nTotal:"+ destino.getDistancia();
		str += "\n";
		System.out.println(str);
		}
		
				

	}

	public String imprimeDijkstra(Nodo v) {
		String str = "";
		if (v.getPai() != null) {
			str += imprimeDijkstra(v.getPai());
			str += "\nNodo" +  v.getNome() +", Pai: " + v.getPai().getNome();
			fila.add(v);
		}else {
			fila.add(v);
		}
		return str;
	}
	
	public void filaDijkstra(Nodo destino) {
		
		if (destino.getPai() != null) {
			filaDijkstra(destino.getPai());		
			fila.add(destino);
		}else {
			fila.add(destino);
		}		
	}
	
	
	public ArrayList<Nodo> getFila() {
		return fila;
	}

	public void setFila(ArrayList<Nodo> fila) {
		this.fila = fila;
	}

	public String imprimeFila() {		
		
		String s = "";
		for(Nodo n : fila) {
			s += n.getNome() +";";
		}
		
		
		
		return s;
		
	}
	
	

	
	
	
	/*
	@Override
	public String toString() {
		String str = "";
		for (Map.Entry<Integer, Nodo> entry : grafo.entrySet()) {
			str += "\n[" + entry.getValue().getNome() + " x=" + entry.getValue().getI() + " y="
					+ entry.getValue().getJ() + "]\n  " + entry.getValue().getNodosAdjacentes();
		}
	*/
}
