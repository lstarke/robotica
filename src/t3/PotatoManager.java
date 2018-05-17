package t3;

import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

/** * 
 * @author Francisca
 *
 */

public class PotatoManager {
	
	
	public static void main(String[] args) {
		
		//defineVelocidade(100);
		//motorCabeca.setSpeed(70);
		//motorCabeca.lock(70);
		/*String cor ="";

		cor += ",R"+ sensorCor.getColor().getRed();
		cor += ",G"+ sensorCor.getColor().getGreen();
		cor += ",B"+ sensorCor.getColor().getBlue()+"\n";
		
		
		int redmin = 999;
		int gremin =999;
		int blumin = 999;
		
		int redmax = 999;
		int gremax =999;
		int blumax = 999;
		
		
		while(!Button.ENTER.isDown()){
		
		System.out.println(cor);
		
		}
		
		while(!Button.ENTER.isDown()){
			
		}*/
		
		for (int i = 1 ; i<7; i++){
		andar(PotatoManager.dintanceToMotorRotate(26));
		motorCabeca.setSpeed(100);
		PotatoManager.viraCabeca(PotatoManager.grausToCabecaMotorRotate(90),EnumDirecao.DIREITA);
		System.out.println("   "+sensorUltrasonico.getDistance());
		//PotatoManager.viraCabeca(PotatoManager.grausToCabecaMotorRotate(90),EnumDirecao.DIREITA);
		//PotatoManager.viraCabeca(PotatoManager.grausToCabecaMotorRotate(90),EnumDirecao.ESQUERDA);
		PotatoManager.viraCabeca(PotatoManager.grausToCabecaMotorRotate(90),EnumDirecao.ESQUERDA);
		System.out.println("   "+sensorUltrasonico.getDistance());
		PotatoManager.viraCabeca(PotatoManager.grausToCabecaMotorRotate(90),EnumDirecao.ESQUERDA);
		System.out.println("   "+sensorUltrasonico.getDistance());
		PotatoManager.viraCabeca(PotatoManager.grausToCabecaMotorRotate(90),EnumDirecao.DIREITA);
		System.out.println("   "+sensorUltrasonico.getDistance());
		}
		//para();
		//motorCabeca.rotate(10);
		//Motor.A.rotate(3000);
		
		
		//PotatoManager.viraCabeca(PotatoManager.grausToCabecaMotorRotate(90),EnumDirecao.DIREITA);
	}
	
	/**
	 * Parametros de calibragem baseados na rotacao do motor
	 * 
	 *
	 */
	
	
	//private static final int VELOCIDADE = 150 ;
	private static int grau_90_MotorRotate_100  = 190;
	private static int grau_90_CabecaMotorRotate =100 ;
	///private static int distancia_10_MotorRotate_100 = 2400; 
	private static int distancia_10_MotorRotate_100 = 265; 
	private static int rotate_Calibragem_100 = 100;	
	private static NXTRegulatedMotor motorRodaDireita = Motor.B;
	private static NXTRegulatedMotor motorRodaEsquerda = Motor.C;
	private static NXTRegulatedMotor motorCabeca = Motor.A;
	private static int distancia_paredeUltraSonico;
	
	public static ColorSensor sensorCor = new ColorSensor(SensorPort.S4);
	public static UltrasonicSensor sensorUltrasonico = new UltrasonicSensor(SensorPort.S3);	
	
	public static void calibragemTeste() throws InterruptedException {
		
		defineVelocidade(100);
		
		

	}
	
	public PotatoManager(){
		motorCabeca.setSpeed(70);
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
		motorRodaDireita.setSpeed(velocidade);
		motorRodaEsquerda.setSpeed(velocidade);
		
	}
	
	/** Motor do robo rotaciona o angulo do motor "motorRotade" para direita ou para esqueça. 
	*/
	public static void vira (int motorRotate, EnumDirecao direcao) {
			
		motorRodaDireita.stop(true);
		motorRodaEsquerda.stop(true);
		
		boolean isMoving = false;
		while (isMoving){	
		isMoving = motorRodaDireita.isMoving() ||  motorRodaEsquerda.isMoving();			
		}	
	
		
		
		switch (direcao) {
			case DIREITA:
			motorRodaDireita.rotate(motorRotate);			
			motorRodaEsquerda.rotate(-motorRotate);
			break;
			
			case ESQUERDA:
				motorRodaDireita.rotate(-motorRotate);
				motorRodaEsquerda.rotate(motorRotate);
			break;	

		default:
			break;
		}
		
		
	}
	public static void para(){
		//while (
		//}
		motorRodaDireita.stop(true);
		motorRodaEsquerda.stop(true);
	}
	

	public static void andar(int motorRotate) {		
			 motorRodaDireita.rotate(motorRotate,true);
			 motorRodaEsquerda.rotate(motorRotate);					
		
	}
	/*
	public static void andar(int motorRotate) {	
		boolean isMoving = false;
		
		for(int i = 0; i< motorRotate; i++){
			motorRodaDireita.forward();
			motorRodaEsquerda.forward();
		}
		
		//motorRodaDireita.stop(true);
		//motorRodaEsquerda.stop(true);
		
	
	}
*/
	
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
	
	
	public static int grausToCabecaMotorRotate(int grau){
		int motorRotate = (grau_90_CabecaMotorRotate * grau) /90;
				
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
			viraDirecionada4d(valoDirecional);
			
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
	
	public static void viraDirecionada4d(int valorDirecional) {		
		
		vira(grausToMotorRotate(90 * valorDirecional), EnumDirecao.DIREITA);		
		
	}
	
	public static void viraDirecionadaCabeca4d(int valorDirecional) {		
		
		viraCabeca(grausToMotorRotate(90 * valorDirecional), EnumDirecao.DIREITA);	
		
	}
	/**
	 *  rotaciona angulo da cabeca "motorRotade" para direita ou para esqueça. 
	 *  
	 * @param motorRotate quantidade de angulos do
	 * @param direcao
	 */

	public static void viraCabeca(int motorRotate, EnumDirecao direcao) {		
		
		if(direcao == EnumDirecao.DIREITA){
		motorCabeca.rotate(motorRotate);
		
		}else if(direcao == EnumDirecao.ESQUERDA){
			motorCabeca.rotate(-motorRotate);
		}				

	}
	
	public static Boolean encontrouParede() {
		//sensorUltrasonico.capture();
		if(sensorUltrasonico.getDistance()< distancia_paredeUltraSonico) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	



}
