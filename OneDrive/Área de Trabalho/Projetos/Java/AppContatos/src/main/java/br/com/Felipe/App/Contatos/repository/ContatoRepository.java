package br.com.Felipe.App.Contatos.repository;

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
}
