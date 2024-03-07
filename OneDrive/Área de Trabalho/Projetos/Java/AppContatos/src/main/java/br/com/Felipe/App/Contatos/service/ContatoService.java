package br.com.Felipe.App.Contatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Felipe.App.Contatos.interfaces.ContatoServiceInterface;
import br.com.Felipe.App.Contatos.model.Contato;
import br.com.Felipe.App.Contatos.model.Pessoa;
import br.com.Felipe.App.Contatos.repository.ContatoRepository;
import br.com.Felipe.App.Contatos.repository.PessoaRepository;

@Service
public class ContatoService implements ContatoServiceInterface {

	ContatoRepository contatoRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	public ContatoService(ContatoRepository contatoRepository) {
		this.contatoRepository = contatoRepository;
	}
	
	@Override
	public Contato save(Contato contato) {
		
		if(contato.getPessoa().getId() != null) {
			Optional<Pessoa> findPessoa = pessoaRepository.findById(contato.getPessoa().getId());
			if(findPessoa.isEmpty()) {
				System.out.println("Pessoa não encontrado.");
				return null;
			}else {
				contato.setPessoa(findPessoa.get());
				return contatoRepository.save(contato);
			}
		}else {
			System.out.println("Não existe Pessoa");
			return null;
		}		
	}

	@Override
	public Optional<Contato> getById(Long id) {
		return contatoRepository.findById(id);
	}

	@Override
	public List<Contato> getAll() {
		return contatoRepository.findAll();
	}

	@Override
	public Contato update(Contato contato) {
		Optional<Contato> findContato = contatoRepository.findById(contato.getId());
		if(findContato.isPresent()) {
			Contato upContato = findContato.get();
			upContato.setPessoa(findContato.get().getPessoa());
			upContato.setTipoContato(contato.getTipoContato());
			upContato.setContato(contato.getContato());
			return contatoRepository.save(upContato);
		}
		return contato;
	}

	@Override
	public void delete(Long id) {
		contatoRepository.deleteById(id);
	}

}
