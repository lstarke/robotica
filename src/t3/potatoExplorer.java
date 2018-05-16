package t3;

import lejos.robotics.kinematics.RobotArm;

public class potatoExplorer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}		
	
	private static PotatoRobo robo;
	private static Mapa mapa;
	
	
	
public void potatoExprorer(PotatoRobo robo, Mapa mapa) {
	this.robo = robo;
	this.mapa = mapa;
		
	}
	
	
	
	public void exprorer() {
		
		Nodo nodoAtual = new Nodo();
		
		nodosAdjacentes(nodoAtual);
		//olha para cima
		//verifica o que tem
			//adiciona caminho do
			//se encontrar caminho
		
	}


	private void nodosAdjacentes(Nodo nodoAtual) {
		
		if(isCaminho(EnumDirecao.FRENTE)){
			
			
		}else if(isCaminho(EnumDirecao.TRAZ)) {
			
		}else if(isCaminho(EnumDirecao.DIREITA)) {
			
		}else if(isCaminho(EnumDirecao.ESQUERDA)) {
			
		}
	
				
	}
	
			/**
			 * è caminho se não encontrou parede
			 * @param direcao
			 * @return
			 */
	private boolean isCaminho(EnumDirecao direcao) {
		robo.moveCabeca(direcao);
		
		return !PotatoManager.encontrouParede();

	
		
	}

}
