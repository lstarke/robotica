package t3;

import java.util.ArrayList;

import org.jfree.date.AnnualDateRule;

import lejos.robotics.kinematics.RobotArm;
import t3.Nodo.EnumStatus;

/** * 
 * @author Francisca
 *
 */

public class PotatoExplorer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}		
	
	
private static PotatoRobo robo = PotatoRobo.getInstance();
private static Mapa mapa = Mapa.getInstance();
private static ArrayList<Nodo> nodoListExplorados = new ArrayList<Nodo>();
	
	
	
public void potatoExprorer(PotatoRobo robo, Mapa mapa) {
	this.robo = robo;
	this.mapa = mapa;
		
	}	
	
	
	public void explorerMapa(Nodo nodoAtual) {
	
		addAdjacentes(nodoAtual);
		//caminho.add(nodo Atual)
		
		if(nodoAtual.getStatus() == EnumStatus.N_EXPLORADO) {
		
		if(nodoAtual.getNodoFrente() != null && nodoAtual.getNodoFrente().getStatus()  == EnumStatus.EXPLORADO) {
			robo.manager.Move4dDistancia(EnumDirecao.FRENTE, robo.getDirecaoRobo(), mapa.tamanhoQuadros);
			explorerMapa(nodoAtual.getNodoFrente());
			
		}
		if(nodoAtual.getNodoEsquerda() != null && nodoAtual.getNodoEsquerda().getStatus()  == EnumStatus.EXPLORADO) {
			robo.manager.Move4dDistancia(EnumDirecao.ESQUERDA, robo.getDirecaoRobo(), mapa.tamanhoQuadros);
			explorerMapa(nodoAtual.getNodoEsquerda());
		}
		if(nodoAtual.getNodoDireita() != null && nodoAtual.getNodoDireita().getStatus()  == EnumStatus.EXPLORADO) {
			robo.manager.Move4dDistancia(EnumDirecao.DIREITA, robo.getDirecaoRobo(), mapa.tamanhoQuadros);
			explorerMapa(nodoAtual.getNodoDireita());
			
		}
		if(nodoAtual.getNodoTraz() != null && nodoAtual.getNodoTraz().getStatus()  == EnumStatus.EXPLORADO) {
			robo.manager.Move4dDistancia(EnumDirecao.TRAZ, robo.getDirecaoRobo(), mapa.tamanhoQuadros);
			explorerMapa(nodoAtual.getNodoTraz());
		}
		
			
			addNodoEplorado(nodoAtual);			
			nodoAtual.setStatus(EnumStatus.EXPLORADO);
		}
		
		//caminho inverso
		//volta o caminho percorrido
		
	}

	/**
	 * Adiona Ajacentes de cada direcao encontrada
	 * @param nodoAtual
	 */


	private void addAdjacentes(Nodo nodoAtual) {
		int i;
		int j;
		
		if(isCaminho(EnumDirecao.FRENTE, nodoAtual)){
			i = nodoAtual.getI();
			j =nodoAtual.getJ() + 1;		
			
			nodoAtual.setNodoFrente(Mapa.getNodo(i, j));
			nodoAtual.getNodoFrente().setNodoTraz(nodoAtual);	
		}	
		
		if(isCaminho(EnumDirecao.TRAZ, nodoAtual)) {
			
			i = nodoAtual.getI();
			j =nodoAtual.getJ() - 1;		
			
			nodoAtual.setNodoFrente(Mapa.getNodo(i, j));
			nodoAtual.getNodoFrente().setNodoFrente(nodoAtual);	
			
		}
		
		if(isCaminho(EnumDirecao.DIREITA, nodoAtual)) {
			
			i = nodoAtual.getI()+1;
			j =nodoAtual.getJ();		
			
			nodoAtual.setNodoFrente(Mapa.getNodo(i, j));
			nodoAtual.getNodoFrente().setNodoEsquerda(nodoAtual);	
			
			
		}
		if(isCaminho(EnumDirecao.ESQUERDA, nodoAtual)) {
			i = nodoAtual.getI()+1;
			j =nodoAtual.getJ();		
			
			nodoAtual.setNodoFrente(Mapa.getNodo(i, j));
			nodoAtual.getNodoFrente().setNodoDireita(nodoAtual);
			
			
		}
	
				
	}
	
			/**
			 * É caminho se não encontrou parede
			 * @param direcao
			 * @return
			 */
	private boolean isCaminho(EnumDirecao direcao, Nodo nodoAtual) {
		boolean iscaminho = false;
		// verifica se lugar esta dentro do mapa
		if(isDirecaoDentroMapSize(nodoAtual.getI(), nodoAtual.getJ(), direcao)) {
			//busca parade;
			robo.moveCabeca(direcao);
			iscaminho = ! PotatoManager.encontrouParede();	
		}
			
		
		return iscaminho;
		
	}
	
	/**
	 * Verifica se a direção está dentro do limite do mapa
	 */	
	private boolean isDirecaoDentroMapSize(int i, int j, EnumDirecao direcao) {
				
		//|        | traz |
		//|esquerfa|   x  |direita|
		//| 	   |frente|
		
		//FRENTE
		if(direcao == EnumDirecao.FRENTE) {
			return isDentroMapSize(i+1, j);
		}else if (direcao == EnumDirecao.DIREITA) {
			return isDentroMapSize(i, j+1);
		}else if (direcao == EnumDirecao.ESQUERDA) {
			return isDentroMapSize(i, j-1);
		}else if (direcao == EnumDirecao.TRAZ) {
			return isDentroMapSize(i-1, j);
		}else {
			return false;
		}
	}
	
	/**Verifica se o valor não está fora do limite do mapa
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	
	private boolean isDentroMapSize(int i, int j) {
		boolean retorno = true;
		
		//verifica i
		if(i >= mapa.sizeI) {
			retorno = false;
			
		}else if(i < 0) {
		   retorno = false;	
		}
		//verifica J
		
		if(j >= mapa.sizeJ) {
			retorno = false;
			
		}else if(j < 0) {
		   retorno = false;	
		}
		
		return retorno;
	}	
	
	public static void addNodoEplorado(Nodo nodo){
		if(nodoListExplorados != null){
		nodoListExplorados.add(nodo);	
		}else{
			nodoListExplorados = new ArrayList<Nodo>();
		}
	
	}
	
	

	public static void setNodoListExplorados(ArrayList<Nodo> nodoListExplorados) {
		nodoListExplorados = nodoListExplorados;
	}
	
	public boolean isTodoExplorado(){
		if(nodoListExplorados.size() == (mapa.sizeI * mapa.sizeJ)){
			return true;
		}else{
			return false;
		}
		
	}
				
	

}
