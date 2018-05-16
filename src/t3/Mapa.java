package t3;

import java.util.ArrayList;

public class Mapa {
	
	
	private static int[][] matrizNavegacao = null;
	private static Mapa instance = null;
	private static ArrayList<Nodo> nodoList = new ArrayList<Nodo>();
	

	private Mapa() {
		
	}
	
	public static Mapa getMapa() {
		if(instance == null) {
			instance = new Mapa();
		}
		return instance;
	}
	
	
	public static int[][] createDefaultMapa(){
		
	int [][] matrizMapX = 
		{{2, -1, 0, 0, 0, 0, 0},
		 {0, 0, 0, 0, -1, 0, 0},
		 {0, -1, 0, 0, 0, 0, 0},
		 {-1, 0, 0, -1, -1, 0, 0},
		 {-1, 1, 0, 0, 0, 0, 0},
		 {0, -1, 0, 0, 0, 0, 0},
		 {0, 0, 0, -1, 0, 0, 1}};
	
	return  matrizMapX;
	
	}
	public static String imprimeMatrizEmString(int[][] m) {
		String matriz = "";
		// System.out.println("_________________");
		matriz = "_________________\n";
		try {
			int rows = m.length;
			int columns = m[0].length;
			String str = "|\t";

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					str += m[i][j] + "\t";
				}

				matriz += (str + "|\n");
				str = "|\t";
			}

		} catch (Exception e) {
			System.out.println("Matriz Vazia!");
		}
		return matriz;
	}
	
				
}
