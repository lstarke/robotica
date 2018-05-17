package t3;

public class Teste {

	public static void main(String[] args) {


		Mapa mapa = Mapa.getInstance();
		
		
		
		Nodo nodoAtual = mapa.getNodo(0, 2);
		
				
		nodoAtual.setNodoFrente(Mapa.getNodo(0, 1));		
		nodoAtual.getNodoFrente().setNodoTraz(nodoAtual);
		
		nodoAtual.setNodoDireita(Mapa.getNodo(0, 1));
		
		System.out.println(nodoAtual.getNome() );
		System.out.println(nodoAtual.getNodoFrente().getNome());
		System.out.println(nodoAtual.getNodoFrente().getNodoTraz().getNome());
		
		//System.out.println(getNome() );
		
	}

}
