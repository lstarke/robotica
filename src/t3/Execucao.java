package t3;

import java.util.ArrayList;

public class Execucao {
	
	private static int posicaoRoboI =1;
	private static int posicaoRoboJ =0;

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
	
		
		PotatoExplorer pe = new PotatoExplorer(posicaoRoboI,posicaoRoboJ, EnumDirecao.DIREITA, false);		
		ArrayList<EnumProduto> produtoToColetar = new ArrayList<EnumProduto>();
		produtoToColetar.add(EnumProduto.PRODUTO_1_PRETO);
		
		try {
			pe.explorerMapa(pe.robo.nodoAtual,produtoToColetar);		
		
		Thread.sleep(400);
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pe.voltaInicio();
		pe.coletaProduto(Mapa.getNodo(posicaoRoboI, posicaoRoboJ));
		
		
	}

}
