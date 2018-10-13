/**
 * 
 * Node Class de modelagem dos vertices
 * 
 * @author Maria Luisa
 * @version  1.0
 * 
 */

package fifa;
import java.util.ArrayList;
import fifa.Edge;
import fifa.Graph;
import fifa.MainCode;
import fifa.DijkstraAlgorithm;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TriangleInequality {
	public DijkstraAlgorithm D;
	public long[] edgesIndexInShortestPaths;
	public Edge[] edgesInShortestPaths;
	public long edgesLength;
	public ArrayList<Long> valuesOutput;

	/**
	 * 
	 * TriangleInequality Construtor da classe
	 * 
	 * @param resultDijkstra Resultado do Algoritmo de Dijkstra
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public TriangleInequality(DijkstraAlgorithm resultDijkstra){
		this.D = resultDijkstra;
		this.edgesIndexInShortestPaths = new long[(int)this.D.G.edgesLength];
		this.edgesInShortestPaths = new Edge[(int)this.D.G.edgesLength];
		this.edgesLength = 0;
		this.valuesOutput = new ArrayList<Long>();
		this.getResult();
	}

	/**
	 * 
	 * getResult funcao que usa o principio da desigualdade triangular para calcular (no proximo passo) quais arestas sao necessarias
	 * 
	 * @param resultDijkstra Resultado do Algoritmo de Dijkstra
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void getResult(){
		long count = 0;
		for (long i = 1; i <= this.D.G.edges.length; i++) {
			long distNodeBefore = this.D.G.nodes[(int)this.D.G.edges[(int)i-1].from].getDistanceFromSource();
			long triangle = distNodeBefore + this.D.G.edges[(int)i-1].length;

			if(triangle == this.D.G.nodes[(int)this.D.G.edges[(int)i-1].to].getDistanceFromSource()){
				this.edgesIndexInShortestPaths[(int)count] = i;
				this.edgesInShortestPaths[(int)count] = this.D.G.edges[(int)i-1];
				count++;
			}
		}
		this.edgesLength = count;
		this.printResult();
	}

	/**
	 * 
	 * printResult funcao que imprime o resultado no arquivo de saida
	 * 
	 * @param resultDijkstra Resultado do Algoritmo de Dijkstra
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void printResult(){
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(MainCode.outputFile);
			bw = new BufferedWriter(fw);
			bw.write(Long.valueOf(this.edgesLength).toString() + "\n");
			for (long i = 0; i < this.edgesLength; i++) {
				bw.write(Long.valueOf(this.edgesIndexInShortestPaths[(int)i]).toString());
				if(i == this.edgesLength-1){
					bw.write("\n");
				}else{
					bw.write(" ");
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
