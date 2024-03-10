package br.com.Felipe.App.Contatos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Felipe.App.Contatos.dto.ContatoDTO;
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
	public List<ContatoDTO> getAllContatosById(Long id) throws ResourceNotFoundException {
		
		List<Object> listResult = contatoRepository.getAllContatosById(id);
		
		if(listResult == null) {
			throw new ResourceNotFoundException("[CONTATO] Dados de pessoa não encontrados pelo ID: " + id);
		}
		if(listResult.isEmpty()) {
			throw new ResourceNotFoundException("[CONTATO] Dados de pessoa não encontrados pelo ID: " + id);
		}
		
		List<ContatoDTO> listContatoDTO = new ArrayList<>();
		
		listResult.forEach(result -> {
			listContatoDTO.add(returnContatoDTO((Object[]) result));
		});
		
		if(listContatoDTO.size() > 0) {
			return listContatoDTO;
		}
			
		return null;
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
	
	private ContatoDTO returnContatoDTO(Object[] result) {		
		ContatoDTO contatoDTO = new ContatoDTO();
		
		if(result != null) {
			contatoDTO.setIdPessoa( ((Long)result[0]).longValue() );			
			contatoDTO.setIdContato( ((Long)result[1]).longValue() );
			contatoDTO.setNome( (String)result[2] );
			contatoDTO.setContato( (String)result[3] );
			contatoDTO.setTipoContato( (String)result[4] );
		}
		
		return contatoDTO;
	}

}
