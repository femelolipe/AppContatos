package br.com.fmo.AppProdurtos.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.fmo.AppProdurtos.model.Estoque;

public interface EstoqueServiceInterface {

	
	Estoque save(Estoque estoque);
	Optional<Estoque> getById(Long id);
	List<Estoque> getAll();
	Estoque update(Estoque estoque);
	void delete(Long id);
}
