package br.com.Felipe.App.Contatos.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.Felipe.App.Contatos.dto.ContatoDTO;
import br.com.Felipe.App.Contatos.model.Contato;

public interface ContatoServiceInterface {


	Contato save(Contato contato);
	
	Optional<Contato> getById(Long id);
	
	List<ContatoDTO> getAllContatosById(Long id);
	
	Contato update(Contato contato);
	
	void delete(Long id);
}
