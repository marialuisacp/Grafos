/**
 * DijkstraAlgorithm Classe de implementacao do algoritmo Dijkstra
 * 
 * @author Maria Luisa
 * @version 1.0
 * 
 * 
 */

package fifa;
import fifa.IOFiles;
import fifa.Edge;
import fifa.Graph;
import fifa.Heap;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.List;

public class DijkstraAlgorithm{
	public Graph G;
	public boolean[] nodes; 
	public Heap heapNodes; 

	/**
	 * 
	 * Construtor da classe, inicia os valores
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public DijkstraAlgorithm(Graph G){
		this.G = G;
		this.heapNodes = new Heap(this.G.nodes.length);
		this.nodes = new boolean[this.G.nodes.length];
		this.getShortestDistances();
	}

	/**
	 * 
	 * getShortestDistances funcao que percorre os vertices realizando a relaxacao nas arestas
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void getShortestDistances() {
		this.G.nodes[(int)0].setDistanceFromSource(0);
		long nextNode = 0;
		long c = 0;
		for (long i = 0; i < this.G.nodes.length; i++) {

			if(nextNode == 0 && c > 0){
				i = this.G.nodes.length;
			}

			ArrayList<Edge> current = this.G.nodes[(int)nextNode].getEdges();
			long neighbourIndex = 0;
			for (long j = 0; j < current.size(); j++) {
				neighbourIndex = current.get((int)j).getNeighbours((int)nextNode);			
				if (!this.G.nodes[(int)neighbourIndex].isVisited()) {
					long tentative = this.G.nodes[(int)nextNode].getDistanceFromSource() + current.get((int)j).getLength();
					if (tentative < this.G.nodes[(int)neighbourIndex].getDistanceFromSource()) {
						this.G.nodes[(int)neighbourIndex].setDistanceFromSource(tentative);
						this.heapNodes.insertItem(-tentative, neighbourIndex);
					}					
				}
			}
			this.G.nodes[(int)nextNode].setVisited(true);
			this.nodes[(int)nextNode] = true;
			nextNode = this.heapNodes.getShortItem()[1];			
			long x = 0;
			while(this.nodes[(int)nextNode] == true && x < this.G.nodes.length && (nextNode != 0 || c == 0)){
				nextNode = this.heapNodes.getShortItem()[1];
				x++;
			}
			c++;
		}
	}
}

