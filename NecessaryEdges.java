/**
 * 
 * NecessaryEdges Classe de implementacao das arestas necessarias
 * 
 * @author Maria Luisa
 * @version  1.0
 * 
 */

package fifa;
import java.util.ArrayList;
import fifa.Node;
import fifa.Edge;
import fifa.MainCode;
import fifa.Graph;
import fifa.Heap;
import fifa.DijkstraAlgorithm;
import fifa.TriangleInequality;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class NecessaryEdges {
	public TriangleInequality T;
	public long necessaryEdgesLength;
	public Heap heapEdges; 
	public long[] vertexes;
	public boolean[] founds;
	public long[] dfsN, dfsL, dfsParent;
	public boolean[] a;
	public long dfsNumberCounter, dfs_root, rootChildren;
	public Graph subGraph;
	public Edge[] newEdges;

	/**
	 * 
	 * NecessaryEdges o construtor da classe, inicia os valores a partir do resultado da etapa anterior
	 * 
	 * @param  T o resultado do calculo da desigualdade triangular
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public NecessaryEdges(TriangleInequality T){
		this.T = T;
		this.subGraph = new Graph();
		this.newEdges = new Edge[(int)this.T.edgesLength];
		this.heapEdges = new Heap(this.T.edgesLength);
		this.vertexes = new long[(int)this.T.D.G.sizeV];
		this.founds = new boolean[(int)this.T.D.G.sizeV];
		this.necessaryEdgesLength = 0;

		for(long i = 0; i < this.T.edgesLength; i++){			
			Edge e = this.T.edgesInShortestPaths[(int)i];
			this.newEdges[(int)i] = e;
			this.vertexes[(int)this.newEdges[(int)i].from] = this.newEdges[(int)i].id;
			this.founds[(int)this.newEdges[(int)i].from] = false;
		}

		this.subGraph.insertEdges(newEdges);


		this.dfsNumberCounter = 0;
		this.dfsN = new long[(int)this.subGraph.nodesLength];
		this.dfsL = new long[(int)this.subGraph.nodesLength];
		this.dfsParent = new long[(int)this.subGraph.nodesLength];
		this.a = new boolean[(int)this.subGraph.nodesLength];

		for (long i = 0; i < this.subGraph.nodesLength; i++){
			this.dfsN[(int) i] = -1;
			this.dfsL[(int) i] = 0;
			this.dfsParent[(int) i] = 0;
		}

		for (long i = 0; i < this.subGraph.nodesLength; i++){
			if (this.dfsN[(int)i] == -1) {
				this.dfs_root = i; 
				this.rootChildren = 0;
				this.findBridges(this.subGraph.nodes[(int)i]);
				this.a[(int)dfs_root] = (this.rootChildren > 1);
			} 
		}
		this.printOutput();
	}

	/**
	 * 
	 * findBridges Funcao recursiva para encontrar uma atesta Bridge
	 * 
	 * @param n o objeto do vertice que esta sendo analisado
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void findBridges(Node u) {
		this.dfsL[(int)u.id] = (int) this.dfsNumberCounter++; 
		this.dfsN[(int)u.id] = (int) this.dfsNumberCounter++; 

		ArrayList<Long> adj = this.getAdjacents(u);
		for (long j = 0; j < (int)adj.size(); j++) {
			long v = adj.get((int)j);
			if (this.dfsN[(int)v] == -1) { 
				this.dfsParent[(int)v] = u.id;
				if(u.id == this.dfs_root){
					this.rootChildren++; 
				}
				this.findBridges(this.subGraph.nodes[(int)v]);				
				if(this.dfsL[(int)v] >= this.dfsN[(int)u.id]) 
					a[(int)u.id] = true; 
				if(this.dfsL[(int)v] > this.dfsN[(int)u.id]){
					if(this.founds[(int)u.id] == false){
						this.founds[(int)u.id] = true;
						this.heapEdges.insertItem(-this.vertexes[(int)u.id], 1);
						this.necessaryEdgesLength++;
					}
				}
				this.dfsL[(int)u.id] = Math.min(this.dfsL[(int)u.id], this.dfsL[(int)v]); 
			}else if(v != this.dfsParent[(int)u.id]){
				this.dfsL[(int)u.id] = Math.min(this.dfsL[(int)u.id], this.dfsN[(int)v]); 
			}
		}
	}

	/**
	 * 
	 * getAdjacents Dado um vertice retorna os vertices adjacentes
	 * 
	 * @param n o objeto do vertice
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public ArrayList<Long> getAdjacents(Node n){
		ArrayList<Edge> E = n.getEdges();
		ArrayList<Long> nodesIds = new ArrayList<Long>();
		for(Edge e : E){
			// if(n.id != e.to){
				nodesIds.add(e.to);
			// }
			// if(n.id != e.from){
				nodesIds.add(e.from);
			// }
		}
		return nodesIds;
	}

	/**
	 * 
	 * printOutput funcao que imprime o restultado no arquivo de saida
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void printOutput(){
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			fw = new FileWriter(MainCode.outputFile, true);
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			out.printf(Long.valueOf(this.necessaryEdgesLength).toString() + "\n");
			for (long i = 0; i < this.necessaryEdgesLength; i++) {
				long id_e = -this.heapEdges.getShortItem()[0];
				if(i == 0)
					out.printf(Long.valueOf(id_e).toString());
				else
					out.printf(" " + Long.valueOf(id_e).toString());
			}
			out.close();
		} catch (IOException e) {

		}
	}
}

