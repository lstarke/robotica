package t3;

import java.util.ArrayList;

public class Mapa {
	
	
	private static int[][] matrizNavegacao;
	private static Mapa instance = null;
	private static ArrayList<Nodo> nodoList = new ArrayList<Nodo>();
	private static Nodo menorI = null;
	private static Nodo menorJ = null;
	private static int menorI_Int = Integer.MAX_VALUE;
	private static int menorJ_Int = Integer.MAX_VALUE;
	

	private Mapa() {
		
	}
	
	public static Mapa getMapa() {
		if(instance == null) {
			instance = new Mapa();
		}
		return instance;
	}
	
	public static void addNodo(Nodo nodo){
		nodoList.add(nodo);
		
		
		if(nodo.getX() < menorI_Int){
			menorI_Int = nodo.getX();
		}
		if(nodo.getY() < menorJ_Int){
			menorJ_Int = nodo.getY();
		}
		
	}
	public static Nodo buscaNodo(int i, int j){
		Nodo nodo = new Nodo();
		for (Nodo n: nodoList) 
		{
			if(nodo.getX() == i && nodo.getY() == j){
				return n;
			}
		}
		return null;
		
	}
	
	public static void montaMatrizNodo(){
		matrizNavegacao = new int[7][7];
		int modI = 0 - menorI_Int;
		int modJ = 0 - menorJ_Int;
		/*
		for (int i =0 ; i < matrizNavegacao.length ; i++ ) {
			for(int j=0; i < matrizNavegacao.length; j++){
				
				matrizNavegacao[i][j]=
				
			
			
		}
		}*/
		
		
		
		//nodoList.
		
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
