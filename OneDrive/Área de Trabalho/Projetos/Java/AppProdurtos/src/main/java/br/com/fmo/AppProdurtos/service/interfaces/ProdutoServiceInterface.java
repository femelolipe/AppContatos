package br.com.fmo.AppProdurtos.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.fmo.AppProdurtos.model.Produto;

public interface ProdutoServiceInterface {

		//Salvar o produto
		Produto save(Produto produto);
		//Recuperar 1 produto
		Optional<Produto> getById(Long id);
		//Recuperar todos os produtos
		List<Produto> getAll();
		//Atualizar o produto
		Produto update(Produto produto);
		//Deletar o produto
		void delete(Long id);
	
}
