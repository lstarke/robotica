package t3;

import java.util.ArrayList;

import org.apache.commons.cli.PosixParser;
import org.jfree.chart.plot.dial.DialTextAnnotation;

import t3.Mapa.EnumMapa;
import t3.PotatoExplorer.EnumStatusExploracao;
import lejos.nxt.ColorSensor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.mapping.SliderPanel;

/** * 
 * @author Francisca
 *
 */

public class PotatoManegerTeste {
	
	
	public static void main(String[] args) {
		
	
	}
	
	/**
	 * Parametros de calibragem baseados na rotacao do motor
	 * 
	 *
	 */	
	
	private static final int VELOCIDADE = 150 ;
	private static int grau_90_MotorRotate_100  = 190;
	private static int grau_90_CabecaMotorRotate =100 ;
	///private static int distancia_10_MotorRotate_100 = 2400; 
	private static int distancia_10_MotorRotate_100 = 265; 
	private static int rotate_Calibragem_100 = 100;	
	//private static NXTRegulatedMotor motorRodaDireita;
	//private static NXTRegulatedMotor motorRodaEsquerda;
	//private static NXTRegulatedMotor motorCabeca;
	//private static int distancia_paredeUltraSonico;
	
	//public static ColorSensor sensorCor;
	//public static UltrasonicSensor sensorUltrasonico;	
	public static boolean modoTeste = false;
	public static PotatoRobo robo = PotatoRobo.getInstance();
	public static boolean log = false;
	public static boolean map = false;		
	
	private static boolean  sleepMode =  false;
	private static float 	slowMod = 0.1f;
	
	public static void calibragemTeste() throws InterruptedException {
		
		

	}
	
	public static void dorme(int i) {
		if(sleepMode)
		try {
			Thread.sleep((long) (i * slowMod));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	
	}
	
	public PotatoManegerTeste(){
		System.out.println("Iniciaa ManeGer SIMULACAO");
	}
	
	
	public static void defineVelocidade(int velocidade) {
		//motorRodaDireita.setSpeed(velocidade);
		//motorRodaEsquerda.setSpeed(velocidade);
		
	}

	public static void para(){
		//while (
		//}
		//motorRodaDireita.stop(true);
		//motorRodaEsquerda.stop(true);
	}
	
	/**Retorna um inr de taamnho 3 com as cores  observadas no sensor
	 * R = [0]
	 * g = [1]
	 * G = [2]
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static int[] observaCor(){
		
		int[] cor = new int[3];
		cor[0] = 0;//R;	
		cor[1] = 0;//G;
		cor[2] = 0;//B;	
		
		
	
		int posicaoI = robo.nodoAtual.getI();
		int posicaoJ = robo.nodoAtual.getJ();
		
		 if(Mapa.getMatrizSimulacao()[posicaoI*2][posicaoJ*2] == EnumMapa.PRODUTO.id){
			 cor[0] = 255;//R;	
			 cor[1] = 255;//G;
			 cor[2] = 255;//B;	
		 }		
		
	
		return cor;		
	}

	public static Boolean encontrouParede() {
		//sensorUltrasonico.capture();
		int posI = PotatoRobo.nodoAtual.getI()*2;
		int posJ = PotatoRobo.nodoAtual.getJ()*2;
		boolean retorno = false;
		
		switch (PotatoRobo.getDirecaoCabeca()) {
		case DIREITA:
			
		if(Mapa.getMatrizSimulacao()[posI][posJ+1] == Mapa.EnumMapa.OBSTACULO.id){
			retorno = true;
		}			
			break;
		case ESQUERDA:
			if(Mapa.getMatrizSimulacao()[posI][posJ -1] == Mapa.EnumMapa.OBSTACULO.id){
				retorno = true;
			}
			break;
		case FRENTE:
			if(Mapa.getMatrizSimulacao()[posI+1][posJ] == Mapa.EnumMapa.OBSTACULO.id){
				retorno = true;
			}
			break;
		case TRAZ:
			
			if(Mapa.getMatrizSimulacao()[posI-1][posJ] == Mapa.EnumMapa.OBSTACULO.id){
				retorno = true;
			}
			break;
			
	
		default:
			break;
		}
		
		return retorno;
		
		/*
		if(sensorUltrasonico.getDistance()< distancia_paredeUltraSonico) {
			return true;
		}else {
			return false;
		}
		*/
		
		
	}

	/**
	*  Motor do robo rotaciona o angulo do motor "motorRotade" para direita ou para esquerda.	
	* @param motorRotate rotacoes do motor
	* @param direcao
	*/
	public static void viraRotate (int motorRotate, EnumDirecao direcao) {
		dorme(400);
	
		int mod = 0;
		mod  = motorRotate/ grausToMotorRotate(90) ;
		
		if(mod < 0){
			mod = -mod;
			if(direcao == EnumDirecao.DIREITA){
				direcao = EnumDirecao.ESQUERDA;
			}else{
				direcao = EnumDirecao.DIREITA;
			}
		}
					
			
			for (int i = 0; i < mod; i++) {
				
				dorme(100);		
				
	
				EnumDirecao direcaoParaIr = direcaoParaIrGlobal(PotatoRobo.getDirecaoRobo(), direcao);	
				
				int valoDirecional  =  direcao.valorDirecional(PotatoRobo.getDirecaoRobo().valor);
				//int valoDirecional  =  direcaoParaIr.valorDirecional(PotatoRobo.getDirecaoRobo().valor);
				//Não esta saindo certo PotatoRobo.setDirecaoCabeca(PotatoRobo.giraDirecao(valoDirecional,  PotatoRobo.getDirecaoCabeca()));
				PotatoRobo.setDirecaoCabeca(direcaoParaIr);
				PotatoRobo.setDirecaoRobo(direcaoParaIr);	
			//	PotatoRobo.setDirecaoRobo(direcao);
				//System.out.println("VIRAMotor: MotorRotate" + motorRotate + ", mod:" + mod+ ",dir:" + direcao +", ir:"+ direcaoParaIr + ", Robo:" + PotatoRobo.getDirecaoRobo());
				
				if(map) {
				System.out.println(Mapa.imprimeRoboEmString());
				} 
				
			}
			
			
			if(mod == 0 && map) {				
				dorme(100);	
				System.out.println(Mapa.imprimeRoboEmString());
			}
			if(log) {
				System.out.println("\n---Vira:");
				System.out.println("Direcao:"+ direcao +"\nGraus:" + (motorRotate/grau_90_MotorRotate_100 *90)+ "\nRoboDirecao:"
			+ PotatoRobo.getDirecaoRobo() + "\nCabecaDirecao:"+ PotatoRobo.getDirecaoCabeca()+ "\nmod:"+ mod );
			}
		
	
		
				
	}

	public static void viraAngulo (int angulo, EnumDirecao direcao) {
		
		viraRotate(grausToMotorRotate(angulo),direcao);
						
	}

	/**
	 * Anda para frente  
	 * @param motorRotate rotacao do motor
	 */
	public static void andarRotate(int motorRotate) {	
	
		dorme(400);
		
		int mod = 0;
		int mod2  = motorRotate/ distancia_10_MotorRotate_100 ;
		if(mod2 != 0){
		mod  = (Mapa.tamanhoQuadros /10) /mod2 ;
		}else{
			mod = 0;
		}
		//System.out.println("Anda : MotorRotae:" + motorRotate + ", mod:" + mod + ",Direcao:" + PotatoRobo.getDirecaoRobo());	
		//EnumDirecao direcaoParaIr = direcaoParaIrGlobal(PotatoRobo.getDirecaoRobo(), EnumDirecao.FRENTE);
		
		for(int i = 0 ; i < mod; i++){
						
			dorme(100);
			
			//Robo já foi virado;
			moveMatriz( PotatoRobo.getDirecaoRobo(), 1);
			
			if(map) {
			System.out.println(Mapa.imprimeRoboEmString());
			}
			
			//robo.nodoAtual
		}
		
		if(log) {
			System.out.println("\n---Anda:");
			System.out.println("Distancia:"+ ((motorRotate/ distancia_10_MotorRotate_100) *10) + "\nRoboDirecao:"
		+ PotatoRobo.getDirecaoRobo() + "\nCabecaDirecao:"+ PotatoRobo.getDirecaoCabeca() );
		}
			// motorRodaDireita.rotate(motorRotate,true);
			 //motorRodaEsquerda.rotate(motorRotate);	
		
		//Mapa.tamanhoQuadros
		
					
	}
	
	
	/**
	 * Anda para frente 
	 * @param distancia cm
	 */
	public static void andarDistancia(int distancia){
		
		andarRotate(dintanceToMotorRotate(distancia));
	}

	/**
		 *  rotaciona angulo da cabeca "motorRotade" para direita ou para esqueça. 
		 *  
		 * @param motorRotate quantidade de angulos do
		 * @param direcao
		 */
	
		public static void viraCabeca(int motorRotate, EnumDirecao direcao) {		
			
			int mod = 0;
			mod  = motorRotate/ grausToCabecaMotorRotate(90) ;
			
	
			if(mod < 0){
				mod = -mod;
				if(direcao == EnumDirecao.DIREITA){
					direcao = EnumDirecao.ESQUERDA;
				}else{
					direcao = EnumDirecao.DIREITA;
				}
			}
				
			for (int i = 0; i < mod; i++) {
				
				dorme(300);
				
				EnumDirecao direcaoCabeca = PotatoRobo.getDirecaoCabeca();
				EnumDirecao direcaoParaIr = direcaoParaIrGlobal(direcaoCabeca, direcao);				
				//System.out.println("VIRACabeca: MotorRotate" + motorRotate + ", mod:" + mod+ ",dir:" + direcao +", ir:"+ direcaoParaIr + ", Robo:" + PotatoRobo.getDirecaoCabeca());
				PotatoRobo.setDirecaoCabeca(direcaoParaIr);	
				//System.out.println("VIRACabeca: MotorRotate" + motorRotate + ", mod:" + mod+ ",dir:" + direcao +", ir:"+ direcaoParaIr + ", Robo:" + PotatoRobo.getDirecaoCabeca());
				
				if(map) {
				System.out.println(Mapa.imprimeRoboEmString());
				}
				
			}
			if(mod == 0 && map) {
				dorme(300);
				System.out.println(Mapa.imprimeRoboEmString());
			}
			
			if(log) {
				System.out.println("---\nViraCabeca:");
				System.out.println("ViraCabeca:"+ direcao +"\nGraus:" +(motorRotate/grau_90_CabecaMotorRotate*90) + "\nRoboDirecao:"
			+ PotatoRobo.getDirecaoRobo() + "\nCabecaDirecao:"+ PotatoRobo.getDirecaoCabeca() );
			}
	
			
					
	
		}

	/**
	 * Rotaciona o Robo 90 graus * valor direcional
	 * ex:/n
	 *  0, não rotaciona	 *  
	 *  1 , rotaciona 90 , 90 graus para direita
	 *  2 , rotaciona 180, 180 graus para direita
	 *  -1 , rotaciona -90 , 90 graus para esquerda
	 *  -2 , rotaciona -180, 180 graus para esquerda.
	 * @param valorDirecional
	 */
	
	public static void viraDirecionada4d(int valorDirecional) {		
		
		viraAngulo(90 * valorDirecional, EnumDirecao.DIREITA);		
		
	}

	public static void viraDirecionadaCabeca4d(int valorDirecional) {		
		
		viraCabeca(grausToCabecaMotorRotate(90 * valorDirecional), EnumDirecao.DIREITA);	
		
	}

	/**
	 * Movimenta robo para tal direção especificada em relação ao corpo do robo.
	 * @param direcao
	 * @param distancia   Distancia em cm a ser percorrida.
	 */
	public static void Move4dDistancia(EnumDirecao direcao, int distancia) {
		
		switch (direcao) {
		
		case FRENTE:
			andarDistancia(distancia);			
			break;
		case TRAZ:
			viraAngulo(180, EnumDirecao.DIREITA);
			andarDistancia(distancia);
			break;
			
		case DIREITA:
			viraAngulo(90, EnumDirecao.DIREITA);
			andarDistancia(distancia);
			
		case ESQUERDA:
			viraAngulo(90, EnumDirecao.ESQUERDA );
			andarDistancia(distancia);
	
		default:
			break;
		}
		
	}

	/**
	 * Movimenta o Robo em uma das quatros direções baseado na Posicao atual, e Posicao objetiva do robo. 
	 * @param posicaoAtualI
	 * @param posicaoAtualJ
	 * @param posicaoProximaI
	 * @param posicaoProximaJ
	 * @param direcaoRobo Posicao atual do robo.
	 * @param distancia Distancia em cm a ser percorrida.
	 */
	
	public static void Move4dDistancia(int posicaoAtualI, int posicaoAtualJ, int posicaoProximaI, int posicaoProximaJ, EnumDirecao direcaoRobo, int distancia){
		
		EnumDirecao direcaoParaIr= direcaoParaIr(posicaoAtualI, posicaoAtualJ, posicaoProximaI, posicaoProximaJ);
		 Move4dDistancia(direcaoParaIr, direcaoRobo, distancia );
		 }

	/**
	 * Movimenta o Robo em uma das quatros direções baseado na direcao para ir e direção objetiva do robo.
	 * @param direcaoParaIr Direção que o robo deve Ir.
	 * @param direcaoRobo Posicao atual do robo.
	 * @param distancia Distancia em cm a ser percorrida.
	 */
	
	public static void Move4dDistancia(EnumDirecao direcaoParaIr, EnumDirecao direcaoRobo, int distancia){
		
		int valoDirecional  =  direcaoParaIr.valorDirecional(direcaoRobo.valor);	
		viraDirecionada4d(valoDirecional);
		andarDistancia(distancia);
	}

	/**
	 * Percorre um ArrayList de Int
	 * @param caminho
	 * @param direcaoRobo
	 * @param distanciaMapa
	 * @param posicaoRoboI
	 * @param posicaoRoboJ
	 * @throws InterruptedException
	 */
		public static void andaCaminho(ArrayList<int[]> caminho,EnumDirecao direcaoRobo, int distanciaMapa, int posicaoRoboI, int posicaoRoboJ) throws InterruptedException{
	
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
	
				andarDistancia(distanciaMapa);
				//System.out.println( "Andar:" + distanciaMapa );
				//System.out.println(  p[0] +"," + p[1]+ "-" + direcao);
				//Thread.sleep(1000);
				
				posicaoRoboI = posicaoProximaI;
				posicaoRoboJ = posicaoProximaJ;
				
				direcaoRobo = direcao;
	
	
			}
	
			
	
		}

	/**
	 * Percorre um  Array de caminho. 
	 * @param caminho Array com os nodos que compoem o caminho.Considera o objeto de Posição "0" como a posicao atual;
	 * @param direcaoRobo
	 * @param distanciaMapa
	 * @param posicaoRoboI
	 * @param posicaoRoboJ
	 * @throws InterruptedException
	 */
	
	@SuppressWarnings("static-access")
	public static void andaCaminho(ArrayList<Nodo> caminho,EnumDirecao direcaoRobo, int distanciaMapa, Boolean inverso) throws InterruptedException{
	
		
		int posicaoProximaI = 0;
	    int posicaoProximaJ = 0;
	    if(!inverso) {    	    
	    	
	    	int posicaoRoboI = caminho.get(0).getI(); 
			int posicaoRoboJ = caminho.get(0).getJ();
	
		for(int i = 1; i < caminho.size(); i++) {
			
			posicaoProximaI = caminho.get(i).getI();
			posicaoProximaJ = caminho.get(i).getJ();			
		
			//System.out.println(caminho.get(i).getNome());
			EnumDirecao direcaoParaIr= direcaoParaIr(posicaoRoboI, posicaoRoboJ, posicaoProximaI, posicaoProximaJ);
			robo.Move4dDistancia(direcaoParaIr, direcaoRobo, distanciaMapa);
			//Move4dDistancia(direcaoParaIr, direcaoRobo, distanciaMapa);			
			
			posicaoRoboI = posicaoProximaI;
			posicaoRoboJ = posicaoProximaJ;			
			
			direcaoRobo = direcaoParaIr;
			
			if(Mapa.getMatrizSimulacao()[robo.nodoAtual.getI()*2][robo.nodoAtual.getJ()*2] == 3 && PotatoExplorer.statusExploracao == EnumStatusExploracao.COLETA) {
				Mapa.getMatrizSimulacao()[robo.nodoAtual.getI()*2][robo.nodoAtual.getJ()*2] = 99;
			}else {
				Mapa.getMatrizSimulacao()[robo.nodoAtual.getI()*2][robo.nodoAtual.getJ()*2] = 2;
			}
			System.out.println( Mapa.imprimeRoboEmString(robo.desenhaRobo(), robo.nodoAtual.getI(), robo.nodoAtual.getJ(), true));
			
		}
	    }else {
	    	
	    	int posicaoRoboI = caminho.get(caminho.size()-1).getI(); 
			int posicaoRoboJ = caminho.get(caminho.size()-1).getJ();
		
			for(int i =  caminho.size()-2; i >= 0; i--)  {
				
				Nodo p = caminho.get(i);
				posicaoProximaI = p.getI();
				posicaoProximaJ = p.getJ();		
				
				System.out.println(caminho.get(i).getNome());			
				EnumDirecao direcaoParaIr= direcaoParaIr(posicaoRoboI, posicaoRoboJ, posicaoProximaI, posicaoProximaJ);
				robo.Move4dDistancia(direcaoParaIr, direcaoRobo, distanciaMapa);
				//Move4dDistancia(direcaoParaIr, direcaoRobo, distanciaMapa);			
				
				posicaoRoboI = posicaoProximaI;
				posicaoRoboJ = posicaoProximaJ;			
				
				direcaoRobo = direcaoParaIr;
				
				//System.out.println( Mapa.imprimeRoboEmString(robo.desenhaRobo(), robo.nodoAtual.getI(), robo.nodoAtual.getJ(), true));
	
				}	
		}	    
	
	}

	
	private static void moveMatriz( EnumDirecao direcao, int distancia){
		
		int posI = PotatoRobo.nodoAtual.getI();
		int posJ = PotatoRobo.nodoAtual.getJ();
		
		switch (direcao) {
		case FRENTE:		
			PotatoRobo.nodoAtual = Mapa.getNodo(posI+distancia, posJ);
			break;
			
		case TRAZ:
			PotatoRobo.nodoAtual = Mapa.getNodo(posI-distancia, posJ);
			break;
		case DIREITA:
			PotatoRobo.nodoAtual = Mapa.getNodo(posI, posJ + 1);
			break;
		case ESQUERDA:
			PotatoRobo.nodoAtual = Mapa.getNodo(posI, posJ-1);
			break;
			
		

		default:			
			
			PotatoRobo.nodoAtual = Mapa.getNodo(posI, posJ);
			break;
		}
		
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
	 * Defifine que direcao ele deve ir, baseado na posicao atual dele e na posicao do objetivo.
	 * @param posicaoAtualI
	 * @param posicaoAtualJ
	 * @param posicaoProximaI
	 * @param posicaoProximaJ
	 * @return
	 */
	 
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
	
	/**
	 * Retorna a direcao que precisa seguir globalmente para executar um movimento local
	 * @param direcao direção global atual
	 * @param direcaoProxima direção para qual deseja seguir independente da direcao atual
	 * @return
	 */
	public static EnumDirecao direcaoParaIrGlobal(EnumDirecao direcao, EnumDirecao direcaoProxima){

		EnumDirecao direcaoParaIr = null;
		
		switch (direcao) {
		case FRENTE:		
			
			switch (direcaoProxima) {
			case FRENTE:		
				direcaoParaIr = EnumDirecao.FRENTE; 
				break;
			case TRAZ:
				direcaoParaIr = EnumDirecao.TRAZ;
				break;
			case DIREITA:
				direcaoParaIr = EnumDirecao.ESQUERDA;
				break;
			case ESQUERDA:
				direcaoParaIr = EnumDirecao.DIREITA;
				break;			

			default:
				break;
			}		
			
			break;
		case TRAZ:
			
			switch (direcaoProxima) {
			case FRENTE:		
				direcaoParaIr = EnumDirecao.TRAZ; 
				break;
			case TRAZ:
				direcaoParaIr = EnumDirecao.FRENTE;
				break;
			case DIREITA:
				direcaoParaIr = EnumDirecao.DIREITA;
				break;
			case ESQUERDA:
				direcaoParaIr = EnumDirecao.ESQUERDA;
				break;			

			default:
				break;
			}	
			break;
		case DIREITA:
			switch (direcaoProxima) {
			case FRENTE:		
				direcaoParaIr = EnumDirecao.DIREITA; 
				break;
			case TRAZ:
				direcaoParaIr = EnumDirecao.ESQUERDA;
				break;
			case DIREITA:
				direcaoParaIr = EnumDirecao.FRENTE;
				break;
			case ESQUERDA:
				direcaoParaIr = EnumDirecao.TRAZ;
				break;			

			default:
				break;
			}		
			break;
		case ESQUERDA:
			switch (direcaoProxima) {
			case FRENTE:		
				direcaoParaIr = EnumDirecao.ESQUERDA; 
				break;
			case TRAZ:
				direcaoParaIr = EnumDirecao.DIREITA;
				break;
			case DIREITA:
				direcaoParaIr = EnumDirecao.TRAZ;
				break;
			case ESQUERDA:
				direcaoParaIr = EnumDirecao.FRENTE;
				break;			

			default:
				break;
			}		
			break;			

		default:
			break;
		}		
		

		return direcaoParaIr;

	}
	
	
	
	



}
