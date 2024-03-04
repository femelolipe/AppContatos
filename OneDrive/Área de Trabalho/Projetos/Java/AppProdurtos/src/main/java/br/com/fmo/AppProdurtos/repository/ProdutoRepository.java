package br.com.fmo.AppProdurtos.repository;

import org.springframework.stereotype.Repository;

import br.com.fmo.AppProdurtos.dto.ProdutoDTO;
import br.com.fmo.AppProdurtos.model.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query(value = "SELECT p.id, p.codigo_barras, p.nome, p.preco, e.quantidade FROM produto p INNER JOIN estoque e on p.id = e.produto_id", nativeQuery = true)
	List<Object[]> findProdutosAndQuantidade();
	
	@Query(value = "SELECT * FROM buscar_produtos_estoque()", nativeQuery = true)
	List<Object[]> findProdutoSimplesAndQuantidade();
	
}
