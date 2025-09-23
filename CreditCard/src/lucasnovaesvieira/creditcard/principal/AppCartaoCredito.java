package lucasnovaesvieira.creditcard.principal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lucasnovaesvieira.creditcard.entities.Endereco;
import lucasnovaesvieira.creditcard.entities.Pessoa;
import lucasnovaesvieira.creditcard.util.ValidarCep;

public class AppCartaoCredito {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		int escolha = 1;

		loop1: while (escolha != 2) {

			System.out.println("Bem Vindo! Vamos criar seu cartão de Crédito? (1-SIM | 2-NÃO)");
			escolha = sc.nextInt();
			sc.nextLine();

			switch (escolha) {

			case 1:

				System.out.print("Digite seu nome: ");
				String nome = sc.nextLine();
				System.out.print("Digite seu CPF: ");
				//TODO: caso digite um CPF inválido, mandar repetir a validação
				String cpf = sc.nextLine();
				System.out.print("Digite o número do CEP: ");
				String cep = sc.nextLine();
				
				Endereco endereco = null;
				
				try {
					endereco = ValidarCep.verificarCep(cep);
					//TODO: caso o CEP retorne inválido, mandar repetir a validação ao invés de encerrar o programa (criar as classes throws)
				}catch(RuntimeException e) {
					System.out.println("Não consegui obter o endereço a parte deste CEP.");
					System.out.println("Encerrando aplicação.");
					break loop1;
				}
				
				
				//MOSTRANDO INFOS SOBRE O CEP:
				System.out.println("-".repeat(50));
				System.out.println(endereco.toStringParcial());
				System.out.println("-".repeat(50));
				
				//CRIANDO COMPLEMENTO ENDEREÇO
				System.out.print("Qual o número? ");
				String numero = sc.nextLine();
				System.out.print("Qual o complemento (Caso não exista digite 'n')? ");
				String complemento = sc.nextLine();
				
				endereco.complementarEndereco(numero, complemento);

				Pessoa usuario = new Pessoa(nome, cpf, endereco);

				usuario.getCartao().consultarLimite();

				loop2: while (escolha != 9) {

					System.out.println("Escolha as opções:");
					System.out.println("1 - Consultar Limite");
					System.out.println("2 - Consultar Compras");
					System.out.println("3 - Consultar/Gerar Endereço (FORMATO JSON)");
					System.out.println("4 - Realizar Compras");
					System.out.println("9 - Finalizar Programa");

					escolha = sc.nextInt();

					switch (escolha) {

					case 1:

						usuario.getCartao().consultarLimite();
						break;

					case 2:

						usuario.getCartao().consultarCompras();
						break;
						
					case 3: //TODO: criar uma classe que faça essa função de ver arquivo
						
						System.out.println("-".repeat(50));
						System.out.println("SEU ENDEREÇO A PARTIR DO FORMATO JSON:");
						System.out.println();
						String json = gson.toJson(usuario.getEndereco());
						//TODO: seria interessante também criar um JSON com o objeto Pessoa (listando todos os dados que um User pode ter)
						System.out.println(json);
						System.out.println();
											
						int escolhaGerarArquivo = 0;
						loop3: while(escolhaGerarArquivo != 2) {
							
							System.out.println("Deseja gerar um arquivo? (1-SIM | 2-NÃO)");
							escolhaGerarArquivo = sc.nextInt();
							sc.nextLine();
							
							switch(escolhaGerarArquivo) {
							
							case 1: //TODO: criar uma classe que faça essa função de gerar arquivo
								System.out.println("Onde desejar salvar o arquivo? Escreva o caminho:");
								String path = sc.nextLine();
								
								try(BufferedWriter texto = new BufferedWriter(new FileWriter(path + "\\arquivo.json"))){
									
									texto.write(json);
									System.out.println("Texto salvo com sucesso!");
									
								}catch(IOException e) {
									System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
								}
								
								break loop3;
								
							default:
								System.out.println("*****OPÇÃO INVÁLIDA*****");
							}
							
						}
										
						System.out.println("-".repeat(50));
						
						break;
					case 4:

						System.out.print("Digite o nome da compra: ");
						String nomeCompra = sc.next();
						System.out.print("Digite o valor da compra: ");
						double valorCompra = sc.nextDouble();

						usuario.getCartao().efetuarCompra(nomeCompra, valorCompra);

						break;

					case 9:
						escolha = 2;
						break loop2;

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
