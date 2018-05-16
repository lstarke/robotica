package t3;

import org.apache.bcel.generic.RETURN;

public class PotatoRobo {
	private static EnumDirecao direcaoRobo = EnumDirecao.FRENTE;
	private static EnumDirecao direcaoCabeca = EnumDirecao.FRENTE;
	private static PotatoRobo instance = null;
	public static PotatoManager manager = new PotatoManager();	
	//private static NodoAtual;
	
	private PotatoRobo() {
		
	}
	public static PotatoRobo getInstance() {
		if(instance == null) {
			instance = new PotatoRobo();
		}
		return instance;
	}
	
	public static EnumDirecao getDirecaoRobo() {
		return direcaoRobo;
	}
	public static void setDirecaoRobo(EnumDirecao direcaoRobo) {
		PotatoRobo.direcaoRobo = direcaoRobo;
	}
	public static EnumDirecao getDirecaoCabeca() {
		return direcaoCabeca;
	}
	public static void setDirecaoCabeca(EnumDirecao direcaoCabeca) {
		PotatoRobo.direcaoCabeca = direcaoCabeca;
	}
	public void moveCabeca(EnumDirecao direcao) {		
		direcaoCabeca = direcao;		
		int valorDirecional = direcao.valorDirecional(direcaoCabeca.valor);
		PotatoManager.viraDirecionadaCabeca4d(valorDirecional);		
		
	}
	
	
	
	

}
