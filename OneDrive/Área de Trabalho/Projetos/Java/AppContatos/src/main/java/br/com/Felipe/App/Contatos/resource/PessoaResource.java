package br.com.Felipe.App.Contatos.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Felipe.App.Contatos.dto.PessoaDTO;
import br.com.Felipe.App.Contatos.model.Pessoa;
import br.com.Felipe.App.Contatos.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/pessoas")
public class PessoaResource {

	private PessoaService pessoaService;
	
	@Autowired
	public PessoaResource(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@Operation(summary = "Buscar todas as pessoas cadastradas")
	@GetMapping
	public ResponseEntity<List<Pessoa>> gelAllPessoas(){
		
		List<Pessoa> pessoas = pessoaService.getAll();
		
		if(pessoas == null) {
			return ResponseEntity.notFound().build();
		}
		if(pessoas.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoas);				
	}
	
	@Operation(summary = "Cadastrar uma nova pessoa")
	@PostMapping
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
		Pessoa newPessoa = pessoaService.save(pessoa);
		
		if(newPessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(newPessoa);
	}
	
	@Operation(summary = "Buscar uma pessoa cadastrada pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Long id){
		Optional<Pessoa> pessoa = pessoaService.getById(id);
		
		if(pessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		if(pessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoa);
	}
	
	@Operation(summary = "Atualizar os dados de uma pessoa cadastrada")
	@PutMapping
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa){
		Pessoa upPessoa = pessoaService.update(pessoa);
		
		if(upPessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(upPessoa);
	}
	
	@Operation(summary = "Remover um pessoa cadastrada através do ID")
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Pessoa> deletar = pessoaService.getById(id);
		
		if(deletar == null) {
			return ResponseEntity.notFound().build();
		}
		
		if(deletar.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		pessoaService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Operation(summary = "Buscar os da pessoa no formato Mala direta")
	@GetMapping("/findMalaDireta/{id}")
	public ResponseEntity<List<PessoaDTO>> findMalaDireta(@PathVariable Long id){
		List<PessoaDTO> listPessoaDTO = pessoaService.findMalaDireta(id);
				
		if(listPessoaDTO == null) {
			return ResponseEntity.notFound().build();
		}
		if(listPessoaDTO.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(listPessoaDTO);
	}
}
