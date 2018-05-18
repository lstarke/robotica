package t3;

import java.util.ArrayList;
/** * 
 * @author Francisca
 *
 */

public class Mapa {


	private static Nodo[][] matrizNavegacao;
	private static Mapa instance = null;
	public static int sizeI =7;
	public static int sizeJ =7;
	public static int tamanhoQuadros =26;
	//private static Nodo menorI = null;
	//private static Nodo menorJ = null;
	//private static int menorI_Int = Integer.MAX_VALUE;
	//private static int menorJ_Int = Integer.MAX_VALUE;
	

	private Mapa() {
		create(sizeI, sizeJ);
	}
	
	public static Mapa getInstance() {
		if(instance == null) {
			instance = new Mapa();
		}
		return instance;
	}
	
	public static int getSizeI() {
		return sizeI;
	}

	public static void setSizeI(int sizeI) {
		Mapa.sizeI = sizeI;
	}

	public static int getSizej() {
		return sizeJ;
	}

	public static void setSizej(int sizej) {
		Mapa.sizeJ = sizej;
	}

	
	
	public static void create(int i, int j) {
		matrizNavegacao = new Nodo[i][j];
		/*
		for(int ix = 0; ix < i ; ix++) {
			for(int jx = 0; jx < j ; jx++) {
				matrizNavegacao[ix][jx] = null;
			}
		}
		*/
		
		
		setSizeI(i);
		setSizej(j);	
		
	}
	


	

	
	
	
	/**
	 * Se não existe o Nodo, ele cria um na posicação i j
	 * @param i
	 * @param j
	 * @return
	 */
	public static Nodo getNodo(int i, int j){
	
		if (matrizNavegacao[i][j] == null) {
			//System.out.println(i+","+ j);
			Nodo nodo = new Nodo(i,j);
			matrizNavegacao[i][j] = nodo;
			return nodo;
			
		}else {
			return matrizNavegacao[i][j];	
		}
				
		
		
	}
	
	

	
	
	/*public static int[][] createDefaultMapa(){
		
	int [][] matrizMapX = 
		{{2, -1, 0, 0, 0, 0, 0},
		 {0, 0, 0, 0, -1, 0, 0},
		 {0, -1, 0, 0, 0, 0, 0},
		 {-1, 0, 0, 0, 0, 0, 0},
		 {-1, 1, 0, 0, 0, 0, 0},
		 {0, -1, 0, 0, 0, 0, 0},
		 {0, 0, 0, 0, 0, 0, 0}};
	
	return  matrizMapX;
	
	}
	*/
	
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
