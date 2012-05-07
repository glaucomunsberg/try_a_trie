/**
 * Classe do objeto àrvore de sufixo
 * 
 *  @author glaucomunsberg@gmail.com
 */
import java.util.Arrays;
import java.util.Scanner;

public class ArvoreDeSufixo
{
    protected ArvoreTrie raiz1;
    protected ArvoreTrie raiz2;
    protected Scanner leitor;
    protected char[] palavraChar,palavraCharInvertida ;
    
    public ArvoreDeSufixo()
    {
        raiz1 = new ArvoreTrie();
        raiz2 = new ArvoreTrie();
        leitor = new Scanner(System.in);
    }
    
    public static void main(String args[])
    {
        ArvoreDeSufixo arvSufixo = new ArvoreDeSufixo();
        arvSufixo.iniciar();
    }
    
    public void iniciar()
    {
        StringBuilder stringPalavra = new StringBuilder(leitor.nextLine());
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
            boolean retornoRaiz1, retornoRaiz2;
            for(int a=0; a < palavraChar.length; a++)
            {
                retornoRaiz1 = raiz1.inserirString( Arrays.copyOfRange(palavraChar, a, palavraChar.length));
                retornoRaiz2 = raiz2.inserirString( Arrays.copyOfRange(palavraCharInvertida, a, palavraChar.length));
            }
        } 
        stringPalavra.delete(0, stringPalavra.length());
    }
    
    public boolean busca(char[] palavra)
    {
        return true;
    }
    
    public void finalizar()
    {
        raiz1 = null;
        raiz2 = null;
        System.gc();
        System.exit(0);
    }
    
}
