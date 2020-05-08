package com.jean.jokenpo.resources;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.jean.jokenpo.core.JogadaCore;
import com.jean.jokenpo.exceptions.CoreException;
import com.jean.jokenpo.model.Jogada;


@RestController
@RequestMapping("/jogadas")
public class JogadaResource {
	
	@Autowired
	private JogadaCore core;
	
	@PostMapping("/jogar")
	public String post() throws CoreException {
		return core.processar();
	}
	
	@PostMapping
	public ResponseEntity<Jogada> post(@Valid @RequestBody Jogada jogada) {
		jogada = core.cadastrar(jogada);
		return ResponseEntity.created(getUri("/" + jogada.getJogador()).toUri()).build();
	}
	
	@GetMapping("/{jogador}")
	public ResponseEntity<Jogada> get(@PathVariable("jogador") String jogador) throws CoreException{
		Jogada jogada = core.consultar(jogador)
				.orElseThrow(() -> new CoreException(HttpStatus.NOT_FOUND));
		return ResponseEntity.ok(jogada);
	}
	
	@GetMapping
	public ResponseEntity<Collection<Jogada>> get(){
		return ResponseEntity.ok(core.listar());
	}
	
	@DeleteMapping("/{jogador}")
	public ResponseEntity<Jogada> delete(@PathVariable("jogador") String jogador) throws CoreException{
		core.remover(jogador);
		return ResponseEntity.ok().build();
	}
	

	public UriComponents getUri(String path) {
		UriComponentsBuilder uri = UriComponentsBuilder.newInstance()
	      .scheme("http").host("localhost").port(5000).path("/jogadas");
		
		if(path != null) {
			uri = uri.path(path);
		}
		
	    return uri.build();
	}

}
