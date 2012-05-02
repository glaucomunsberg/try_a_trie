/**
 * Classe do objeto Nodo da árvore trie
 * 	Boolean isFinal - tem uma referência para se da raiz até a sua posicação é uma string.
 * 	Nodo nodos[] - Um array de Nodos que representando cada uma das posições do alfabeto
 *  int numeroDePrefixo - Conta quantos prefixos existem nele
 * @author glaucomunsberg@gmail.com
 *
 */

public class Nodo {
	protected boolean isFinal;
	protected Nodo nodos[];
	protected int numeroDePrefixo;
	protected Nodo prev;
	
	public Nodo()
	{
		isFinal = false;
		nodos = new Nodo[26];
		numeroDePrefixo = 0;
		prev = null;
	}
        
	protected void finalize()
	{
                isFinal = false;
	}

}
