package t1;

public enum EnumDirecao {

	TRAZ(0),	
	DIREITA(1),
	FRENTE(2),	
	ESQUERDA(3);	
	
	//| |0|
	//|3|x|1|
	//| |2|
	
	public int valor;
	
	EnumDirecao(int i) {
		this.valor = i;
	}
	
	public int valorDirecional(int valorAtual) {
		//if(Math.abs(valorAtual - valor) >= 3) {
		//	return 4 - Math.abs((valorAtual - valor));
		//}else {
		
			return valorAtual - valor ;
		//}
	}
}
