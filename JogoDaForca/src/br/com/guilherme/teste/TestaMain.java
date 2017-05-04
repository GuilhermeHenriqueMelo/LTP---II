package br.com.guilherme.teste;

import java.util.Scanner;

import br.com.guilherme.jogoForca.JogoDaForca;
import br.com.guilherme.jogoForca.Palavra;

public class TestaMain {
	
	public static void limpaTela() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
                           "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
		                   "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira uma palavra para ser adivinhada: ");
		String palavra = scan.nextLine();
		
		System.out.println("Coloque uma dica para a palavra escolhida: ");
		String dica = scan.nextLine();
		limpaTela();
		
		
		
		
		Palavra p = new Palavra(palavra);
		JogoDaForca jogo = new JogoDaForca(p, dica);
		
		jogo.bemVindo();
		
		jogo.preparaPartida();
		jogo.jogar();
		
		jogo.fimDeJogo();
		scan.close();
		System.out.println("FIM!");
	}
}
