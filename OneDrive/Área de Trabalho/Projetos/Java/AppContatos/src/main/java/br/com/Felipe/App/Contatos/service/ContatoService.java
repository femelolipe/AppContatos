package br.com.Felipe.App.Contatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Felipe.App.Contatos.exception.ResourceNotFoundException;
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
	public Contato save(Contato contato) throws ResourceNotFoundException  {
		
		if(contato.getPessoa().getId() != null) {
			Optional<Pessoa> findPessoa = pessoaRepository.findById(contato.getPessoa().getId());
			if(findPessoa.isEmpty()) {
				throw new ResourceNotFoundException("[CONTATO] Pessoa não encontrada");
			}else {
				if(contato.getTipoContato().toString().length() == 0) {
					throw new ResourceNotFoundException("[CONTATO] O tipo do Contato, não pode ser vazio");
				}
				
				if(contato.getContato().toString().length() == 0) {
					throw new ResourceNotFoundException("[CONTATO] O Contato, não pode ser vazio");
				}
				contato.setPessoa(findPessoa.get());
				return contatoRepository.save(contato);
			}
		}else {
			throw new ResourceNotFoundException("[CONTATO] Pessoa não encontrada");
		}		
	}

	@Override
	public Optional<Contato> getById(Long id) throws ResourceNotFoundException {
		Optional<Contato> contato = contatoRepository.findById(id);
		
		if(contato == null) {
			throw new ResourceNotFoundException("[CONTATO] Contato não encontrado pelo ID: " + id);
		}
		if(contato.isEmpty()) {
			throw new ResourceNotFoundException("[CONTATO] Contato não encontrado pelo ID: " + id);
		}
		
		return contato;
	}

	@Override
	public List<Contato> getAll() throws ResourceNotFoundException {
		List<Contato> listContato = contatoRepository.findAll();
		
		if(listContato == null) {
			throw new ResourceNotFoundException("[CONTATO] Nenhuma contato cadastrado");
			}
			
		if(listContato.size() == 0) {
			throw new ResourceNotFoundException("[CONTATO] Nenhuma contato cadastrado");
			}
			
		return listContato;
	}

	@Override
	public Contato update(Contato contato) throws ResourceNotFoundException {
		Optional<Contato> findContato = contatoRepository.findById(contato.getId());
		Optional<Pessoa> findPessoa = pessoaRepository.findById(contato.getPessoa().getId());
		
		if(findContato == null) {
			throw new ResourceNotFoundException("[CONTATO] Contato não encontrado pelo ID: " + contato.getId());
		}
		
		if(findContato.isEmpty()) {
			throw new ResourceNotFoundException("[CONTATO] Contato não encontrado pelo ID: " + contato.getId());
		}
		
		if(findPessoa.isEmpty()) {
			throw new ResourceNotFoundException("[CONTATO] Pessoa não encontrada pelo ID: " + contato.getPessoa().getId());
		}
		
		if(contato.getTipoContato().toString().length() == 0) {
			throw new ResourceNotFoundException("[CONTATO] O tipo do Contato, não pode ser vazio");
		}
		
		if(contato.getContato().toString().length() == 0) {
			throw new ResourceNotFoundException("[CONTATO] O Contato, não pode ser vazio");
		}
		
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
	public void delete(Long id) throws ResourceNotFoundException {
		Optional<Contato> deletar = contatoRepository.findById(id);
		
		if(deletar == null) {
			throw new ResourceNotFoundException("[CONTATO] ID: " + id + " não encontrado, não é possível deletar");
		}
		if(deletar.isEmpty()) {
			throw new ResourceNotFoundException("[CONTATO] ID: " + id + " não encontrado, não é possível deletar");
		}
		
		contatoRepository.deletePorId(id);
	}

}
