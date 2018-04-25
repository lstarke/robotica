package t1;

import java.util.ArrayList;

import lejos.robotics.navigation.Waypoint;

public class BatataLabirintoSimulator {
	
	private static PotatoManager pM = new PotatoManager();
	private static PotatoWaveFront pWF = new PotatoWaveFront();
	private static EnumDirecao direcaoAtual = EnumDirecao.FRENTE;
	private static int posicaoAtualI = 1;
	private static int posicaoAtualJ = 9;
	
	private static int objetivoI, objetivoJ;
	
	private static int[][] matrizNavegacao; 
	private static ArrayList<int[]> caminho;
	//| |0|
	//|3|x|1| = 2 frente, 1 = direita; 3 = esquerda 0 = tráz;
	//| |2|
	public static void main(String[] args) throws InterruptedException {
		matrizNavegacao  = PotatoWaveFront.waveFront2(PotatoWaveFront.matrizEntrada());		
		System.out.println(PotatoWaveFront.imprimeMatrizEmString(matrizNavegacao));
		System.out.println(PotatoWaveFront.imprimeRoboEmString(matrizNavegacao, posicaoAtualI, posicaoAtualJ, direcaoAtual));
		
		int [] posicao = PotatoWaveFront.procuraObjetivo(matrizNavegacao);
		objetivoI = posicao[0];
		objetivoJ = posicao[1];
		
		caminho =  pWF.menorCaminho(matrizNavegacao, posicaoAtualI, posicaoAtualJ);
		
		for(int[] p : caminho) {
			//System.out.print(p[0]+ "," + p[1]+"; ");
			Thread.sleep(500);
			direcaoAtual = PotatoWaveFront.vira(matrizNavegacao, p[0], p[1], direcaoAtual);
			Thread.sleep(500);
			System.out.println(PotatoWaveFront.imprimeRoboEmString(matrizNavegacao, p[0], p[1], direcaoAtual));
		}
		
		
		
		
	}
	
 
	
	
	

}
