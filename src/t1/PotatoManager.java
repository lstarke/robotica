package t1;

import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.Motor;


public class PotatoManager {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//menuCalibragemDistanca();
		calibragemTeste();

	}	
	
	
	/**
	 * Parametros de calibragem baseados na rotacao do motor
	 * @author fexavier
	 *
	 */
	private static final int VELOCIDADE = 150 ;
	private static int grau_90_MotorRotate_100  = 190;
	private static int distancia_10_MotorRotate_100 = 2400; 
	private static int rotate_Calibragem_100 = 100;	
	
	/*public static enum EnumDirecao {
	       DIREITA,ESQUERDA,FRENTE,TRAZ;
	} 
	*/
	
	
	public static void calibragemTeste() throws InterruptedException {
		
		defineVelocidade(100);
	//	vira(grausToMotorRotate(90), EnumDirecao.DIREITA);
		
		
		
		/*Thread.sleep (3000);
		vira(grauX(90), Direcao.DIREITA);
		Thread.sleep (3000);
		vira(grauX(90), Direcao.DIREITA);
		Thread.sleep (3000);
		andar(distanciaX(20));*/
	}
	
	public static void  menuCalibragemDistanca(){
	Boolean sair = false;
	System.out.println("Distancia_10");
	System.out.println("Atuaal:"+ distancia_10_MotorRotate_100 );
	
		while(sair){
			
			
			int buttonPress =Button.readButtons();
			
			switch (buttonPress) {
			
			case Button.ID_RIGHT:
				
				distancia_10_MotorRotate_100  += 10;
				
			break;
			case Button.ID_LEFT:

				distancia_10_MotorRotate_100  -= 10;
				
			break;
					
			case Button.ID_ENTER:

				sair = true;
				
			break;
				

			default:
				break;
			}
			
			
			
		}
		
		
	}
	
	public static void  menuCalibragemGrau(){
		Boolean sair = false;
		System.out.println("Grau_90");
		System.out.println("Atual:"+ grau_90_MotorRotate_100 );
		
			while(sair){
				
				
				int buttonPress =Button.readButtons();
				
				switch (buttonPress) {
				
				case Button.ID_RIGHT:
					
					grau_90_MotorRotate_100  += 10;
					
				break;
				case Button.ID_LEFT:

					grau_90_MotorRotate_100  -= 10;
					
				break;
						
				case Button.ID_ENTER:

					sair = true;
					
				break;
					

				default:
					break;
				}
				
				
				
			}
			
			
		}	
	
	
	public static void defineVelocidade(int velocidade) {
		Motor.B.setSpeed(velocidade);
		Motor.C.setSpeed(velocidade);
		
	}
	
	/** A partir de um ponto parado o Bot vira contadorX para a direita se true,
	ou contadorx para esquerda se direita  for false
	*/
	public static void vira (int motorRotate, EnumDirecao direcao) {
			
		Motor.B.stop(true);
		Motor.C.stop(true);
		
		boolean isMoving = false;
		while (isMoving){	
		isMoving = Motor.B.isMoving() ||  Motor.C.isMoving();			
		}	
	
		
		
		switch (direcao) {
			case DIREITA:
			Motor.B.rotate(motorRotate);			
			Motor.C.rotate(-motorRotate);
			break;
			
			case ESQUERDA:
				Motor.B.rotate(-motorRotate);
				Motor.C.rotate(motorRotate);
			break;	

		default:
			break;
		}
		
		
	}
	

	/*public static void andar(int motorRotate) {		
			 Motor.B.rotate(motorRotate);
			 Motor.C.rotate(motorRotate);					
		
	}*/
	
	public static void andar(int motorRotate) {	
		boolean isMoving = false;
		/*while (isMoving){	
			isMoving = Motor.B.isMoving() ||  Motor.C.isMoving();			
		}
		*/
		
		for(int i = 0; i< motorRotate; i++){
			Motor.B.forward();
			Motor.C.forward();
		}
		
		//Motor.B.stop(true);
		//Motor.C.stop(true);
		
	
}

	
	/**Tranforma o valor do angulo em valores de rotacoes do motor
	 * 
	 * @param grau quantidade de graus que deseja obter o valor em rotacoes do motor
	 * @return
	 */
	
	
	public static int grausToMotorRotate(int grau){
		int motorRotate = (grau_90_MotorRotate_100 * grau) /90;
				
		return  motorRotate;
	}
	
	/**Tranforma o valor do angulo em valores de rotacoes do motor
	 * 
	 * @param grau quantidade de graus que deseja obter o valor em rotacoes do motor
	 * @return
	 */
	
	public static int dintanceToMotorRotate(int distancia){
	
		int motorRotate = (distancia_10_MotorRotate_100 * distancia ) / 10;
		return motorRotate ;
	}
	
	/**
	 * Move para tal direção em relação a posição atual do robo
	 * @param direcao
	 * @param distancia
	 */
	public static void Move4d(EnumDirecao direcao, int distancia) {
		
		switch (direcao) {
		
		case FRENTE:
			andar(distancia);			
			break;
		case TRAZ:
			vira(180, EnumDirecao.DIREITA);
			andar(distancia);
			break;
			
		case DIREITA:
			vira(90, EnumDirecao.DIREITA);
			andar(distancia);
			
		case ESQUERDA:
			vira(90, EnumDirecao.ESQUERDA );
			andar(distancia);

		default:
			break;
		}
		
	}
	
	public static EnumDirecao direcaoParaIr(int posicaoAtualI, int posicaoAtualJ, int posicaoProximaI, int posicaoProximaJ){

		EnumDirecao direcaoParaIr = null;

		if(posicaoAtualI > posicaoProximaI){

			direcaoParaIr = EnumDirecao.TRAZ;

		}

		if(posicaoAtualI < posicaoProximaI){

			direcaoParaIr = EnumDirecao.FRENTE;

		}

		if(posicaoAtualJ > posicaoProximaJ){

			direcaoParaIr = EnumDirecao.ESQUERDA;

		}

		if(posicaoAtualJ < posicaoProximaJ){

			direcaoParaIr = EnumDirecao.DIREITA;

		}

		

		return direcaoParaIr;

		

	}

	

	public static void andaCaminho(ArrayList<int[]> caminho,EnumDirecao direcaoRobo, int distanciaMapa, int posicaoRoboI, int posicaoRoboJ) throws InterruptedException{

		//direcaoAtual = EnumDirecao.FRENTE;
		////Adicionar posicao inical do robo no caminho

		//int posicaoAtualI = 0; 
		//int posicaoAtualJ = 0;
		int posicaoProximaI = 0;
	    int posicaoProximaJ = 0;
	    int valoDirecional = 0;
	    
	   System.out.println( posicaoRoboI +"," +  posicaoRoboJ + "-" + direcaoRobo);
	   
		for(int[] p : caminho) {

			
			posicaoProximaI = p[0];
			posicaoProximaJ = p[1];		
			
			EnumDirecao direcao= direcaoParaIr(posicaoRoboI, posicaoRoboJ, posicaoProximaI, posicaoProximaJ);
			
		
			valoDirecional  =  direcao.valorDirecional(direcaoRobo.valor);	
			rotacaoDirecionada4d(valoDirecional);
			
			System.out.println( "Rotaciona:" + valoDirecional * 90);			

			andar(dintanceToMotorRotate(distanciaMapa));
			//System.out.println( "Andar:" + distanciaMapa );
			//System.out.println(  p[0] +"," + p[1]+ "-" + direcao);
			//Thread.sleep(1000);
			
			posicaoRoboI = posicaoProximaI;
			posicaoRoboJ = posicaoProximaJ;
			
			direcaoRobo = direcao;


		}

		

	}
	
	/**
	 * Rotaciona o Robo 90 graus * valor direcional
	 * ex:
	 *  0, não rotaciona	 *  
	 *  1 , rotaciona 90 , 90 graus para direita
	 *  2 , rotaciona 180, 180 graus para direita
	 *  -1 , rotaciona -90 , 90 graus para esquerda
	 *  -2 , rotaciona -180, 180 graus para esquerda
	 * @param valorDirecional
	 */
	
	public static void rotacaoDirecionada4d(int valorDirecional) {		
		
		vira(grausToMotorRotate(90 * valorDirecional), EnumDirecao.DIREITA);		
		
	}
	
	
	



}
