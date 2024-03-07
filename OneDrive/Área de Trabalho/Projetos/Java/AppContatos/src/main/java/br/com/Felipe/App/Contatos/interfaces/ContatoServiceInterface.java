package br.com.Felipe.App.Contatos.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.Felipe.App.Contatos.model.Contato;

public interface ContatoServiceInterface {


	Contato save(Contato contato);
	
	Optional<Contato> getById(Long id);
	
	List<Contato> getAll();
	
	Contato update(Contato contato);
	
	void delete(Long id);
}
