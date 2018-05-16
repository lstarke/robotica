package t3;

import java.util.ArrayList;


public class Nodo {
	
	private String nome;
	private int x;
	private int y;		
	private Nodo NodoFrente;
	private Nodo NodoTraz;
	private Nodo NodoDireita;
	private Nodo NodoEsquerda;
	
	
	public void Vertice(String nome, int x, int y) {
		setNome(nome);
		setX(x);
		setY(y);
		
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
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
	
	
	
	

}
