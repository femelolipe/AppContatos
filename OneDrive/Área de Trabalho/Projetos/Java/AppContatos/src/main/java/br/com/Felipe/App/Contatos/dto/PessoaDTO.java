package br.com.Felipe.App.Contatos.dto;

public class PessoaDTO {

	private Long id;
	private String nome;
	private String malaDireta;
	
	public PessoaDTO() {}
	
	public PessoaDTO(Long id, String nome, String malaDireta) {
		this.id = id;
		this.nome = nome;
		this.malaDireta = malaDireta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	public String getMalaDireta() {
		return malaDireta;
	}

	public void setMalaDireta(String endereco, String cep, String cidade, String uf) {
		this.malaDireta = endereco + " - " + cep + " - " +  cidade + "/" + uf;
	}

	@Override
	public String toString() {
		String resposta = "ID: " + this.id + 
				"\nNome: " + this.nome + 
				"\nMala Direta: " + this.malaDireta;
		
		return resposta;
	}
}
