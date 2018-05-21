package t3;

import java.util.ArrayList;

import lejos.robotics.kinematics.RobotArm;

public class Teste {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {


		PotatoExplorer pe = new PotatoExplorer(6,0);		
		
		String mapaS = "";
		
		//mapaS = pe.mapa.imprimeMatrizEmString(pe.mapa.getMatrizSimulacao());
		
		mapaS += "\n"+ pe.mapa.imprimeRoboEmString(pe.robo.desenhaRobo(),pe.robo.nodoAtual.getI(),pe.robo.nodoAtual.getJ(),true);
		
		System.out.println(mapaS);
		
		pe.getProdutoToColetar().add(EnumProduto.PRODUTO_1_PRETO);
		pe.explorerMapa(pe.robo.nodoAtual, new ArrayList<Nodo>());
		
		
		///pe.menorCaminhoAteprodutos(Mapa.getNodo(6, 0));
		//System.out.println("\n/////////MENOR CAMINHO ////////////");
		//pe.menorCaminhoAteprodutos2(Mapa.getNodo(6, 0),0);
		
		//System.out.println(pe.imprimeProdutoPosiçoes());
		
		/*Nodo nodoAtual = mapa.getNodo(0, 2);
		
				
		nodoAtual.setNodoFrente(Mapa.getNodo(0, 1));		
		nodoAtual.getNodoFrente().setNodoTraz(nodoAtual);
		
		nodoAtual.setNodoDireita(Mapa.getNodo(0, 1));
		
		System.out.println(nodoAtual.getNome() );
		System.out.println(nodoAtual.getNodoFrente().getNome());
		System.out.println(nodoAtual.getNodoFrente().getNodoTraz().getNome());
		
		/*
		MapearCaminho
		MapearProduto

		AndarCaminho
		EncontraProduto
		
		*/


	}

}
