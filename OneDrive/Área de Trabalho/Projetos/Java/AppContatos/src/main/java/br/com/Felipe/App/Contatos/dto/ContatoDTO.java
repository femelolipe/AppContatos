package br.com.Felipe.App.Contatos.dto;

public class ContatoDTO {

	private Long idPessoa;
	private String contato;
	private String tipoContato;
	private Long idContato;
	private String nome;
	
	public ContatoDTO() {}
	
	
	public ContatoDTO(Long idPessoa, String contato, String tipoContato, Long idContato, String nome) {
		this.idPessoa = idPessoa;
		this.contato = contato;
		this.tipoContato = tipoContato;
		this.idContato = idContato;
		this.nome = nome;
	}
	
	
	public Long getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public String getTipoContato() {
		return tipoContato;
	}
	public void setTipoContato(String tipoContato) {
		this.tipoContato = tipoContato;
	}
	public Long getIdContato() {
		return idContato;
	}
	public void setIdContato(Long idContato) {
		this.idContato = idContato;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


	
}
