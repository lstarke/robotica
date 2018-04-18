package t1;

public class potatoManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}//
	
	
	/**Parametros usados na calibragem do robo
	**/
	private static int grau_90_velocidade_100 = 1900; 
	private static int distancia_10_velocidade_100= 2300; //10 cm
	private static int vel_calibragem_100 = 100;
	private static int valor_distancia = 25;
	private static final int VELOCIDADE = 320;
	
	public static enum Direcao {
	       DIREITA,ESQUERDA,FRENTE,TRAZ;
	} 
	
	
	
	public static void calibragem() throws InterruptedException {
		Thread.sleep (3000);
		vira(grauX(90), Direcao.DIREITA);
		Thread.sleep (3000);
		vira(grauX(90), Direcao.DIREITA);
		Thread.sleep (3000);
		vira(grauX(90), Direcao.DIREITA);
		Thread.sleep (3000);
		andar(distanciaX(20));
	}
	
	public void defineVelocidade(int velocidade) {
		//Motor.B.setSpeed(VELOCIDADE);
		//Motor.C.setSpeed(VELOCIDADE);
		
	}
	
	/** A partir de um ponto parado o Bot vira contadorX para a direita se true,
	ou contadorx para esquerda se direita  for false
	*/
	public static void vira (int contador, Direcao direcao) {
			
		switch (direcao) {
			case DIREITA:
			rotate(contador); //Motor.B.rotate(contador);
			rotate(-contador);//Motor.C.rotate(-contador);
			break;
			
			case ESQUERDA:
				rotate(-contador);//Motor.B.rotate(-contador);
				rotate(contador);//Motor.C.rotate(contador);
			break;	

		default:
			break;
		}
		
		
	}
	

	public static void andar(int contador) {		
			rotate(contador); //Motor.B.rotate(contador);
			rotate(contador); //Motor.C.rotate(contador);					
		
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
	Retorna o contador para  a distncia X (em cm) em qualquer velocidade,
	distancia_10_velocidade_100 deve ter sido previmente calibrada na velocidade base 100
	
	**/
	public static int distanciaX(int distancia) {
		//divide pela a velocidade de teste 100
		int distanciax =  (vel_calibragem_100* Distancia_vel_100(distancia))/VELOCIDADE ;
		
		return distanciax;
		

	} 	
	
	
	/**Retorna o valor do "contador" baseado no grau informado por parametro
	usando a velocidade "100".
	o valor "grau_90_velocidade_100"
	deve ser previamente calibrada para o Bot, setando o quanto  necessrio para virar 90 na velocidade 100
	**/
	private static int grau_vel_100 (int grau) {
		
		//grau_90 = 100; // varivel para alterar				
		int contador = 0;		
		contador = (grau_90_velocidade_100 * grau)/90;	
		
		return contador;
		
	}
	
	/**Retorna o Valor do "Contador" baseado da distancia informado por parmetro
	"O valor deve distancia_10_velocidade_100" deve ser previamente calibrado para o Bot, setando o quanto  necessrio para
	o  Bot andar 10 cm na velocidade base 100
	**/
	private static int Distancia_vel_100(int distancia) {
	    //distancia_10 = 100;
		int contador = 0;
		contador =  (distancia_10_velocidade_100 * distancia)/10;
		
		return contador;
		
	}
	
	
public static void rotate (int i) {
		
	}
	
	
	public static  void forward(){
		
	}
	
	public static void backward() {
		
	}	

}
