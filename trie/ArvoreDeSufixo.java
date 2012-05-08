/**
 * Classe do objeto àrvore de sufixo
 * 
 *  @author glaucomunsberg@gmail.com
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class ArvoreDeSufixo
{
    protected ArvoreTrie raiz;
    protected Scanner leitor;
    protected StringBuilder stringPalavra;
    protected char[] palavraChar,palavraCharInvertida ;
    
    public ArvoreDeSufixo()
    {
        raiz = new ArvoreTrie();
        leitor = new Scanner(System.in);
    }
    
    public static void main(String args[])
    {
        ArvoreDeSufixo arvSufixo = new ArvoreDeSufixo();
        arvSufixo.iniciar();
    }
    
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
                retornoRaiz1 = raiz.inserirString( Arrays.copyOfRange(palavraChar, a, palavraChar.length));
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
    
    public boolean buscaPalindromas()
    {
        boolean achou = false;
        int maiorTamanho = 1;
        
        int b =0;
        LinkedList<String> listaDeMaiores = new LinkedList<String>();
        System.out.printf("tamnho: %s\n",palavraCharInvertida.length);
        for(int a = 0; a <= palavraCharInvertida.length ; a++ )
        {
            achou = raiz.buscarString( Arrays.copyOfRange(palavraCharInvertida, b, a) );       
            if( achou )
            {
                System.out.printf("Achou de %d a %d\n", b, a );
                if( isPalindroma( Arrays.copyOfRange( palavraCharInvertida, b, a) ) )
                {
                     System.out.printf("Achou de %d a %d - palindroma\n", b, a );
                   if( a-b >= maiorTamanho)
                   {
                       if( a-b == maiorTamanho)
                       {
                           System.out.printf("Achou de %d a %d - palindroma - do mesmo tamanho\n", b, a );
                           listaDeMaiores.add(Arrays.copyOfRange( palavraCharInvertida, b, a).toString());
                       }
                       else
                       {
                           System.out.printf("Achou de %d a %d - palindroma - tamanho tamanho\n", b, a );
                           listaDeMaiores.clear();
                           maiorTamanho++;
                           listaDeMaiores.add(Arrays.toString(Arrays.copyOfRange( palavraCharInvertida, b, a)));
                       }
                   }
                }
            }
            else
            {
                b++;
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
        StringBuilder palavraBuffer = new StringBuilder( palavra.toString() ); 
        if( palavraBuffer.equals( palavraBuffer.reverse() ) )
            return true;
        else
            return false;
    }
    
    public boolean busca(char[] palavra)
    {
        return true;
    }
    
    public void finalizar()
    {
        raiz = null;
        System.gc();
        System.exit(0);
    }
    
}
