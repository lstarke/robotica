package t1;

import java.util.ArrayList;

public class PotatoWaveFront {

	//@franciscaedyrXavier
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		matrizEntrada(1, 1);
		//imprimeMatriz(matrizMap);
		int objetivo[] = procuraObjetivo(matrizMap);		
		//waveFront(objetivo, matrizMap);
		imprimeMatriz(waveFront2(objetivo, matrizMap));
		
	}
	
	private static int[][] matrizMap;
	
	public static void matrizEntrada(int dimencaoX, int dimencaoY) {
		
		/*int [][]matrizMapX = 
				{{0,0,0,0,0,0,0},
				 {0,0,0,0,-1,-1,-1},
				 {0,0,0,0,-1,0,0},
				 {0,0,2,0,-1,-1,0},
				 {0,0,0,0,-1,0,0},
				 {0,0,0,0,-1,0,0},
				 {0,0,0,0,0,0,0}};*/
		int [][]matrizMapX = 
			  {{-1,2,0 ,0,0,0,0,0,0,0,},
				{-1,0,0 ,0,0,0,0,0,0,0,},
				{-1,-1,-1,-1,-1,0,0,0,0,0,},
				{0,0,-1,-1,-1,0,0,0,-1,0,},
				{0,0,-1,-1,-1,0,0,-1, 0,-1,},
				{-1,-1,-1,0,0,0,0,0,-1,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,}};
		/*int [][]matrizMapX =  {{0,0,0 ,0,0,0,0,0,0,0,},
				{0,0,0 ,0,0,0,0,0,0,0,},
				{0,0,-1,-1,-1,0,0,0,0,0,},
				{0,0,-1,-1,-1,0,0,0,0,0,},
				{0,0,-1,-1,-1,0,0,0, 2,0,},
				{0,0,-1,0,0,0,0,0,0,0,},
				{0,-1,0,0,0,0,0,0,0,0,},
				{-1,0,0,0,0,0,0,0,0,0,}};
				*/

		
		matrizMap = matrizMapX;
		}
	
	public static int[] procuraObjetivo(int[][] matriz) {
		int[] objetivo = {-1,-1};
		int sizeI = matriz.length;
		int SizeJ =matriz[0].length;		
		//System.out.println(sizeI);
		//System.out.println(SizeJ);
		
		
		for(int i = 0; i < sizeI; i++) {
			for(int j = 0; j < SizeJ; j++) {
				
				if(matriz[i][j] == Mapa.OBJETIVO.id){
					objetivo[0] = i;
					objetivo[1] = j;
				}				
				
				}				
			
			}
		//System.out.println(objetivo[0]+","+objetivo[1]);
		
		return objetivo;	
		}	
	/**WaveFront
	 * 
	 * @param posição do objtivo na matriz com valores  0 =i e j=1 
	 * @param matriz matri de mapeamento dos obstaculos
	 */
	public static int[][] waveFront2(int[] objetivo, int [][] matriz) {
		int objetivoI = objetivo [0];
		int objetivoJ = objetivo [1];
		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;
		//
		
		//int valor = matriz[objetivoI][objetivoJ];
		for(int i = objetivoI, negI= objetivoI; i < sizeI || negI >=0; i++, negI--) {				
			for(int j = objetivoJ, negJ= objetivoJ; j < sizeJ || negJ >=0; j++, negJ--) {			
				
				//PERCORRE QUARANTE 1				
				if(i<sizeI && j < sizeJ) {	
					//System.out.println("1:"+i+","+j +" = " +valor);	
					wavefrontQuarante(i, j, matriz);		
				}				
				//QUADRANTE 2				
				if(negI>= 0 && j < sizeJ) {
					//	System.out.println("2:"+negI+","+j +" = " +valor);						
						wavefrontQuarante(negI, j, matriz);					
					
				}		
				//QUADRANTE 3
				if(i<sizeI && negJ >= 0) {
					//System.out.println("3:"+i+","+negJ+" = " +valor);
					wavefrontQuarante(i, negJ, matriz);	
					}										
				//QUADRANTE 4
				
				if(negI >= 0 && negJ >= 0) {					
				//System.out.println("4:"+negI+","+negJ+" = " +valor);
				wavefrontQuarante(negI, negJ, matriz);	
				}					
			}				
		}
		imprimeMatriz(matriz);		
		preencheZeros(matriz);
		
		/*for(int i = 0; i< sizeI ; i++ ) {
			for(int j = 0; j< sizeJ ; j++ ) {	
				if(matriz[i][j] == 0) {
				indentificapontoInalcacavel(i, j, matriz);
				}
			}
		}*/
		
	
		return matriz;
						

		
	}
	/**
	 * Processo do quadrante do waveFront	 * 
	 * @param i
	 * @param j
	 * @param matriz
	 */
	private static void wavefrontQuarante(int i, int j, int [][] matriz) {
		//if(i >= 0 && j >=0) {
		int valor = matriz[i][j] +1;
		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;
		
		if(matriz[i][j] > 0){				
			if(j+1< sizeJ) {
				if(matriz[i][j+1]== Mapa.CAMINHO.id ) {
					matriz[i][j+1] = valor;				
					//System.out.println(matriz[i][j]+"->"+i+","+j+" = " +valor);
				}
			}
			if(i+1< sizeI) {
				if(matriz[i+1][j]== Mapa.CAMINHO.id) {
				matriz[i+1][j] = valor;			
				//System.out.println(matriz[i][j]+"->"+i+","+j+" = " +valor);
				}
				}
			
			if(j-1>= 0) {
				if(matriz[i][j-1] == Mapa.CAMINHO.id) {
					matriz[i][j-1] = valor;				
					//System.out.println(matriz[i][j]+"->"+i+","+j+" = " +valor);
				}
			}
			if(i-1 >= 0) {
				if(matriz[i-1][j] == Mapa.CAMINHO.id) {
				matriz[i-1][j] = valor;		
			//	System.out.println(matriz[i][j]+"->"+i+","+j+" = " +valor);
				
				}
			}
		}
			 
		 
	}
	
	private static int[][] preencheZeros( int [][]matriz) {
		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;
		boolean existeZeros = false;
		int cont = 0;
		
		do {
			existeZeros = false; // tem de considerar que não tem nenhum para poder reiniciar a verificação
			for(int i = 0 ; i< sizeI ; i++) {
				for(int j = 0; j< sizeJ; j++) {
					
					if(matriz[i][j]  == Mapa.CAMINHO.id) {
						 if(preencheZerosBuscaValor(i, j, matriz) == 0) { // se depois de tratar continua zero
							 if(cont == (sizeI * sizeJ)) {
								  matriz[i][j] = -1;
							 }else {								 
							 existeZeros = true;
							 }
							 
						 }else{
							 if(!existeZeros) { //se  não existe nenhum zero até então  i
								 //System.out.println(matriz[i][j]);
								 existeZeros = false;
							 }
							
						 }
						
					}else {
						 if(!existeZeros) { //se  não existe nenhum zero até então  i
							 existeZeros = false;
						 }
					}
					
				}
			}
			cont++;
			//imprimeMatriz(matriz);
		}while(existeZeros);		
		
		return matriz;
	
	}
	/*
	private static void indentificapontoInalcacavel(int i, int j, int [][]matriz) {
		ArrayList<int[]> caminho = new ArrayList<>();
		ArrayList<int[]> alterado= new ArrayList<>();
		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;
		boolean corrigir = false;
		int valor;
		int positionx[] = {i,j} ;
		int position[] = new int[2];
		int positionInicial[] = {i,j};
		caminho.add(positionx);
		alterado.add(positionInicial);
		
		
		matriz[i][j] = -2;
	
		boolean volta = true;
		boolean mudaDirecao = false;
		boolean saida = false;
		
		//for(int ix = i; ix < sizeI ; ix++ ) {
		//	for(int jx = j; jx < sizeJ ; jx++ ) {
		//System.out.println("batata1");
				//direita
				while(positionInicial != position && saida == false ) {
					mudaDirecao = false;
					saida = false;
					//System.out.println("batata2");
					//position = new int[2];					
					position = caminho.get(caminho.size()-1);
					i = position[0] ;
					j = position[1] ;
					
					//System.out.println("inicioa:" +  i +", "+ j);
					
				if(i+1 < sizeI) {
					if(matriz[i+1][j]  == Mapa.OBSTACULO.id || matriz[i+1][j]  == -2) {
					 mudaDirecao = true;
					}else {
					if(matriz[i+1][j]  == Mapa.CAMINHO.id) { 
					 matriz[i][j] = -2;					 
					 position[0] = i+1;
					 position[1] = j;					 
					 caminho.add(position);
					}else {
						saida = true;
					}
					
					}
					
				}else {
					mudaDirecao = true;
				}
				//baixo
				if(mudaDirecao){					
					if(j+1 < sizeJ) {
						if(matriz[i][j+1]  == Mapa.OBSTACULO.id || matriz[i+1][j] == -2) {
						 mudaDirecao = true;
						}else {
						if(matriz[i][j+1]  == Mapa.CAMINHO.id) { 
							 matriz[i][j] = -2;									
							 position[0] = i;
							 position[1] = j+1;							
							 caminho.add(position);
						}else {
							saida = true;
						}
						}
					}else {
						mudaDirecao = true;
					}
		
				}
				//esqueda
				if(mudaDirecao){
					
					if(i-1 >=0) {
						if(matriz[i-1][j]  == Mapa.OBSTACULO.id | matriz[i+1][j] == -2) {
						 mudaDirecao = true;
						}else {
							if(matriz[i-1][j]  == Mapa.CAMINHO.id) { 
							 matriz[i][j] = -2;									 
							 position[0] = i-1;
							 position[1] = j;							
							 caminho.add(position);
							}else {
								saida = true;
							}
							}
					}else {
						mudaDirecao = true;
					}
						
					
				}
				//cima
				if(mudaDirecao){
					
					if(j-1 >= 0) {
						if(matriz[i][j-1]  == Mapa.OBSTACULO.id || matriz[i][j-1]  == -2) {
						volta = true;
						}else {
							if(matriz[i-1][j]  == Mapa.CAMINHO.id){
							 
							 matriz[i][j] = -2;							 
							 position[0] = i-1;
							 position[1] = j;
							 caminho.add(position);
							}else {
								saida = true;
							}
						}
						
					}else {
						matriz[i][j] = -2;
						position = caminho.get(caminho.size()-1);						
						volta = true;
					}
				
					
				}
				//System.out.println("AAA");
				//imprimeMatriz(matriz);
				//System.out.println("AA");
		
				
				}
				
				for(int[] p : alterado){
					System.out.println("saida:" +saida +"|"+  p[0] +", "+ p[1]);	
					if(saida) {
						
						int iz =p[0];
						int jz =p[1];
						matriz[iz][jz] = 0; 
						//imprimeMatriz(matriz);
					}else {
						int iz =p[0];
						int jz =p[1];
						matriz[ iz][jz] = -1;
					}
						
					
				}
				///System.out.println("BBB");
				//imprimeMatriz(matriz);
				//System.out.println("BBB");
	
	}
	*/
	
	private static int preencheZerosBuscaValor(int i, int j, int [][]matriz) {
		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;
		int valor = 0;
		
		if(i+1 < sizeI) {
			if(matriz[i+1][j]  != Mapa.OBSTACULO.id  && matriz[i+1][j] != Mapa.CAMINHO.id  ) {
			valor = matriz[i+1][j] +1;	
			matriz[i][j] = valor;
			}
		}
		if(j+1 < sizeJ) {
			if(matriz[i][j+1]  != Mapa.OBSTACULO.id && matriz[i][j] == Mapa.CAMINHO.id  && matriz[i][j+1] != Mapa.CAMINHO.id ) {
				valor = matriz[i][j+1] +1;	
				matriz[i][j] = valor;
				}		
		}
		if(i-1 >= 0) {
			if(matriz[i-1][j]  != Mapa.OBSTACULO.id && matriz[i][j] == Mapa.CAMINHO.id  && matriz[i-1][j] != Mapa.CAMINHO.id ) {
				valor = matriz[i-1][j] +1;	
				matriz[i][j] = valor;
				}			
		}
		if(j-1 >= 0) {
			if(matriz[i][j-1]  != Mapa.OBSTACULO.id && matriz[i][j] == Mapa.CAMINHO.id && matriz[i][j-1] != Mapa.CAMINHO.id ) {
				valor = matriz[i][j-1] +1;	
				matriz[i][j] = valor;
				}			
		}
		//imprimeMatriz(matriz);
		return valor;
		
	}
	
	public static void imprimeMatriz(int[][] m){
		 System.out.println("_________________");
	    try{
	        int rows = m.length;
	        int columns = m[0].length;
	        String str = "|\t";

	        for(int i=0;i<rows;i++){
	            for(int j=0;j<columns;j++){
	                str += m[i][j] + "\t";
	            }

	            System.out.println(str + "|");
	            str = "|\t";
	        }

	    }catch(Exception e){System.out.println("Matriz Vazia!");}
	}
	
	public enum Mapa {
		CAMINHO(0), OBJETIVO(2),OBSTACULO(-1),ROBO(1); 
		private int id;
		private Mapa(int i) {
			this.id = i;
		}
	}
	
	//@franciscaedyrXavier
}
