package br.com.Felipe.App.Contatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Felipe.App.Contatos.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
