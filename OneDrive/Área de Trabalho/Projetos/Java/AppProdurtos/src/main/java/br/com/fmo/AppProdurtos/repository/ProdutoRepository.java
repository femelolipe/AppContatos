package br.com.fmo.AppProdurtos.repository;

import org.springframework.stereotype.Repository;
import br.com.fmo.AppProdurtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	
}
