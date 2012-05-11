/**
 * Classe do objeto àrvore de sufixo
 * 
 *  @author glaucomunsberg@gmail.com
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class ArvoreDeSufixo extends ArvoreTrie
{
    protected Scanner leitor;
    protected StringBuilder stringPalavra;
    protected char[] palavraChar,palavraCharInvertida ;
    
    public ArvoreDeSufixo()
    {
        leitor = new Scanner(System.in);
    }
    
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
            boolean retornoRaiz1 = false;
            for(int a=0; a < palavraChar.length; a++)
            {
                retornoRaiz1 = inserirString( Arrays.copyOfRange(palavraChar, a, palavraChar.length));
                System.out.printf("%s\n", Arrays.toString( Arrays.copyOfRange( palavraChar, a, palavraChar.length ) ) );
            }
            if(!retornoRaiz1)
            {
                System.out.printf("erro de inserção\n");
            }
        } 
        stringPalavra.delete(0, stringPalavra.length());
        buscaPalindromas();
    }
    
    @Override
    protected boolean inserirString(char[] string)
	{
		Nodo nodo = raiz;
		nodo.numeroDePrefixo++;	//Diz que ali tem mais um nodo
		//if( this.isDebug)
			//showDebug(String.format("Inserindo: %s\n", Arrays.toString(string)) );
		for( int a = 2; a < string.length; a++)
		{			
			int intChar = this.posicaoDoChar(string[a]);
			if( nodo.nodos[ intChar ] == null )
			{
				
				nodo.nodos[ intChar ] = new Nodo();
				
				/*
				 * Complicado...Mas vamos tentar
				 * 	o nodo tem seus nodos vazios e acima diz que um dos nodos do nodo
				 * 	agora deve ser instanciado. Abaixo então o que fizemos:
				 * 	SE o nodos que criamos no nodo tal é nulo é pq ele não alocou
				 * 	nenhum dos seus nodos internos, logo faltou memória
				 */
				if( nodo.nodos[ intChar ].nodos == null )
				{
					//if( this.isDebug)
						//showDebug("Nodo não criado volta false\n");
					return false;
				}
                                nodo.isFinal = true;
				nodo.nodos[ intChar ].prev = nodo;
				//if( this.isDebug)
					//showDebug(String.format("Inserido nodo %d\n", intChar));
			}
			nodo.nodos[ intChar ].numeroDePrefixo++;
			nodo = nodo.nodos[ intChar ];
		}
		//if( this.isDebug)
			//showDebug("\n");
		nodo.isFinal = true;
		return nodo.isFinal;
	}
    
    public boolean buscaPalindromas()
    {
        boolean achou = false;
        int maiorTamanho = 0;
        
        //int b =0;
        LinkedList<String> listaDeMaiores = new LinkedList<String>();
        System.out.printf("tamnho: %s\n",palavraCharInvertida.length);
        for(int a = 0; a <= palavraCharInvertida.length ; a++ )
        {
            for(int b = a; b <= palavraCharInvertida.length; b++)
            {
                System.out.printf("%s\n", Arrays.toString( Arrays.copyOfRange( palavraCharInvertida, a, b ) ) );
                achou = buscarString( Arrays.copyOfRange(palavraCharInvertida, a, b) );       
                if( achou )
                {
                    System.out.printf("%s -  achou \n", Arrays.toString( Arrays.copyOfRange( palavraCharInvertida, a, b ) ) );
                    if( isPalindroma( Arrays.copyOfRange( palavraCharInvertida, a,b ) ) )
                    {
                        System.out.printf("%s -  achou - Palindroma \n", Arrays.toString( Arrays.copyOfRange( palavraCharInvertida, a, b ) ) );
                        if( b-a >= maiorTamanho)
                        {
                            if( b-a == maiorTamanho)
                            {
                                System.out.printf("%s -  achou - Palindroma - do mesmo tamanho\n", Arrays.toString( Arrays.copyOfRange( palavraCharInvertida, a, b ) ) );
                                listaDeMaiores.add(Arrays.copyOfRange( palavraCharInvertida, a,b ).toString());
                            }
                            else
                            {
                                System.out.printf("%s -  achou - Palindroma - maior\n", Arrays.toString( Arrays.copyOfRange( palavraCharInvertida, a, b ) ) );
                                listaDeMaiores.clear();
                                maiorTamanho = b-a;
                                listaDeMaiores.add(Arrays.toString(Arrays.copyOfRange( palavraCharInvertida, a,b)));
                            }
                        }
                    }
                }
            }
          
        }
        while(!listaDeMaiores.isEmpty())
        {
            String lista = listaDeMaiores.remove();
            System.out.printf("%s",lista );
        }
        return true;
    }
    
    public boolean isPalindroma(char[] palavra)
    {  
      if(palavra.length ==2)
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
    
    public static void main(String args[])
    {
        ArvoreDeSufixo arvSufixo = new ArvoreDeSufixo();
        arvSufixo.iniciar();
    }
    
    @Override
    public void finalizar()
    {
        super.finalizar();
        System.gc();
        System.exit(0);
    }
    
}
