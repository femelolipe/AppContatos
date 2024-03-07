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

import br.com.Felipe.App.Contatos.model.Pessoa;
import br.com.Felipe.App.Contatos.service.PessoaService;

@RestController
@RequestMapping("api/pessoas")
public class PessoaResource {

	private PessoaService pessoaService;
	
	@Autowired
	public PessoaResource(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
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
	
	@PostMapping
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
		Pessoa newPessoa = pessoaService.save(pessoa);
		if(newPessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(newPessoa);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Long id){
		Optional<Pessoa> pessoa = pessoaService.getById(id);
		
		if(pessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoa);
	}
	
	@PutMapping
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa){
		Pessoa upPessoa = pessoaService.update(pessoa);
		
		if(upPessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(upPessoa);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		pessoaService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
