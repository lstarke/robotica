package t3;

import java.util.ArrayList;

import lejos.nxt.Button;

public class ExecucaoNXT {
	
	private static int posicaoRoboI =6;
	private static int posicaoRoboJ =6;

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
	
		
		PotatoExplorer pe = new PotatoExplorer(posicaoRoboI,posicaoRoboJ, EnumDirecao.DIREITA, false);
		/*pe.robo.setDirecaoRobo(EnumDirecao.TRAZ);
		pe.robo.setDirecaoCabeca(EnumDirecao.TRAZ);
		pe.addAdjacentes(pe.mapa.getNodo(posicaoRoboI, posicaoRoboJ));
		*/
		
		//PotatoRobo.moveCabeca(EnumDirecao.TRAZ);;
		
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
