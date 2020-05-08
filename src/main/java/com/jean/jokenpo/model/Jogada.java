package com.jean.jokenpo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Jogada {
	@NotBlank(message = "Informe o nome do jogador")
	private String jogador;
	@NotNull(message = "Informe a acao")
	private Acao acao;
	
	
	public Jogada() {
		
	}
	
	public Jogada(String jogador, Acao acao) {
		this.jogador = jogador;
		this.acao = acao;
	}

	public String getJogador() {
		return jogador;
	}

	public void setJogador(String jogador) {
		this.jogador = jogador;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	
	@Override
	public String toString() {
		return jogador + " - " + acao.getDescricao(); 
	}
}
