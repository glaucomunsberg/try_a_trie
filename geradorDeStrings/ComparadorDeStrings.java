/**
 * O comparador recebe dois arquivos
 * 	e os compara se achar diferença a ponta a linha
 * 	e a diferença
 * @author glaucoroberto
 *
 */
public class ComparadorDeStrings 
{
	protected LeitorDeArquivo arquivo1;
	protected LeitorDeArquivo arquivo2;
	
	public ComparadorDeStrings()
	{
		arquivo1 = new LeitorDeArquivo("saida.txt");
		arquivo2 = new LeitorDeArquivo("saida_desejada.txt");
	}
	
	public void iniciar()
	{
		String doArquivo1;
		String doArquivo2;
		int linha=0;
		doArquivo1 = arquivo1.lerLinha();
		doArquivo2 = arquivo2.lerLinha();
		while( doArquivo1 != null && doArquivo2 != null)
		{
			linha++;
			if( !doArquivo1.equals(doArquivo2) )
			{
				System.out.printf("São Diferentes na linha %d\nArquivo 1 - %s\nArquivo 2 - %s\n\n", linha, doArquivo1, doArquivo2);
			}
			doArquivo1 = arquivo1.lerLinha();
			doArquivo2 = arquivo2.lerLinha();
		}
		if(linha == 0)
		{
			if( arquivo1 == null)
			{
				System.out.printf("Arquivo1 faltando");
			}
			if( arquivo2 == null)
			{
				System.out.printf("Arquivo2 faltando");
			}
		}
		this.finalizar();
	}
	public void finalizar()
	{
		arquivo2.fechar();
		arquivo1.fechar();
	}
	
	public static void main(String args[])
	{
		ComparadorDeStrings comparador = new ComparadorDeStrings();
		comparador.iniciar();
	}
	
}
