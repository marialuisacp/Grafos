package fifa;

public class Heap {

	long[][] S;
	long n;
	long index;

	/**
	 *
	 * Construtor da classe, cria a estrutura com o tamanho passado como parametro
	 *
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public Heap(long size){
		this.initQueue(size);
	}

	/**
	 *
	 * initQueue Inicializa a fila
	 *
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void initQueue(long size) {
		S = new long[(int)size][2];
		n = 0;
	}

	/**
	 *
	 * parent Retorna a chave do pai de i 
	 *
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public long parent(long i) {
		return (i / 2);
	}

	/**
	 *
	 * left Retorna o indice do filho a esquerda de i
	 *
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public long left(long i) {
		return (2 * i);
	}

	/**
	 *
	 * right Retorna o indice do filho a direita de i
	 *
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public long right(long i) {
		return (2 * i + 1);
	}

	/**
	 *
	 * insertItem Insere itens na fila
	 *
	 * @param x Valor a ser inserido
	 * @param id O id do vertice (para ser usado pelo Dijkstra)
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void insertItem(long x, long id) {
		n = n + 1;
		S[(int)n - 1][0] = Long.MIN_VALUE;
		S[(int)n - 1][1] = id;
		this.decreaseKey((int)n,(int)x);
	}

	/**
	 *
	 * buildHeapshorter Metodo que organiza os elementos da heap, coloca o menor na raiz, Página 112 Thomas H. Cormen 3 ed
	 *
	 * @param A o vetor a ser ordenado
	 * @param n quantidade de elementos do vetor
	 * @param i Representa a posição do noh raiz da arvore
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	private void buildHeapshorter(long A[][], long n, long i) {
		long maior = 0;
		long shorter = 0;
		long left = this.left(i);                             
		long right = this.right(i);                               

		if ((left <= n) && (A[(int)left - 1][0] > A[(int)i - 1][0])) {  
			shorter = left;   
		} else {
			shorter = i;                
		}
		if ((right <= n) && (A[(int)right - 1][0] > A[(int)shorter - 1][0])) {
			shorter = right;                                   
		}
		if (shorter != i) {                                   
			this.exchange(A, i, shorter);                               
			this.buildHeapshorter(A, n, shorter);                        
		}
	}

	/**
	 *
	 * exchange Realiza a mudanca de posição de dois elementos do vetor
	 *
	 * @param A Vetor com os dados
	 * @param i Primeira posicao de troca
	 * @param j Segunda posicao de troca
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void exchange(long[][] A, long i, long j) {
		long aux = A[(int)i - 1][0];
		long aux2 = A[(int)i - 1][1];
		A[(int)i - 1][0] = A[(int)j - 1][0];
		A[(int)i - 1][1] = A[(int)j - 1][1];
		A[(int)j - 1][0] = aux;
		A[(int)j - 1][1] = aux2;
	}

	/**
	 *
	 * getShortItem Extrai o menor elemento da fila de prioridades
	 *
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public long[] getShortItem() {
		long[] c = new long[2];
		if (n < 1) {
			c[0] = 0;
		} else {
			c[0] = S[0][0];
			c[1] = S[0][1];
			S[0][0] = S[(int)n - 1][0];
			S[0][1] = S[(int)n - 1][1];
			n = n - 1;
			this.buildHeapshorter(S, (int)n, 1);
		}
		return c;
	}

	/**
	 *
	 * decreaseKey Metodo que altera o valor da chave
	 *
	 * @author Maria Luisa
	 * @version  1.0
	 * 
	 */
	public void decreaseKey(long x, long k) {
		S[(int)x-1][0] = k;
		while ((x > 1) && S[(int)this.parent((int)x)-1][0] < S[(int)x-1][0]){
			this.exchange(S,x, this.parent(x));            
			x = this.parent(x);
		}   
	}
}