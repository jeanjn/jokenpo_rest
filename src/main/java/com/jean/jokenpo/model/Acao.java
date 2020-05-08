package com.jean.jokenpo.model;

public enum Acao {
	
	Spock("Spock"),
	Tesoura("Tesoura"),
	Papel("Papel"),
	Pedra("Pedra"),
	Largato("Largato");
	
	private String descricao;
	
	Acao(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
