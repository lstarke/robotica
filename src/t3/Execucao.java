package t3;

import java.util.ArrayList;

import lejos.nxt.Button;

public class Execucao {
	
	private static int posicaoRoboI =0;
	private static int posicaoRoboJ =3;

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
	
		Button.ENTER.callListeners();
		
		PotatoExplorer pe = new PotatoExplorer(posicaoRoboI,posicaoRoboJ, EnumDirecao.TRAZ, false);		
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
