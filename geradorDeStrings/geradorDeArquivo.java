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
			System.err.println("você não tem acesso de escrita");
			System.exit(1);
		}
		catch( FileNotFoundException fileNotFoundException)
		{
			System.err.println("erro ao criar arquivo");
			System.exit(1);
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
			System.err.println("Erro ao escrever");
		}
		catch( NoSuchElementException element)
		{
			System.err.println("Invalida entrada!");
		}
	}
	
	/**
	 * closeFile
	 * 	fecha o arquivo aberto pelo
	 * 		método openFile
	 */
	public void closeFile()
	{
		if(saida != null)
		{
			saida.close();
		}
	}

}
