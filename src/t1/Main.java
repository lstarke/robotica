package t1;

import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
//@franciscaedyrXavier
public class Main {
	
	private static final int DENTRO     = 7;
	private static final int FORA       = 6;		
	private static final int VELOCIDADE = 320;
	
	private static boolean   direita    = false;
	private static boolean   esquerda   = true;	
	private static int       valorLuz   = 0;
	private static int       distanciaObstaculo = 0;
	public static ColorSensor sensorCor = new ColorSensor(SensorPort.S2);
	public static UltrasonicSensor sensorUltrasonico = new UltrasonicSensor(SensorPort.S3);	
	
	public static void main(String[] args) throws InterruptedException {
		
		//ColorSensor sensorCor = new ColorSensor(SensorPort.S2);
		//UltrasonicSensor sensorUltrasonico = new UltrasonicSensor(SensorPort.S3);		

		// seta a velovidade que dever� ser utilizada nos motores
		Motor.B.setSpeed(VELOCIDADE);
		Motor.C.setSpeed(VELOCIDADE);		

		int cont = 0;
		int mod  = 0;
		int cont2 =0;
		
		
		while (true) {
			
			//if(!esta_no_buraco){
			
			valorLuz = sensorCor.getColorID();
			
			//System.out.println("Saiu");
			
			//System.out.println("batatas");
			if (valorLuz == DENTRO ) {
				andar(distancia_Intervalo);
				System.out.println("DentroLinhas");
				//Motor.B.forward();
				//Motor.C.forward();
				mod =0;
				cont2 =0;				
				esquerdaContador = 0;
				direitaContador = 0;
				
				verificaObstaculo();
				
				
			}			
			
			if (valorLuz == FORA) {				
				if(cont2 > 5){
					//Motor.B.forward();
					//Motor.C.forward();
					mod =0;
					//cont2--;
					cont2=0;
					esta_no_buraco = true;
					pulaburaco();					
					System.out.println(cont2);
				}else{
				
					//verificaObstaculo();
				//cont2 =0;
					if (esquerda) {
						//System.out.println("esquerda");
							
						Motor.B.backward();					
						Motor.C.forward();
						cont++;
						esquerdaContador++;
						if (cont >= 7 +mod) {
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
							if (cont >= 7 +mod) {							
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
	
		
	/**Parametros usados na calibragem do robo
	**/
	private static int grau_90_velocidade_100 = 1900; 
	private static int distancia_10_velocidade_100= 2300; //10 cm
	private static int vel_calibragem_100 = 100;
	private static int valor_distancia = 25;

	
	
	
	/**Informa��es sobre o mundo
	**/
	private static int tamanhoRobo = 0;
	
	private static int tamanhoObstaculo = 10;
	private static int direitaContador= 0;
	private static int esquerdaContador = 0;
	
	private static int buraco_Tamanho =10;
	private static int modificador_curva =22;//25
	private static boolean esta_no_buraco = false;
	
	private static int distancia_Intervalo = distanciaX(5);
	private static boolean avistou_objeto = false;
	
	
	/**Rotina para calibragem do Bot
	**/ 

	public static void calibragem(){
		System.out.println("Graux:"+grauX(90));
		
		Motor.B.setSpeed(VELOCIDADE);
		Motor.C.setSpeed(VELOCIDADE);
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
	
	private static void verificaObstaculo(){
		
		distanciaObstaculo = sensorUltrasonico.getDistance();			
		
		if (distanciaObstaculo < valor_distancia) {
			contornaObstaculo();
			avistou_objeto = true;
			System.out.println("Entrou Obstaculo");
		}
	}
	
	/**
	Se encontrou um buraco o robo ira rotacionar a soba de seu deslocamento para os lado e andar� a dist�ncia definida em "buraco_tamanho"
	**/
	public static void pulaburaco() {
		int contador = 0;
		//para();
		 //System.out.println(esquerdaContador);
		 //velocidade(100);
		
		if(direita) {//direita � o lado que ele precisa ir
			 contador = direitaContador +  esquerdaContador + (modificador_curva*5);// esquerdaContador+30 ;//- direitaContador;
			System.out.println("direita"+ contador);
		}else {
			contador = esquerdaContador+ direitaContador+(modificador_curva * 5);//direitaContador ;//- esquerdaContador;
			 System.out.println("esquerda"+contador);
		}
		vira(contador, direita);//direita � o lado que ele tem de virar
		
		/*while(true){
		
			para();
		
			}*/
			
		andar(distanciaX(buraco_Tamanho));
		
		//para();
		//while(Motor.B.isMoving()){
			//esta_no_buraco = true;
		//}
		//esta_no_buraco = true;
		
	
		
	}
	
	/*** Para teste
	**/
	public static void contornaObstaculo () {
		
		vira(grauX(65), false);// vira para esquerda
		andar( distanciaX(20));
		vira(grauX(55), true);
		andar( distanciaX(20));
		vira(grauX(55), true);
		//andar( distanciaX(10));		
		
		
		boolean achoulinha = false;
		while(!achoulinha){
			andarFrente();
			
			if(sensorCor.getColorID() == DENTRO){
				achoulinha = true;
			}
			
		}
		vira(grauX(4-5), false);//vira para esquerda
		

		/*while(true){
		if(Motor.B.isMoving()){
		para();
		System.out.println("Parado");
		//esta_no_buraco = false;
		}
		}*/
		
		//para();
		/*
		vira(grauX(90), true); //true vira a direita
		andar( distanciaX(tamanhoObstaculo +tamanhoRobo));
		vira(grauX(90), false);//false vira a esquerda
		andar(distanciaX(tamanhoObstaculo +tamanhoRobo));
		vira(grauX(90), false);//mudar o angulo
		andar(distanciaX(tamanhoObstaculo + tamanhoRobo));
		vira(grauX(90), true); //true vira a direita
		
		*/
		//volta rotina normal
		
	}
	
	
	
	/** A partir de um ponto parado o Bot vira contadorX para a direita se true,
	ou contadorx para esquerda se direita  for false
	*/
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
	/**
	Retorna o contador para  girar o grau em x em qualquer velocidade,
	grau_90_velocidade_100 deve ter sido previmente calibrada na velocidade base 100
	**/
	
	public static int grauX(int grau) {
		//divide pela a velocidade de teste 100
		int graux =  (vel_calibragem_100*grau_vel_100(grau))/VELOCIDADE ;
				
		return graux;
	}
	

	/**
	Retorna o contador para  a dist�ncia X (em cm) em qualquer velocidade,
	distancia_10_velocidade_100 deve ter sido previmente calibrada na velocidade base 100
	
	**/
	public static int distanciaX(int distancia) {
		//divide pela a velocidade de teste 100
		int distanciax =  (vel_calibragem_100* Distancia_vel_100(distancia))/VELOCIDADE ;
		
		return distanciax;
		

	} 
	
	
	/**
	Anda um intervalo contador vezes em linha reta
	**/
	public static void andar(int contador) {
		for(int i = 0; i <= contador; i++ ) {			
			Motor.B.forward();
			Motor.C.forward();
		}
		
	}
	
	
	/**Retorna o valor do "contador" baseado no grau informado por parametro
	usando a velocidade "100".
	o valor "grau_90_velocidade_100"
	deve ser previamente calibrada para o Bot, setando o quanto � necess�rio para virar 90� na velocidade 100
	**/
	private static int grau_vel_100 (int grau) {
		
		//grau_90 = 100; // vari�vel para alterar				
		int contador = 0;		
		contador = (grau_90_velocidade_100 * grau)/90;	
		
		return contador;
		
	}
	
	/**Retorna o Valor do "Contador" baseado da distancia informado por par�metro
	"O valor deve distancia_10_velocidade_100" deve ser previamente calibrado para o Bot, setando o quanto � necess�rio para
	o  Bot andar 10 cm na velocidade base 100
	**/
	private static int Distancia_vel_100(int distancia) {
	    //distancia_10 = 100;
		int contador = 0;
		contador =  (distancia_10_velocidade_100 * distancia)/10;
		
		return contador;
		
	}
	
	/**
	Para os dois motores (B e C) ao mesmo tempo
	
	**/
	public static void para(){
	
		Motor.B.stop();
		Motor.C.stop();
	}
	
	public static void andarFrente(){
		Motor.B.forward();
		Motor.C.forward();
	}
	
	/** Defini uma velocidade padr�o para os doi motores (B e c) ao mesmo tempo)
	**/
	public static void velocidade(int velocidade){
		
		Motor.B.setSpeed(velocidade);
		Motor.C.setSpeed(velocidade);
	}
	

	//@franciscaedyrXavier
}
