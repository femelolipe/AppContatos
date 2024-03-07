package br.com.Felipe.App.Contatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Felipe.App.Contatos.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
