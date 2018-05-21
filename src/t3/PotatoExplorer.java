package t3;

import java.util.ArrayList;

import javax.microedition.location.Orientation;

import org.apache.commons.cli.PosixParser;
import org.jfree.date.AnnualDateRule;

import lejos.robotics.kinematics.RobotArm;
import t3.Mapa.EnumMapa;
import t3.Nodo.EnumStatus;

/** * 
 * @author Francisca
 *
 */

public class PotatoExplorer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}		
	
	
public static PotatoRobo robo = PotatoRobo.getInstance();
public static Mapa mapa = Mapa.getInstance();
public static ArrayList<Nodo> nodoListExplorados = new ArrayList<Nodo>();
private int cont = 0;

public static ArrayList<Nodo> produtoList = new ArrayList<Nodo>();

@SuppressWarnings("static-access")
public PotatoExplorer( int  posicaoRoboI, int posicaoRoboJ){
	
	robo.nodoAtual = Mapa.getNodo(posicaoRoboI, posicaoRoboJ);
	mapa.setMatrizSimulacao(mapa.createDefaultMapa());
	
		

}



/* só existe um mapa e um robo
public PotatoExplorer(PotatoRobo robo, Mapa mapa) {
	this.robo = robo;
	this.mapa = mapa;
		
	}	*/


	public void menorCaminhoAteprodutos(Nodo nodoOrigem){
		Dijkstra d = new Dijkstra();
		for(Nodo objetivo : produtoList){
			d.dijkstra(nodoOrigem, objetivo);
			///retorna caminho e addnuma lista;
			
			nodoOrigem = objetivo;
			
		}
	}

	
	
	@SuppressWarnings("static-access")
	public void explorerMapa(Nodo nodoAtual, ArrayList<Nodo> caminho) throws InterruptedException {
	//System.out.println(cont++);
		
		mapa.getMatrizSimulacao()[nodoAtual.getI()*2][nodoAtual.getJ()*2] = 1;
		nodoAtual.setNodoPercorrido(true);
		
		robo.nodoAtual = nodoAtual;
		
	    
		if(nodoAtual.getStatus() == EnumStatus.N_EXPLORADO)
		{
			
			procuraProduto(nodoAtual);
			
			addAdjacentes(nodoAtual);
			//Nodo nodoAnterior = null;	
				
			Nodo nodoFrente = nodoAtual.getNodoFrente();
		    Nodo nodoEsquerda = nodoAtual.getNodoEsquerda();
		    Nodo nodoDireita = nodoAtual.getNodoDireita();
		    Nodo nodoTraz = nodoAtual.getNodoTraz();
			caminho.add(nodoAtual);
			
			//System.out.println(robo.nodoAtual.getNome());
	
			if(nodoFrente!= null && nodoFrente.getStatus()  == EnumStatus.N_EXPLORADO && !nodoFrente.isNodoPercorrido() ) {
				robo.Move4dDistancia(EnumDirecao.FRENTE, robo.getDirecaoRobo(), mapa.tamanhoQuadros);
				explorerMapa(nodoFrente, caminho);
				
			}
			if(nodoEsquerda != null && nodoEsquerda.getStatus()  == EnumStatus.N_EXPLORADO && !nodoEsquerda.isNodoPercorrido()) {
				robo.Move4dDistancia(EnumDirecao.ESQUERDA, robo.getDirecaoRobo(), mapa.tamanhoQuadros);
				explorerMapa(nodoEsquerda,caminho);
			}
			if(nodoDireita!= null && nodoDireita.getStatus()  == EnumStatus.N_EXPLORADO && !nodoDireita.isNodoPercorrido()) {
				robo.Move4dDistancia(EnumDirecao.DIREITA, robo.getDirecaoRobo(), mapa.tamanhoQuadros);
				explorerMapa(nodoDireita,caminho);
				
			}
			if(nodoTraz != null && nodoTraz.getStatus()  == EnumStatus.N_EXPLORADO && !nodoTraz.isNodoPercorrido()) {
				robo.Move4dDistancia(EnumDirecao.TRAZ, robo.getDirecaoRobo(), mapa.tamanhoQuadros);
				explorerMapa(nodoTraz,caminho);
			}			
			addNodoEplorado(nodoAtual);			
			nodoAtual.setStatus(EnumStatus.EXPLORADO);
			
		}else {
			
		}
		
		if(Mapa.isTodoExplorado()){
			System.out.println("TODOS EXPLORADOS");
				
		}else{
			robo.manager.andaCaminho(caminho, robo.getDirecaoRobo(), mapa.tamanhoQuadros, true);
		}
		//String mapaS = "\n"+ mapa.imprimeRoboEmString(robo.desenhaRobo(),robo.nodoAtual.getI(),robo.nodoAtual.getJ(),true);
		
		//System.out.println(mapaS);
		
		//caminho inverso
		//volta o caminho percorrido
		
	}
	
	private boolean isNodoNoCaminho(Nodo nodo, ArrayList<Nodo> caminho){

		boolean  isNodoInCaminho = false;
		
		for(int i = 0 ; i<caminho.size(); i++){
		
			if(caminho.get(i).equals(nodo)){
			isNodoInCaminho = true;
			break;
			}
		}
		
		return isNodoInCaminho;		
	  
		
	}

	/**
	 * Adiona Ajacentes de cada direcao encontrada em relação ao mapa global
	 * @param nodoAtual
	 */


	private void addAdjacentes(Nodo nodoAtual) {
		int i = nodoAtual.getI();
		int j = nodoAtual.getJ();
		String adjacentes = "";
		
		if(isCaminho(EnumDirecao.FRENTE, nodoAtual)){
			
			Nodo n = Mapa.getNodo(i+1, j);
					
			nodoAtual.setNodoFrente(n);
			nodoAtual.getNodoFrente().setNodoTraz(nodoAtual);	
			adjacentes+= ";Frente-" + n.getNome();
		}	
		
		if(isCaminho(EnumDirecao.DIREITA, nodoAtual)) {
			
			Nodo n = Mapa.getNodo(i, j+1);
			
			nodoAtual.setNodoDireita(n);
			nodoAtual.getNodoDireita().setNodoEsquerda(nodoAtual);
			adjacentes+= ";Direita"+ n.getNome();;
			
			
		}
		
		if(isCaminho(EnumDirecao.ESQUERDA, nodoAtual)) {
			
			Nodo n = Mapa.getNodo(i, j - 1);
			
			nodoAtual.setNodoEsquerda(n);
			nodoAtual.getNodoEsquerda().setNodoDireita(nodoAtual);
			adjacentes+= ";Esquerda"+ n.getNome();;
			
		}
		
		if(isCaminho(EnumDirecao.TRAZ, nodoAtual)) {
			
			Nodo n = Mapa.getNodo(i -1, j);
			
			nodoAtual.setNodoTraz(n);
			nodoAtual.getNodoTraz().setNodoFrente(nodoAtual);	
			adjacentes+= ";traz"+ n.getNome();;
			
		}
		
		
		
		//System.out.println(nodoAtual.getNome() + adjacentes);
	
				
	}
	
			/**
			 * É caminho se não encontrou parede
			 * @param direcao
			 * @return
			 */
	@SuppressWarnings("static-access")
	private boolean isCaminho(EnumDirecao direcao, Nodo nodoAtual) {
		boolean iscaminho = false;
		// verifica se lugar esta dentro do mapa
		if(isDirecaoDentroMapSize(nodoAtual.getI(), nodoAtual.getJ(), direcao)) {
			//busca parade;
			robo.moveCabeca(direcao);
			iscaminho = ! robo.manager.encontrouParede();	
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
	
	public boolean isTodoExplorado(){
		if(nodoListExplorados.size() == (mapa.sizeI * mapa.sizeJ)){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	public static void procuraProduto(Nodo nodo){
		
		if(isProduto(robo.observaCor(), nodo)){
	
		}
		
	}

	public static boolean isProduto(int[] cor, Nodo nodo){
		boolean isProduto = false;
		int r = cor[0];
		int g = cor[1];
		int b = cor[2];
		
		if(r > 225){
		
			if(g >  225){
				if(b > 225){					
					nodo.setProduto(EnumProduto.PRODUTO_1_PRETO);
					addProdutList(nodo);
					isProduto = true;
				}
			}
		}
		return isProduto;
		
	}
	
	
	
	public static void addProdutList(Nodo nodo){		
		produtoList.add(nodo);		
	}
				
	public static void setNodoListExplorados(ArrayList<Nodo> nodoListExplorados) {
		nodoListExplorados = nodoListExplorados;
	}

}
