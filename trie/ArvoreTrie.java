import java.util.Arrays;

public class ArvoreTrie 
{
	Nodo raiz;
	LeitorDeArquivo lerArquivo;
	geradorDeArquivo gerarArquivo;
	boolean isDebug;
	
	public static void main(String args[] )
	{
		ArvoreTrie trie = new ArvoreTrie();
		trie.setIsDebug(true);
		trie.iniciar();
		trie.finalizar();
	}
	
	public ArvoreTrie()
	{
		raiz = new Nodo();
		raiz.isFinal = false; 
		raiz.numeroDePrefixo =0;
		lerArquivo = new LeitorDeArquivo("entrada.txt");
		gerarArquivo = new geradorDeArquivo("saida.txt");
		setIsDebug(false);
	}
	
	/**
	 * Método que retorna se esse código
	 * 	está em modo de debug
	 * @return Boolean { true - se está em debug | false - se não está debugando }
	 */
	public boolean isDebug()
	{
		return this.isDebug;
	}
	
	/**
	 * Recebe um booleando para informar se o código quando
	 * 	for executado será debugado ou não
	 * @param isdebug
	 */
	public void setIsDebug(boolean isdebug)
	{
		this.isDebug = isdebug;
	}
	
	/**
	 * Método que inicia e executa as ações sobre a árvore
	 */
	public void iniciar()
	{
		char palavraChar[];
		StringBuilder stringAcaoPalavra = new StringBuilder();
		String stringLida = lerArquivo.lerLinha();
		
		while( stringLida != null )
		{
			/*
			 * Pega a string e a converte para um array de char
			 */
			stringAcaoPalavra.append(stringLida);
			palavraChar = new char[stringAcaoPalavra.length()];
			palavraChar = stringAcaoPalavra.toString().toCharArray();
			
			/*
			 * De acordo com cada comando que se encontra na primeira
			 * 	posicao executa a ação.
			 * 	Ententendo: a palavra é passada para o inserir que retorna
			 * 	 um boolean sobre a ação que é repassado para o gerador de
			 * 	 saída que grava arquivo saida.txt
			 */
			if( palavraChar[0] == 'i')
			{
				geradorDeSaida( inserirString( palavraChar ) );
			}
			else
			{
				if(palavraChar[0] == 'b')
				{
					geradorDeSaida( buscarString( palavraChar ) );
				}
				else
				{
					geradorDeSaida( removerString( palavraChar ) );
				}
			}
			
			/*
			 * Limpa o StringBuiler e faz a leitura da próxima linha
			 */
			stringAcaoPalavra.delete(0, stringAcaoPalavra.length());
			stringLida = lerArquivo.lerLinha();
		}
		System.out.printf("a na posicao: %d\nz na posicao: %d", posicaoDoChar('a'), posicaoDoChar('z'));
	}
	
	
	public void finalizar()
	{
		lerArquivo.fechar();
		gerarArquivo.fechar();
	}
	/**
	 * Método que insere uma string dentro da trie
	 * @param Char[] string
	 * @return Boolean {true - se inseriu ou há o elemento já inserido | false - se não pode inserir na árvore }
	 */
	protected boolean inserirString(char[] string)
	{
		Nodo nodo = raiz;
		nodo.numeroDePrefixo++;	//Diz que ali tem mais um nodo
		if( this.isDebug)
			showDebug(String.format("Inserindo: %s\n", Arrays.toString(string)) );
		for( int a = 2; a < string.length; a++)
		{			
			int intChar = this.posicaoDoChar(string[a]);
			if( nodo.nodos[intChar] == null)
			{
				
				nodo.nodos[intChar] = new Nodo();
				
				/*
				 * Complicado...Mas vamos tentar
				 * 	o nodo tem seus nodos vazios e acima diz que um dos nodos do nodo
				 * 	agora deve ser instanciado. Abaixo então o que fizemos:
				 * 	SE o nodos que criamos no nodo tal é nulo é pq ele não alocou
				 * 	nenhum dos seus nodos internos, logo faltou memória
				 */
				if( nodo.nodos[intChar].nodos == null)
				{
					if( this.isDebug)
						showDebug("Nodo não criado volta null\n");
					return false;
				}
				if( this.isDebug)
					showDebug(String.format("Inserido nodo %d\n", intChar));
			}
			nodo.nodos[intChar].numeroDePrefixo++;
			nodo = nodo.nodos[intChar];
		}
		if( this.isDebug)
			showDebug("\n");
		nodo.isFinal = true;
		return true;
	}
	
	/**
	 * Método para remover uma string dentro da trie
	 * @param Char[] string
	 * @return boolean { true - se removeu | false - se não removeu }
	 */
	protected boolean removerString(char[] string)
	{
		for( int a = 2; a < string.length; a++)
		{
			System.out.printf("%s",string[a] );
		}
		return true;
	}
	
	/**
	 * Método para realizar busca da string dentro
	 * 	da árvore trie
	 * 
	 * @param Char[] string
	 * @return boolean { true - se achou | false - se não achou }
	 */
	protected boolean buscarString(char[] string)
	{
		Nodo nodo = raiz;
		if( this.isDebug)
			showDebug(String.format("Inserindo: %s\n", Arrays.toString(string)));
		for( int a = 2; a < string.length; a++)
		{
			if( nodo.nodos[this.posicaoDoChar(string[a])] != null)
			{
				if( this.isDebug)
					showDebug(String.format("buscou posicao %d\n", this.posicaoDoChar(string[a])));
				nodo = nodo.nodos[this.posicaoDoChar(string[a])];
			}
			else
			{
				if( this.isDebug)
					showDebug("não achou\n");
				return false;
			}
		}
		if( this.isDebug)
			showDebug("\n");
		return nodo.isFinal;
	}
	
	/**
	 * Recebe um boolean que será gravado no
	 * 	arquivo de saida saida.txt
	 * @param boolean saida
	 */
	protected void geradorDeSaida(boolean saida)
	{
		if(saida)
		{
			gerarArquivo.adicionar("V\n");
		}
		else
		{
			gerarArquivo.adicionar("F\n");
		}
	}
	
	/**
	 * O método retorna o código que representa o char
	 * 	de 0 a 25 que será a sua posição na árvore trie
	 * @param char letra
	 * @return int{0 - 25}
	 */
	public int posicaoDoChar(char letra)
	{
		return (int)letra - 97;
	}
	
	/**
	 * Método de debugar o código no terminal
	 * 	recebe a mensagem que deve ser impressa no terminal
	 * @param mensagem
	 */
	protected void showDebug(String mensagem)
	{
		System.out.printf("%s", mensagem);
	}
}
