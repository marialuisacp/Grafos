/**
 * 
 * Edge Class de modelagem das arestas
 * 
 * @author Maria Luisa
 * @version  1.0
 * 
 */

package fifa;

public class Edge {
	public long from;
	public long to;
	public long length;
	public long id;
	
	/**
	 * 
	 * Edge construtor da classe aresta
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public Edge(long from, long to, long length, long id) {
		this.from = from-1;
		this.to = to-1;
		this.length = length;
		this.id = id;
	}

	/**
	 * 
	 * getFrom retorna o no de origem
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public long getFrom() {
		return from;
	}

	/**
	 * 
	 * getTo retorna o no de destino
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public long getTo() {
		return to;
	}

	/**
	 * 
	 * getLength retorna o tamanho do vetor de arestas
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public long getLength() {
		return length;
	}

	/**
	 * 
	 * getNeighbours funcao que pega o proximo vizinho, esta funcao identifica se o vertice eh um no de origem, caso sim, retorna o no destino, caso nao, retorna ele mesmo
	 * 
	 * @param  node o no a ser analisado
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public long getNeighbours(long node) {
		if (this.from == node) {
			return this.to;
		} else {
			return node;
		}
	}
			
}
