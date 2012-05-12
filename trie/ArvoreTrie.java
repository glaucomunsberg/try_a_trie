/**
 * Para maiores informações:
 * @autor   Glauco Roberto Munsberg dos Santos
 * @github  git@github.com:glaucomunsberg/try_a_trie.git
 * @version 0.9.9
 */

import java.util.Scanner;

public class ArvoreTrie 
{  
        private char palavraChar[];
	protected Nodo raiz;
        protected int numDeNodos;
	public boolean isDebug;
        protected Scanner leitor;
        
	
	public static void main(String args[] )
	{
                
		ArvoreTrie trie = new ArvoreTrie(26);
		trie.setIsDebug(false);
		trie.iniciar();
		trie.finalizar();
	}
	
	public ArvoreTrie(int numDeNodos)
	{
                this.numDeNodos = numDeNodos;
		raiz = new Nodo(numDeNodos);
		raiz.isFinal = false;
		raiz.numeroDePrefixo = 0;
		raiz.prev = null;
		setIsDebug(false);
                leitor = new Scanner(System.in);
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
	private void setIsDebug(boolean isdebug)
	{
		this.isDebug = isdebug;
	}
	
	/**
	 * Método que inicia e executa as ações sobre a árvore
	 */
	public void iniciar()
	{
		
		StringBuilder stringAcaoPalavra = new StringBuilder();
		String stringLida = leitor.nextLine();
		
		do{
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
                        switch(palavraChar[0])
                        {
                            case 'i':
                                        geradorDeSaida( inserirString( palavraChar ) );
                                        break;
                            case 'b':
                                        geradorDeSaida( buscarString( palavraChar ) );
                                        break;
                            case 'r':
                                        geradorDeSaida( removerString( palavraChar ) );
                                        break;
                            default:
                                         finalizar();   
                        }
                        
			/*
			 * Limpa o StringBuiler e faz a leitura da próxima linha
			 */
			stringAcaoPalavra.delete(0, stringAcaoPalavra.length());
			stringLida = leitor.nextLine();
		}while( stringLida != null );
		
	}
	
	
	public void finalizar()
	{
                raiz = null;
                System.gc();
                System.runFinalization();;
                System.exit(0);
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

		for( int a = 2; a < string.length; a++)
		{			
			int intChar = this.posicaoDoChar(string[a]);
			if( nodo.nodos[ intChar ] == null )
			{	
                            try
                            {
                                nodo.nodos[ intChar ] = new Nodo(numDeNodos);
                            }
                            catch(Exception erro)
                            {
                                System.gc();
                                System.runFinalization();
                            }

                           /*
                            * 	O nodo tem seus nodos vazios e acima diz que um dos nodos do nodo
                            * 	agora deve ser instanciado. Abaixo então o que fizemos:
                            * 	SE o nodos que criamos no nodo tal é nulo é pq ele não alocou
                            * 	nenhum dos seus nodos internos, logo faltou memória
                            */
                            if( nodo.nodos[ intChar ].nodos == null )
                            {
                                    return false;
                            }
                            nodo.nodos[ intChar ].prev = nodo;
			}
			nodo.nodos[ intChar ].numeroDePrefixo++;
			nodo = nodo.nodos[ intChar ];
		}
		nodo.isFinal = true;
		return nodo.isFinal;
	}
	
	/**
	 * Método para remover uma string dentro da trie
	 * 	Primeiro ele procura pela string, é o mesmo algoritmo do
	 * 	buscar, porém com um passo a mais.
	 * @param Char[] string
	 * @return boolean { true - se removeu | false - se não removeu }
	 */
	protected boolean removerString(char[] string)
	{
		Nodo nodo = raiz;
		/*
		 * Primeiro passo do remover é buscar a string até o final. 
		 * Isso faz com que o nodo esteja no nodo mais distante da string
		 * 	para que se possa voltar deletando
		 */
		int a;
		for( a = 2; a < string.length; a++)
		{
			if( nodo.nodos[ this.posicaoDoChar( string[a] ) ] != null )
			{
				nodo = nodo.nodos[ this.posicaoDoChar( string[a] ) ];
			}
			else
			{
				return false;
			}
		}
                
		/*
		 * Segundo passo é marcar esse nodo como não final, pois
		 * 	estaremos removendo a palavra que está até aqui. Depois
		 * 	enquanto ele não tiver prev null (raiz)
		 */
                if( nodo.isFinal != true)
                {
                    return false;
                }
		nodo.isFinal = false;
		Nodo temp;
		
		while( nodo.prev != null)
		{
			if( nodo.numeroDePrefixo <= 0)
			{
				temp = nodo;
				nodo = nodo.prev;
				nodo.numeroDePrefixo--;
				temp.finalize();
			}
			else
			{
				nodo = nodo.prev;
			}
		}
                /**
                 * Se o nodo é igual a raiz, então houve sucesso
                 *  no retornar
                 */
		if( nodo.equals(raiz))
		{
			return true;
		}
		else
		{
			return false;
		}
		
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
		
		for( int a = 2; a < string.length; a++)
		{
			if( nodo.nodos[ this.posicaoDoChar( string[a] ) ] != null)
			{
				nodo = nodo.nodos[ this.posicaoDoChar( string[a] ) ];
			}
			else
			{
				return false;
			}
		}
		return nodo.isFinal;
	}
	
	/**
	 * Recebe um boolean que faz com que seja imprimido
	 *  V ou F de acordo com o seu valor
	 * @param boolean saida
	 */
	protected void geradorDeSaida(boolean saida)
	{
		if(saida)
		{
			System.out.println("v");
		}
		else
		{
			System.out.println("f");
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
