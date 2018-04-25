package t1;

import lejos.nxt.Button;
import lejos.nxt.Motor;


public class potatoManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	/**
	 * Parametros de calibragem baseados na rotacao do motor
	 * @author fexavier
	 *
	 */
	private static final int VELOCIDADE = 320;
	private static int grau_90_MotorRotate_100  = 200;
	private static int distancia_10_MotorRotate_100 = 200; 
	private static int rotate_Calibragem_100 = 100;	
	
	public static enum Direcao {
	       DIREITA,ESQUERDA,FRENTE,TRAZ;
	} 
	
	
	
	public static void calibragemTeste() throws InterruptedException {
		/*Thread.sleep (3000);
		vira(grauX(90), Direcao.DIREITA);
		Thread.sleep (3000);
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
	
	
	
	public void defineVelocidade(int velocidade) {
		Motor.B.setSpeed(VELOCIDADE);
		Motor.C.setSpeed(VELOCIDADE);
		
	}
	
	/** A partir de um ponto parado o Bot vira contadorX para a direita se true,
	ou contadorx para esquerda se direita  for false
	*/
	public static void vira (int motorRotate, Direcao direcao) {
			
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
	

	public static void andar(int motorRotate) {		
			 Motor.B.rotate(motorRotate);
			 Motor.C.rotate(motorRotate);					
		
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
	
	public static int dintanceToMotorRotate(int distance){
	
		int motorRotate = (distancia_10_MotorRotate_100 * distance ) / 10;
		return motorRotate ;
	}



}
