/**
 * Classe responsável pela geração de dois arquivos
 * 	que serão usado como teste. O primeiro arquivo
 * 	"entrada.txt" é composto por uma ação e uma palavra
 * 	em cada linha que serão usados pelo programa de árvore B;
 * 	"saida.txt" contém apenas V ou F, de acordo com o que
 * 	se espera de saida do programa segundo aquela palavra
 * 	no programa da árvore B
 * @author glaucomunsberg@gmail.com
 *
 */

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class geradorDeString
{
	private Random random;						//Responsável pela aleatoriedade
	private ArrayList<String> listDePalavras;	//ArrayList "array" que conterá as palavras
	private int numeroDePalavras;				//O numero de palavras que será gerado em entrada.txt e resultados em saida.txt
	private int maiorNumeroDeLetras;			//O maior número de letras que pode haver em cada palavra
	private int tipodeOperacao;					//Tipo da operacao que está sendo realizada como Inserir, Remover e Buscar
	private int tipoDeString;					//Tipo de string por "palavras" ou "DNA"
	private boolean isTamanhoFixo;
        private geradorDeArquivo saida, entrada;	//Classe que insere na saida.txt e entrada.txt
	
	
	public static void main(String args[])
	{
		geradorDeString gerador = new geradorDeString();
		gerador.começarProcesso();
		gerador.finalizarProcesso();
		
	}

	/**
	 * Construtor da classe
	 */
	public geradorDeString()
	{
		random = new Random();
		listDePalavras = new ArrayList<String>();
		saida = new geradorDeArquivo("saida_desejada.txt");
		entrada = new geradorDeArquivo("entrada.txt");

	}
	
	/**
	 * Começa a fazer o a coleta dos dados e processamento
	 */
	public void começarProcesso(){
		numeroDePalavras = Integer.parseInt( JOptionPane.showInputDialog(null, "Quantas Strings você quer gerar?!","Gerador Random", JOptionPane.PLAIN_MESSAGE));
                maiorNumeroDeLetras = Integer.parseInt( JOptionPane.showInputDialog(null, "O programa gerará \'palavras\' de quantas letras?!","Gerador Random", JOptionPane.PLAIN_MESSAGE));
                int tamanhoFixo = Integer.parseInt( JOptionPane.showInputDialog(null, " tamanho das palavras geradas são de tamanho fixo?\n1.Sim\n2.Não","Tamanho das palavras", JOptionPane.PLAIN_MESSAGE));
		if( tamanhoFixo == 1)
                {
                    isTamanhoFixo = true;
                }
                else
                {
                    isTamanhoFixo = false;
                }
                tipoDeString = Integer.parseInt( JOptionPane.showInputDialog(null, "Qual tipo de palavra deseja imprimir?\n1.Palavras com 26 caracteres\n2.Strings de DNA","Tipos de palavras", JOptionPane.PLAIN_MESSAGE));
		System.out.printf("\nTecnologia: Processador Recursivo Corp.\nAguarde enquanto nossos melhores macacos sorteiam as letras...\n");
		geradorDeTiposDeAcoes();
		
	}
	
	/**
	 * Método para finalização e exibição do resultado
	 */
	public void finalizarProcesso()
	{
                entrada.adicionar("@\n");
		entrada.fechar();
		saida.fechar();
		JOptionPane.showMessageDialog(null, String.format("Foram gerado os arquivos entrada.txt e saida_desejada.txt\n Total de palavras geradas: %d\nPalavras ao final da execução: %d",this.numeroDePalavras, this.listDePalavras.size()),"Aleatoriedade não tão aleatória assim", JOptionPane.PLAIN_MESSAGE);
	}
	/**
	 * Gerador de tipos de ações dos tipos:
	 * 	I = Inserir
	 * 	B = Busca
	 * 	R = Remove
	 * O método gera aleatóriamente estas ações
	 * 	e chama o método geradorDePalavras para
	 * 	gerar uma a palavra e então uni-las em uma ação
	 */
	private void geradorDeTiposDeAcoes()
	{
		String acao;
		for(int a=0; a < numeroDePalavras; a++)
		{
			acao = null;
			tipodeOperacao = random.nextInt(3);
			switch(tipodeOperacao)
			{
				case 0:
						acao = "i ";
						break;
				case 1:
						acao = "b ";
						break;
				case 2:
						acao = "r ";
						break;
				default:
						break;
			}
			if(this.tipoDeString == 1)
			{
				gerarDePalavras(acao);
			}
			else
			{
				geradorDeDNA(acao);
			}
			
		}
	}

	/**
	 * Gerador de Palavras aleatórias de tamanho
	 * 	que varia de 1 até o tamanho indicado pelo
	 * 	usuário. Recebe como parametro a primeira
	 * 	parte (comando)
	 * @param String palavra
	 */
	private void gerarDePalavras(String acao)
	{

		
		int valor;														//valor em int de um char que gerará
		int tamanhoDaPalavraAtual = 0;									//Tamanho de palavra
		StringBuilder stringTemp = new StringBuilder(); 				//String temporárioa
		/**
		 * Diz qual será o tamanho da palavra
		 */
		while(tamanhoDaPalavraAtual == 0)
		{
                    if( this.isTamanhoFixo )
                        tamanhoDaPalavraAtual = this.maiorNumeroDeLetras;
                    else
			tamanhoDaPalavraAtual = random.nextInt(maiorNumeroDeLetras+1);
		}

		/**
		 * segundo o tamanho da palavra ele gerará cada
		 * 	um dos caractéres para formá-la
		 */
		for(int a=0; a < tamanhoDaPalavraAtual; a++)
		{
			valor = 97 + random.nextInt(122 - 97);
			stringTemp.append( (char)valor);
		}

		
		/**
		 *  Soma o comando mais a palavra gerada
		 */
		listDePalavras.add( String.format("%s%s\n", acao,stringTemp.toString()) );
        gravarNoArquivoDeEntradas(String.format("%s%s\n", acao,stringTemp.toString()));
        geradorDeResultado(acao, stringTemp.toString());
	}
	
	/**
	 * Gerador de bases do DNA aleatórias de tamanho
	 * 	que varia de 1 até o tamanho indicado pelo
	 * 	usuário. Recebe como parametro a primeira
	 * 	parte (comando)
	 * @param String palavra
	 */
	private void geradorDeDNA(String acao)
	{
		StringBuilder stringTemp = new StringBuilder(); 				//String temporárioa
		int valor;														//valor em int de um char que gerará
		int tamanhoDaPalavraAtual = 0;									//Tamanho de palavra

		/**
		 * Diz qual será o tamanho da palavra
		 */
		while(tamanhoDaPalavraAtual == 0)
		{
                    if( this.isTamanhoFixo )
                        tamanhoDaPalavraAtual = this.maiorNumeroDeLetras;
                    else
			tamanhoDaPalavraAtual = random.nextInt(maiorNumeroDeLetras+1);
		}

		/**
		 * segundo o tamanho da palavra ele gerará cada
		 * 	um dos caractéres para formá-la
		 */
		for(int a=0; a < tamanhoDaPalavraAtual; a++)
		{
			valor = random.nextInt(4);
			switch(valor)
			{
				case 0:
					stringTemp.append("t");
					break;
				case 1:
					stringTemp.append("g");
					break;
				case 2:
					stringTemp.append("c");
					break;
				case 3:
					stringTemp.append("a");
					break;
				default:
					stringTemp.append("?");
			}
			
		}

		
		/**
		 *  Soma o comando mais a palavra gerada
		 */
		listDePalavras.add( String.format("%s%s\n", acao,stringTemp.toString()) );
        gravarNoArquivoDeEntradas(String.format("%s%s\n", acao,stringTemp.toString()));
        geradorDeResultado(acao, stringTemp.toString());
		
	}

	/**
	 * Segundo as palavras geradas e a ação que gerou para essa palavra
	 * então ele deve gerar um resultado verdadeiro ou falas segundo a
	 * simulação dessa ação na árvore tre
	 */
	private void geradorDeResultado(String acao, String palavra)
	{
		if( acao == "i ")
		{
			gravarNoArquivoDeResultado(podeIserir(palavra));
		}
		else
		{
			if(acao == "b ")
			{
				gravarNoArquivoDeResultado(podeBuscar(palavra));
			}
			else
			{
				if( acao == "r ")
				{
					gravarNoArquivoDeResultado(podeRemover(palavra));
				}
			}
		}
	}

	/**
	 *  Recebe uma string que é composta pela acao seguida por
	 *  uma palavra que será gravada no arquivo entrada.txt
	 *  @param String linha{"acao"+" "+"palavra"}
	 */
	private void gravarNoArquivoDeEntradas(String linha)
	{
		entrada.adicionar(linha);
	}

	/**
	 * Recebe um valor boolean que será gravado dentro
	 * de um arquivo saida.txt com valores
	 * boolean{ true - V | false - F}
	 * @param boolean saida
	 */
	private void gravarNoArquivoDeResultado(boolean valorSaida)
	{
		if(valorSaida == true)
		{
			saida.adicionar("v\n");
		}
		else
		{
			saida.adicionar("f\n");
		}
	}
	
	/**
	 * Método dedicado para verificar no ArrayList
	 * 	se o elemento pode ser inserido. Em todos os
	 * 	casos ele retornará true, se ele encontrar já inserido
	 * 	então adicionará um ao contador de repetição
	 * @param String palavra
	 * @return Boolean { true }
	 */
	public boolean podeIserir(String palavra)
	{
		if(listDePalavras.contains(palavra) )
		{
			return true;
		}
		else
		{
			listDePalavras.add(palavra);
			return true;
		}
	}
	
	/**
	 * Método que verifica se já existe no arrayList
	 * 	a palavra, se ele retornar que existe então
	 * 	ele pode remover, caso não encontre retorna false
	 * @param String palavra
	 * @return Boolean{true - Pode remover | false - Não encontrou }
	 */
	public boolean podeRemover(String palavra)
	{
		int posicao = listDePalavras.lastIndexOf(palavra);
		if(posicao != -1)
		{
			listDePalavras.remove(posicao);
			System.out.printf("%d\n", posicao);
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * análogo ao Método podeRemover, verifica se o valor
	 * 	está inserido, porém não o remove. mas retorna true
	 * 	se o encontrou ou false se não encontrou
	 * @param palavra
	 * @return
	 */
	public boolean podeBuscar(String palavra)
	{
		int posicao = listDePalavras.lastIndexOf(palavra);
		if(posicao != -1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}