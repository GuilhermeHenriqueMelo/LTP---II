package br.com.guilherme.jogoForca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class JogoDaForca {
	private Palavra palavra;
	private String dica;
	private char[] alfabeto; // Esse atributo ser� preenchido com as letras digitadas pelo usu�rio!
	private char caracter;
	private List<Integer> pos;  // Uma lista funciona "quase" como um array, por�m, ela n�o se "importa" com o tamanho!
	private short tamanhoDaPalavra;
	
	private Scanner scan = new Scanner(System.in);
	
	private static short CONTADOR = 0; // Essa constante serve para acabar com o loop do m�todo jogar() !!
	private static short POSICAO_LETRAS_USADAS = 0; // Essa constante serve para identificar a posi��o em que deve ser guardada o caracter digitado pelo usu�rio!!
	
	
	public JogoDaForca(Palavra p, String dica) {
		this.palavra = p;
		this.dica = dica;
		this.caracter = ' ';
		this.tamanhoDaPalavra = (short) this.palavra.getPalavra().length();
		this.alfabeto = new char[28]; // O atributo alfabeto possuir� 28 de tamanho para que ele aceite o caracter "ç" e o "espaço"!
	}
	
	public Palavra getPalavra() {
		return palavra;
	}

	public void setPalavra(Palavra palavra) {
		this.palavra = palavra;
	}

	public String getDica() {
		return dica;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}

	
	public void preparaPartida() { // Esse m�todo inicializa algumas vari�veis para que o jogo inicie sem problemas!
		CONTADOR = 0;
		POSICAO_LETRAS_USADAS = 0;
		this.palavra.setPalavra(this.palavra.getPalavra().toLowerCase());
		
		for(short i = 0; i < this.alfabeto.length; i++) {
			this.alfabeto[i] = '-';
		}
		System.out.println(this.dica + "\n\n");
	}
	
	public String bemVindo() {
		return "Bem-vindo ao jogo da forca!" + 
	           "Voce deve digitar caracteres ate completar a palavra!" + 
			   "Nao existe limite de tentativas!! Boa sorte!";
	}
	
	public void preencheResposta() {
		this.pos = posDoCaracterNaPalavra(this.caracter);
		
		if (!this.pos.isEmpty()) {
			for(int pos : this.pos) {
				this.palavra.setLetras(pos);   // Esse m�todo define, na(s) posi��o(�es) exata do array de booleano, o valor "true" caso o caracter exista!! 
			}
		CONTADOR+= this.pos.size(); // Essa linha de c�digo possui essa l�gica pois, caso a palavra tenha o caracter repetidas vezes (Exemplo: Arara), o caracter ser�
		}                           // contabilizado corretamente!!
	}
	
	public void leituraDoCaracter() {
		short ok = 0;
		System.out.print("Caracter: ");
		String aux = scan.nextLine();
			do{ // O la�o de repeti��o impede que um caracter j� digitado seja aceito novamente!!
				this.caracter= aux.charAt(0);
				if (!isCaracterJaUtilizado(this.caracter)) {
					ok = 1;
				} else {
					System.out.println("\nO caracter ja foi digitado! Digite Novamente!\nCaracter: ");
					aux = "";
					aux = scan.nextLine();
				}
			} while(ok == 0);
		
		preencheResposta();
		System.out.println("\n\n\n");
	}
	
	public boolean isCaracterJaUtilizado(char c) {
		c = botaCaracterEmLowerCase(c);
		
		for(short i = 0; i < this.tamanhoDaPalavra; i++) {
			if (c == this.alfabeto[i]) {
				return true;
			}
		}
		return false;
	}
	
	public List<Integer> posDoCaracterNaPalavra(char c) { // Esse m�todo retorna a(s) posi��o(�es) em que o caracter aparece.
		c = botaCaracterEmLowerCase(c);                   // Caso o caracter n�o exista na palavra a lista ficar� vazia!!
		
		char[] palavra = this.palavra.getPalavra().toCharArray();
		List<Integer> lista = new ArrayList<>();
		
		for(int i = 0; i < this.tamanhoDaPalavra; i++) {
			if (c == palavra[i]) {
				lista.add(i);
			}
		}
		return lista;
	}
	
	public void imprimeResposta() {
		char[] palavra = this.palavra.getPalavra().toCharArray();
		for(short i = 0; i < this.tamanhoDaPalavra; i++) {
			if (this.palavra.getLetras(i)) {    // Na classe Palavra, exite um array de booleano com o tamanho da palavra.
				System.out.print(palavra[i]);   // Caso a posi��o do array esteja com o valor "true", o caracter da palavra ser� impresso!! 
			} else {
				System.out.print("_");
			}
		}
		System.out.println("\n\n");
	}
	
	public void imprimeLetrasJaUsadas() {
		this.alfabeto[POSICAO_LETRAS_USADAS] = botaCaracterEmLowerCase(this.caracter);
		POSICAO_LETRAS_USADAS++;
		
		for(char c : this.alfabeto) {
			System.out.print(c + " ");
		}
		System.out.println("\n\n");
	}
	
	public char botaCaracterEmLowerCase(char c) {
		String aux = "" + c;
		aux = aux.toLowerCase();
		c = aux.charAt(0);
		
		return c;
	}
	
	public void jogar() {
		leituraDoCaracter();
		
		imprimeResposta();
		
		imprimeLetrasJaUsadas();
		
		if (CONTADOR != this.tamanhoDaPalavra) {  // O que acontece aqui � que, literalmente, enquanto o jogador n�o completar a palavra o jogo n�o termina! 
			jogar();                              // Estou usando de recursividade ao inv�s de um loop, j� que o mesmo consome recursos do sistema e tempo de execu��o!!
		}
	}
	
	public void fimDeJogo() {
		System.out.println("Parabens!!! Voce acertou a palavra!\n\n\n");
		System.out.println("(._.)");
	    System.out.println("<) )-  " + this.palavra.getPalavra());
		System.out.println(" / \\"); 
	}
	
	public void limpaTela() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
	                       "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
				           "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}
