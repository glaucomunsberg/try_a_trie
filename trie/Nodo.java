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
	
	public Nodo(int numDeNodos)
	{
		isFinal = false;
		nodos = new Nodo[numDeNodos];
		numeroDePrefixo = 0;
		prev = null;
	}   
        @Override
	protected void finalize()
	{
                isFinal = false;
	}

}
