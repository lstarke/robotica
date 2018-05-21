package t3;

import java.util.ArrayList;

import lejos.robotics.kinematics.RobotArm;

public class Teste {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {


		PotatoExplorer pe = new PotatoExplorer(6,0, EnumDirecao.DIREITA, false);		
		
		String mapaS = "";
		
		//mapaS = pe.mapa.imprimeMatrizEmString(pe.mapa.getMatrizSimulacao());
		
		mapaS += "\n"+ pe.mapa.imprimeRoboEmString(pe.robo.desenhaRobo(),pe.robo.nodoAtual.getI(),pe.robo.nodoAtual.getJ(),true);
		
		System.out.println(mapaS);
		
		pe.getProdutoToColetar().add(EnumProduto.PRODUTO_1_PRETO);
		pe.explorerMapa(pe.robo.nodoAtual, new ArrayList<Nodo>());
		
		Thread.sleep(400);
		
		pe.coletaProduto(Mapa.getNodo(6, 0));
		
		

	}

}
