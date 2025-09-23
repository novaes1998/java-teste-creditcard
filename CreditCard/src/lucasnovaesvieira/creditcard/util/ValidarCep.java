package lucasnovaesvieira.creditcard.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import lucasnovaesvieira.creditcard.entities.Endereco;

public class ValidarCep {
	
	
	

	private ValidarCep() {}
	

	public static Endereco verificarCep(String cep) {
		
		String cepTratado = cep.replace(" ", "?");
		
		try
		{
			String endereco = "https://viacep.com.br/ws/" + cepTratado + "/json/";
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(URI.create(endereco)).build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
				
				return gerarEndereco(response.body());

		
		}catch(Exception e)
		{
			throw new RuntimeException("Ocorreu um erro ao buscar o CEP!\n" + "Erro: " + e.getMessage());
		}

	}
	
	
	private static Endereco gerarEndereco(String json) {
		
		Gson gson = new Gson();		
		Endereco endereco = gson.fromJson(json, Endereco.class);
		return endereco;
		
	}
	
}
