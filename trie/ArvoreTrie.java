public class ArvoreTrie 
{
	Nodo raiz;
	LeitorDeArquivo lerArquivo;
	
	public static void main(String args[] )
	{
		ArvoreTrie trie = new ArvoreTrie();
		trie.iniciar();
		trie.finalizar();
	}
	
	public ArvoreTrie()
	{
		raiz = new Nodo();
		lerArquivo = new LeitorDeArquivo("entrada.txt");
	}
	
	public void iniciar()
	{
		StringBuilder stringAcaoPalavra = new StringBuilder();
		String stringLida = lerArquivo.lerLinha();
		char palavraChar[];
		while( stringLida != null )
		{
			stringAcaoPalavra.append(stringLida);
			palavraChar = new char[stringAcaoPalavra.length()];
			palavraChar = stringAcaoPalavra.toString().toCharArray();
			if( palavraChar[0] == 'i')
			{
				geradorDeSaida( inserirString( palavraChar, raiz ) );
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
			stringAcaoPalavra.delete(0, stringAcaoPalavra.length());
			stringLida = lerArquivo.lerLinha();
		}
		System.out.printf("a na posicao: %d\nz na posicao: %d", posicaoDoChar('a'), posicaoDoChar('z'));
	}
	
	
	public void finalizar()
	{
		raiz = null;
		lerArquivo.fechar();
	}
	/**
	 * Método que insere uma string dentro da trie
	 * @param Char[] string
	 * @return Boolean {true - se inseriu ou há o elemento já inserido | false - se não pode inserir na árvore }
	 */
	protected boolean inserirString(char[] string, Nodo nodo)
	{
		nodo.numeroDePrefixo++;
		for( int a = 2; a < string.length; a++)
		{
			int intChar = this.posicaoDoChar(string[a]);
			if( nodo.nodos[intChar] == null)
			{
				nodo.nodos[intChar] = new Nodo();
				if( nodo.nodos[intChar].nodos == null)
				{
					return false;
				}
			}
			nodo.nodos[intChar].numeroDePrefixo++;
			nodo = nodo.nodos[intChar];
		}
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
		for( int a = 2; a < string.length; a++)
		{
			System.out.printf("%s",string[a] );
		}
		return true;
	}
	
	/**
	 * Recebe um boolean que será gravado no
	 * 	arquivo de saida saida.txt
	 * @param boolean saida
	 */
	protected void geradorDeSaida(boolean saida)
	{
		
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
	
}
