package t3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra {

	private HashMap<Integer, Nodo> grafo = new HashMap<Integer, Nodo>();// nome,Nodo
	private ArrayList<Nodo> Q = new ArrayList<Nodo>();

	public Dijkstra() {

	}

	public HashMap<Integer, Nodo> getGrafo() {
		return grafo;
	}

	public int getOrdem() {
		return grafo.size();
	}


	public void setGrafo(HashMap<Integer, Nodo> grafo) {
		this.grafo = grafo;
	}


 double calculaDistancia(Nodo A, Nodo B) {

		int x1 = A.getI();
		int y1 = A.getJ();

		int x2 = B.getI();
		int y2 = B.getJ();

		// raiz quadrada de (x2-x1)^2+(y2-y1)^2
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	
	

	private void initializeSingleSource(Nodo s) {
		for (Map.Entry<Integer, Nodo> entry : grafo.entrySet()) {
			entry.getValue().setDistancia(99);
			entry.getValue().setPai(null);
		}
		s.setDistancia(0);
	}

	
	
	private ArrayList<Nodo> addGrafoNaLista() {
		for (Map.Entry<Integer, Nodo> entry : grafo.entrySet()) {

			if (entry.getValue().getNodosAdjacentes().size() != 0)
				Q.add(entry.getValue());
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
			//String dV = ((v.getDistancia() == Double.MAX_VALUE) ? "  ∞ " : nf.format(v.getDistancia()));
			//logging("dijkstra-relax", "[" + v.getNome() + "] (" + dV + " > " + nf.format(u.getDistancia()) + " + "
				//	+ nf.format(w) + ") OK");

			v.setDistancia(u.getDistancia() + w);
			v.setPai(u);
		} else {
			//String dV = ((v.getDistancia() == Double.MAX_VALUE) ? "  ∞ " : nf.format(v.getDistancia()));
			/*logging("dijkstra-relax",
					"[" + v.getNome() + "] (" + dV + " > " + nf.format(u.getDistancia()) + " + " + nf.format(w) + ")");
					*/
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

	public void dijkstra(Nodo origem, Nodo destino) {
		dijkstra(origem);
		String str = "";
		//str += String.format("%5s %5s %10s %25s", "X", "Y", "Vértice", "Distância");
		//str += "\n";
		str += imprimeDijkstra(destino);
		//str += String.format("%5s %5s %10s %25s", "", "", "Total", destino.getDistancia());
		System.out.println(str);

	}

	public String imprimeDijkstra(Nodo v) {
		String str = "";
		if (v.getPai() != null)
			str += imprimeDijkstra(v.getPai());

		//str += format("%5s %5s %10s %25s", v.getI(), v.getJ(), "V" + v.getNome(),
		//		((v.getPai() == null) ? v.getDistancia() : v.getDistancia() - v.getPai().getDistancia()));
		str += "\n";
		return str;
	}

	
	/*
	public String matrizDijkstra() {
	//	NumberFormat nf = new DecimalFormat("#0.00");
		String str = " ";
		String str1 = "d";
		String str2 = "π";

		for (Map.Entry<Integer, Nodo> entry : grafo.entrySet()) {
		//	str += String.format("%7s", entry.getValue().getNome());
		//	str1 += String.format("%7s", nf.format(entry.getValue().getDistancia()));
		//	str2 += String.format("%7s", entry.getValue().getPai());
		}

		return str + "\n" + str1 + "\n" + str2;
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
