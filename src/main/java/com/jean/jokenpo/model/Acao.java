package com.jean.jokenpo.model;

public enum Acao {
	
	Spock("Spock"),
	Tesoura("Tesoura"),
	Papel("Papel"),
	Pedra("Pedra"),
	Lagarto("Lagarto");
	
	private String descricao;
	
	Acao(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
