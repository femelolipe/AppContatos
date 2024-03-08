package br.com.Felipe.App.Contatos.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Felipe.App.Contatos.model.Contato;
import br.com.Felipe.App.Contatos.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/contatos")
public class ContatoResource {

	private ContatoService contatoService;
	
	@Autowired
	public ContatoResource(ContatoService contatoService) {
		this.contatoService = contatoService;
	}
	
	@Operation(summary = "Cadastrar um novo contato para uma pessoa")
	@PostMapping("/save")
	public ResponseEntity<Contato> save(@RequestBody Contato contato){
		Contato newContato = contatoService.save(contato);
		
		if(newContato == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(newContato);
	}
	
	@Operation(summary = "Buscar os dados de contato de uma pessoa por ID")
	@RequestMapping("/{id}")
	public ResponseEntity<Optional<Contato>> getById(@PathVariable Long id){
		Optional<Contato> findContato = contatoService.getById(id);
		
		if(findContato == null) {
			return ResponseEntity.notFound().build();
		}
		
		if(findContato.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(findContato);
	}
	
	@Operation(summary = "Buscar todos os dados de contato cadastrados")
	@RequestMapping("/all")
	public ResponseEntity<List<Contato>> getAll(){
		List<Contato> findContato = contatoService.getAll();
		
		if(findContato == null) {
			return ResponseEntity.notFound().build();
		}
		if(findContato.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(findContato);
	}
	
	@Operation(summary = "Atualizar os dados de contato de uma pessoa")
	@PutMapping("/update")
	public ResponseEntity<Contato> update(@RequestBody Contato contato){
		Contato upContato = contatoService.update(contato);
		
		if(upContato == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(upContato);		
	}
	
	@Operation(summary = "Remover os dados de contato de uma pessoa por ID")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Contato> delete(@PathVariable Long id){
		Optional<Contato> deletar = contatoService.getById(id);
		
		if(deletar == null) {
			return ResponseEntity.notFound().build();
		}
		
		if(deletar.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		contatoService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
