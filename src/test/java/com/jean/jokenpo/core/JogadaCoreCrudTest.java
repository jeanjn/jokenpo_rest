package com.jean.jokenpo.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jean.jokenpo.JokenpoApplication;
import com.jean.jokenpo.exceptions.CoreException;
import com.jean.jokenpo.model.Acao;
import com.jean.jokenpo.model.Jogada;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JokenpoApplication.class)
public class JogadaCoreCrudTest {
	
	@Autowired
	private JogadaCore core;
	
	@Test
	public void cadastrarTest() {
		core.cadastrar(new Jogada("2", Acao.Papel));
		Optional<Jogada> jogada = core.consultar("2");
		assertNotNull(jogada.get());
	}
	
	@Test
	public void consultarTest() {
		core.cadastrar(new Jogada("2", Acao.Papel));
		Optional<Jogada> jogada = core.consultar("2");
		assertNotNull(jogada.get());
	}
	
	@Test
	public void removerTest() throws CoreException {
		core.cadastrar(new Jogada("2", Acao.Papel));
		core.remover("2");
		Optional<Jogada> jogada = core.consultar("2");
		assertTrue(!jogada.isPresent());
	}
	
	@Test
	public void removerCoreExceptionTest() throws CoreException {
		assertThrows(CoreException.class, () -> core.remover("")); 
		
	}
	
	@Test
	public void listarTest() {
		core.cadastrar(new Jogada("2", Acao.Papel));
		assertEquals(core.listar().size(), 1);
	}

}
