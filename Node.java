/**
 * 
 * Node Classe de modelagem dos vertices
 * 
 * @author Maria Luisa
 * @version  1.0
 * 
 */

package fifa;
import java.util.ArrayList;

public class Node {	
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<Edge> Alledges = new ArrayList<Edge>();
	private boolean visited = false;
	private long distanceFromSource = Long.MAX_VALUE;
	public long id;

	public Node(long n){
		this.id = n;
	}

	/**
	 * 
	 * getDistanceFromSource retorna a distancia do vertice origem do grafo
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public long getDistanceFromSource() {
		return distanceFromSource;
	}

	/**
	 * 
	 * setDistanceFromSource seta a distancia do vertice origem do grafo
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void setDistanceFromSource(long distanceFromSource) {
		this.distanceFromSource = distanceFromSource;
	}

	/**
	 * 
	 * isVisited retorna se o vertice ja foi vistado ou nao
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * 
	 * setVisited seta se o vertice ja foi vistado ou nao
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * 
	 * getEdges retorna o array das arestas daquele vertice
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}

	/**
	 * 
	 * setEdges insere as arestas no array 
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void setEdges(ArrayList<Edge> E) {
		this.edges = E;
	}


	public void setAllEdges(ArrayList<Edge> E) {
		this.Alledges = E;
	}


	public ArrayList<Edge> getAllEdges() {
		return Alledges;
	}
}
