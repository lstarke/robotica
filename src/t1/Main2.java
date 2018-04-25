package t1;

import java.util.Date;

import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

public class Main2 {

	
	public static void main(String[] args) throws InterruptedException {
		
		ColorSensor sensorCor = new ColorSensor(SensorPort.S2);
		
		final int DENTRO = 7;
		final int FORA   = 6;
		
		int valorLuz = 0;

		Motor.B.setSpeed(velocidade_Atual);
		Motor.C.setSpeed(velocidade_Atual);
		String batta;
		

		int cont = 0;
		int mod  = 0;
		int cont2 =0;

		
		while (true) {
			
			//if(!esta_no_buraco){
			
			valorLuz = sensorCor.getColorID();
			//System.out.println("batatas");
			if (valorLuz == DENTRO ) {
				andar(distancia_Intervalo);
				//Motor.B.forward();
				//Motor.C.forward();
				mod =0;
				cont2 =0;
				
				esquerdaContador = 0;
				direitaContador = 0;
			}			
			
			if (valorLuz == FORA) {				
				if(cont2 > 4){
					//Motor.B.forward();
					//Motor.C.forward();
					mod =0;
					//cont2--;
					cont2=0;
					esta_no_buraco = true;
					pulaburaco();					
					System.out.println(cont2);
				}else{
				
				//cont2 =0;
					if (esquerda) {
						//System.out.println("esquerda");
							
						Motor.B.backward();					
						Motor.C.forward();
						cont++;
						esquerdaContador++;
						if (cont >= 10 +mod) {
							esquerda = false;
							direita  = true;
							cont = 0;
							mod+=modificador_curva;
							cont2++;
						
						}
					} else {
						if (direita) {
					
							Motor.C.backward();
							Motor.B.forward();
							cont++;
							direitaContador++;
							if (cont >= 10 +mod) {							
								esquerda = true;
								direita  = false;
								mod+=modificador_curva;
								cont = 0;
								cont2++;
								
							}
						}
					}
				}
			//}
				valorLuz = sensorCor.getColorID();
				//System.out.println("valorLuz"+ valorLuz);
			}
		}
		
		
	}
	private static int grau_90_velocidade_100 = 1900; 
	private static int distancia_10_velocidade_100= 2300; //10 cm
	private static int vel_calibragem_100 = 100;
	private static boolean direita = false;
	private static boolean esquerda = true;
	private static int tamanhoRobo = 0;
	
	private static int tamanhoObstaculo = 10;
	private static int direitaContador= 0;
	private static int esquerdaContador = 0;
	private static int velocidade_Atual = 300;
	private static int buraco_Tamanho =10;
	private static int modificador_curva =20;
	private static boolean esta_no_buraco = false;
	
	private static int distancia_Intervalo = distanciaX(4);
	
	
	

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
	
	
	public static void pulaburaco() {
		int contador = 0;
		//para();
		 //System.out.println(esquerdaContador);
		 //velocidade(100);
		
		if(direita) {//direita Ž o lado que ele precisa ir
			 contador = direitaContador + esquerdaContador + (modificador_curva*5);// esquerdaContador+30 ;//- direitaContador;
			System.out.println("direita"+ contador);
		}else {
			contador = esquerdaContador+direitaContador+(modificador_curva * 5);//direitaContador ;//- esquerdaContador;
			 System.out.println("esquerda"+contador);
		}
		vira(contador, direita);//direita Ž o lado que ele tem de virar
		
		/*/while(true){
			if(Motor.B.isMoving()){
			para();
			System.out.println("Parado");
			//esta_no_buraco = false;
			}
			}
			*/
		andar(distanciaX(buraco_Tamanho));
		
		//para();
		//while(Motor.B.isMoving()){
			//esta_no_buraco = true;
		//}
		//esta_no_buraco = true;
		
	
		
	}
	
	
	public static void contornaObstaculo () {
		
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
	
	public static void velocidade(int velocidade){
		
		Motor.B.setSpeed(velocidade);
		Motor.C.setSpeed(velocidade);
	}
	


}
