package br.com.Felipe.App.Contatos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Felipe.App.Contatos.dto.PessoaDTO;
import br.com.Felipe.App.Contatos.exception.ResourceNotFoundException;
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
	public Pessoa save(Pessoa pessoa) throws ResourceNotFoundException {
				
		if(pessoa.getUf().toString().length() > 2) {
			throw new ResourceNotFoundException("[PESSOA] Não é permitido mais do que 2 caracteres para o campo uf");
		}
		
		if(pessoa.getNome().toString().length() == 0) {
			throw new ResourceNotFoundException("[PESSOA] O nome não pode estar vazio");
		}
		return pessoaRepository.save(pessoa);
	}

	@Override
	public Optional<Pessoa> getById(Long id) throws ResourceNotFoundException {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		
		if(pessoa == null) {
			throw new ResourceNotFoundException("[PESSOA] Pessoa não encontrada pelo ID: " + id);
		}
		if(pessoa.isEmpty()) {
			throw new ResourceNotFoundException("[PESSOA] Pessoa não encontrada pelo ID: " + id);
		}
		
		return pessoa;
	}

	@Override
	public List<Pessoa> getAll() throws ResourceNotFoundException {
		List<Pessoa> listPessoas = pessoaRepository.findAll();
		
		if(listPessoas == null) {
		throw new ResourceNotFoundException("[PESSOA] Nenhuma pessoa cadastrada");
		}
		
		if(listPessoas.size() == 0) {
			throw new ResourceNotFoundException("[PESSOA] Nenhuma pessoa cadastrada");			
		}
		return listPessoas;
	}

	@Override
	public Pessoa update(Pessoa pessoa) throws ResourceNotFoundException {
		Optional<Pessoa> findPessoa = pessoaRepository.findById(pessoa.getId());
		
		if(findPessoa == null) {
			throw new ResourceNotFoundException("[PESSOA] Pessoa não encontrada pelo ID: " + pessoa.getId());
		}
		
		if(findPessoa.isEmpty()) {
			throw new ResourceNotFoundException("[PESSOA] Pessoa não encontrada pelo ID: " + pessoa.getId());
		}
		
		if(pessoa.getUf().toString().length() > 2) {
			throw new ResourceNotFoundException("[PESSOA] Não é permitido mais do que 2 caracteres para o campo uf");
		}
		
		if(pessoa.getNome().toString().length() == 0) {
			throw new ResourceNotFoundException("[PESSOA] O nome não pode estar vazio");
		}
		
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
	public void delete(Long id) throws ResourceNotFoundException {
		Optional<Pessoa> deletar = pessoaRepository.findById(id);
		
		if(deletar == null) {
			throw new ResourceNotFoundException("[PESSOA] ID: " + id + " não encontrado, não é possível deletar");
		}
		if(deletar.isEmpty()) {
			throw new ResourceNotFoundException("[PESSOA] ID: " + id + " não encontrado, não é possível deletar");
		}
		
		pessoaRepository.deleteById(id);
		
	}
	
	@Override
	public List<PessoaDTO> findMalaDireta(Long id){
				
		List<Object[]> listResult = pessoaRepository.findMalaDireta(id);
		
		if(listResult == null) {
			throw new ResourceNotFoundException("[PESSOA] Dados de mala direta não encontrados pelo ID: " + id);
		}
		if(listResult.isEmpty()) {
			throw new ResourceNotFoundException("[PESSOA] Dados de mala direta não encontrados pelo ID: " + id);
		}
		
		List<PessoaDTO> listPessoaDTO = new ArrayList<>();
		
		listResult.forEach(result -> {
			listPessoaDTO.add(returnPessoaDTO(result));
		});
		
		if(listPessoaDTO.size() > 0) {
			return listPessoaDTO;
		}
		
		return null;
	}
	
	
	private PessoaDTO returnPessoaDTO(Object[] result) {
		PessoaDTO pessoaDTO = new PessoaDTO();
		if(result != null) {
			pessoaDTO.setId( ((Long)result[0]).longValue() );
			pessoaDTO.setNome( (String)result[1] );
			pessoaDTO.setMalaDireta((String)result[2], (String)result[3], (String)result[4], (String)result[5]);
		}
		
		return pessoaDTO;
	}

}
