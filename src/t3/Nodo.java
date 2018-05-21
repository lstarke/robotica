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


	private boolean isNodoPercorrido = false;
	private EnumProduto produto = EnumProduto.NENHUM;
	
	
	
	private double distancia;
	private Nodo pai;		
	private ArrayList<Nodo> nodosAdjacentes;
	//NodosA
	
	
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

	public boolean isNodoPercorrido() {
		return isNodoPercorrido;
	}

	public void setNodoPercorrido(boolean isNodoPercorrido) {
		this.isNodoPercorrido = isNodoPercorrido;
	}
	
	public ArrayList<Nodo> getNodosAdjacentes(){
		if(nodosAdjacentes == null){
			nodosAdjacentes = new ArrayList<Nodo>();
			
			if(NodoDireita != null){
				nodosAdjacentes.add(NodoDireita);
			}
			if(NodoEsquerda != null){
				nodosAdjacentes.add(NodoEsquerda);
			}
			if(NodoFrente != null){
				nodosAdjacentes.add(NodoFrente);
			}
			
			if(NodoTraz != null){
				nodosAdjacentes.add(NodoTraz);
			}
		}
		
		
		return  nodosAdjacentes;
		}
	
	public String imprimeAdjacentes() {
		String s= "Adjacentes:";
		
		for(Nodo n :nodosAdjacentes) {
			s += n.getNome() + ";";
		}
		
		return s;
	}
	
	public int getGrau(){
		int grau = 0;
		if(nodosAdjacentes != null){
			grau = getNodosAdjacentes().size();
		}
		
		return grau;
		
	}
	
	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double d) {
		this.distancia = d;
	}

	public Nodo getPai() {
		return pai;
	}

	public void setPai(Nodo pai) {
		this.pai = pai;
	}

	public EnumProduto getProduto() {
		return produto;
	}

	public void setProduto(EnumProduto produto) {
		this.produto = produto;
	}
	
	@Override
	public String toString() {
		String s = "Nodo:" + this.getNome() + ".";
		s += "\nPai:" + this.pai.getNome();
		s += "\n" +imprimeAdjacentes();
		
		return nome;
		
	}
	
	
	

}
