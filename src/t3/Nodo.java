package t3;

import java.util.ArrayList;
/** * 
 * @author Francisca
 *
 */


public class Nodo {
	
	private String nome ="";
	private int i;
	private int j;		
	private Nodo NodoFrente;
	private Nodo NodoTraz;
	private Nodo NodoDireita;
	private Nodo NodoEsquerda;
	private EnumStatus status = EnumStatus.N_EXPLORADO;
	public enum EnumStatus{
		EXPLORADO,
		N_EXPLORADO,		
		
				}
		
	
	
	public Nodo(int i, int j) {
		setNome("("+ i + ","+ j+ ")");
		setI(i);
		setJ(j);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public Nodo getNodoFrente() {
		return NodoFrente;
	}
	public void setNodoFrente(Nodo nodoFrente) {		
		NodoFrente = nodoFrente;
	}
	public Nodo getNodoTraz() {
		return NodoTraz;
	}
	public void setNodoTraz(Nodo nodoTraz) {
		NodoTraz = nodoTraz;
	}
	public Nodo getNodoDireita() {
		return NodoDireita;
	}
	public void setNodoDireita(Nodo nodoDireita) {
		NodoDireita = nodoDireita;
	}
	public Nodo getNodoEsquerda() {
		return NodoEsquerda;
	}
	public void setNodoEsquerda(Nodo nodoEsquerda) {
		NodoEsquerda = nodoEsquerda;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}
	
	
	
	

}
