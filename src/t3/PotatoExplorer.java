package t3;

import java.util.ArrayList;

import javax.microedition.location.Orientation;

import org.apache.bcel.generic.CALOAD;
import org.apache.bcel.verifier.VerifierAppFrame;
import org.apache.commons.cli.PosixParser;
import org.jfree.date.AnnualDateRule;
import org.jfree.date.RelativeDayOfWeekRule;

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
private static boolean olharParaTraz =  false;

private static ArrayList<Nodo> produtoList = new ArrayList<Nodo>();
private static ArrayList<EnumProduto> produtoToColetar = new ArrayList<EnumProduto>();
private static ArrayList<ArrayList<Nodo>> caminhoProdutoList = new ArrayList<ArrayList<Nodo>>();
private static Nodo nodoInicio = null;

public enum EnumStatusExploracao{
 INDEFINIDO,
 EXPLORA,
 VOLTA,
 COLETA;
}

public static EnumStatusExploracao statusExploracao = EnumStatusExploracao.INDEFINIDO  ;

@SuppressWarnings("static-access")
public PotatoExplorer( int  posicaoRoboI, int posicaoRoboJ, EnumDirecao direcaoRobo, boolean olharParaTraz){
	robo.setDirecaoRobo(direcaoRobo);
	//considera que a cabeça esta sempre a mesma direcao do robo
	//A direção da cabeça é global e não em relação ao robo
	robo.setDirecaoCabeca(direcaoRobo);
	robo.nodoAtual = Mapa.getNodo(posicaoRoboI, posicaoRoboJ);
	nodoInicio = robo.nodoAtual;
	mapa.setMatrizSimulacao(mapa.createDefaultMapa());
	
		

}



/* só existe um mapa e um robo
public PotatoExplorer(PotatoRobo robo, Mapa mapa) {
	this.robo = robo;
	this.mapa = mapa;
		
	}	*/

	@SuppressWarnings("static-access")
	public void explorerMapa(Nodo nodoOrigem, ArrayList<EnumProduto> produtoToColetar) throws InterruptedException {
		statusExploracao = statusExploracao.EXPLORA;
		System.out.println("---EXPLORER-MAP---");
		
		setProdutoToColetar(produtoToColetar);
		explorerMapa(robo.nodoAtual, new ArrayList<Nodo>(),0);
	}

	/**
	 * explora todos os lados do nodo atual
	 * @param nodoAtual
	 * @param caminho
	 * @param volta
	 * @throws InterruptedException
	 */
	 
	@SuppressWarnings("static-access")
	private void explorerMapa(Nodo nodoAtual, ArrayList<Nodo> caminho,int volta) throws InterruptedException {
	System.out.println(nodoAtual.getNome());
		if(mapa.getMatrizSimulacao()[nodoAtual.getI()*2][nodoAtual.getJ()*2] == 0) {
			mapa.getMatrizSimulacao()[nodoAtual.getI()*2][nodoAtual.getJ()*2] = 1;
		}
	
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
			//System.out.println("yEEP");
			if(nodoFrente!= null && nodoFrente.getStatus()  == EnumStatus.N_EXPLORADO && !nodoFrente.isNodoPercorrido() ) {
				robo.Move4dDistancia(EnumDirecao.FRENTE, robo.getDirecaoRobo(), mapa.tamanhoQuadros);
				//System.out.println("MOVEFRENTE");
				explorerMapa(nodoFrente, caminho, 0);
				
				
			}
			
			if(nodoDireita!= null && nodoDireita.getStatus()  == EnumStatus.N_EXPLORADO && !nodoDireita.isNodoPercorrido()) {
				robo.Move4dDistancia(EnumDirecao.DIREITA, robo.getDirecaoRobo(), mapa.tamanhoQuadros);
				//System.out.println("MOVEDIREITA");
				explorerMapa(nodoDireita,caminho, 0);
				
				
			}
			
			if(nodoEsquerda != null && nodoEsquerda.getStatus()  == EnumStatus.N_EXPLORADO && !nodoEsquerda.isNodoPercorrido()) {
				robo.Move4dDistancia(EnumDirecao.ESQUERDA, robo.getDirecaoRobo(), mapa.tamanhoQuadros);
				//System.out.println("MOVEeSQUERDA");
				explorerMapa(nodoEsquerda,caminho, 0);
				
			}
			
			if(nodoTraz != null && nodoTraz.getStatus()  == EnumStatus.N_EXPLORADO && !nodoTraz.isNodoPercorrido()) {
				robo.Move4dDistancia(EnumDirecao.TRAZ, robo.getDirecaoRobo(), mapa.tamanhoQuadros);
				explorerMapa(nodoTraz,caminho , 0);
				
			}			
			addNodoEplorado(nodoAtual);			
			nodoAtual.setStatus(EnumStatus.EXPLORADO);
			
		}else {
			
		}
		
		if(Mapa.isTodoExplorado()){
			//System.out.println("TODOS EXPLORADOS");			
				
			
		}else{
			//volta++;
			//explorerVoltaCaminho(nodoAtual, caminho, volta);
			explorerVoltaCaminho(nodoAtual, caminho);
			
			//robo.manager.andaCaminho(caminho, robo.getDirecaoRobo(), mapa.tamanhoQuadros, true);
		}
		//String mapaS = "\n"+ mapa.imprimeRoboEmString(robo.desenhaRobo(),robo.nodoAtual.getI(),robo.nodoAtual.getJ(),true);
		
		//System.out.println(mapaS);
		
		//caminho inverso
		//volta o caminho percorrido
		
	}
	
	/**
	 * Retorna o Caminho percorrido pelo explorer
	 * @param nodoAtual nodo em que o explorerMapa se encontra
	 * @param caminho / caminho percorido no momento
	 * @param volta contador de volta, referente a quantas casa teve de retornar
	 * @throws InterruptedException
	 */
	@SuppressWarnings("static-access")
	private void explorerVoltaCaminho(Nodo nodoAtual, ArrayList<Nodo> caminho, int volta) throws InterruptedException {
		robo.nodoAtual = nodoAtual;
		int idx = caminho.size()- (volta +1);		
		Nodo nodoAnterior = caminho.get(idx);
		EnumDirecao direcao = direcaoNodoAdjacente(nodoAtual, nodoAnterior);	
		volta++;
		//caminho.add(volta);
		//caminho.remove(caminho.size()-1);
		

		robo.Move4dDistancia(direcao, robo.getDirecaoRobo(), mapa.tamanhoQuadros);	
		explorerMapa(nodoAnterior, caminho, volta);
	}
	
	
	/**Encontra o nodo que tem caminho não explorado e precorre o menor caminho até ele
	 * @param nodoAtual
	 * @param caminho
	 * @throws InterruptedException
	 */
	@SuppressWarnings("static-access")
	private void explorerVoltaCaminho(Nodo nodoAtual, ArrayList<Nodo> caminho) throws InterruptedException {
		
		Nodo nodoObjetivo = nodoAtual;
		for(int i = caminho.size()-1; i>=  0; i--) {
			
			if(caminho.get(i).getNodoFrente() !=null && !caminho.get(i).getNodoFrente().isNodoPercorrido()) {				
				nodoObjetivo = caminho.get(i).getNodoFrente(); 
				break;
				
			}else if (caminho.get(i).getNodoDireita()!= null && !caminho.get(i).getNodoDireita().isNodoPercorrido()) {
				nodoObjetivo = caminho.get(i).getNodoDireita();
				break;
				
			}else if (caminho.get(i).getNodoEsquerda()!= null && !caminho.get(i).getNodoEsquerda().isNodoPercorrido()) {
				nodoObjetivo = caminho.get(i).getNodoEsquerda();
				break;
				
			}else if (caminho.get(i).getNodoTraz() != null && !caminho.get(i).getNodoTraz().isNodoPercorrido()) {
				nodoObjetivo = caminho.get(i).getNodoTraz();
				break;
				
			}
		}		
		
		ArrayList<Nodo> menorCaminho = menorCaminho(nodoAtual, nodoObjetivo);
		robo.andaCaminho(menorCaminho, robo.getDirecaoRobo(), mapa.tamanhoQuadros, false);
		explorerMapa(nodoObjetivo, caminho,0);
		
	}
	
	@SuppressWarnings("static-access")
	/***
	 * Anda Caminho de um ponto Rogigem até um ponto objetivo
	 * @param nodoOrigem
	 * @param nodoObjetivo
	 * @throws InterruptedException
	 */
	 
	public static void andaCaminho(Nodo nodoOrigem, Nodo nodoObjetivo ) throws InterruptedException {		
		//ajusta a cabeça para a posicao atual do robo
		
		robo.andaCaminho(menorCaminho(nodoOrigem, nodoObjetivo),robo.getDirecaoRobo(), mapa.tamanhoQuadros, false);
		
		
	}
	
	@SuppressWarnings("static-access")
	public static void voltaInicio() {
		statusExploracao = statusExploracao.VOLTA;
		System.out.println("----VOLTA-INICIO--");
		try {
			andaCaminho(robo.nodoAtual, nodoInicio);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public EnumDirecao direcaoNodoAdjacente(Nodo pai ,Nodo adjacente) {
		
		if(pai.getNodoFrente() != null && pai.getNodoFrente().equals(adjacente)) {
			return EnumDirecao.FRENTE;
		}else if (pai.getNodoEsquerda() != null && pai.getNodoEsquerda().equals(adjacente)) {
			return EnumDirecao.ESQUERDA;
		}else if (pai.getNodoDireita() != null && pai.getNodoDireita().equals(adjacente)) {
			return EnumDirecao.DIREITA;
		}else if(pai.getNodoTraz() != null && pai.getNodoTraz().equals(adjacente)) {
			return EnumDirecao.TRAZ;			
		}
				
		
		return null;
		
	}
	
	public static String imprimeProdutoPosicoes() {
		String listaProdutos = "Produtos:";
		for(Nodo n : produtoList) {
		 listaProdutos += n.getNome()+";"; 
		}		
		
		return listaProdutos;
	}
	
	@SuppressWarnings("static-access")
	public static void coletaProduto(Nodo nodoOrigem) {
		statusExploracao = statusExploracao.COLETA;
		System.out.println("--COLETA-PRODUTOS");
		//robo.nodoAtual = Mapa.getNodo(6, 0);		
		ArrayList<ArrayList<Nodo>>  caminhosList = getCaminhoProdutoList(nodoOrigem);
		
		for(ArrayList<Nodo> caminho : caminhosList) {
			try {
				robo.manager.andaCaminho(caminho, robo.getDirecaoRobo(), mapa.tamanhoQuadros, false);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}	
	

/**
 * Nodo faz parte do caminho do percorrido atualmentes? 
 * @param nodo
 * @param caminho
 * @return
 */
	@SuppressWarnings("unused")
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
		
		
		if(nodoAtual.getI()  == 5 && nodoAtual.getJ() == 4) {
		adjacentes = "";		
			//
		}
		
	
		if(isCaminho(EnumDirecao.DIREITA, nodoAtual) ) {
			
			Nodo n = Mapa.getNodo(i, j+1);
			
			nodoAtual.setNodoDireita(n);
			nodoAtual.getNodoDireita().setNodoEsquerda(nodoAtual);
			adjacentes+= ";Direita"+ n.getNome();			
			
		}
		
		EnumDirecao direcaoCabeca = robo.getDirecaoCabeca();
		EnumDirecao direcaoRobo = robo.getDirecaoRobo();
		
		if(isCaminho(EnumDirecao.ESQUERDA, nodoAtual)) {
			
			Nodo n = Mapa.getNodo(i, j - 1);
			
			nodoAtual.setNodoEsquerda(n);
			nodoAtual.getNodoEsquerda().setNodoDireita(nodoAtual);
			adjacentes+= ";Esquerda"+ n.getNome();
			
		}
		
		direcaoCabeca = robo.getDirecaoCabeca();
		direcaoRobo = robo.getDirecaoRobo();
		
		if(isCaminho(EnumDirecao.TRAZ, nodoAtual)) {
			
			Nodo n = Mapa.getNodo(i -1, j);
			
			nodoAtual.setNodoTraz(n);
			nodoAtual.getNodoTraz().setNodoFrente(nodoAtual);	
			adjacentes+= ";traz"+ n.getNome();
			
		}
		
		direcaoCabeca = robo.getDirecaoCabeca();
		direcaoRobo = robo.getDirecaoRobo();
		
		if(isCaminho(EnumDirecao.FRENTE, nodoAtual)){
			
			Nodo n = Mapa.getNodo(i+1, j);
					
			nodoAtual.setNodoFrente(n);
			nodoAtual.getNodoFrente().setNodoTraz(nodoAtual);	
			adjacentes+= ";Frente-" + n.getNome();
		}	
		
		direcaoCabeca = robo.getDirecaoCabeca();
		direcaoRobo = robo.getDirecaoRobo();
		
	//	System.out.println(nodoAtual.getNome() + adjacentes);
	
				
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
			//System.out.println("Cab"+ direcao);
			
			if(!olharParaTraz) {
				//int valorDicional = 0;
				//valorDicional = direcao.valorDirecional(robo.getDirecaoRobo().valor);
				//valorDicional =  Math.abs(valorDicional);
			//	int valorDirecional = robo.getDirecaoCabeca().valorDirecional(direcao.valor);
				//int valorDirecional = robo.getDirecaoRobo().valorDirecional(direcao.valor); //verifica tra em relação ao robo
				int valorRotacao = robo.getDirecaoRobo().valor - direcao.valor;
				
				
				int valor = Math.abs(valorRotacao);
				
				//System.out.println(direcao);
				//System.out.println("dir" + valor) ;
				//System.out.println(" " ) ;
				if(valor != 2){		
						
					//EnumDirecao direcaoParair = robo.giraDirecao(valorDirecional,robo.getDirecaoCabeca());
					//robo.moveCabeca(direcaoParair);
					robo.moveCabeca(direcao);					
					//System.out.println("Executa " ) ;
					//System.out.println("" ) ;
					iscaminho = ! robo.manager.encontrouParede();
								
					
					//System.out.println( direcaoParair);
					
					//System.out.println("2"+robo.getDirecaoCabeca());
					//System.out.println("Cab"+ direcaoParair);
					//robo.moveCabeca(direcao);
				}else {
					iscaminho = false;
				}
				//}
			}else {
			//busca parade;
				int valorDirecional = robo.getDirecaoCabeca().valorDirecional(robo.getDirecaoCabeca().valor);
				EnumDirecao direcaoParair = robo.giraDirecao(valorDirecional,robo.getDirecaoCabeca());
				robo.moveCabeca(direcaoParair);
				//robo.moveCabeca(direcao);
				iscaminho = ! robo.manager.encontrouParede();
			}
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
	
	@SuppressWarnings("static-access")
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
	
	/**
	 * Adiciona nodo apenas uma vez a lista de explorados
	 * @param nodo
	 */
	
	public static void addNodoEplorado(Nodo nodo){
		if(nodoListExplorados != null) {
			if(!nodoListExplorados.contains(nodo)){
				nodoListExplorados.add(nodo);	
			}
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
	
	
	@SuppressWarnings("static-access")
	public static void procuraProduto(Nodo nodo) throws InterruptedException{
		
		if(isProduto(robo.observaCor(), nodo)){
	
			//coletar produto
			if(produtoToColetar.contains(nodo.getProduto())) {
			addProdutList(nodo);
			}
		}
		
	}

	public static boolean isProduto(int[] cor, Nodo nodo) throws InterruptedException{
		boolean isProduto = false;
		int r = cor[0];
		int g = cor[1];
		int b = cor[2];
		
		if(r > 100){
		
			if(g >  100){
				if(b > 100){					
					nodo.setProduto(EnumProduto.PRODUTO_0_PADRAO);					
					isProduto = true;
					
					System.out.println("PRODUTO");
					System.out.println("R" + r );
					System.out.println("G" + g );
					System.out.println("B" + b );
					Thread.sleep(500);
				}
			}
		}
		
		
		return isProduto;
		
		
	}
	
	private static ArrayList<Nodo> menorCaminho(Nodo nodoOrigem,Nodo nodoObjetivo) {
		ArrayList<Nodo> caminho = new ArrayList<Nodo>();
		Dijkstra d = new Dijkstra(nodoListExplorados);
		d.dijkstra(nodoOrigem, nodoObjetivo, false);
		caminho = d.getFila();
		return caminho;
		
		
	}
	
	
	/**
	 * Calcula o Menor caminho para o Objetivo e alimenta a list caminhoProdutoList
	 * @param nodoOrigem
	 */
	
	@SuppressWarnings("unused")
	private static void menorCaminhoAteprodutos(Nodo nodoOrigem){
		Dijkstra d = new Dijkstra(nodoListExplorados);
		int tamanhoTotal = 0;
		for(Nodo objetivo : produtoList){
			
			System.out.println("\nOrigem:"+ nodoOrigem.getNome() + ", Objetivo:" + objetivo.getNome());
			d.dijkstra(nodoOrigem, objetivo, false);
			
			System.out.println("Fila:" + d.imprimeFila());
			System.out.println("Tamanho:"+ d.getFila().size());
			tamanhoTotal += d.getFila().size() -1;
			
			caminhoProdutoList.add(d.getFila());			
			///retorna caminho e addnuma lista;			
			nodoOrigem = objetivo;			
		}
		
		System.out.println("TamanhoTotal:"+ tamanhoTotal);
		
		
	}
	
	 /***
	  * Calcula o Menor caminho para o Objetivo e alimenta a list caminhoProdutoList partindo do produto mais próximo
	  * @param nodoOrigem
	  * @param tamanhoTotal
	  */
	
	private static void menorCaminhoAteprodutos2(Nodo nodoOrigem, int tamanhoTotal){
		Dijkstra d = new Dijkstra(nodoListExplorados);
		ArrayList<Nodo> menorFila =new ArrayList<Nodo>();
		Nodo menorObjetivo = null;
		int menorTamanho = Integer.MAX_VALUE;
		
		
		for(Nodo objetivo : produtoList){
			d.dijkstra(nodoOrigem, objetivo, false);
			if(d.getFila().size() < menorTamanho) {
				menorTamanho = d.getFila().size();
				menorFila = d.getFila();
				menorObjetivo = objetivo;
			}
		}
		
		if(menorFila.size() > 0) {
		
		produtoList.remove(menorObjetivo);
		caminhoProdutoList.add(menorFila);
		
		//System.out.println("\nOrigem:"+ nodoOrigem.getNome() + ", Objetivo:" + menorObjetivo.getNome());
		//System.out.println("Fila:" + d.imprimeFila());
		//System.out.println("Tamanho:"+ menorFila.size());	
		}else {
			System.out.println("---Não existe Caminho--");
		}
		
		if(produtoList.size() > 0) {
			menorCaminhoAteprodutos2(menorObjetivo, tamanhoTotal);
		}
		
		System.out.println("TamanhoTotal:"+ tamanhoTotal);
		
	
	}	
	
	
	
	public static ArrayList<ArrayList<Nodo>> getCaminhoProdutoList(Nodo nodoOrigem) {
		int size = caminhoProdutoList.size() ;
		if(size <= 0) {
			//menorCaminhoAteprodutos(nodoOrigem);
			//System.err.println("ESCOLHER UM DOS CAMINHOS!");
			menorCaminhoAteprodutos2(nodoOrigem, 0);
		}
		
		return caminhoProdutoList;
	}



	public static void addProdutList(Nodo nodo){		
		produtoList.add(nodo);		
	}
				
	public void setNodoListExplorados(ArrayList<Nodo> nodoListExplorados) {
		nodoListExplorados = nodoListExplorados;
	}



	public static ArrayList<EnumProduto> getProdutoToColetar() {
		return produtoToColetar;
	}



	public static void setProdutoToColetar(ArrayList<EnumProduto> produtoToColetar) {
		PotatoExplorer.produtoToColetar = produtoToColetar;
	}

}
