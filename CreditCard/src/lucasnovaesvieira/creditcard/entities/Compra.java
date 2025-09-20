package lucasnovaesvieira.creditcard.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Compra implements Comparable <Compra> {
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private String nome;
	private double valor;
	private LocalDate data;	
	
	private int nroTransacao;
	private static int contador = 1;
	
	
	protected Compra(String nome, double valor) {
		this.nome = nome;
		this.valor = valor;
		this.data = LocalDate.now();
		this.nroTransacao = gerarNumeroTransacao();
	}


	protected String getNome() {
		return nome;
	}


	protected void setNome(String nome) {
		this.nome = nome;
	}


	protected double getValor() {
		return valor;
	}


	protected void setValor(double valor) {
		this.valor = valor;
	}


	protected String getData() {
		return data.format(formatter);
	}
		
	
	protected int getNroTransacao() {
		return nroTransacao;
	}


	private int gerarNumeroTransacao() {
		
		return contador++;
		
	}


	@Override
	public int compareTo(Compra outraCompra) {

		return Double.valueOf(this.valor).compareTo(outraCompra.valor);
		
	}
	
}
