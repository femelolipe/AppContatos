package br.com.Felipe.App.Contatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.Felipe.App.Contatos.model.Contato;
import jakarta.transaction.Transactional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM Contato c WHERE c.id = :id")
	void deletePorId(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "SELECT p.id, c.id, nome, contato, tipo_contato FROM contatos c INNER JOIN TBPessoa p on c.id_pessoa = p.id WHERE c.id_pessoa = :id", nativeQuery = true)
	List<Object> getAllContatosById(Long id);
}
