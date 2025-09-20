package lucasnovaesvieira.creditcard.entities;

public class Pessoa {

	private String nome;
	private String cpf;
	protected Cartao cartao;

	public Pessoa(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
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
	
	

}
