package br.com.guilherme.jogoForca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class JogoDaForca {
	private Palavra palavra;
	private String dica;
	private char[] alfabeto; // Esse atributo será preenchido com as letras digitadas pelo usuário!
	private char caracter;
	private List<Integer> pos;  // Uma lista funciona "quase" como um array, porém, ela não se "importa" com o tamanho!
	private short tamanhoDaPalavra;
	
	private Scanner scan = new Scanner(System.in);
	
	private static short CONTADOR = 0; // Essa constante serve para acabar com o loop do método jogar() !!
	private static short POSICAO_LETRAS_USADAS = 0; // Essa constante serve para identificar a posição em que deve ser guardada o caracter digitado pelo usuário!!
	
	
	public JogoDaForca(Palavra p, String dica) {
		this.palavra = p;
		this.dica = dica;
		this.caracter = ' ';
		this.tamanhoDaPalavra = (short) this.palavra.getPalavra().length();
		this.alfabeto = new char[27]; // O atributo alfabeto possuirá 27 de tamanho para que ele aceite o caracter "ç"!
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

	
	public void preparaPartida() { // Esse método inicializa algumas variáveis para que o jogo inicie sem problemas!
		CONTADOR = 0;
		POSICAO_LETRAS_USADAS = 0;
		this.palavra.setPalavra(this.palavra.getPalavra().toLowerCase());
		
		for(short i = 0; i < this.alfabeto.length; i++) {
			this.alfabeto[i] = '-';
		}
		System.out.println(this.dica + "\n\n");
	}
	
	public String bemVindo() {
		System.out.println("Bem-vindo ao jogo da forca!");
		System.out.println("Voce deve digitar caracteres ate completar a palavra!");
		System.out.println("Nao existe limite de tentativas!! Boa sorte!");
	}
	
	public void preencheResposta() {
		this.pos = posDoCaracterNaPalavra(this.caracter);
		
		if (!this.pos.isEmpty()) {
			for(int pos : this.pos) {
				this.palavra.setLetras(pos);   // Esse método define, na(s) posição(ões) exata do array de booleano, o valor "true" caso o caracter exista!! 
			}
		CONTADOR+= this.pos.size(); // Essa linha de código possui essa lógica pois, caso a palavra tenha o caracter repetidas vezes (Exemplo: Arara), o caracter será
		}                           // contabilizado corretamente!!
	}
	
	public void leituraDoCaracter() {
		short ok = 0;
		System.out.print("Caracter: ");
		String aux = scan.nextLine();
			do{ // O laço de repetição impede que um caracter já digitado seja aceito novamente!!
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
	
	public List<Integer> posDoCaracterNaPalavra(char c) { // Esse método retorna a(s) posição(ões) em que o caracter aparece.
		c = botaCaracterEmLowerCase(c);                   // Caso o caracter não exista na palavra a lista ficará vazia!!
		
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
				System.out.print(palavra[i]);   // Caso a posiãço do array esteja com o valor "true", o caracter da palavra será impresso!! 
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
		
		if (CONTADOR != this.tamanhoDaPalavra) {  // O que acontece aqui é que, literalmente, enquanto o jogador não completar a palavra o jogo não termina! 
			jogar();                              // Estou usando de recursividade ao invés de um loop, já que o mesmo consome recursos do sistema e tempo de execução!!
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
