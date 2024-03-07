package br.com.Felipe.App.Contatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Felipe.App.Contatos.interfaces.PessoaServicoInterface;
import br.com.Felipe.App.Contatos.model.Pessoa;
import br.com.Felipe.App.Contatos.repository.PessoaRepository;

@Service
public class PessoaService implements PessoaServicoInterface {

	private PessoaRepository pessoaRepository;
	
	@Autowired
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	@Override
	public Pessoa save(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Override
	public Optional<Pessoa> getById(Long id) {
		return pessoaRepository.findById(id);
	}

	@Override
	public List<Pessoa> getAll() {
		return pessoaRepository.findAll();
	}

	@Override
	public Pessoa update(Pessoa pessoa) {
		Optional<Pessoa> findPessoa = pessoaRepository.findById(pessoa.getId());
		
		if(findPessoa.isPresent()) {
			Pessoa updatePessoa = findPessoa.get();
			updatePessoa.setNome(pessoa.getNome());
			updatePessoa.setEndereco(pessoa.getEndereco());
			updatePessoa.setCep(pessoa.getCep());
			updatePessoa.setCidade(pessoa.getCidade());
			updatePessoa.setUf(pessoa.getUf());
			return pessoaRepository.save(updatePessoa);
		}
		return pessoa;
	}

	@Override
	public void delete(Long id) {
		pessoaRepository.deleteById(id);
		
	}

}
