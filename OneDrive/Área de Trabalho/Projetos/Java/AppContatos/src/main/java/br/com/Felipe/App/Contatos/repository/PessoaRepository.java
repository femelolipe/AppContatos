package br.com.Felipe.App.Contatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.Felipe.App.Contatos.model.Pessoa;
import jakarta.transaction.Transactional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Transactional
	@Modifying
	@Query(value = "SELECT id, nome, logradouro, cep, cidade, uf FROM TBPessoa p WHERE p.id = :id", nativeQuery = true)
	List<Object[]> findMalaDireta(Long id);
}
