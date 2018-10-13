/**
 * 
 *  Trabalho Pratico do Modulo 2 - Grafos 
 *  Projeto e Analise de Algoritmos
 *  --------------------------------------------------------
 *  
 *  Departamento de Ciencia da Computacao 
 *  Universidade Federal de Minas Gerais  
 *  
 *  Maria Luisa Costa Pinto
 *  dcc.ufmg.br/~maria.luisa
 *  Abril 2018
 *
 */

package fifa;
import fifa.IOFiles;
import fifa.Edge;
import fifa.DijkstraAlgorithm;

/**
 * MainCode Class principal
 * 
 * @author Maria Luisa
 * @version 1.0
 * 
 * 
 */
public class MainCode{

	public static String inputFile = "input.txt";
	public static String outputFile = "output.txt";

	public static void main(String[] args) {

		inputFile = args[0];
		outputFile = args[1];
		
		IOFiles c = new IOFiles(inputFile, outputFile);
		DijkstraAlgorithm resultDijkstra = new DijkstraAlgorithm(c.G);
		TriangleInequality run = new TriangleInequality(resultDijkstra);
		NecessaryEdges n = new NecessaryEdges(run);
	}
}
