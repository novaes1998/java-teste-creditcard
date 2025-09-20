package lucasnovaesvieira.creditcard.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Cartao {

	private Pessoa titular;
	protected String numero;
	private double limite;
	private List<Compra> compras;
	protected double totalValorDeCompras;
	protected int totalTransacoes;

	public Cartao(Pessoa titular) {
		this.titular = titular;
		this.numero = gerarNumero();
		this.limite = gerarLimite();
		this.compras = new ArrayList<>();
		this.totalTransacoes = 0;
		this.totalValorDeCompras = 0.0;
	}

	protected void setTitular(Pessoa titular) {
		this.titular = titular;
	}

	public void consultarLimite() {
		System.out.println("-".repeat(50));
		System.out.printf("Olá %s, seu limite atual é de: R$%.2f %n", titular.getNome(), limite);
		System.out.println("-".repeat(50));
		System.out.println();
	}

	protected String gerarNumero() {

		Random random = new Random();
		long gerarNumeroCartao = random.nextLong(9999999999999999L);
		return String.valueOf(gerarNumeroCartao);

	}

	protected double gerarLimite() {

		int nonoDigitoCPF = Integer.parseInt(String.valueOf(titular.getCpf().charAt(8)));
		switch (nonoDigitoCPF) {
		case 1: {
			return 1000.00;
		}
		case 2: {
			return 1500.00;
		}
		case 3: {
			return 2000.00;
		}
		case 4: {
			return 2500.00;
		}
		case 5: {
			return (3000.00);
		}
		case 6: {
			return (3500.00);
		}
		case 7: {
			return 4000.00;
		}
		case 8: {
			return 4500.00;
		}
		case 9: {
			return 5000.00;
		}
		case 0: {
			return 500.00;
		}
		default:
			return 0;
		}
	}

	public void efetuarCompra(String nome, double valor) {

		if (limite >= valor) {

			Compra compra = new Compra(nome, valor);
			compras.add(compra);
			totalValorDeCompras += valor;
			limite -= valor;
			totalTransacoes++;

			System.out.println("-".repeat(50));
			System.out.println("*****COMPRA REALIZADA*****");
			System.out.printf("Valor da compra: R$%.2f %n", valor);
			System.out.printf("Limite atual: R$%.2f %n", limite);
			System.out.println("-".repeat(50));
			System.out.println();
		} else {
			System.out.println("-".repeat(50));
			System.out.println("*****LIMITE INSUFICIENTE*****");
			System.out.printf("Valor da compra R$%.2f é maior que o seu limite! %n", valor);
			System.out.printf("Limite atual: R$%.2f %n", limite);
			System.out.println("-".repeat(50));
			System.out.println();
		}

	}

	public void consultarCompras() {

		System.out.println("-".repeat(50));

		if (compras.size() == 0) {

			System.out.println("***** NÃO EXISTE COMPRAS REALIZADAS*****");
			System.out.println("-".repeat(50));
			System.out.println();

		} else {

			System.out.println("*****COMPRAS REALIZADAS*****");
			
			Collections.sort(compras);
			
			for (Compra c : compras) {

				System.out.printf("%s - %s (R$%.2f), nroº Transação (%d)\n", c.getData(), c.getNome(), c.getValor(),
						c.getNroTransacao());
			}

			System.out.println("-".repeat(50));
			System.out.println();

		}

	}

}
