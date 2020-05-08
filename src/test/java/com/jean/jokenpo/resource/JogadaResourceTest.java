package com.jean.jokenpo.resource;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jean.jokenpo.JokenpoApplication;
import com.jean.jokenpo.core.JogadaCore;
import com.jean.jokenpo.model.Acao;
import com.jean.jokenpo.model.Jogada;
import com.jean.jokenpo.resources.JogadaResource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JokenpoApplication.class)
public class JogadaResourceTest {
	
	private static Jogada JOGADA = new Jogada("1", Acao.Largato);
	
	@Autowired
	private JogadaResource resource;
	
	@Mock
	private JogadaCore core;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(this.resource).build();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void listarTest() throws Exception {
		 MvcResult mvcResult = mockMvc
		.perform(get("/jogadas"))
		.andExpect(status().isOk()).andReturn();
		
		String responseAsJson = "[]"; 
		
		assertEquals(mvcResult.getResponse().getContentAsString(), responseAsJson);
	}
	
	@Test
	public void cadastrarTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String json = ow.writeValueAsString(JOGADA);
		
		 MvcResult mvcResult = mockMvc
		.perform(post("/jogadas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isCreated()).andReturn();
		
		String location = "http://localhost:5000/jogadas/" + JOGADA.getJogador();
		String locationResponse = mvcResult.getResponse().getHeader("location");
		
		assertEquals(location ,locationResponse);
	}
	
	@Test
	public void removerTest() throws Exception {
		cadastrarTest();
		
		 mockMvc
		.perform(delete("/jogadas/" + JOGADA.getJogador()))
		.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void consultarTest() throws Exception {
		cadastrarTest();
		
		MvcResult mvcResult = mockMvc
		.perform(get("/jogadas/" + JOGADA.getJogador()))
		.andExpect(status().isOk()).andReturn();
		 
		 String resultado = mvcResult.getResponse().getContentAsString();
		 assertEquals("{\"jogador\":\"1\",\"acao\":\"Largato\"}", resultado);
	}
	
	@Test
	public void jogarTest() throws Exception {
		cadastrarTest();
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String json = ow.writeValueAsString(new Jogada("2", JOGADA.getAcao()));
		
		 mockMvc
		.perform(post("/jogadas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isCreated()).andReturn();
		
		MvcResult mvcResult = mockMvc
		.perform(post("/jogadas/jogar"))
		.andExpect(status().isOk()).andReturn();
		 
		 String resultado = mvcResult.getResponse().getContentAsString();
		 assertEquals("Empate", resultado);
	}
}
