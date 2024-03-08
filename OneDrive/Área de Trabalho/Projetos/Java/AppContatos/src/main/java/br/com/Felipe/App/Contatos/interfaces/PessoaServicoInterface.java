package br.com.Felipe.App.Contatos.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.Felipe.App.Contatos.dto.PessoaDTO;
import br.com.Felipe.App.Contatos.model.Pessoa;


public interface PessoaServicoInterface {

	Pessoa save(Pessoa pessoa);
	
	Optional<Pessoa> getById(Long id);
	
	List<Pessoa> getAll();
	
	Pessoa update(Pessoa pessoa);
	
	void delete(Long id);
	
	List<PessoaDTO> findMalaDireta(Long id);
}
