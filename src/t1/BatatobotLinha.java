package t1;

import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;


public class BatatobotLinha {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		/*System.out.println("Na velocidade 100");
		System.out.println("Grau100:(90)"+grau_vel_100(90));
		System.out.println("Grau100:(180)"+grau_vel_100(180));
		System.out.println("Distancia100:(100)"+Distancia_vel_100(10));
		System.out.println("Distancia100:(200)"+Distancia_vel_100(20));
	
		System.out.println("Na velocidade x");
		System.out.println("Graux:(90)"+grauX(90));
		System.out.println("Graux:(180)"+grauX(180));
		System.out.println("DistanciaX:(100)"+distanciaX(10));
		System.out.println("DistanciaX:(200)"+distanciaX(20));
		
		*/
		
	calibragem();
	}
	
	
	
	ColorSensor color =  new ColorSensor(SensorPort.S2);
	
	private static int grau_90_velocidade_100 = 1900; // me 9
	private static int distancia_10_velocidade_100= 2300; //10 cm
	private static int vel_calibragem_100 = 100;
	private static boolean direita = true;
	private static int tamanhoRobo = 30;
	private static int tamanhoObstaculo = 10;
	private static int direitaContador= 0;
	private static int esquerdaContador = 0;
	private static int velocidade_Atual = 400;
	private static int buraco_Tamanho =100;
	
	public static void calibragem(){
		System.out.println("Graux:"+grauX(90));
		
		Motor.B.setSpeed(velocidade_Atual);
		Motor.C.setSpeed(velocidade_Atual);
		//vira(grauX(90), false);
		andar(distanciaX(20));
	
		while(true){
		if(Motor.B.isMoving()){
		para();
		System.out.println("Parado");
		}
		}
		
		/*
		Thread.sleep (10000);
		vira(grauX(90), true);
		Thread.sleep (10000);
		vira(grauX(90), true);
		Thread.sleep (10000);
		vira(grauX(90), true);
		Thread.sleep (10000);
		andar(distanciaX(20));
		*/
	}
	
	
	public void pulaburaco() {
		int contador = 0;
		if(direita) {//direita Ž o lado que ele precisa ir
			 contador = esquerdaContador - direitaContador;
		}else {
			contador = direitaContador - esquerdaContador;
		}
		vira(contador, direita);//direita Ž o lado que ele tem de virar
		andar(buraco_Tamanho + tamanhoRobo);
		
	}
	
	
	public void contornaObstaculo () {
		
		vira(grauX(90), true); //true vira a direita
		andar( distanciaX(tamanhoObstaculo +tamanhoRobo));
		vira(grauX(90), false);//false vira a esquerda
		andar(distanciaX(tamanhoObstaculo +tamanhoRobo));
		vira(grauX(90), false);//mudar o angulo
		andar(distanciaX(tamanhoObstaculo + tamanhoRobo));
		vira(grauX(90), true); //true vira a direita
		
		//volta rotina normal
		
	}
	
	
	public static void vira (int contador, boolean direita) {
		
		
		for(int i = 0; i <= contador; i++ ) {
			
			if(direita) {
			//vir apara direita
				Motor.B.forward(); //MOTO.esquerda.forward();
				Motor.C.backward();//MOTOR.direita.backward()
			}else {
			
		  //vir apara esquerda
				Motor.C.forward(); //MOTOR.direita.backward()
				Motor.B.backward();//MOTO.esquerda.forward();
			}
			
		}		
		
	}
	
	public static int grauX(int grau) {
		//divide pela a velocidade de teste 100
		int graux =  (vel_calibragem_100*grau_vel_100(grau))/velocidade_Atual ;
				
		return graux;
	}
	
	public static int distanciaX(int distancia) {
		//divide pela a velocidade de teste 100
		int distanciax =  (vel_calibragem_100* Distancia_vel_100(distancia))/velocidade_Atual ;
		
		return distanciax;
		

	} 
	
	public static void andar(int contador) {
		for(int i = 0; i <= contador; i++ ) {
			Motor.B.forward();
			Motor.C.forward();
		}
		
	}
	
	private static int grau_vel_100 (int grau) {
		
		//grau_90 = 100; // vari‡vel para alterar				
		int contador = 0;		
		contador = (grau_90_velocidade_100 * grau)/90;	
		
		return contador;
		
	}
	
	private static int Distancia_vel_100(int distancia) {
	    //distancia_10 = 100;
		int contador = 0;
		contador =  (distancia_10_velocidade_100 * distancia)/10;
		
		return contador;
		
	}
	public static void para(){
	
		Motor.B.stop();
		Motor.C.stop();
	}
	

	
/*	rotate(int angle)
 * oRotaciona a quantidade de graus informados
 rotateTo(int angle)
 * oRotaciona atŽ o ‰ngulo informado
 */
	



}
