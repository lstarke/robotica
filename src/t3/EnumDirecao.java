package t3;
/** * 
 * @author Francisca
 *
 */

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
		if(valor - valorAtual  == 3) {
			//System.out.println(valorAtual);
			//System.out.println(valor);
			return -1 ;
		}else {
			if(valor - valorAtual  == -3) {
				//System.out.println(valorAtual);
				//System.out.println(valor);
			return  1	;
			}else {
				//System.out.println(valorAtual);
			//	System.out.println(valor);	
			return valor - valorAtual  ;
			}
		}
	}
}
