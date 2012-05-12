/**
 * Para maiores informações:
 * @autor   Glauco Roberto Munsberg dos Santos
 * @github  git@github.com:glaucomunsberg/try_a_trie.git
 * @version 0.9.9
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class ArvoreDeSufixo extends ArvoreTrie
{
    protected Scanner leitor;
    protected StringBuilder stringPalavra;
    protected char[] palavraChar,palavraCharInvertida ;
    
    /**
     * Construtor
     */
    public ArvoreDeSufixo(int numDeNodos)
    {
        super(numDeNodos);
        leitor = new Scanner(System.in);
    }
    
    /**
     * Método main que executa
     * @param args 
     */
    public static void main(String args[])
    {
        ArvoreDeSufixo arvSufixo = new ArvoreDeSufixo(4);
        arvSufixo.iniciar();
    }
    
    /**
     * Método inicia sobre escreve o de ArvoreTrie
     * pois se diferencia na sua execução
     */
    @Override
    public void iniciar()
    {
        stringPalavra = new StringBuilder(leitor.nextLine());
        palavraChar = new char[stringPalavra.length()];
        palavraChar = stringPalavra.toString().toCharArray();
        palavraCharInvertida = new char[stringPalavra.length()];
        palavraCharInvertida = stringPalavra.reverse().toString().toCharArray();
        if( (palavraChar[0] == '@') )
        {
            finalizar();
        }
        else
        {
            for(int a=0; a < palavraChar.length; a++)
            {
                inserirString( Arrays.copyOfRange(palavraChar, a, palavraChar.length));
            }
        } 
        stringPalavra.delete(0, stringPalavra.length());
        buscaPalindromas();
    }
    
    /**
     * Método inserirString sobre escreve o da ArvoreTrie
     * pois cada nodo é uma substring, ou seja, a diferença
     * está em que todos são finais
     * @param char[] string
     * @return boolean { true - se inseriu corretamente | false - se não inseriu }
     */
    @Override
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
				 * Complicado...Mas vamos tentar
				 * 	o nodo tem seus nodos vazios e acima diz que um dos nodos do nodo
				 * 	agora deve ser instanciado. Abaixo então o que fizemos:
				 * 	SE o nodos que criamos no nodo tal é nulo é pq ele não alocou
				 * 	nenhum dos seus nodos internos, logo faltou memória
				 */
				if( nodo.nodos[ intChar ].nodos == null )
				{
					return false;
				}
                                nodo.isFinal = true;
				nodo.nodos[ intChar ].prev = nodo;
			}
			nodo.nodos[ intChar ].numeroDePrefixo++;
			nodo = nodo.nodos[ intChar ];
		}
		nodo.isFinal = true;
		return nodo.isFinal;
	}
    
    /**
     * 
     * @return 
     */
    public boolean buscaPalindromas()
    {
        boolean achou = false;
        int maiorTamanho = 0;
        
        LinkedList<String> maioresPalavras = new LinkedList<> ();
        
        for(int a = 0; a <= palavraCharInvertida.length ; a++ )
        {
            for(int b = a; b <= palavraCharInvertida.length; b++)
            {

                achou = buscarString( Arrays.copyOfRange(palavraCharInvertida, a, b) );       
                if( achou )
                {
                    if( isPalindroma( Arrays.copyOfRange( palavraCharInvertida, a,b ) ) )
                    {
 
                        if( b-a >= maiorTamanho)
                        {
                            if( b-a == maiorTamanho)
                            {
                                maioresPalavras.add( String.valueOf(Arrays.copyOfRange(palavraCharInvertida, a, b) ) ); 
                            }
                            else
                            {
                                maioresPalavras.clear();
                                maiorTamanho = b-a;
                                maioresPalavras.add(String.valueOf(Arrays.copyOfRange(palavraCharInvertida, a, b) ));
                            }
                        }
                    }
                }
                else
                {
                    break;
                }
            }
          
        }
        raiz = null;
        
        System.gc();
        System.runFinalization();
        
        String[] args = new String[maioresPalavras.size()];
        int n=0;
        while(!maioresPalavras.isEmpty())
        {
            args[n] = maioresPalavras.remove().toString();
            n++;
        }
        maioresPalavras.clear();
        imprimeOrdenado(args);
        return true;
    }
    
    /**
     * Método que utiliza o Array Sort para imprimir
     *  de modo ordenado os valores do vetor
     * @param palindromas 
     */
    public void imprimeOrdenado(String[] palindromas)
    {
        Arrays.sort(palindromas);
        for(int i=0; i<palindromas.length; i++) {  
            System.out.println( palindromas[i] );  
        }
    }
    

    @Override
    /**
     * Método que sobre escreve o método usado na árvore
     *  trie para que seja capaz de suportar numero menor
     *  de nodos, sendo assim mapeia os sufixos 'a','t','g'
     *  e 'c' e numerais de 0 a 3
     * @return int {0 a 3}
     */
    public int posicaoDoChar(char letra)
    {
        switch(letra)
        {
            case 'a':
            case 'A':
                return 0;
            case 't':
            case 'T':
                return 1;
            case 'g':
            case 'G':
                return 2;
            case 'c':
            case 'C':
                return 3;
            default:
                return 0;
        }
    }
    
    /**
     * Método responsável por retornar se é ou não palíndromo
     * @param char[] palavra
     * @return boolean { true - se é palindromo | false - se não for palindromo}
     */
    public boolean isPalindroma(char[] palavra)
    {  
      if(palavra.length == 2)
            return palavra[0] == palavra[1];
      int i = 0;
      int j = palavra.length - 1;
      while(j >= 0)
      {
          if(!(palavra[j] == palavra[i]))
          {
              return false;
          }
          i++;
          j--;
      }
      return true;
    }
    
    /**
     * Método que sobre escreve o método da ArvoreTrie
     * Para que possa finalizála corretamente
     */
    @Override
    public void finalizar()
    {
        super.finalizar();
        System.gc();
        System.runFinalization();
        System.exit(0);
    }
    
}
