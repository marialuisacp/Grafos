/**
 * 
 * IOFiles Class para ler e escrever nos arquivos de entrada e saida
 * 
 * @author Maria Luisa
 * @version  1.0
 * 
 */

package fifa;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import fifa.Edge;
import fifa.Graph;

public class IOFiles{
	public String inputFileName;
	public String outputFileName;
	public Graph G;
	public Edge[] E;

	/**
	 * 
	 * IOFiles metodo construtor da classe, apenas inicializa o nome dos arquivos de entrada e saida
	 * 
	 * @param inputFile nome do arquivo de entrada
	 * @param oututFile nome do arquivo de saida
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public IOFiles(String inputFile, String outputFile){
		this.inputFileName = inputFile;
		this.outputFileName = outputFileName;
		this.G = new Graph();
		this.readFiles();	
	}

	/**
	 * 
	 * readFiles metodo que constroi o grafo a partir da leitura do arquivo de entrada
	 * 
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void readFiles(){
		try {
			long l = 0, v = 0, positionArrayEdges = 0; 
			Scanner scanner = new Scanner(new FileReader(this.inputFileName)).useDelimiter("\\s");
			long[] valuesEdge = new long[(int)3];
			Edge e;
			while(scanner.hasNextInt()) {
				v = scanner.nextInt();				
				if(l == 0){ 	
					this.G.setSizeV(v);
				};
				if(l == 1){
					this.E = new Edge[(int)v];
					this.G.setSizeE(v);
				}
				if(l > 1){
					if(l % 3 == 2) valuesEdge[(int)0] = v;
					if(l % 3 == 0) valuesEdge[(int)1] = v;
					if(l % 3 == 1){ 
						valuesEdge[(int)2] = v;
						e = new Edge(valuesEdge[(int)0], valuesEdge[(int)1], valuesEdge[(int)2], positionArrayEdges+1);
						this.E[(int)positionArrayEdges] = e;
						positionArrayEdges++;
					}
				}
				l++;
			}
			this.G.insertEdges(E);

		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe);
		}
	}

}

