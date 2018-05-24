package t3;

import java.util.ArrayList;

import t1.EnumDirecao;
import t3.Nodo.EnumStatus;
/** * 
 * @author Francisca
 *
 */

public class Mapa {


	private static Nodo[][] matrizNavegacao;
	private static int[][] matrizSimulacao;
	private static Mapa instance = null;
	public static int sizeI =7;
	public static int sizeJ =7;
	public static int tamanhoQuadros =26;	
	
	private static boolean isTodoExplorado = false;
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
	
	

	
	
	public static int[][] createDefaultMapa(){
		
	/*int [][] matrizMapX = 
		{{2, -1, 0, 0, 0, 0, 0},
		 {0, 0, 0, 0, -1, 0, 0},
		 {0, -1, 0, 0, 0, 0, 0},
		 {-1, 0, 0, 0, 0, 0, 0},
		 {-1, 1, 0, 0, 0, 0, 0},
		 {0, -1, 0, 0, 0, 0, 0},
		 {0, 0, 0, 0, 0, 0, 0}};
		 */
		
		
		
		/**
		 * Definir apenas paredes que se encontram Abaixo e á esquerda
		 */
		
		/*
		int [][] matrizMapX = 
			{{0,  0,    0,  0,    0, -1,   0,  0,   0,  0,   0,  0,   0, 0},
			 {0,  0,    0,  0,    0, -1,   0,  0,   0,  0,   0,  0,   0, 0},
			 {0, -1,    0,  0,    0, -1,   0,  0,   0, -1,   0,  0,   0, 0},
			 {0, -1,    0,  0,    0, -1,   0,  0,   0, -1,   0,  0,   0, 0},
			 {0, -1,    0,  0,    0, -1,   3,  0,   0, -1,   0,  0,   0, 0},
			 {0, -1,    0,  0,    0, -1,   0,  0,   0, -1,   0,  0,   0, 0},
			 {0, -1,    3,  0,    3, -1,   3,  0,   0, -1,   3,  0,   0, 0},
			 {0, -1,    0,  0,    0, -1,   0,  0,   0, -1,   0,  0,   0, 0},
			 {0, -1,    0,  0,    0, -1,   0,  0,   0, -1,   0,  0,   0, 0},
			 {0, -1,    0,  0,    0, -1,   0,  0,   0, -1,   0,  0,   0, 0},
			 {0,  0,    0,  0,    0,  0,   0,  0,   0,  0,   0,  0,   0, 0},
			 {0,  0,   -1, -1,   -1, -1,  -1, -1,  -1, -1,  -1,  0,   0, 0},
			 {0,  0,    0,  0,   -0,  0,   3,  0,   0,  0,   0,  0,   0, 0},
			 {0,  0,    0,  0,    0,  0,   0,  0,   0,  0,   0,  0,   0, 0},
			 
			 };
			 
			 */
		/*
		
		int [][] matrizMapX = 
			{
					 {0,  0,    0,  -1,      3, 0},
					 {0,  0,    0,  -1,      0, 0},
					 {0,  0,    0,   0,      0, 0},
					 {0,  0,    0,   0,      0, 0},
			 };
				
				*/
		
		/**
		 * Definir apenas paredes que se encontram Abaixo e á esquerda
		 */		
		
		int [][] matrizMapX = 
			{{0,  0,    0,  0,    0,  0,   0,  0,   0,  0,   0,   0,   0, 0},
			{0,  0,    0,  0,    0,  0,   0,  0,   0,  0,   0,   0,   0, 0},
			{0,  0,    0,  0,    0,  0,   0,  0,   0,  0,   0,  -1,   0, 0},
			{0,  0,   -1, -1,   -1, -1,  -1, -1,  -1, -1,   0,  -1,   0, 0},
			{0,  0,    0,  0,    0,  0,   3,  0,   0,  0,   0,  -1,   0, 0},
			{0,  0,    0,  0,    0,  0,   0,  0,   0,  0,   0,  -1,   0, 0},
			{0,  0,    3,  0,    3,  0,   3,  0,   0,  0,   0,  -1,   0, 3},
			{0,  0,   -1, -1,   -1, -1,  -1, -1,  -1, -1,   0,  -1,   0, 0},
			{0,  0,    0,  0,    0,  0,   3,  0,   0,  0,   0,  -1,   0, 0},
			{0,  0,    0,  0,    0,  0,   0,  0,   0,  0,   0,  -1,   0, 0},
			{0,  0,    0,  0,    0,  0,   0,  0,   0,  0,   0,  -1,   0, 0},
			{0,  0,   -1, -1,   -1, -1,  -1, -1,  -1, -1,   0,  -1,   0, 0},
			{0,  0,    0,  0,    0,  0,   0,  0,   0,  0,   0,   0,   0, 0},
			{0,  0,    0,  0,    0,  0,   0,  0,   0,  0,   0,   0,   0, 0},
			 
			 };
			 
				
	
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
	
	public static String imprimeMatrizEmString(String[][] m, boolean isMapaDobrado) {
		String matriz = "";
		// System.out.println("_________________");
		matriz = "_________________\n";
		try {
			int rows = m.length;
			int columns = m[0].length;
			String str = "|\t";
			

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (j % 2 != 0 || i % 2 != 0 ){
						if( m[i][j].contentEquals("-1")){
							str += "|_|"+ "\t";	
						}else{
							str += "--"+ "\t";	
						}
					}else{					
					str += m[i][j] + "\t";
						
					}
						
				}
				
				matriz += (str + "|\n");
				str = "|\t";
			}

		} catch (Exception e) {
			System.out.println("Matriz Vazia!");
		}
		return matriz;
	}
	
	public static String imprimeRoboEmString() {
		return imprimeRoboEmString(PotatoRobo.desenhaRobo(), PotatoRobo.nodoAtual.getI(), PotatoRobo.nodoAtual.getJ(), true);
	}
	
/**Impressão para testes
 * 
 * @param robo imagem atual do robo em dtring ex: ^.<
 * @param PosicaoI
 * @param posicaoJ
 * @return
 */
	public static String imprimeRoboEmString(String robo, int PosicaoI, int posicaoJ, boolean isMapaDobrado) {
		
		if(isMapaDobrado){
			PosicaoI  = PosicaoI  * 2;

			posicaoJ  = posicaoJ  * 2;
		}
		//System.out.println(robo+","+PosicaoI + ","+ posicaoJ);
		int sizeI = matrizSimulacao.length;
		int sizeJ = matrizSimulacao[0].length;
		String[][] matrizS = new String[sizeI][sizeJ];

		for (int i = 0; i < sizeI; i++) {
			for (int j = 0; j < sizeJ; j++) {
				if (PosicaoI == i && posicaoJ == j) {
					matrizS[i][j] = robo;
					
				} else {
					matrizS[i][j] = String.valueOf( matrizSimulacao[i][j]);
				}
			}

		}

		return imprimeMatrizEmString(matrizS, isMapaDobrado);
	}
	
	public static Nodo[][] getMatrizNavegacao() {
	return matrizNavegacao;
}

public static void setMatrizNavegacao(Nodo[][] matrizNavegacao) {
	Mapa.matrizNavegacao = matrizNavegacao;
}

	public static int[] procuraRobo(int[][] matriz) {
		int[] robo = { -1, -1 };
		int sizeI = matriz.length;
		int SizeJ = matriz[0].length;
		// System.out.println(sizeI);
		// System.out.println(SizeJ);

		for (int i = 0; i < sizeI; i++) {
			for (int j = 0; j < SizeJ; j++) {

				if (matriz[i][j] == EnumMapa.ROBO.id) {
					robo[0] = i;
					robo[1] = j;
				}

			}
		}


			return robo;
	}
	

public static int[][] getMatrizSimulacao() {
	return matrizSimulacao;
}

public static void setMatrizSimulacao(int[][] matrizSimulacao) {
	Mapa.matrizSimulacao = matrizSimulacao;
}

public enum EnumMapa {
	CAMINHO(0), OBJETIVO(2), OBSTACULO(-1), ROBO(1), PRODUTO(3);
	int id;

	private  EnumMapa(int i) {
		this.id = i;
	}
}

public static boolean isTodoExplorado() {
	
	if(!isTodoExplorado){
		
			int sizeI= matrizNavegacao.length;
			int sizeJ = matrizNavegacao[0].length;			
			

			for (int i = 0; i < sizeI; i++) {
				for (int j = 0; j < sizeJ; j++) {
				
					if(Mapa.getNodo(i, j).isNodoPercorrido() == false){
						return false;
					}
					
				}
				
			}
			
			isTodoExplorado = true;
		
	}
	
	return isTodoExplorado;
}



	
				
}
