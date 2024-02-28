package br.com.fmo.AppProdurtos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.fmo.AppProdurtos.model.Estoque;
import br.com.fmo.AppProdurtos.model.Produto;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

	Optional<Estoque> findByProduto(Optional<Produto> findProduto);

	@Query("SELECT e FROM Estoque e WHERE e.quantidade <= :quantidade")
	List<Estoque> findEstoqueQuantidadeLessThan(Integer quantidade);
	
	@Query(value = "SELECT * FROM estoque where quantidade > ?1", nativeQuery = true)
	List<Estoque> findEstoqueQuantidadeGreaterThan(Integer quantidade);

}
