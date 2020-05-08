package com.jean.jokenpo.core;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
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
public class JogadaCoreTest {

	@Autowired
	private JogadaCore core;
	
	@BeforeEach
	public void setUp() {
		core.limparJogadas();
	}
	
	@Test
	public void empateTest() throws CoreException {
		core.cadastrar(new Jogada("1", Acao.Largato));
		core.cadastrar(new Jogada("2", Acao.Papel));
		core.cadastrar(new Jogada("3", Acao.Pedra));
		core.cadastrar(new Jogada("4", Acao.Spock));
		core.cadastrar(new Jogada("5", Acao.Tesoura));
		
		String resultado = core.processar();
		assertEquals("Empate", resultado);
	}
	
	@Test
	public void tesouraTest() throws CoreException {
		core.cadastrar(new Jogada("1", Acao.Largato));
		core.cadastrar(new Jogada("2", Acao.Papel));
		core.cadastrar(new Jogada("5", Acao.Tesoura));
		
		String resultado = core.processar();
		assertEquals("Vencedor: 5", resultado);
	}
	
	@Test
	public void tesouraEmpateTest() throws CoreException {
		core.cadastrar(new Jogada("1", Acao.Largato));
		core.cadastrar(new Jogada("2", Acao.Papel));
		core.cadastrar(new Jogada("4", Acao.Tesoura));
		core.cadastrar(new Jogada("5", Acao.Tesoura));
		
		String resultado = core.processar();
		assertEquals("Empate", resultado);
	}
	
	@Test
	public void largatoTest() throws CoreException {
		core.cadastrar(new Jogada("1", Acao.Largato));
		core.cadastrar(new Jogada("2", Acao.Papel));
		core.cadastrar(new Jogada("4", Acao.Spock));
		
		String resultado = core.processar();
		assertEquals("Vencedor: 1", resultado);
	}
	
	@Test
	public void papelTest() throws CoreException {
		core.cadastrar(new Jogada("2", Acao.Papel));
		core.cadastrar(new Jogada("3", Acao.Pedra));
		core.cadastrar(new Jogada("4", Acao.Spock));
		
		String resultado = core.processar();
		assertEquals("Vencedor: 2", resultado);
	}
	
	@Test
	public void pedraTest() throws CoreException {
		core.cadastrar(new Jogada("1", Acao.Largato));
		core.cadastrar(new Jogada("3", Acao.Pedra));
		core.cadastrar(new Jogada("5", Acao.Tesoura));
		
		String resultado = core.processar();
		assertEquals("Vencedor: 3", resultado);
	}
	
	@Test
	public void spockTest() throws CoreException {
		core.cadastrar(new Jogada("3", Acao.Pedra));
		core.cadastrar(new Jogada("4", Acao.Spock));
		core.cadastrar(new Jogada("5", Acao.Tesoura));
		
		String resultado = core.processar();
		assertEquals("Vencedor: 4", resultado);
	}
}
