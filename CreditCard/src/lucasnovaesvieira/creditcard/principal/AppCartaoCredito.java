package lucasnovaesvieira.creditcard.principal;

import java.util.Locale;
import java.util.Scanner;

import lucasnovaesvieira.creditcard.entities.Pessoa;

public class AppCartaoCredito {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		int escolha = 1;

		while (escolha != 2) {

			System.out.println("Bem Vindo! Vamos criar seu cartão de Crédito? (1-SIM | 2-NÃO)");
			escolha = sc.nextInt();

			switch (escolha) {

			case 1:

				System.out.print("Digite seu nome: ");
				String nome = sc.next();
				System.out.print("Digite seu CPF: ");
				String cpf = sc.next();

				Pessoa usuario = new Pessoa(nome, cpf);

				usuario.getCartao().consultarLimite();

				loop: while (escolha != 4) {

					System.out.println("Escolha as opções:");
					System.out.println("1 - Consultar Limite");
					System.out.println("2 - Consultar Compras");
					System.out.println("3 - Realizar Compras");
					System.out.println("4 - Finalizar Programa");

					escolha = sc.nextInt();

					switch (escolha) {

					case 1:

						usuario.getCartao().consultarLimite();
						break;

					case 2:

						usuario.getCartao().consultarCompras();
						break;

					case 3:

						System.out.print("Digite o nome da compra: ");
						String nomeCompra = sc.next();
						System.out.print("Digite o valor da compra: ");
						double valorCompra = sc.nextDouble();

						usuario.getCartao().efetuarCompra(nomeCompra, valorCompra);

						break;

					case 4:
						escolha = 2;
						break loop;

					default:
						System.out.println("*****OPÇÃO INVÁLIDA*****");

					}
				}

			case 2:

				System.out.println("*****PROGRAMA FINALIZADO*****");

				break;

			default:
				System.out.println("*****OPÇÃO INVÁLIDA*****");

			}

		}

		sc.close();

	}

}
