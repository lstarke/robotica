package t1;

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
		/*int [][]matrizMapX =  {{0,0,0 ,0,0,0,0,0,0,0,},
				{0,0,0 ,0,0,0,0,0,0,0,},
				{0,0,-1,-1,-1,0,0,0,0,0,},
				{0,0,-1,-1,-1,0,0,0,0,0,},
				{0,0,-1,-1,-1,0,0,0, 2,0,},
				{0,0,-1,0,0,0,0,0,0,0,},
				{0,-1,0,0,0,0,0,0,0,0,},
				{-1,0,0,0,0,0,0,0,0,0,}};*/
		int [][]matrizMapX =  {{0,0,0 ,0,0,0,0,0,0,0,},
				{0,0,0 ,0,0,0,0,0,0,0,},
				{0,0,-1,-1,-1,0,0,0,0,0,},
				{0,0,-1,-1,-1,0,0,0,0,0,},
				{0,0,-1,-1,-1,0,0,0, 2,0,},
				{0,0,-1,0,0,0,0,0,0,0,},
				{0,-1,0,0,0,0,0,0,0,0,},
				{-1,0,0,0,0,0,0,0,0,0,}};

		
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
		//metodo em teste preencheZeros(matriz);
		
	
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
		
		for(int i = 0 ; i< sizeI ; i++) {
			for(int j = 0; j< sizeJ; j++) {
				
				preencheZerosXR(i, j, matriz);
			}
		}
		return matriz;
	
	}
	/** Verefica e corrige os quadrantes na direção=  direita baixo
	 * 
	 * @param i
	 * @param j
	 * @param matriz
	 */
	private static int preencheZerosXR(int i, int j, int [][]matriz) {
		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;
		int valor = 0;
		
		if(matriz[i][j] == Mapa.CAMINHO.id) {
			
			if(i+1 < sizeI) {
				if(matriz[i+1][j] > 0 ) { // maior que zero par anão pegar os obstaculos
					valor = matriz[i+1][j]+1;
					matriz[i][j] =  valor ;
				}else {
					if(matriz[i+1][j] == Mapa.OBSTACULO.id) {					
					valor = 0;
					//valor = preencheZerosXL(i-1, j, matriz);
					}else {
					 valor = preencheZerosXR(i+1, j ,matriz)+1;
					 matriz[i][j] =  valor;
					}
				}	
			// se  o valor continua 0 verifica par ao J
			if(j+1 < sizeJ && valor == 0) {				
				
				if(matriz[i][j+1] > 0 ) { // maior que zero par anão pegar os obstaculos
					valor = matriz[i][j+1] +1;
					matriz[i][j] =  valor ;
				}else {
					if(matriz[i][1+j] == Mapa.OBSTACULO.id) {					
						valor = 0;
						//valor = preencheZerosXL(i, j, matriz);
					}else {
					valor = preencheZerosXR(i, j+1 ,matriz)+1;
					matriz[i][j] =  valor;
					}
				}
				
			}
			
			}
			//System.out.println("");
			//imprimeMatriz(matriz);
		}
		return valor;
		
	}
	
	private static int preencheZerosXL(int i, int j, int [][]matriz) {
		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;
		int valor = 0;
	
		if(j-1 >= 0 ) {				
			
			if(matriz[i][j-1] > 0 ) { // maior que zero par anão pegar os obstaculos
				valor = matriz[i][j-1] +1;
				matriz[i][j] =  valor ;
			}else {
				if(matriz[i][j-1] == Mapa.OBSTACULO.id) {					
					valor = 0;
				}else {
					valor = preencheZerosXL(i,j-1,matriz);
				//matriz[i][j] =  valor;
				}
			}
		}
		
		if(i-1 >= 0 && valor == 0) {				
			
			if(matriz[i-1][j] > 0 ) { // maior que zero par anão pegar os obstaculos
				valor = matriz[i-1][j] +1;
				matriz[i][j] =  valor ;
			}else {
				if(matriz[i-1][j] == Mapa.OBSTACULO.id) {					
					valor = 0;
				}else {
				valor = preencheZerosXL(i-1, j ,matriz)+1;
				//matriz[i][j] =  valor;
				}
			}
		}
		return valor;
	}
	
	public static void imprimeMatriz(int[][] m){
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
