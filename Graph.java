/**
 * 
 * Graph Class de modelagem das arestas
 * 
 * @author Maria Luisa
 * @version  1.0
 * 
 */

package fifa;
import java.util.ArrayList;
import fifa.Edge;
import fifa.Node;

public class Graph {
	public long sizeE;
	public long sizeV;
	public Node[] nodes;
	public Edge[] edges;
	public long nodesLength;
	public long edgesLength;

	/**
	 * 
	 * Graph metodo construtor da classe, inicia os tamanhos com zero
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public Graph(){
		this.sizeE = 0;
		this.sizeV = 0;
	}

	/**
	 * 
	 * getNodes retorna o array de vertices
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public Node[] getNodes() {
		return nodes;
	}	
	
	/**
	 * 
	 * getEdges retorna o array de arestas
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public Edge[] getEdges() {
		return edges;
	}

	/**
	 * 
	 * setSizeE metodo que insere a quantidade de arestas lida no arquivo de entrada
	 * 
	 * @param s valor a ser inserido
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void setSizeE(long s){
		this.sizeE = s;
	}

	/**
	 * 
	 * setSizeV metodo que insere a quantidade de vertices lida no arquivo de entrada
	 * 
	 * @param s valor a ser inserido
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void setSizeV(long s){
		this.sizeV = s;
	}

	/**
	 * 
	 * getNodesArrayLength retorna o tamanho do array de vertices
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public long getNodesArrayLength() {
		return nodesLength;
	}
	
	/**
	 * 
	 * getEdgesArrayLength retorna o tamanho do array de arestas
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public long getEdgesArrayLength() {
		return edgesLength;
	}

	/**
	 * 
	 * insertEdges insere as arestas na estrutura de dados Grafo
	 * 
	 * @param E o array de arestas
	 * @return nodesLength o numero de vertices encontrado
	 * @author Maria Luisa
	 * @version  1.0
	 *
	 */
	public void insertEdges(Edge[] E){
		this.edges = E;
		this.nodesLength = nodesLength(E);
		this.nodes = new Node[(int)this.nodesLength];
		for (long n = 0; n < this.nodesLength; n++) {
			this.nodes[(int)n] = new Node(n);
		}
		this.edgesLength = E.length;
		
		
		for (long edgeToAdd = 0; edgeToAdd < this.edgesLength; edgeToAdd++) {
			this.nodes[(int)E[(int)edgeToAdd].getFrom()].getEdges().add(E[(int)edgeToAdd]);
			this.nodes[(int)E[(int)edgeToAdd].getTo()].getEdges().add(E[(int)edgeToAdd]);
		}
		
	}

	/**
	 * 
	 * nodesLength Calcula o numero de vertices em um array de arestas
	 * 
	 * @param E o array de arestas
	 * @return nodesLength o numero de vertices encontrado
	 * @author Maria Luisa
	 * @version  1.0
	 *
	 */
	private long nodesLength(Edge[] E) {
		long nodesLength = 0;
		for (Edge e:E ) {
			if (e.getTo() > nodesLength) nodesLength = e.getTo();
			if (e.getFrom() > nodesLength) nodesLength = e.getFrom();
		}
		nodesLength++;	
		return nodesLength;		
	}
}
