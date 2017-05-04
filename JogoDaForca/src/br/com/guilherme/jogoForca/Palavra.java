package br.com.guilherme.jogoForca;

public class Palavra {
	private String palavra;
	private boolean[] letras;
	
	public Palavra(String palavra) {
		this.palavra = palavra;
		this.letras = new boolean[this.palavra.length()]; // As posi��es do array j� iniciam como "false" por default
	}
	
	public String getPalavra() {
		return this.palavra;
	}
	
	public void setPalavra(String p) {
		this.palavra = p;
	}
	
	public boolean[] getLetras() {
		return this.letras;
	}
	
	public boolean getLetras(int i) { // Este metodo possui o mesmo nome do cima, mas n�o gera erro!!
		return this.letras[i];        // Isso acontece porque eles s�o difirentes em "implementa��o", pois esse recebe como par�metro um inteiro.
	}
	
	public void setLetras(boolean[] vet) {
		this.letras = vet;
	}
	
	public void setLetras(int i) { // Este metodo possui o mesmo nome do cima, mas n�o gera erro!!
		this.letras[i] = true;     // Isso acontece porque eles s�o difirentes em "implementa��o", pois esse recebe como par�metro um inteiro.
	}
}
