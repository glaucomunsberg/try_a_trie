import java.util.Arrays;
public class ArvoreTrie 
{
	Nodo raiz;
	LeitorDeArquivo lerArquivo;
	
	public static void main(String args[] )
	{
		ArvoreTrie trie = new ArvoreTrie();
		trie.iniciar();
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
		while( stringLida != null )
		{
			System.out.printf("%s", stringLida);
			stringAcaoPalavra.append(stringLida);
			if( stringAcaoPalavra.substring(0, 1) == "I ")
			{
				inserirString( stringAcaoPalavra.substring(2) );
			}
			else
			{
				if(stringAcaoPalavra.substring(0, 1) == "B ")
				{
					buscarString( stringAcaoPalavra.substring( 2 ) );
				}
				else
				{
					if( stringAcaoPalavra.substring(0, 1) == "R ")
					{
						removerString( stringAcaoPalavra.substring( 2 ) );
					}
				}
			}
		}
		
	}
	
	protected boolean inserirString(String string)
	{
		System.out.printf("Inserir\n");
		return true;
	}
	
	public boolean removerString(String string)
	{
		System.out.printf("Remover\n");
		return true;
	}
	
	public boolean buscarString(String string)
	{
		System.out.printf("Buscar\n");
		return true;
	}
	
	public void geradorDeSaida(boolean saida)
	{
		
	}
}
