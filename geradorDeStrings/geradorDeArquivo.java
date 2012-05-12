/**
 * Para maiores informações:
 * @autor   Glauco Roberto Munsberg dos Santos
 * @github  git@github.com:glaucomunsberg/try_a_trie.git
 * @version 0.9.9
 */
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;

public class geradorDeArquivo 
{
	private Formatter saida;
	
	/** geradorDeArquivos
	 * 		Método responsável pela abertura do arquivo
	 * @param String arg{nome do arquivo}
	 */
	public geradorDeArquivo(String arg)
	{
		try
		{
			if( arg == null)
			{
				saida = new Formatter("Cap14/arquivoDeTexto.txt");
			}
			else
			{
				saida = new Formatter(arg);
			}
		}
		catch( SecurityException securityException)
		{
		}
		catch( FileNotFoundException fileNotFoundException)
		{
		}
	}
	
	/** addRecords
	 *  faz a leitura e a escrita no arquivo aberto
	 *  	pelo método openFile
	 */
	public void adicionar( String mensagem)
	{
		try
		{
			if(mensagem != null)
			{
				saida.format("%s", mensagem);
				saida.flush();
			}
		}
		catch( FormatterClosedException formatterClosedException)
		{
		}
		catch( NoSuchElementException element)
		{
		}
	}
	
	/**
	 * closeFile
	 * 	fecha o arquivo aberto pelo
	 * 		método openFile
	 */
	public void fechar()
	{
		if(saida != null)
		{
			saida.close();
		}
	}

}
