package lucasnovaesvieira.creditcard.entities;

public class Pessoa {

	private String nome;
	private String cpf;
	protected Cartao cartao;
	protected Endereco endereco;

	public Pessoa(String nome, String cpf, Endereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.cartao = new Cartao(this);
	}

	public String getNome() {
		return nome;
	}


	public String getCpf() {
		return cpf;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	

}
