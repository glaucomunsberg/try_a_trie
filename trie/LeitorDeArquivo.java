/**
 * Classe LeitorDeArquivo retirada do projeto Javeco que lê o arquivo informado
 * 	String abrir    - Recebe o nome do arquivo que será aberto
 *  Scanner entrada - Entrada do arquivo
 * @author glaucomunsberg@gmail.com
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LeitorDeArquivo
{
	private String abrir;						//Recebe o endereço do arquivo
	private Scanner entrada;					//Scanner para a leitura do arquivo
	
	/**
	 * faz a abertura do programa
	 * @param args
	 */
	public LeitorDeArquivo(String args)
	{
		if( args == null)
		{
			abrir = "entrada.txt";
		}
		else
		{
			abrir = args;
		}
		try
		{
			entrada = new Scanner( new File(abrir));
		}
		catch(FileNotFoundException aaaa)
		{
			
		}
		
	}
	
	/**
	 * faz a leitura da próxima linha até que não
	 * 	aja mais elementos então retorna null
	 * @return
	 */
	public String lerLinha()
	{
		if( entrada.hasNext() )
		{
			return entrada.nextLine();
		}
		else
		{
			return null;
		}
		
	}
	
	public void fechar()
	{
		if( entrada != null )
		{
			entrada.close();
		}
	}

}
