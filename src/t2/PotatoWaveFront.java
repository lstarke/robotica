package t2;

import java.util.ArrayList;

import javax.microedition.lcdui.List;

import lejos.util.Matrix;

public class PotatoWaveFront {

	// @franciscaedyrXavier
	
	//private static PotatoManager pM = new PotatoManager();
	private static PotatoWaveFront pWF = new PotatoWaveFront();
	private static EnumDirecao direcaoAtual = EnumDirecao.TRAZ;
	private static int posicaoRoboI;
	private static int posicaoRoboJ;
	private static int objetivoI, objetivoJ;	
	private static int[][] matrizNavegacao; 
	private static ArrayList<int[]> caminho;
	//| |0|
		//|3|x|1| = 2 frente, 1 = direita; 3 = esquerda 0 = tráz;
		//| |2|
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		matrizNavegacao = matrizEntrada();
		PotatoManager.defineVelocidade(400);
		
		int [] posicao = procuraObjetivo(matrizNavegacao);
		if(posicao[0] != -1 && posicao[1] != -1) {
			matrizNavegacao  = waveFront2(matrizNavegacao);		
			System.out.println(imprimeMatrizEmString(matrizNavegacao));
			objetivoI = posicao[0];
			objetivoJ = posicao[1];
			System.out.println("Objetivo:"  + posicao[0]+", " + posicao[1]+"");
			Thread.sleep(1000);
			
		int [] robo = procuraRobo(matrizNavegacao);
		if(robo[0] != -1 && robo[1] != -1) { 
			posicaoRoboI =  robo[0];
			posicaoRoboJ =  robo[1];
			System.out.println("PosicaoRobo:"  + robo[0]+", " + robo[1]+"\n");
			Thread.sleep(1000);
		   System.out.println(imprimeRoboEmString(matrizNavegacao, posicaoRoboI, posicaoRoboJ, direcaoAtual));
				
		   Thread.sleep(1000);
				caminho =  pWF.menorCaminho(matrizNavegacao, posicaoRoboI, posicaoRoboJ);				
				PotatoManager.andaCaminho(caminho, direcaoAtual, 7,  posicaoRoboI, posicaoRoboJ);
				
				//Thread.sleep(1000);
				
			}else {
				System.out.println("Posição do objetivo não encontrado!");
				//Thread.sleep(1000);
			}			
			
		}else {
			System.out.println("Robo não encontrado!");
			Thread.sleep(1000);
		}
			
		

	}

	private static int[][] matrizMap;

	/**
	 * Matriz de teste
	 * 
	 * @param dimencaoX
	 * @param dimencaoY
	 */
	public static int[][] matrizEntrada() {

		/*
		 * int [][]matrizMapX = {{0,0,0,0,0,0,0}, {0,0,0,0,-1,-1,-1}, {0,0,0,0,-1,0,0},
		 * {0,0,2,0,-1,-1,0}, {0,0,0,0,-1,0,0}, {0,0,0,0,-1,0,0}, {0,0,0,0,0,0,0}};
		 */
		/*int[][] matrizMapX = {
				{ -1, 0, 2, 0, 0, 0, 0, 0, 0, 0, },
				{ -1, 0, 0, 0, 0, 0,0 ,0 , 0, 0, },
				{ -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, },
				{ 0, 0, -1, -1, -1, 0, 0, 0, -1, 0, },
				{ 0, 0, -1, -1, -1, 0, 0, -1, 0, -1, },
				{ -1, -1, -1, 0, 0, 0, 0, 0, -1, 0, },
				{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } };*/
		
		int [][] matrizMapX = 
		{{2, -1, 0, 0, 0, 0, 0},
		 {0, 0, 0, 0, -1, 0, 0},
		 {0, -1, 0, 0, 0, 0, 0},
		 {-1, 0, 0, -1, -1, 0, 0},
		 {-1, 1, 0, 0, 0, 0, 0},
		 {0, -1, 0, 0, 0, 0, 0},
		 {0, 0, 0, -1, 0, 0, 1}};
				
		/*
		 * int [][]matrizMapX = {{0,0,0 ,0,0,0,0,0,0,0,}, {0,0,0 ,0,0,0,0,0,0,0,},
		 * {0,0,-1,-1,-1,0,0,0,0,0,}, {0,0,-1,-1,-1,0,0,0,0,0,}, {0,0,-1,-1,-1,0,0,0,
		 * 2,0,}, {0,0,-1,0,0,0,0,0,0,0,}, {0,-1,0,0,0,0,0,0,0,0,},
		 * {-1,0,0,0,0,0,0,0,0,0,}};
		 */

		matrizMap = matrizMapX;
		return matrizMap;
	}

	public static int[] procuraObjetivo(int[][] matriz) {
		int[] objetivo = { -1, -1 };
		int sizeI = matriz.length;
		int SizeJ = matriz[0].length;
		// System.out.println(sizeI);
		// System.out.println(SizeJ);

		for (int i = 0; i < sizeI; i++) {
			for (int j = 0; j < SizeJ; j++) {

				if (matriz[i][j] == Mapa.OBJETIVO.id) {
					objetivo[0] = i;
					objetivo[1] = j;
				}

			}

		}
		// System.out.println(objetivo[0]+","+objetivo[1]);

		return objetivo;
	}
	
	public static int[] procuraRobo(int[][] matriz) {
		int[] objetivo = { -1, -1 };
		int sizeI = matriz.length;
		int SizeJ = matriz[0].length;
		// System.out.println(sizeI);
		// System.out.println(SizeJ);

		for (int i = 0; i < sizeI; i++) {
			for (int j = 0; j < SizeJ; j++) {

				if (matriz[i][j] == Mapa.ROBO.id) {
					objetivo[0] = i;
					objetivo[1] = j;
				}

			}

		}
		// System.out.println(objetivo[0]+","+objetivo[1]);

		return objetivo;
	}

	/**
	 * WaveFront
	 * 
	 * @param posição
	 *            do objtivo na matriz com valores 0 =i e j=1
	 * @param matriz
	 *            matri de mapeamento dos obstaculos
	 */
	public static int[][] waveFront2(int[][] matriz) {
		int[] objetivo = procuraObjetivo(matriz);
		int objetivoI = objetivo[0];
		int objetivoJ = objetivo[1];
		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;
		//

		// int valor = matriz[objetivoI][objetivoJ];
		for (int i = objetivoI, negI = objetivoI; i < sizeI || negI >= 0; i++, negI--) {
			for (int j = objetivoJ, negJ = objetivoJ; j < sizeJ || negJ >= 0; j++, negJ--) {

				// PERCORRE QUARANTE 1
				if (i < sizeI && j < sizeJ) {
					// System.out.println("1:"+i+","+j +" = " +valor);
					wavefrontQuarante(i, j, matriz);
				}
				// QUADRANTE 2
				if (negI >= 0 && j < sizeJ) {
					// System.out.println("2:"+negI+","+j +" = " +valor);
					wavefrontQuarante(negI, j, matriz);

				}
				// QUADRANTE 3
				if (i < sizeI && negJ >= 0) {
					// System.out.println("3:"+i+","+negJ+" = " +valor);
					wavefrontQuarante(i, negJ, matriz);
				}
				// QUADRANTE 4

				if (negI >= 0 && negJ >= 0) {
					// System.out.println("4:"+negI+","+negJ+" = " +valor);
					wavefrontQuarante(negI, negJ, matriz);
				}
			}
		}
		 imprimeMatriz(matriz);
		preencheZeros(matriz);

		return matriz;

	}

	/**
	 * Processo do quadrante do waveFront *
	 * 
	 * @param i
	 * @param j
	 * @param matriz
	 */
	private static void wavefrontQuarante(int i, int j, int[][] matriz) {
		// if(i >= 0 && j >=0) {
		int valor = matriz[i][j] + 1;
		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;

		//if (matriz[i][j] > 1) {
		if (matriz[i][j] != Mapa.CAMINHO.id && matriz[i][j] != Mapa.OBSTACULO.id && matriz[i][j] != Mapa.ROBO.id) {
			if (j + 1 < sizeJ) {
				if (matriz[i][j + 1] == Mapa.CAMINHO.id) {
					matriz[i][j + 1] = valor;
					// System.out.println(matriz[i][j]+"->"+i+","+j+" = " +valor);
				}
			}
			if (i + 1 < sizeI) {
				if (matriz[i + 1][j] == Mapa.CAMINHO.id) {
					matriz[i + 1][j] = valor;
					// System.out.println(matriz[i][j]+"->"+i+","+j+" = " +valor);
				}
			}

			if (j - 1 >= 0) {
				if (matriz[i][j - 1] == Mapa.CAMINHO.id) {
					matriz[i][j - 1] = valor;
					// System.out.println(matriz[i][j]+"->"+i+","+j+" = " +valor);
				}
			}
			if (i - 1 >= 0) {
				if (matriz[i - 1][j] == Mapa.CAMINHO.id) {
					matriz[i - 1][j] = valor;
					// System.out.println(matriz[i][j]+"->"+i+","+j+" = " +valor);

				}
			}
		}

	}

	private static int[][] preencheZeros(int[][] matriz) {
		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;
		boolean existeZeros = false;
		int cont = 0;

		do {
			existeZeros = false; // tem de considerar que não tem nenhum para poder reiniciar a verificação
			for (int i = 0; i < sizeI; i++) {
				for (int j = 0; j < sizeJ; j++) {

					if (matriz[i][j] == Mapa.CAMINHO.id) {
						if (preencheZerosBuscaValor(i, j, matriz) == 0) { // se depois de tratar continua zero
							if (cont == (sizeI * sizeJ)) {
								matriz[i][j] = -1;
							} else {
								existeZeros = true;
							}

						} else {
							if (!existeZeros) { // se não existe nenhum zero até então i
								// System.out.println(matriz[i][j]);
								existeZeros = false;
							}

						}

					} else {
						if (!existeZeros) { // se não existe nenhum zero até então i
							existeZeros = false;
						}
					}

				}
			}
			cont++;
			// imprimeMatriz(matriz);
		} while (existeZeros);

		return matriz;

	}

	private static int preencheZerosBuscaValor(int i, int j, int[][] matriz) {
		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;
		int valor = 0;

		if (i + 1 < sizeI) {
			if (matriz[i + 1][j] != Mapa.OBSTACULO.id && matriz[i + 1][j] != Mapa.CAMINHO.id  && matriz[i+1][j] != Mapa.ROBO.id) {
				valor = matriz[i + 1][j] + 1;
				matriz[i][j] = valor;
			}
		}
		if (j + 1 < sizeJ) {
			if (matriz[i][j + 1] != Mapa.OBSTACULO.id && matriz[i][j] == Mapa.CAMINHO.id
					&& matriz[i][j + 1] != Mapa.CAMINHO.id && matriz[i][j+1] != Mapa.ROBO.id) {
				valor = matriz[i][j + 1] + 1;
				matriz[i][j] = valor;
			}
		}
		if (i - 1 >= 0) {
			if (matriz[i - 1][j] != Mapa.OBSTACULO.id && matriz[i][j] == Mapa.CAMINHO.id
					&& matriz[i - 1][j] != Mapa.CAMINHO.id && matriz[i - 1][j] != Mapa.ROBO.id) {
				valor = matriz[i - 1][j] + 1;
				matriz[i][j] = valor;
			}
		}
		if (j - 1 >= 0) {
			if (matriz[i][j - 1] != Mapa.OBSTACULO.id && matriz[i][j] == Mapa.CAMINHO.id
					&& matriz[i][j - 1] != Mapa.CAMINHO.id && matriz[i][j-1] != Mapa.ROBO.id) {
				valor = matriz[i][j - 1] + 1;
				matriz[i][j] = valor;
			}
		}
		// imprimeMatriz(matriz);
		return valor;

	}

	/**
	 * Imprime a matriz passada por parâmetro
	 * 
	 * @param m
	 */
	public static void imprimeMatriz(int[][] m) {
		System.out.println("_________________");
		try {
			int rows = m.length;
			int columns = m[0].length;
			String str = "|\t";

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					str += m[i][j] + "\t";
				}

				System.out.println(str + "|");
				str = "|\t";
			}

		} catch (Exception e) {
			System.out.println("Matriz Vazia!");
		}
	}

	/**
	 * Salva a impressão da matriz em uma string
	 * 
	 * @param m
	 * @return
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

	public static String imprimeMatrizEmString(String[][] m) {
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

	
	public enum Mapa {
		CAMINHO(0), OBJETIVO(2), OBSTACULO(-1), ROBO(1);
		private int id;

		private Mapa(int i) {
			this.id = i;
		}
	}

	/*/**
	 * retorna a direcao que contem o menor valor considera: (i+1,j) frente (i-1,j)
	 * traz (i,j+1) direita (i,j-1) esquerta
	 * 
	 * @param matriz
	 * @param posicaoI
	 * @param posicaoJ
	 * @return
	 */
	public static EnumDirecao vira(int[][] matriz, int posicaoI, int posicaoJ, EnumDirecao diratual) {
		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;
		EnumDirecao direcaoParaIr = diratual;

		
		int menor = Integer.MAX_VALUE;

		// verifica frente
		if (posicaoI + 1 < sizeI) {
			if (menor > matriz[posicaoI + 1][posicaoJ] && matriz[posicaoI + 1][posicaoJ] != Mapa.OBSTACULO.id ) {
				menor = matriz[posicaoI + 1][posicaoJ];
				direcaoParaIr = EnumDirecao.FRENTE;
			}
		}

		// verifica atraz
		if (posicaoI - 1 >= 0) {
			if (menor > matriz[posicaoI - 1][posicaoJ] && matriz[posicaoI - 1][posicaoJ] != Mapa.OBSTACULO.id) {
				menor = matriz[posicaoI - 1][posicaoJ];
				direcaoParaIr = EnumDirecao.TRAZ;
			}

		}
		// verifica frente
		if (posicaoJ + 1 < sizeJ) {
			if (menor > matriz[posicaoI][posicaoJ + 1] && matriz[posicaoI ][posicaoJ +1] != Mapa.OBSTACULO.id) {
				menor = matriz[posicaoI][posicaoJ + 1];
				direcaoParaIr = EnumDirecao.DIREITA;
			}

		}

		// verifica esquerda
		if (posicaoJ - 1 >=0 ) {
			if (menor > matriz[posicaoI][posicaoJ - 1] && matriz[posicaoI][posicaoJ-1] != Mapa.OBSTACULO.id) {
				menor = matriz[posicaoI][posicaoJ - 1];
				direcaoParaIr = EnumDirecao.ESQUERDA;
			}
		}

		return direcaoParaIr;

	}

	/**
	 * Retorna um Array lista com o menor caminho da matriz tratada pelo o wavefront
	 * @param matriz tratada pelo waveFront
	 * @param posicaoI
	 * @param posicaoJ
	 * @return
	 */
	public ArrayList<int[]> menorCaminho(int[][] matriz, int posicaoI, int posicaoJ) {
		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;
		ArrayList<int[]> caminho = new ArrayList();
		int i = posicaoI;
		int j = posicaoJ;
		///para se o caminho não for definido  não entrar em loop
		int contadoCaminho = 0;

		do {
			
			//menor iniciado em o vai ignorar pareder -1
			int menor = Integer.MAX_VALUE;

			// verifica baixo
			if (posicaoI + 1 < sizeI) {
				if (menor > matriz[posicaoI + 1][posicaoJ] && matriz[posicaoI + 1][posicaoJ] != Mapa.OBSTACULO.id  && matriz[posicaoI + 1][posicaoJ] != Mapa.ROBO.id ) {
					//System.out.println("baixo");
					menor = matriz[posicaoI + 1][posicaoJ];
					i = posicaoI+1;
					j = posicaoJ;

				}
			}

			// verifica eCima
			if (posicaoI - 1 >= 0) {
				if (menor > matriz[posicaoI - 1][posicaoJ]  && matriz[posicaoI - 1][posicaoJ] != Mapa.OBSTACULO.id  && matriz[posicaoI - 1][posicaoJ] != Mapa.ROBO.id) {
					//System.out.println("Cima");
					menor = matriz[posicaoI - 1][posicaoJ];
					i = posicaoI-1;
					j = posicaoJ;

				}

			}
			// verifica direita
			//System.out.println((posicaoJ+1) +"<="+sizeJ+"|"+ menor);
			if (posicaoJ + 1 < sizeJ) {
				//System.out.println("EntrouDireita");
				if (menor > matriz[posicaoI][posicaoJ + 1]  && matriz[posicaoI ][posicaoJ +1] != Mapa.OBSTACULO.id  && matriz[posicaoI][posicaoJ +1] != Mapa.ROBO.id) {
					//System.out.println("direita");
					menor = matriz[posicaoI][posicaoJ + 1];
					i = posicaoI;
					j = posicaoJ+1;

				}

			}

			// verifica esquerda
			if (posicaoJ - 1 >= 0) {				
				if (menor > matriz[posicaoI][posicaoJ - 1] && matriz[posicaoI ][posicaoJ -1] != Mapa.OBSTACULO.id  && matriz[posicaoI ][posicaoJ -1] != Mapa.ROBO.id) {
					//System.out.println("esquerda");
					menor = matriz[posicaoI][posicaoJ - 1];
					i = posicaoI;
					j = posicaoJ-1;

				}
			}			
			
			int[] posicao = { i, j };
			caminho.add(posicao);
			posicaoI = i;
			posicaoJ = j;
			//System.out.print(i+ "," + j+"; ");
			
			contadoCaminho++;

			
		} while (matriz[i][j] != Mapa.OBJETIVO.id && contadoCaminho < (sizeI*sizeJ));


		return caminho;

	}

	/**
	 * Defini imagem do robo no mapa para usar nos testes
	 * 
	 * @param matriz
	 * @param PosicaoI
	 * @param posicaoJ
	 * @param direcaoRobo
	 * @return
	 */

	public static String imprimeRoboEmString(int[][] matriz, int PosicaoI, int posicaoJ, EnumDirecao direcaoRobo) {
		String robo = "V";

		switch (direcaoRobo) {
		case FRENTE:
			robo = "V";
			break;
		case TRAZ:
			robo = "^";
			break;
		case DIREITA:
			robo = ">";
		case ESQUERDA:
			robo = "<";
			break;
		default:
			robo = "x";
			break;
		}

		int sizeI = matriz.length;
		int sizeJ = matriz[0].length;
		String[][] matrizS = new String[sizeI][sizeJ];

		for (int i = 0; i < sizeI; i++) {
			for (int j = 0; j < sizeJ; j++) {
				if (PosicaoI == i && posicaoJ == j) {
					matrizS[i][j] = robo;
				} else {
					matrizS[i][j] = String.valueOf(matriz[i][j]);
				}
			}

		}

		return imprimeMatrizEmString(matrizS);

		// matriz[PosicaoI][posicaoJ] = -999; // marca posicao do robo
		// String matrizS = imprimeMatrizS(matriz);
		// StringBuffer batata;
		// batata.replace ~~ não funciona

	}
	
	

	// @franciscaedyrXavier
}
