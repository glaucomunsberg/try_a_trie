/**
 * Classe do objeto Nodo da árvore trie
 * 	Boolean isFinal - tem uma referência para se da raiz até a sua posicação é uma string.
 * 	Nodo nodos[] - Um array de Nodos que representando cada uma das posições do alfabeto
 *  int numeroDePrefixo - Conta quantos prefixos existem nele
 * @author glaucomunsberg@gmail.com
 *
 */

public class Nodo {
	protected boolean isFinal = false;
	protected Nodo nodos[];
	protected int numeroDePrefixo;
	
	public Nodo()
	{
		nodos = new Nodo[26];
	}

}
