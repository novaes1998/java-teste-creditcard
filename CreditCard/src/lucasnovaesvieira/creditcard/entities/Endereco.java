package lucasnovaesvieira.creditcard.entities;

public class Endereco {
	
	protected String cep;
	protected String logradouro;
	protected String complemento;
	protected String unidade;
	protected String bairro;
	protected String localidade;
	protected String estado;
	
	
	public Endereco(String cep, String logradouro, String bairro, String localidade, String estado) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.estado = estado;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}


	public void complementarEndereco(String numero, String complemento) {
		
		this.complemento = numero;
		
		if(complemento.equals("n")) {
			this.unidade = "NÃO EXISTE";
		}else {
			this.unidade = complemento;
		}
		
	}
	
	
	public String toStringParcial() {
		return "CEP: " + cep
				+ "\nLOGRADOURO: " + logradouro
				+ "\nBAIRRO: " + bairro
				+ "\nCIDADE: " + localidade
				+ "\nESTADO: " + estado;
	}
	
	@Override
	public String toString() {
		return "CEP: " + cep
				+ "\nLOGRADOURO: " + logradouro
				+ "\nNÚMERO: " + complemento
				+ "\nCOMPLEMENTO: " + unidade
				+ "\nBAIRRO: " + bairro
				+ "\nCIDADE: " + localidade
				+ "\nESTADO: " + estado;
	}
	
	
	


}
