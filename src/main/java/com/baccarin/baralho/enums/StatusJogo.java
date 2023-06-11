package com.baccarin.baralho.enums;

public enum StatusJogo {

	VITORIA("Vitória"), EMPATE("Empate");

	private String descricao;

	StatusJogo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
